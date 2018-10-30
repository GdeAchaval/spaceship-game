package edu.austral.starship.model.weapon;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.*;

// change name to rapid fire? cambios q ni idea fijarse si estan bien
public class BoostedWeapon extends WeaponDecorator {


    public BoostedWeapon(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Bullet shoot() {
        Vector2 position = super.spaceship.getPosition();
        Vector2 direction = super.spaceship.getDirection();
        return new BigBullet(
                new Rectangle((int) position.getX(), (int) position.getY(), 20, 13),
                position,
                new BigBulletCollisionVisitor(),
                direction);
    }
}
