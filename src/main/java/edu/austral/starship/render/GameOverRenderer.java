package edu.austral.starship.render;

import edu.austral.starship.model.Player;
import processing.core.PGraphics;


public class GameOverRenderer implements Renderer {
    private Player player1;
    private Player player2;

    public GameOverRenderer(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void render(PGraphics graphics) {
        graphics.stroke(0, 255, 0);
        graphics.fill(0, 255, 0);
        graphics.background(0, 0, 0);
        String text = "PLAYER ";
        if (player1.getSpaceship().getHealth() <= 0) {
            text += 2;
        }
        if (player2.getSpaceship().getHealth() <= 0) {
            text += 1;
        }
        graphics.text(text + " WINS!", 550, 400);
    }
}
