package edu.austral.starship.model.bullet;
import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.GameObject;
import java.awt.*;


public abstract class Bullet extends GameObject {
    Bullet(Shape shape, Vector2 position, Visitor collisionVisitor) {
        super(shape, position, collisionVisitor);
    }
}
