package edu.austral.starship.render;


import processing.core.PGraphics;

public class PauseRenderer implements Renderer{

    @Override
    public void render(PGraphics graphics) {
        graphics.background(0, 0, 0);
        graphics.text("PAUSED GAME", 600, 400);
    }
}
