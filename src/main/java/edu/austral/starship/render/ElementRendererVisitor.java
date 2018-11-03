package edu.austral.starship.render;

import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;


public class ElementRendererVisitor implements Visitor {

    private PGraphics graphics;
    private PImage ss1;
    private PImage ss2;
    private PImage ss3;
    private PImage smallbullet;
    private PImage bigbullet;
    private PImage asteroid;

    public ElementRendererVisitor(PImage ss1, PImage ss2, PImage ss3, PImage smallbullet, PImage bigbullet, PImage asteroid) {
        this.ss1 = ss1;
        this.ss2 = ss2;
        this.ss3 = ss3;
        this.smallbullet = smallbullet;
        this.bigbullet = bigbullet;
        this.asteroid = asteroid;
    }

    @Override
    public void visitAsteroid(Asteroid asteroid) {
        Vector2 position = asteroid.getPosition();
        Rectangle bounds = asteroid.getShape().getBounds();
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;
        graphics.beginDraw();
        drawBounds(x, y, width, height);
        graphics.image(this.asteroid, position.getX(), position.getY(), (float) (height * 0.95), (float) (width * 0.95));
        graphics.endDraw();
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {
        Vector2 position = bigBullet.getPosition();
        Rectangle bounds = bigBullet.getShape().getBounds();
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;
        graphics.beginDraw();
        drawBounds(x, y, width, height);
        rotate(bigBullet, position, width * 3, height * 3, this.bigbullet);
        graphics.endDraw();
    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {
        Vector2 position = smallBullet.getPosition();
        Rectangle bounds = smallBullet.getShape().getBounds();
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;

        graphics.beginDraw();

        drawBounds(x, y, width, height);
        rotate(smallBullet, position, width * 13, height * 13, this.smallbullet);

        graphics.endDraw();
    }

    @Override
    public void visitSpaceship(Spaceship spaceship) {
        Vector2 position = spaceship.getPosition();
        int player = spaceship.getPlayer();
        Rectangle bounds = spaceship.getShape().getBounds();
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;


        graphics.beginDraw();

        drawBounds(x, y, width, height);

        graphics.pushMatrix();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(position.getX(), position.getY());
        graphics.rotate(spaceship.getDirection().angle());

        if (player == 1) {
            graphics.image(this.ss1, 0, 0, width, width); //image is square
        }
        if (player == 2) {
            graphics.image(this.ss2, 0, 0, width, width);
        }
        if (player == 3) {
            graphics.image(this.ss3, 0, 0, width, width);
        }

        graphics.endDraw();
        graphics.popMatrix();
    }


    void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }

    private void drawBounds(int x, int y, int width, int height) {
        graphics.point(x, y);
        graphics.point(x + width, y);
        graphics.point(x, y + height);
        graphics.point(x + width, y + height);
    }

    private void rotate(GameObject gameObject, Vector2 position, int width, int height, PImage image) {
        graphics.pushMatrix();
        graphics.imageMode(PConstants.CENTER);
        graphics.translate(position.getX(), position.getY());
        graphics.rotate(gameObject.getDirection().angle());
        graphics.image(image, 0, 0, height, width);
        graphics.endDraw();
        graphics.popMatrix();
    }
}
