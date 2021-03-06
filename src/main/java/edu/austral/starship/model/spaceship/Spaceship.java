package edu.austral.starship.model.spaceship;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static edu.austral.starship.CustomGameFramework.*;

public class Spaceship extends GameObject {

    public final static int INITIAL_HEALTH = 1000;
    private static long timeLastChange = 0;

    private Queue<Weapon> weapons;
    private int playerTag;
    private int health;

    public Spaceship(Rectangle shape, Visitor collisionVisitor, int playerTag, Vector2 direction) {
        super(shape, Vector2.vector(shape.x + (shape.width / 2), shape.y + (shape.height / 2)), collisionVisitor, direction);
        this.weapons = new LinkedList<>();
        this.playerTag = playerTag;
        this.health = INITIAL_HEALTH;
    }

    public void moveForward() {
        if (super.getPosition().getX() < RIGHT_LIMIT) {
            super.addPosition(Vector2.vector(10, 0));
            super.changeDirection(Vector2.vector(0.5f, 0));
        }
    }

    public void moveBackward() {
        if (super.getPosition().getX() > LEFT_LIMIT) {
            super.addPosition(Vector2.vector(-10, 0));
            super.changeDirection(Vector2.vector(-0.5f, 0));
        }
    }

    public void moveUpwards() {
        if (super.getPosition().getY() > TOP_LIMIT + 90) {
            super.addPosition(Vector2.vector(0, -10));
            super.changeDirection(Vector2.vector(0, -0.5f));
        }
    }

    public void moveDownwards() {
        if (super.getPosition().getY() < BOTTOM_LIMIT) {
            super.addPosition(Vector2.vector(0, 10));
            super.changeDirection(Vector2.vector(0, 0.5f));
        }
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public Bullet shoot() {
        if (!weapons.isEmpty()) {
            return weapons.peek().shoot();
        }
        return null;
    }

    public void changeWeapon() {
        long millis = System.currentTimeMillis();
        if (millis - timeLastChange > 200) {
            weapons.add(weapons.remove());
            timeLastChange = millis;
        }
    }

    public void hit(int damage) {
        this.health -= damage;
        if (getHealth() <= 0) destroy();
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitSpaceship(this);
    }

    public int getPlayer() {
        return playerTag;
    }
}
