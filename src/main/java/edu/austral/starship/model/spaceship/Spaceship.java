package edu.austral.starship.model.spaceship;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.weapon.CoreWeapon;
import edu.austral.starship.model.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static edu.austral.starship.CustomGameFramework.*;

public class Spaceship extends GameObject {

    private Queue<Weapon> weapons;
    private int player;

    public Spaceship(Shape shape, Vector2 position, Visitor collisionVisitor, int player) {
        super(shape, position, collisionVisitor);
        this.weapons = new LinkedList<>();
        addWeapon(new CoreWeapon(this));
        this.player = player;
    }

    public void moveForward() {
        if(super.getPosition().getX() < RIGHT_LIMIT) {
            super.addPosition(Vector2.vector(10, 0));
            super.changeDirection(Vector2.vector(1, 0));
        }
    }

    public void moveBackward() {
        if(super.getPosition().getX() > LEFT_LIMIT) {
            super.addPosition(Vector2.vector(-10, 0));
            super.changeDirection(Vector2.vector(-1, 0));
        }
    }

    public void moveUpwards() {
        if(super.getPosition().getY() > TOP_LIMIT) {
            super.addPosition(Vector2.vector(0, -8));
            super.changeDirection(Vector2.vector(0, -1));
        }
    }

    public void moveDownwards() {
        if(super.getPosition().getY() < BOTTOM_LIMIT) {
            super.addPosition(Vector2.vector(0, 8));
            super.changeDirection(Vector2.vector(0, 1));
        }
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public Optional<Bullet> shoot() {
        if (!weapons.isEmpty()) {
            return Optional.of(weapons.peek().shoot());
        }
        return Optional.empty();
    }

    public void changeWeapon() {
        weapons.add(weapons.remove());
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitSpaceship(this);
    }

    public int getPlayer() {
        return player;
    }
}
