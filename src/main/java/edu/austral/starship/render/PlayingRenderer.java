package edu.austral.starship.render;

import edu.austral.starship.controller.ElementController;
import edu.austral.starship.model.GameObject;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class PlayingRenderer implements Renderer {

    private ElementRendererVisitor elementRendererVisitor;
    private List<GameObject> gameObjects;
    private ElementController elementController;

    public PlayingRenderer(ElementRendererVisitor elementRendererVisitor, ElementController elementController) {
        this.gameObjects = new ArrayList<>();
        this.elementController = elementController;
        this.elementRendererVisitor = elementRendererVisitor;
    }

    @Override
    public void render(PGraphics graphics) {
        update();
        elementRendererVisitor.setGraphics(graphics);
        gameObjects.forEach(gameObject -> {
            gameObject.accepts(elementRendererVisitor);
        });
    }

    public void update() {
        this.gameObjects = this.elementController.getGameObjects();
    }
}
