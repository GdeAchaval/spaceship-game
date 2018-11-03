package edu.austral.starship.model.weapon;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.SmallBulletCollisionVisitor;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.bullet.SmallBullet;

import java.awt.*;

@SuppressWarnings("Duplicates")

public class RapidFireWeapon extends Weapon {

    private static final int FIRE_RATE_RAPID = 700;

    public RapidFireWeapon(Player player) {
        super(player.getSpaceship(), player);
    }

    @Override
    public Bullet shoot() {
        long lastFired = super.lastFired;
        long elapsedTime = System.currentTimeMillis() - lastFired;
        if (elapsedTime > FIRE_RATE_RAPID) {
            Vector2 position = super.spaceship.getPosition();
            Vector2 direction = super.spaceship.getDirection();
            super.lastFired = System.currentTimeMillis();
            return new SmallBullet(
                    new Rectangle((int) position.getX(), (int) position.getY(), 10, 10),
                    position.add(direction.multiply(60f)),
                    new SmallBulletCollisionVisitor(this),
                    direction);
        } else {
            return null;
        }
    }
}
