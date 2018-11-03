package edu.austral.starship.render;

import edu.austral.starship.model.Player;
import processing.core.PGraphics;

import java.util.List;


public class GameOverRenderer implements Renderer {
    private List<Player> players;

    public GameOverRenderer(List<Player> players) {
        this.players = players;
    }

    @Override
    public void render(PGraphics graphics) {
        StringBuilder text = new StringBuilder("PLAYER ");
        graphics.stroke(0, 255, 0);
        graphics.fill(0, 255, 0);
        graphics.background(0, 0, 0);
        for (Player player : players) {
            if (player.getSpaceship().getHealth() <= 0) {
                text.append(player.getSpaceship().getPlayer());
            }
        }
        graphics.text(text + " DIED!", 550, 400);
    }
}
