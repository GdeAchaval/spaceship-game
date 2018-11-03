package edu.austral.starship.model.weapon;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;
import edu.austral.starship.model.Player;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.*;

public class RapidFireWeapon extends Weapon {

    private static final int FIRE_RATE_RAPID = 600;

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
            return new SmallBullet(
                    new Rectangle((int) position.getX(), (int) position.getY(), 10, 10),
                    position.add(direction.multiply(60f)),
                    new BigBulletCollisionVisitor(this),
                    direction);
        } else {
            return null;
        }
    }
}
