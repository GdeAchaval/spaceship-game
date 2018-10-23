package edu.austral.starship.model.spaceship;


import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Element;

import java.awt.*;

public class Spaceship extends Element {

    private PhysicsManager physicsManager;

    public Spaceship(Shape shape, Vector2 position) {
        super(shape, position);
        this.physicsManager = new PhysicsManager(this);
    }

    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    public void moveForward() {
        System.out.println("Moving forward");
        super.setPosition(Vector2.vector(20, 20));
    }

    public void moveBackward() {

    }

    public void rotateClockwise() {

    }

    public void rotateCounterclockwise() {

    }
}
