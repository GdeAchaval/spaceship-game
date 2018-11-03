package edu.austral.starship.render;


import processing.core.PGraphics;

public class PauseRenderer implements Renderer {

    @Override
    public void render(PGraphics graphics) {
        graphics.stroke(255, 255, 255);
        graphics.fill(255, 255, 255);
        graphics.background(0, 0, 0);
        graphics.text("PAUSED GAME", 600, 400);
    }
}
