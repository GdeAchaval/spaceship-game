package edu.austral.starship.model;

import edu.austral.starship.Element;
import edu.austral.starship.Visitor;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;


public abstract class GameObject implements Collisionable<GameObject>, Element {
    private boolean active;
    private Shape shape;
    private Vector2 position;
    private Visitor collisionVisitor;

    public GameObject(Shape shape, Vector2 position, Visitor collisionVisitor) {
        this.shape = shape;
        this.position = position;
        this.collisionVisitor = collisionVisitor;
        this.active = true;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public void collisionedWith(GameObject collisionable) {
        collisionable.accepts(this.collisionVisitor);
    }

    public Vector2 getPosition() {
        return position;
    }

    protected void setPosition(Vector2 addedVector) {
        this.position = this.position.add(addedVector);
        this.shape = new Rectangle(
                (int) this.position.getX(),
                (int) this.position.getY(),
                this.getShape().getBounds().width,
                this.getShape().getBounds().height);
    }

    public boolean isActive() {
        return active;
    }

    void setNonActive() {
        this.active = false;
    }
}
