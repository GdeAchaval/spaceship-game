package edu.austral.starship.model;


import edu.austral.starship.model.spaceship.Spaceship;

public class Player {

    private Spaceship spaceship;
    private int score;

    public Player(Spaceship spaceship) {
        this.spaceship = spaceship;
        this.score = 0;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void addScore(int sum) {
        this.score += sum;
    }

    public int getScore() {
        return score;
    }
}
