package edu.austral.starship.model.bullet;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Element;
import java.awt.*;


public abstract class Bullet extends Element {
    Bullet(Shape shape, Vector2 position) {
        super(shape, position);
    }
}
