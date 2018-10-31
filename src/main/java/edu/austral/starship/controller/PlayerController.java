package edu.austral.starship.controller;


import edu.austral.starship.CustomGameFramework;
import edu.austral.starship.Observer;

import java.util.List;
import java.util.Map;

public class PlayerController implements Observer {

    private CustomGameFramework gameFramework;
    private Map<Integer, Runnable> commands;

    public PlayerController(CustomGameFramework gameFramework, Map<Integer, Runnable> commands) {
        this.commands = commands;
        this.gameFramework = gameFramework;
    }

    @Override
    public void update() {
        List<Integer> keyPressed = gameFramework.getKeyCodes();
        keyPressed.forEach(key -> {
            if (commands.containsKey(key)) commands.get(key).run();
        });
    }
}
