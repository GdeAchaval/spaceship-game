package edu.austral.starship.model.spaceship;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Element;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.weapon.CoreWeapon;
import edu.austral.starship.model.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static edu.austral.starship.CustomGameFramework.*;

public class Spaceship extends Element {

    private Queue<Weapon> weapons;

    public Spaceship(Shape shape, Vector2 position) {
        super(shape, position);
        this.weapons = new LinkedList<>();
        addWeapon(new CoreWeapon(this));
    }

    public void moveForward() {
        if(super.getPosition().getX() < RIGHT_LIMIT) {
            super.setPosition(Vector2.vector(8, 0));
        }
    }

    public void moveBackward() {
        if(super.getPosition().getX() > LEFT_LIMIT) {
            super.setPosition(Vector2.vector(-8, 0));
        }
    }

    public void moveUpwards() {
        if(super.getPosition().getY() > TOP_LIMIT) {
            super.setPosition(Vector2.vector(0, -8));
        }
    }

    public void moveDownwards() {
        if(super.getPosition().getY() < BOTTOM_LIMIT) {
            super.setPosition(Vector2.vector(0, 8));
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
}
