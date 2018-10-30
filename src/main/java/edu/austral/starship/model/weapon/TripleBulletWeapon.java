package edu.austral.starship.model.weapon;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.*;

public class TripleBulletWeapon extends WeaponDecorator {

    public TripleBulletWeapon(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Bullet shoot() {
        // shoot 3 times
        Vector2 position = super.spaceship.getPosition();
        Vector2 direction = super.spaceship.getDirection();
        return new BigBullet(
                new Rectangle((int) position.getX(), (int) position.getY(), 20, 13),
                position.add(direction.multiply(60f)),
                new BigBulletCollisionVisitor(),
                direction);
    }
}
