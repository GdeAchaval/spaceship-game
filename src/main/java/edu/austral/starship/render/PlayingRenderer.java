package edu.austral.starship.render;

import edu.austral.starship.controller.ElementController;
import edu.austral.starship.model.GameObject;
import edu.austral.starship.model.Player;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

import static edu.austral.starship.CustomGameFramework.RIGHT_LIMIT;
import static edu.austral.starship.model.spaceship.Spaceship.INITIAL_HEALTH;

public class PlayingRenderer implements Renderer {

    private ElementRendererVisitor elementRendererVisitor;
    private List<GameObject> gameObjects;
    private ElementController elementController;
    private Player player1;
    private Player player2;


    public PlayingRenderer(ElementRendererVisitor elementRendererVisitor, ElementController elementController, Player player1, Player player2) {
        this.gameObjects = new ArrayList<>();
        this.elementController = elementController;
        this.elementRendererVisitor = elementRendererVisitor;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void render(PGraphics graphics) {
        update();
        renderPlayerControls(graphics);
        elementRendererVisitor.setGraphics(graphics);
        gameObjects.forEach(gameObject -> gameObject.accepts(elementRendererVisitor));
    }

    private void update() {
        this.gameObjects = this.elementController.getGameObjects();
    }

    private void renderPlayerControls(PGraphics graphics) {
        drawSeparator(graphics);
        drawHeartsPlayer1(graphics);
        drawScorePlayer1(graphics);
        drawHeartsPlayer2(graphics);
        drawScorePlayer2(graphics);
    }

    private void drawSeparator(PGraphics graphics) {
        graphics.stroke(0, 0, 0);
        graphics.fill(0xA9A9A9, 0.8f);
        graphics.rect(0, 0, RIGHT_LIMIT+30, 50);
    }

    private void drawHeartsPlayer1(PGraphics graphics) {
        graphics.textSize(20);
        graphics.stroke(255, 0, 0);
        graphics.fill(255, 0, 0);
        String text = "";
        int health = player1.getSpaceship().getHealth();
        for (int i = 0; i < health; i+=INITIAL_HEALTH/5) {
            text = text.concat("❤");
        }
        text = text.concat("  " + Integer.toString(health));
        graphics.text(text, 10, 22);
    }

    private void drawScorePlayer1(PGraphics graphics) {
        drawScore(graphics, player1, 10);
    }

    private void drawHeartsPlayer2(PGraphics graphics) {
        graphics.textSize(20);
        graphics.stroke(255, 0, 0);
        graphics.fill(255, 0, 0);
        int health = player2.getSpaceship().getHealth();
        String text = health + "  ";
        for (int i = 0; i < health; i+=INITIAL_HEALTH/5) {
            text = text.concat("❤");
        }
        graphics.text(text, RIGHT_LIMIT-130, 22);
    }

    private void drawScorePlayer2(PGraphics graphics) {
        drawScore(graphics, player2, RIGHT_LIMIT-70);
    }

    private void drawScore(PGraphics graphics, Player player1, int i) {
        graphics.textSize(20);
        graphics.stroke(0, 0, 255);
        graphics.fill(0, 0, 255);
        int health = player1.getScore();
        String text = "Score: " + health;
        graphics.text(text, i, 45);
    }
}
