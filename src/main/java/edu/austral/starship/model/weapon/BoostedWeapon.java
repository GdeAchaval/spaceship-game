package edu.austral.starship.model.weapon;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.*;

public class BoostedWeapon extends Weapon {

    private static final int FIRE_RATE_BOOST = 2000;

    public BoostedWeapon(Player player) {
        super(player.getSpaceship(), player);
    }

    public Bullet shoot() {
        long lastFired = super.lastFired;
        long elapsedTime = System.currentTimeMillis() - lastFired;
        if (elapsedTime > FIRE_RATE_BOOST) {
            Vector2 position = super.spaceship.getPosition();
            Vector2 direction = super.spaceship.getDirection();
            super.lastFired = System.currentTimeMillis();
            return new BigBullet(
                    new Rectangle((int) position.getX(), (int) position.getY(), 20, 13),
                    position.add(direction.multiply(60f)),
                    new BigBulletCollisionVisitor(this),
                    direction);
        } else {
            return null;
        }
    }
}
