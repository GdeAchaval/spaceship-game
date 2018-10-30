package edu.austral.starship.collision;

import edu.austral.starship.Visitor;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;


public class BigBulletCollisionVisitor implements Visitor {

    private final static int BIGB_DAMAGE = 100;

    @Override
    public void visitAsteroid(Asteroid asteroid) {
        asteroid.destroy();
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
        if(spaceship.getHealth() <= 0) spaceship.destroy();
    }
}
