package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    @DisplayName("1 + 2는 3이다")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(a + b, sum);

    }

    @DisplayName("실패하는 케이스) 1+2는 4다.")
    @Test
    public void junitFailTest() {
        int a = 1;
        int b = 2;
        int sum = 4;

        Assertions.assertEquals(a + b, sum);
    }

}
