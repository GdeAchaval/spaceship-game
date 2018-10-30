package edu.austral.starship.model;

import edu.austral.starship.Element;
import edu.austral.starship.Visitor;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;


public abstract class GameObject implements Collisionable<GameObject>, Element {
    private boolean active;
    private Shape shape;
    private Vector2 position;
    private Vector2 direction;
    private Visitor collisionVisitor;

    public GameObject(Shape shape, Vector2 position, Visitor collisionVisitor) {
        this.shape = shape;
        this.position = position;
        this.collisionVisitor = collisionVisitor;
        this.active = true;
        this.direction = Vector2.vector(1, 0);
    }

    public GameObject(Shape shape, Vector2 position, Visitor collisionVisitor, Vector2 direction) {
        this.shape = shape;
        this.position = position;
        this.collisionVisitor = collisionVisitor;
        this.active = true;
        this.direction = direction;
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

    public void addPosition(Vector2 addedVector) {
        this.position = this.position.add(addedVector);
        Rectangle bounds = this.getShape().getBounds();
        this.shape = new Rectangle(
                (int) this.position.getX() - bounds.width / 2,
                (int) this.position.getY() - bounds.height / 2,
                bounds.width,
                bounds.height);
//        AffineTransform af = new AffineTransform();
//        af.rotate(direction.angle());
//        this.shape = af.createTransformedShape(rectangle);
    }

    public boolean isActive() {
        return active;
    }

    void setNonActive() {
        this.active = false;
    }

    public void changeDirection(Vector2 vector) {
        float angle = this.direction.add(vector).angle();
        this.direction = Vector2.vectorFromModule(1, angle);
    }

    public Vector2 getDirection() {
        return direction;
    }
}
