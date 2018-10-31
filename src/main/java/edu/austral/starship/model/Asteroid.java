package edu.austral.starship.model;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid extends GameObject {

    public Asteroid(Shape shape, Vector2 position, Visitor collisionVisitor, Vector2 direction) {
        super(shape, position, collisionVisitor, direction);
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitAsteroid(this);
    }
}
