package edu.austral.starship.model;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid extends GameObject {

    public Asteroid(Shape shape, Vector2 position, Visitor collisionVisitor) {
        super(shape, position, collisionVisitor);
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitAsteroid(this);
    }

    public void destroy() {
        super.setNonActive();
    }

    @Override
    public String toString() {
        return "asteroid";
    }
}
