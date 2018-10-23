package edu.austral.starship.controller;


import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.Observer;
import edu.austral.starship.model.Player;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class PlayerController implements Observer {

    private CustomGameFramework gameFramework;
    private Map<Integer, Runnable> commands;
    private Player player;

    public PlayerController(CustomGameFramework gameFramework, Player player) {
        this.player = player;
        this.gameFramework = gameFramework;
        commands = new HashMap<>();
        // esto en setup
        commands.put(KeyEvent.VK_RIGHT, player.getSpaceship()::moveForward);
    }

    @Override
    public void update() {
        int keyPressed = gameFramework.getKeyCode();
        commands.get(keyPressed).run();
    }
}
