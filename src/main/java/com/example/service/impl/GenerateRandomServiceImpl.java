package com.example.service.impl;

import com.example.service.GenerateRandomService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GenerateRandomServiceImpl implements GenerateRandomService {
    @Override
    public String getGenerateNumbers(int r, StringBuilder n) {
        while (n.toString().length() < 4) {
            int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 10);
            int i = 0;
            while (i < r) {
                if (!n.toString().contains(String.valueOf(boundedRandomValue))) {
                    n.append(boundedRandomValue);
                    break;
                }
                i++;
            }
        }
        return n.toString();
    }

    @Override
    public int[] getGenerateNumbersNotUse(int start, int end, int limit) {
        return ThreadLocalRandom.current().ints(start, end).distinct().limit(limit).toArray();
    }

    @Override
    public String getGenerateNumberThenZero(String zeroNum) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.toString().length() < zeroNum.length()) {
            int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 10);
            if (!zeroNum.contains(String.valueOf(boundedRandomValue))) {
                stringBuilder.append(boundedRandomValue);
            }
        }
        return stringBuilder.toString();
    }
}
