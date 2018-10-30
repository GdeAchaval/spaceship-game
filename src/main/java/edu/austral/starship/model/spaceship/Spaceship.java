package edu.austral.starship.model.spaceship;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.controller.ElementController;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.model.weapon.CoreWeapon;
import edu.austral.starship.model.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static edu.austral.starship.CustomGameFramework.*;

public class Spaceship extends GameObject {

    private final static int INITIAL_HEALTH = 100;

    private Queue<Weapon> weapons;
    private int player;
    private ElementController elementController;
    private int health;


    public Spaceship(Shape shape, Vector2 position, Visitor collisionVisitor, int player, ElementController elemCont) {
        super(shape, position, collisionVisitor);
        this.weapons = new LinkedList<>();
        addWeapon(new CoreWeapon(this));
        this.player = player;
        this.elementController = elemCont;
        this.health = INITIAL_HEALTH;
    }

    public void moveForward() {
        if (super.getPosition().getX() < RIGHT_LIMIT) {
            super.addPosition(Vector2.vector(15, 0));
            super.changeDirection(Vector2.vector(1, 0));
        }
    }

    public void moveBackward() {
        if (super.getPosition().getX() > LEFT_LIMIT) {
            super.addPosition(Vector2.vector(-15, 0));
            super.changeDirection(Vector2.vector(-1, 0));
        }
    }

    public void moveUpwards() {
        if (super.getPosition().getY() > TOP_LIMIT) {
            super.addPosition(Vector2.vector(0, -15));
            super.changeDirection(Vector2.vector(0, -1));
        }
    }

    public void moveDownwards() {
        if (super.getPosition().getY() < BOTTOM_LIMIT) {
            super.addPosition(Vector2.vector(0, 15));
            super.changeDirection(Vector2.vector(0, 1));
        }
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void shoot() {
        if (!weapons.isEmpty()) {
            this.elementController.addGameObject(weapons.peek().shoot());
        }
    }

    public void changeWeapon() {
        weapons.add(weapons.remove());
    }

    public void hit(int damage) {
        this.health -= damage;
        if(damage <= 0) destroy();
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitSpaceship(this);
    }

    public int getPlayer() {
        return player;
    }
}
