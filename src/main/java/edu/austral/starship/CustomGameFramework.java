package edu.austral.starship;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.controller.PlayerController;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.spaceship.Spaceship;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework, Subject {
    private List<Observer> observers;
    private int keyCode;
    private Player player;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500).enableHighPixelDensity();
        observers = new ArrayList<>();
        this.player = new Player(new Spaceship(new Rectangle(), Vector2.vector(10, 10)));
        observers.add(new PlayerController(this, this.player));
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        Vector2 position = player.getSpaceship().getPosition();
        System.out.println(position.getX() + "," + position.getY());
        graphics.ellipse(position.getX(), position.getY(), 50, 50 );
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.keyCode = event.getKeyCode();
        System.out.println("Key pressed:" + event.getKeyCode());
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
