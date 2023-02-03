//package com.hishab.boardgame.config;
//
//import com.hishab.boardgame.domain.UserProfile;
//import com.hishab.boardgame.service.PlaygroundService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class GameTest implements CommandLineRunner {
//
//    @Autowired
//    private PlaygroundService playground;
//
//    @Override
//    public void run(String... args) {
//        UserProfile firstProfile = new UserProfile().setName("Asif").setAge(10);
//        UserProfile secondProfile = new UserProfile().setName("Arif").setAge(20);
//        UserProfile thirdProfile = new UserProfile().setName("Munaf").setAge(40);
//
//        playground.setTotalScore(40);
//        playground.addNewPlayer(firstProfile);
//        playground.addNewPlayer(secondProfile);
//        playground.addNewPlayer(thirdProfile);
//
//        playground.play();
//    }
//}
