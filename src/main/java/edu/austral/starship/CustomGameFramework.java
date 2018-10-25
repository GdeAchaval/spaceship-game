package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.controller.PlayerController;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.spaceship.Spaceship;
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

    // origen arriba a la izquierda
    public static final int BOTTOM_LIMIT = 800;
    public static final int RIGHT_LIMIT = 1200;
    public static final int LEFT_LIMIT = 0;
    public static final int TOP_LIMIT = 0;

    public CustomGameFramework() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings.enableHighPixelDensity();
        windowsSettings.fullScreen();

        this.player1 = new Player(new Spaceship(new Rectangle(10, 10, 10, 10), Vector2.vector(10, 10)));
        this.player2 = new Player(new Spaceship(new Rectangle(1000, 10, 10, 10), Vector2.vector(1000, 10)));

        Map<Integer, Runnable> commands1 = commandsPlayer1();
        Map<Integer, Runnable> commands2 = commandsPlayer2();
        observers.add(new PlayerController(this, this.player1, commands1));
        observers.add(new PlayerController(this, this.player2, commands2));
        this.ss1 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss1.png");
        this.ss2 = imageLoader.load("/Users/GonzaOK/projects/starships/src/main/java/edu/austral/starship/resource/ss2.png");
    }

    private Map<Integer, Runnable> commandsPlayer1() {
        Map<Integer, Runnable> commands1 = new HashMap<>();
        commands1.put(java.awt.event.KeyEvent.VK_RIGHT, player1.getSpaceship()::moveForward);
        commands1.put(java.awt.event.KeyEvent.VK_LEFT, player1.getSpaceship()::moveBackward);
        commands1.put(java.awt.event.KeyEvent.VK_DOWN, player1.getSpaceship()::moveDownwards);
        commands1.put(java.awt.event.KeyEvent.VK_UP, player1.getSpaceship()::moveUpwards);
        commands1.put(java.awt.event.KeyEvent.VK_SHIFT, player1.getSpaceship()::changeWeapon);
        commands1.put(java.awt.event.KeyEvent.VK_SPACE, player1.getSpaceship()::shoot);
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
        return commands2;
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        drawSS1(graphics);
        drawSS2(graphics);
    }

    private void drawSS1(PGraphics graphics) {
        Vector2 position = player1.getSpaceship().getPosition();
        graphics.beginDraw();
        graphics.image(this.ss1, position.getX(), position.getY(), 85, 85);
        graphics.endDraw();
    }

    private void drawSS2(PGraphics graphics) {
        Vector2 position = player2.getSpaceship().getPosition();
        graphics.beginDraw();
        graphics.image(this.ss2, position.getX(), position.getY(), 85, 85);
        graphics.endDraw();
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
}
