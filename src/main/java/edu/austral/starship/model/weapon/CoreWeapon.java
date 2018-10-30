package edu.austral.starship.model.weapon;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.SmallBulletCollisionVisitor;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.*;

public class CoreWeapon extends Weapon {


    public CoreWeapon(Spaceship spaceship) {
        super(spaceship);
    }

    public Bullet shoot() {
        Vector2 position = super.spaceship.getPosition();
        Vector2 direction = super.spaceship.getDirection();
        return new SmallBullet(
                new Rectangle((int) position.getX(), (int) position.getY(), 10, 10),
                position.add(direction.multiply(60f)),
                new SmallBulletCollisionVisitor(),
                direction);
    }
}
