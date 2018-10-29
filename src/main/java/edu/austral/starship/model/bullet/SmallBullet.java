package edu.austral.starship.model.bullet;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.SmallBulletCollisionVisitor;

import java.awt.*;

public class SmallBullet extends Bullet {

    public SmallBullet(Shape shape, Vector2 position, Visitor collisionVisitor) {
        super(shape, position, collisionVisitor);
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitSmallBullet(this);
    }
}
