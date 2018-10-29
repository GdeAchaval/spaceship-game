package edu.austral.starship.model.bullet;


import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.BigBulletCollisionVisitor;

import java.awt.*;

public class BigBullet extends Bullet {

    public BigBullet(Shape shape, Vector2 position, Visitor collisionVisitor) {
        super(shape, position, collisionVisitor);
    }

    @Override
    public void accepts(Visitor visitor) {
        visitor.visitBigBullet(this);
    }
}
