package edu.austral.starship.collision;


import edu.austral.starship.Visitor;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

public class SpaceshipCollisionVisitor implements Visitor {

    @Override
    public void visitAsteroid(Asteroid asteroid) {
        asteroid.destroy();
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {

    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {

    }

    @Override
    public void visitSpaceship(Spaceship spaceship) {
        System.out.println("CRASH");
    }
}
