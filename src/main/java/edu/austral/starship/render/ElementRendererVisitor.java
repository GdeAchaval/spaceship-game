package edu.austral.starship.render;

import edu.austral.starship.Visitor;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.model.Asteroid;
import edu.austral.starship.model.bullet.BigBullet;
import edu.austral.starship.model.bullet.SmallBullet;
import edu.austral.starship.model.spaceship.Spaceship;
import processing.core.PGraphics;
import processing.core.PImage;


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
        int height = asteroid.getShape().getBounds().height;
        int width = asteroid.getShape().getBounds().width;
        graphics.beginDraw();
        graphics.image(this.asteroid, position.getX(), position.getY(), (float) (height*0.9), (float) (width*0.9));
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
        graphics.beginDraw();
        int player = spaceship.getPlayer();
        int size = spaceship.getShape().getBounds().width;
        if(player == 1) {
            // la imagen no deberia ser cuadrada, para asi poder usar correctamente el rectangle para definir size
            graphics.image(this.ss1, position.getX(), position.getY(), size, size);
        }
        if (player == 2) {
            graphics.image(this.ss2, position.getX(), position.getY(), size, size);
        }
        graphics.endDraw();
    }

    void setGraphics(PGraphics graphics) {
        this.graphics = graphics;
    }
}
