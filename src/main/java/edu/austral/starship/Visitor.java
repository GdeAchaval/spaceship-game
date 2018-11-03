package edu.austral.starship;


import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;

public interface Visitor {
    void visitAsteroid(Asteroid asteroid);

    void visitBigBullet(BigBullet bigBullet);

    void visitSmallBullet(SmallBullet smallBullet);

    void visitSpaceship(Spaceship spaceship);
}
