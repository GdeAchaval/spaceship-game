package edu.austral.starship.collision;


import edu.austral.starship.model.GameObject;

import java.util.List;

public class CollisionEngine {

    public void checkCollision(List<GameObject> collisionables) {
        collisionables.forEach(i ->
                collisionables.forEach(j -> {
                    if (i != j && i.getShape().getBounds().intersects(j.getShape().getBounds())) {
                        System.out.println("ix: " + i.getShape().getBounds().x);
                        System.out.println("iy: " + i.getShape().getBounds().y);
                        System.out.println("jx: " + j.getShape().getBounds().x);
                        System.out.println("jy: " + j.getShape().getBounds().x);
                        i.collisionedWith(j);
                        j.collisionedWith(i);
                    }
                }));
    }
}
