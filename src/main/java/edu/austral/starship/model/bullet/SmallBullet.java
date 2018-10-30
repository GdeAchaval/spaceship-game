package edu.austral.starship.model.bullet;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class SmallBullet extends Bullet {

    public SmallBullet(Shape shape, Vector2 position, Visitor collisionVisitor) {
        super(shape, position, collisionVisitor);
    }

    public SmallBullet(Shape shape, Vector2 position, Visitor collisionVisitor, Vector2 direction) {
        super(shape, position, collisionVisitor, direction);
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitSmallBullet(this);
    }
}
