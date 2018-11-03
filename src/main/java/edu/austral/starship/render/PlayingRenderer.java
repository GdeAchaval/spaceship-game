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
    private List<Player> players;

    public PlayingRenderer(ElementRendererVisitor elementRendererVisitor, ElementController elementController, List<Player> players) {
        this.gameObjects = new ArrayList<>();
        this.elementController = elementController;
        this.elementRendererVisitor = elementRendererVisitor;
        this.players = players;
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
        drawHearts(graphics);
        drawScore(graphics);
    }

    private void drawSeparator(PGraphics graphics) {
        graphics.stroke(0, 0, 0);
        graphics.fill(0xA9A9A9, 0.8f);
        graphics.rect(0, 0, RIGHT_LIMIT + 30, 50);
    }

    private void drawHearts(PGraphics graphics) {
        graphics.textSize(20);
        graphics.stroke(255, 0, 0);
        graphics.fill(255, 0, 0);

        for (int i = 0; i < players.size(); i++) {
            int x = 10 + i * 180;
            Player player = players.get(i);
            String text = i + " ";
            int health = player.getSpaceship().getHealth();
            for (int j = 0; j < health; j += INITIAL_HEALTH / 5) {
                text = text.concat("❤");
            }
            graphics.text(text, x, 22);
        }
    }

    private void drawScore(PGraphics graphics) {
        graphics.textSize(20);
        graphics.stroke(0, 0, 255);
        graphics.fill(0, 0, 255);
        for (int i = 0; i < players.size(); i++) {
            int x = 10 + i * 180;
            Player player = players.get(i);
            int health = player.getScore();
            String text = "Score: " + health;
            graphics.text(text, x, 45);
        }
    }
}
