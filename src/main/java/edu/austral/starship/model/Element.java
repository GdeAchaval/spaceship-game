package edu.austral.starship.model;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;


public abstract class Element implements Collisionable<Element> {
    private Shape shape;
    private Vector2 position;

    public Element(Shape shape, Vector2 position) {
        this.shape = shape;
        this.position = position;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public void collisionedWith(Element collisionable) {

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 addedVector) {
        this.position = this.position.add(addedVector);
    }
}
