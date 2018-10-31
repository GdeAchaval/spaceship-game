package edu.austral.starship.collision;

import edu.austral.starship.Visitor;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;
import edu.austral.starship.model.weapon.Weapon;


public class BigBulletCollisionVisitor implements Visitor {

    private final static int BIGB_DAMAGE = 100;
    private Weapon weapon;

    public BigBulletCollisionVisitor(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void visitAsteroid(Asteroid asteroid) {
        asteroid.destroy();
        this.weapon.getPlayer().addScore(1);
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {

    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {
        smallBullet.destroy();
    }

    @Override
    public void visitSpaceship(Spaceship spaceship) {
        spaceship.hit(BIGB_DAMAGE);
        this.weapon.getPlayer().addScore(10);
    }
}
