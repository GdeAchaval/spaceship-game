package edu.austral.starship.model.weapon;


import edu.austral.starship.model.Player;
import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

public abstract class Weapon {
    Spaceship spaceship;
    long lastFired;
    private Player player;

    Weapon(Spaceship spaceship, Player player) {
        this.spaceship = spaceship;
        this.player = player;
    }

    public abstract Bullet shoot();

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public Player getPlayer() {
        return player;
    }
}
