package com.gamelibrary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Pacman implements GameLibrary
{

    @Override
    public void run()
    {
        System.out.println("Pacman Running");
    }

    @Override
    public void jump()
    {
        System.out.println("Pacman Jumps");
    }

    @Override
    public void right()
    {
        System.out.println("Pacman Right");
    }

    @Override
    public void left()
    {
        System.out.println("Pacman Left");
    }
}
