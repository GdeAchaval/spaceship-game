package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.SpaceshipCollisionVisitor;
import edu.austral.starship.controller.ElementController;
import edu.austral.starship.controller.PlayerController;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.spaceship.Spaceship;
import edu.austral.starship.model.weapon.BoostedWeapon;
import edu.austral.starship.render.ElementRendererVisitor;
import edu.austral.starship.render.PauseRenderer;
import edu.austral.starship.render.PlayingRenderer;
import edu.austral.starship.render.Renderer;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CustomGameFramework implements GameFramework, Subject {
    private List<Observer> observers;
    private int keyCode;
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
    private ElementController elementController;

    // origen arriba a la izquierda
    public static final int BOTTOM_LIMIT = 800;
    public static final int RIGHT_LIMIT = 1200;
    public static final int LEFT_LIMIT = 0;
    public static final int TOP_LIMIT = 0;

    private GameState state;

    public CustomGameFramework() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.enableHighPixelDensity();
        windowsSettings.fullScreen();

        this.state = GameState.PLAYING;

        this.elementController = new ElementController();

        Visitor ssCollisionVisitor = new SpaceshipCollisionVisitor();

        Rectangle rect1 = new Rectangle(10, 10, 80, 55);
        Rectangle rect2 = new Rectangle(500, 10, 80, 55);

        Spaceship spaceship1 = new Spaceship(
                rect1,
                Vector2.vector(rect1.x + (rect1.width / 2), rect1.y + (rect1.height / 2)),
                ssCollisionVisitor,
                1,
                elementController
        );
        spaceship1.addWeapon(new BoostedWeapon(spaceship1));
        Spaceship spaceship2 = new Spaceship(
                rect2,
                Vector2.vector(rect2.x + (rect2.width / 2), rect2.y + (rect2.height / 2)),
                ssCollisionVisitor,
                2,
                elementController);

        this.player2 = new Player(spaceship2);
        this.player1 = new Player(spaceship1);

        observers.add(new PlayerController(this, this.player1, commandsPlayer1()));
        observers.add(new PlayerController(this, this.player2, commandsPlayer2()));

        loadImages(imageLoader);

        ElementRendererVisitor elementRendererVisitor = new ElementRendererVisitor(ss1, ss2, smallbullet, bigbullet, asteroid);
        this.playingRenderer = new PlayingRenderer(elementRendererVisitor, elementController);
        this.pauseRenderer = new PauseRenderer();

        elementController.addGameObject(spaceship1);
        elementController.addGameObject(spaceship2);
    }

    private Map<Integer, Runnable> commandsPlayer1() {
        Map<Integer, Runnable> commands1 = new HashMap<>();
        commands1.put(java.awt.event.KeyEvent.VK_RIGHT, player1.getSpaceship()::moveForward);
        commands1.put(java.awt.event.KeyEvent.VK_LEFT, player1.getSpaceship()::moveBackward);
        commands1.put(java.awt.event.KeyEvent.VK_DOWN, player1.getSpaceship()::moveDownwards);
        commands1.put(java.awt.event.KeyEvent.VK_UP, player1.getSpaceship()::moveUpwards);
        commands1.put(java.awt.event.KeyEvent.VK_SHIFT, player1.getSpaceship()::changeWeapon);
        commands1.put(java.awt.event.KeyEvent.VK_SPACE, player1.getSpaceship()::shoot);
        commands1.put(java.awt.event.KeyEvent.VK_P, this::pause);
        return commands1;
    }

    private Map<Integer, Runnable> commandsPlayer2() {
        Map<Integer, Runnable> commands2 = new HashMap<>();
        commands2.put(java.awt.event.KeyEvent.VK_D, player2.getSpaceship()::moveForward);
        commands2.put(java.awt.event.KeyEvent.VK_A, player2.getSpaceship()::moveBackward);
        commands2.put(java.awt.event.KeyEvent.VK_S, player2.getSpaceship()::moveDownwards);
        commands2.put(java.awt.event.KeyEvent.VK_W, player2.getSpaceship()::moveUpwards);
        commands2.put(java.awt.event.KeyEvent.VK_SHIFT, player2.getSpaceship()::changeWeapon);
        commands2.put(java.awt.event.KeyEvent.VK_F, player2.getSpaceship()::shoot);
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

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        switch (this.state) {
            case PLAYING:
                control();
                loadBG(graphics);
                playingRenderer.render(graphics);
                break;
            case PAUSE:
                pauseRenderer.render(graphics);
                break;
        }

    }

    private void control() {
        elementController.control();
    }

    private void loadBG(PGraphics graphics) {
        //graphics.beginDraw();
        graphics.background(255, 255, 255);
        //graphics.image(this.bg, 0, 0, 2048, 1152);
        //graphics.endDraw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.keyCode = event.getKeyCode();
        notifyObservers();
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

    public int getKeyCode() {
        return keyCode;
    }

    private void pause() {
        if (this.state == GameState.PLAYING) {
            this.state = GameState.PAUSE;
        } else {
            this.state = GameState.PLAYING;
        }
    }
}
