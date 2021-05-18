package com.dbSpring.generator;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    public static String generateText(int length, Random r) {
        String s = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return s;
    }

    public static String generatePhone(Random r) {
        return String.format("+7(9%d%d)%d%d%d-%d%d-%d%d", r.nextInt(10),
                r.nextInt(10), r.nextInt(10), r.nextInt(10),
                r.nextInt(10), r.nextInt(10), r.nextInt(10),
                r.nextInt(10), r.nextInt(10));
    }

    public static Time[] generateTime(Random r) {
        int time1 = r.nextInt(4 * 3600000) + 4 * 3600000;
        int time2 = time1 + 14 * 3600000;
        return new Time[]{new Time(time1), new Time(time2)};
    }

    public static BigDecimal generatePrice(Random r, int minPrice, int maxPrice) {
        String s = String.format("%.2f", r.nextFloat() + r.nextInt(maxPrice - minPrice) + minPrice).replace(',', '.');
        return new BigDecimal(s);
    }

    public static boolean generateAvailable(Random r) {
        boolean[] availableChance = {true, true, false, true, true, true, true, true, true, true};
        return availableChance[r.nextInt(availableChance.length)];
    }

    public static String generateSchedule(Random r) {
        Time[] time = generateTime(r);
        return (r.nextInt(2) + 3) + "/" + (r.nextInt(2) + 1) + ", " +
                "c " + time[0] + " до " + time[1];
    }
}
