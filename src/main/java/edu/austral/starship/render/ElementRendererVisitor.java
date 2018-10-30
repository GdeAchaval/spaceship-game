package edu.austral.starship.render;

import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Asteroid;
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
    private PImage smallbullet;
    private PImage bigbullet;
    private PImage asteroid;

    public ElementRendererVisitor(PImage ss1, PImage ss2, PImage smallbullet, PImage bigbullet, PImage asteroid) {
        this.ss1 = ss1;
        this.ss2 = ss2;
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
        graphics.image(this.asteroid, position.getX(), position.getY(), (float) (height*0.95), (float) (width*0.95));
        graphics.endDraw();
    }

    @Override
    public void visitBigBullet(BigBullet bigBullet) {

    }

    @Override
    public void visitSmallBullet(SmallBullet smallBullet) {

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

        if(player == 1) {
            graphics.image(this.ss1, 0, 0, width, width); //image is square
        }
        if (player == 2) {
            graphics.image(this.ss2, 0, 0, width, width);
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
}
