//package com.hishab.boardgame.game;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomConfig implements CommandLineRunner {
//
//    @Autowired
//    private Playground playground;
//
//    @Override
//    public void run(String... args) throws Exception {
//        UserProfile firstProfile = new UserProfile().setName("Asif").setAge(10);
//        UserProfile secondProfile = new UserProfile().setName("Arif").setAge(20);
//        UserProfile thirdProfile = new UserProfile().setName("Munaf").setAge(40);
//
//        playground.setTotalPlayer(3);
//        playground.setTotalScore(100);
//        playground.addNewPlayer(firstProfile);
//        playground.addNewPlayer(secondProfile);
//        playground.addNewPlayer(thirdProfile);
//
//        playground.play();
//    }
//}
