package edu.austral.starship.controller;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.collision.AsteroidCollisionVisitor;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.physics.PhysicsVisitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.austral.starship.CustomGameFramework.BOTTOM_LIMIT;
import static edu.austral.starship.CustomGameFramework.RIGHT_LIMIT;

public class ElementController {
    private CollisionEngine<GameObject> collisionEngine;
    private PhysicsVisitor physicsVisitor;
    private List<GameObject> gameObjects;

    public ElementController() {
        this.collisionEngine = new CollisionEngine<>();
        this.gameObjects = new ArrayList<>();
        this.physicsVisitor = new PhysicsVisitor();
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void control() {
        updateElements();
        move();
        spawn();
        checkCollisions();
    }

    private void move() {
        gameObjects.forEach(gameObject -> gameObject.accepts(physicsVisitor));
    }

    private void spawn() {
        Random random = new Random();
        if (random.nextInt(65) == 1) {
            int posX = random.nextInt(BOTTOM_LIMIT);
            int posY = random.nextInt(RIGHT_LIMIT);
            int size = 50 + random.nextInt(60);
            int angle = random.nextInt(360);
            Vector2 pos = Vector2.vector(posX, posY);
            Rectangle rectangle = new Rectangle(posX - size / 2, posY - size / 2, size, size);
            addGameObject(new Asteroid(rectangle, pos, new AsteroidCollisionVisitor(), Vector2.vectorFromModule(1, angle)));
        }
    }

    private void checkCollisions() {
        collisionEngine.checkCollisions(gameObjects);
    }

    private void updateElements() {
        gameObjects.removeIf(go -> !go.isActive());
    }
}
