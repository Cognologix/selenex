package com.test.service.cmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumber {

    private static  final Logger LOGGER = LoggerFactory.getLogger (RandomNumber.class);
    public  int getRandomNumber() {
        LOGGER.info ("Into Random Function");
        int randNo;
        Random rand = new Random();
        randNo = rand.nextInt(110009);
        int nano = (int) System.nanoTime();
        int ranNo = randNo + nano;
        LOGGER.info ("Random Number::" +ranNo);
        return ranNo;

    }
}
