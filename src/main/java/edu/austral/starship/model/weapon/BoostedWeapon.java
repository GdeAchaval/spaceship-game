package edu.austral.starship.model.weapon;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

import java.awt.geom.Ellipse2D;

// change name to rapid fire? cambios q ni idea fijarse si estan bien
public class BoostedWeapon extends WeaponDecorator {


    public BoostedWeapon(Spaceship spaceship) {
        super(spaceship);
    }

    @Override
    public Bullet shoot() {
        Vector2 position = super.spaceship.getPosition();
        return new SmallBullet(new Ellipse2D.Double(position.getX(), position.getY(), 10, 10), position, new BigBulletCollisionVisitor());
    }
}
