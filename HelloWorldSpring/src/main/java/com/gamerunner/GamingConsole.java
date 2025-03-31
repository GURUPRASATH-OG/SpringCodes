package com.gamerunner;

import com.gamelibrary.GameLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class GamingConsole
{

    //Depedency
    @Autowired
    private  GameLibrary game;
    public void setGame(GameLibrary game) {
        this.game = game;
    }

    public GamingConsole(){}

    //Constructor based depedency Injection
    @Autowired
    public GamingConsole(GameLibrary game)
    {
        this.game = game;
    }

    public void playGame()
    {
        game.run();
        game.jump();
        game.left();
        game.right();

    }
}
