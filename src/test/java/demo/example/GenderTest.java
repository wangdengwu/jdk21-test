package demo.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenderTest {
    @Test
    void testSpeak() {
        Gender male = new Male();
        assertEquals("I am a male", male.speak());
        male = new Female();
        assertEquals("I am a female", male.speak());
    }

//    @Test
//    void testLadyBoy() {
//        assertEquals("I am a lady-boy", new LadyBoy().speak());
//    }
}

//class LadyBoy implements Gender {
//
//    @Override
//    public String speak() {
//        return "I am a lady-boy";
//    }
//}