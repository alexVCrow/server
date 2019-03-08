package com.example.service;

public interface GenerateRandomService {
    String getGenerateNumbers(int r, StringBuilder n);
    int[] getGenerateNumbersNotUse(int start, int end, int limit);
    String getGenerateNumberThenZero(String zeroNum);
}
