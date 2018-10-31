package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.SpaceshipCollisionVisitor;
import edu.austral.starship.controller.ElementController;
import edu.austral.starship.controller.PlayerController;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;
import edu.austral.starship.model.weapon.BoostedWeapon;
import edu.austral.starship.model.weapon.CoreWeapon;
import edu.austral.starship.model.weapon.RapidFireWeapon;
import edu.austral.starship.render.*;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CustomGameFramework implements GameFramework, Subject {
    private List<Observer> observers;
    private List<Integer> keyCodes;
    private Player player1;
    private PImage ss1;
    private Player player2;
    private PImage ss2;

    private PImage smallbullet;
    private PImage bigbullet;
    private PImage asteroid;
    private PImage bg;

    private Renderer playingRenderer;
    private Renderer pauseRenderer;
    private Renderer gameOverRenderer;
    private ElementController elementController;

    // origen arriba a la izquierda
    public static final int BOTTOM_LIMIT = 800;
    public static final int RIGHT_LIMIT = 1250;
    public static final int LEFT_LIMIT = 0;
    public static final int TOP_LIMIT = 0;

    private GameState state;

    public CustomGameFramework() {
        this.observers = new ArrayList<>();
        this.keyCodes = new ArrayList<>();
        this.state = GameState.PLAYING;
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.enableHighPixelDensity();
        windowsSettings.fullScreen();
        loadImages(imageLoader);

        Visitor ssCollisionVisitor = new SpaceshipCollisionVisitor();

        Rectangle rect1 = new Rectangle(10, 400, 80, 65);
        Rectangle rect2 = new Rectangle(1000, 400, 80, 65);

        Spaceship spaceship1 = new Spaceship(
                rect1,
                Vector2.vector(rect1.x + (rect1.width / 2), rect1.y + (rect1.height / 2)),
                ssCollisionVisitor,
                1,
                Vector2.vector(1, 0));
        Spaceship spaceship2 = new Spaceship(
                rect2,
                Vector2.vector(rect2.x + (rect2.width / 2), rect2.y + (rect2.height / 2)),
                ssCollisionVisitor,
                2,
                Vector2.vector(-1, 0));

        this.player2 = new Player(spaceship2);
        this.player1 = new Player(spaceship1);

        spaceship1.addWeapon(new BoostedWeapon(spaceship1, player1));
        spaceship1.addWeapon(new CoreWeapon(spaceship1, player1));
        spaceship2.addWeapon(new CoreWeapon(spaceship2, player2));
        spaceship2.addWeapon(new RapidFireWeapon(spaceship2, player2));

        this.elementController = new ElementController();
        elementController.addGameObject(spaceship1);
        elementController.addGameObject(spaceship2);

        ShootUtil shootUtilPlayer1 = new ShootUtil(elementController, player1);
        ShootUtil shootUtilPlayer2 = new ShootUtil(elementController, player2);

        PlayerController playerController = new PlayerController(this, commandsPlayer1(shootUtilPlayer1));
        PlayerController playerController2 = new PlayerController(this, commandsPlayer2(shootUtilPlayer2));

        observers.add(playerController);
        observers.add(playerController2);

        ElementRendererVisitor elementRendererVisitor = new ElementRendererVisitor(ss1, ss2, smallbullet, bigbullet, asteroid);
        this.playingRenderer = new PlayingRenderer(elementRendererVisitor, elementController, player1, player2);
        this.pauseRenderer = new PauseRenderer();
        this.gameOverRenderer = new GameOverRenderer(player1, player2);
    }


    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        this.keyCodes.clear();
        this.keyCodes.addAll(keySet);
        notifyObservers();
        switch (this.state) {
            case PLAYING:
                control();
                loadBG(graphics);
                playingRenderer.render(graphics);
                break;
            case PAUSE:
                pauseRenderer.render(graphics);
                break;
            case GAME_OVER:
                gameOverRenderer.render(graphics);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void dettach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public List<Integer> getKeyCodes() {
        return keyCodes;
    }

    private void control() {
        if (player1.getSpaceship().getHealth() <= 0 || player2.getSpaceship().getHealth() <= 0) {
            this.state = GameState.GAME_OVER;
        }
        elementController.control();
    }

    private void loadBG(PGraphics graphics) {
        //graphics.beginDraw();
        graphics.background(255, 255, 255);
        //graphics.image(this.bg, 0, 0, 2048, 1152);
        //graphics.endDraw();
    }

    private Map<Integer, Runnable> commandsPlayer1(ShootUtil shootUtil) {
        Map<Integer, Runnable> commands1 = new HashMap<>();
        commands1.put(java.awt.event.KeyEvent.VK_RIGHT, player1.getSpaceship()::moveForward);
        commands1.put(java.awt.event.KeyEvent.VK_LEFT, player1.getSpaceship()::moveBackward);
        commands1.put(java.awt.event.KeyEvent.VK_DOWN, player1.getSpaceship()::moveDownwards);
        commands1.put(java.awt.event.KeyEvent.VK_UP, player1.getSpaceship()::moveUpwards);
        commands1.put(java.awt.event.KeyEvent.VK_ENTER, player1.getSpaceship()::changeWeapon);
        commands1.put(java.awt.event.KeyEvent.VK_SPACE, shootUtil);
        commands1.put(java.awt.event.KeyEvent.VK_P, this::pause);
        return commands1;
    }

    private Map<Integer, Runnable> commandsPlayer2(ShootUtil shootUtil) {
        Map<Integer, Runnable> commands2 = new HashMap<>();
        commands2.put(java.awt.event.KeyEvent.VK_1, player2.getSpaceship()::moveForward);
        commands2.put(java.awt.event.KeyEvent.VK_2, player2.getSpaceship()::moveBackward);
        commands2.put(java.awt.event.KeyEvent.VK_3, player2.getSpaceship()::moveDownwards);
        commands2.put(java.awt.event.KeyEvent.VK_4, player2.getSpaceship()::moveUpwards);
        commands2.put(java.awt.event.KeyEvent.VK_TAB, player2.getSpaceship()::changeWeapon);
        commands2.put(java.awt.event.KeyEvent.VK_5, shootUtil);
        commands2.put(java.awt.event.KeyEvent.VK_Q, this::pause);
        return commands2;
    }

    private void loadImages(ImageLoader imageLoader) {
        this.ss1 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss1.png");
        this.ss2 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss2.png");
        this.smallbullet = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/smallbullet.png");
        this.bigbullet = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/bigbullet.png");
        this.asteroid = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/asteroid.png");
        this.bg = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/bg.png");
    }

    private void pause() {
        if (this.state == GameState.PLAYING) {
            this.state = GameState.PAUSE;
        } else if (this.state == GameState.PAUSE) {
            this.state = GameState.PLAYING;
        }
    }

    private static class ShootUtil implements Runnable {
        final ElementController elementController;
        final Player player;

        private ShootUtil(ElementController elementController, Player player) {
            this.elementController = elementController;
            this.player = player;
        }

        @Override
        public void run() {
            Bullet shoot = player.getSpaceship().shoot();
            if (shoot != null) elementController.addGameObject(shoot);
        }
    }
}
