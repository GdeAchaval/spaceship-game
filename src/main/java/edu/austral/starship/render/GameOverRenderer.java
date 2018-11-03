package edu.austral.starship.render;

import edu.austral.starship.model.Player;
import processing.core.PGraphics;

import java.util.List;


public class GameOverRenderer implements Renderer {
    private List<Player> players;
    private static String text = "PLAYER ";

    public GameOverRenderer(List<Player> players) {
        this.players = players;
    }

    @Override
    public void render(PGraphics graphics) {
        graphics.stroke(0, 255, 0);
        graphics.fill(0, 255, 0);
        graphics.background(0, 0, 0);
        players.forEach(player -> {
            if (player.getSpaceship().getHealth() <= 0) {
                text += player.getSpaceship().getPlayer();
            }
        });
        graphics.text(text + " DIED!", 550, 400);
    }
}
