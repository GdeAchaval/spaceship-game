package edu.austral.starship.model;


import edu.austral.starship.model.spaceship.Spaceship;

public class Player {

    private Spaceship spaceship;

    public Player(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }
}
