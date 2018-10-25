package edu.austral.starship.model.weapon;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;
import java.awt.geom.Ellipse2D;

public class CoreWeapon extends Weapon {


    public CoreWeapon(Spaceship spaceship) {
        super(spaceship);
    }

    public Bullet shoot() {
        Vector2 position = super.spaceship.getPosition();
        return new SmallBullet(new Ellipse2D.Double(position.getX(), position.getY(), 10, 10), position);
    }
}
