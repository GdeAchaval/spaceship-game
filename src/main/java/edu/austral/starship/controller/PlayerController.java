package edu.austral.starship.controller;


import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.Observer;
import edu.austral.starship.model.Player;

import java.util.Map;

public class PlayerController implements Observer {

    private CustomGameFramework gameFramework;
    private Map<Integer, Runnable> commands;
    private Player player;

    public PlayerController(CustomGameFramework gameFramework, Player player, Map<Integer, Runnable> commands) {
        this.commands = commands;
        this.player = player;
        this.gameFramework = gameFramework;
    }

    @Override
    public void update() {
        int keyPressed = gameFramework.getKeyCode();
        if (commands.containsKey(keyPressed)) commands.get(keyPressed).run();

    }
}
