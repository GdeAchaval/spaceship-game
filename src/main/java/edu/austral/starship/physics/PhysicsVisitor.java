package edu.austral.starship.physics;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

import static edu.austral.starship.CustomGameFramework.*;

public class PhysicsVisitor implements Visitor {

    @Override
    public void visitAsteroid(Asteroid asteroid) {
        Vector2 position = asteroid.getPosition();
        if (position.getX() < RIGHT_LIMIT &&
                position.getX() > LEFT_LIMIT &&
                position.getY() < BOTTOM_LIMIT &&
                position.getY() > TOP_LIMIT) {
            asteroid.addPosition(asteroid.getDirection());
        } else {
            asteroid.destroy();
        }
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {

    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {

    }

    @Override
    public void visitSpaceship(Spaceship spaceship) {
    }
}
