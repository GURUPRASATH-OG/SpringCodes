package com.gamerunner;
import com.gamelibrary.GameLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="com")
public class BeansConfig
{
//Manual Bean creation.
//    @Bean
//    public Pacman getPacman()
//    {
//        return new Pacman();
//    }

//    @Bean
//    public SubwaySurfers getSubwaySurfers()
//    {
//        return new SubwaySurfers();
//    }
//   /*Filed based injection/*
//  @Autowired
//  private GameLibrary game;

//    @Bean
//    public GamingConsole getConsole(GameLibrary game)
//    {
//        return new GamingConsole(game);
//    }
}
