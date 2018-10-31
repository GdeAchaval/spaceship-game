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
                position.getY() > TOP_LIMIT+30) {
            asteroid.addPosition(asteroid.getDirection());
        } else {
            asteroid.destroy();
        }
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {
        Vector2 position = bigBullet.getPosition();
        if (position.getX() < RIGHT_LIMIT &&
                position.getX() > LEFT_LIMIT &&
                position.getY() < BOTTOM_LIMIT &&
                position.getY() > TOP_LIMIT+30) {
            bigBullet.addPosition(bigBullet.getDirection().multiply(4f));
        } else {
            bigBullet.destroy();
        }
    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {
        Vector2 position = smallBullet.getPosition();
        if (position.getX() < RIGHT_LIMIT &&
                position.getX() > LEFT_LIMIT &&
                position.getY() < BOTTOM_LIMIT &&
                position.getY() > TOP_LIMIT+30) {
            smallBullet.addPosition(smallBullet.getDirection().multiply(8f));
        } else {
            smallBullet.destroy();
        }
    }

    @Override
    public void visitSpaceship(Spaceship spaceship) {
    }
}
