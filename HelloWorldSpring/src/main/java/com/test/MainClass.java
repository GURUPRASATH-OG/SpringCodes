package com.test;

import com.gamerunner.BeansConfig;
import com.gamerunner.GamingConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gamerunner.GamingConsole;
public class MainClass
{
    public static void main(String[] args)
    {
          //generating beans from xml based configuration file.
//        ClassPathXmlApplicationContext xmlcontext = new ClassPathXmlApplicationContext("beans.xml");
//        GamingConsole Xbox = (GamingConsole) xmlcontext.getBean(GamingConsole.class);
//        Xbox.playGame();

        //Java based configuration=
        ApplicationContext javacontext = new AnnotationConfigApplicationContext(BeansConfig.class);
        GamingConsole ps5 = javacontext.getBean(GamingConsole.class);
        ps5.playGame();
        GamingConsole  ps4 =javacontext.getBean(GamingConsole.class);
        ps4.playGame();


    }
}
