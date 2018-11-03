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
    private List<Player> players;
    private PImage ss1;
    private PImage ss2;
    private PImage ss3;

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

    private static long LAST_PAUSE = 0;

    private GameState state;

    public CustomGameFramework() {
        this.observers = new ArrayList<>();
        this.keyCodes = new ArrayList<>();
        this.players = new ArrayList<>();
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
                ssCollisionVisitor,
                1,
                Vector2.vector(1, 0));
        Spaceship spaceship2 = new Spaceship(
                rect2,
                ssCollisionVisitor,
                2,
                Vector2.vector(-1, 0));

        Player player1 = new Player(spaceship1);
        Player player2 = new Player(spaceship2);
        this.players.add(player1);
        this.players.add(player2);

        spaceship1.addWeapon(new BoostedWeapon(player1));
        spaceship1.addWeapon(new CoreWeapon(player1));
        spaceship2.addWeapon(new CoreWeapon(player2));
        spaceship2.addWeapon(new RapidFireWeapon(player2));

        this.elementController = new ElementController();
        elementController.addGameObject(spaceship1);
        elementController.addGameObject(spaceship2);

        ShootUtil shootUtilPlayer1 = new ShootUtil(elementController, player1);
        ShootUtil shootUtilPlayer2 = new ShootUtil(elementController, player2);

        PlayerController playerController = new PlayerController(this, commandsPlayer1(shootUtilPlayer1, player1));
        PlayerController playerController2 = new PlayerController(this, commandsPlayer2(shootUtilPlayer2, player2));

        observers.add(playerController);
        observers.add(playerController2);

        ElementRendererVisitor elementRendererVisitor = new ElementRendererVisitor(ss1, ss2, ss3, smallbullet, bigbullet, asteroid);
        this.playingRenderer = new PlayingRenderer(elementRendererVisitor, elementController, this.players);
        this.pauseRenderer = new PauseRenderer();
        this.gameOverRenderer = new GameOverRenderer(this.players);
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
        int countAlive = 0;
        for (Player player : players) {
            if (player.getSpaceship().getHealth() > 0) {
                countAlive++;
            }
        }
        if (countAlive <= 1) this.state = GameState.GAME_OVER;
        elementController.control();
    }

    private void loadBG(PGraphics graphics) {
        //graphics.beginDraw();
        graphics.background(255, 255, 255);
        //graphics.image(this.bg, 0, 0, 2048, 1152);
        //graphics.endDraw();
    }

    private Map<Integer, Runnable> commandsPlayer1(ShootUtil shootUtil, Player player) {
        Map<Integer, Runnable> commands1 = new HashMap<>();
        commands1.put(java.awt.event.KeyEvent.VK_RIGHT, player.getSpaceship()::moveForward);
        commands1.put(java.awt.event.KeyEvent.VK_LEFT, player.getSpaceship()::moveBackward);
        commands1.put(java.awt.event.KeyEvent.VK_DOWN, player.getSpaceship()::moveDownwards);
        commands1.put(java.awt.event.KeyEvent.VK_UP, player.getSpaceship()::moveUpwards);
        commands1.put(java.awt.event.KeyEvent.VK_ENTER, player.getSpaceship()::changeWeapon);
        commands1.put(java.awt.event.KeyEvent.VK_SPACE, shootUtil);
        commands1.put(java.awt.event.KeyEvent.VK_P, this::pause);
        return commands1;
    }

    private Map<Integer, Runnable> commandsPlayer2(ShootUtil shootUtil, Player player) {
        Map<Integer, Runnable> commands2 = new HashMap<>();
        commands2.put(java.awt.event.KeyEvent.VK_D, player.getSpaceship()::moveForward);
        commands2.put(java.awt.event.KeyEvent.VK_A, player.getSpaceship()::moveBackward);
        commands2.put(java.awt.event.KeyEvent.VK_S, player.getSpaceship()::moveDownwards);
        commands2.put(java.awt.event.KeyEvent.VK_W, player.getSpaceship()::moveUpwards);
        commands2.put(java.awt.event.KeyEvent.VK_TAB, player.getSpaceship()::changeWeapon);
        commands2.put(java.awt.event.KeyEvent.VK_X, shootUtil);
        commands2.put(java.awt.event.KeyEvent.VK_Q, this::pause);
        return commands2;
    }

    private void loadImages(ImageLoader imageLoader) {
        this.ss1 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss1.png");
        this.ss2 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss2.png");
        this.ss3 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss3.png");
        this.smallbullet = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/smallbullet.png");
        this.bigbullet = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/bigbullet.png");
        this.asteroid = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/asteroid.png");
        this.bg = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/bg.png");
    }

    private void pause() {
        long current = System.currentTimeMillis();
        if (current - LAST_PAUSE > 800) {
            switch (this.state) {
                case PLAYING:
                    this.state = GameState.PAUSE;
                    break;
                case PAUSE:
                    this.state = GameState.PLAYING;
                    break;
                default:
                    break;
            }
            LAST_PAUSE = current;
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
