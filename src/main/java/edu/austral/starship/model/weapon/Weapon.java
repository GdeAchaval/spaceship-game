package edu.austral.starship.model.weapon;


import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

public abstract class Weapon {
    Spaceship spaceship;

    public Weapon(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public abstract Bullet shoot();
}
