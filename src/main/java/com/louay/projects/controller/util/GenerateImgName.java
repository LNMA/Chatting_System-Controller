package com.louay.projects.controller.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateImgName {
    private static Set<Long> setName = new HashSet<>();
    private static List<Long> arrayName = new ArrayList<>(1000000);
    private static int index;

    static {
        int current = 0;
        long max = 1000000;
        long randomNum;
        while (current<max) {
            randomNum = ThreadLocalRandom.current().nextLong(0, 1000000000);
            setName.add(randomNum);
        }
        for (Long l : setName){
            arrayName.add(l);
        }
    }

    public static Long getName(){
        long name = arrayName.get(index);
        index++;
        return name;
    }
}
