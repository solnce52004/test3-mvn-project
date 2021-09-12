package dev.example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class AppSayHelloTest {

    @Test
    public void testSayHello() {
        assertThat(App.sayHello(), containsString("Hello"));
    }
}