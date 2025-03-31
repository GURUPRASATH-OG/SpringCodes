package com.gamelibrary;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SubwaySurfers implements GameLibrary
{
    private String name;
    @Override
    public void run() {
        System.out.println("SubWaySurfer   Running");
    }

    @Override
    public void jump() {
        System.out.println("SubWaySurfer  jumps");
    }

    @Override
    public void right() {
        System.out.println("SubWaySurfer turns right");
    }

    @Override
    public void left() {
        System.out.println("SubWaySurfer turns left");
    }
}
