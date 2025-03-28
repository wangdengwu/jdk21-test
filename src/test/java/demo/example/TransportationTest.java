package demo.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransportationTest {

    @Test
    public void testTrainCreation() {
        var train = new Train(TrainType.SLOW, 50);
        assertEquals(TrainType.SLOW, train.type());
        assertEquals(50, train.speed());
    }

    @Test
    public void testBikeCreation() {
        var bike = new Bike(20);
        assertEquals(20, bike.speed());
    }

    @Test
    public void testShiftCreateWithSlowTrain() {
        var train = new Train(TrainType.SLOW, 50);
        Shift shift = Shift.create(train);
        assertInstanceOf(SlowShift.class, shift);
        assertEquals("move slow with 50km/h speed", shift.move());
    }

    @Test
    public void testShiftCreateWithFastTrain() {
        var train = new Train(TrainType.FAST, 150);
        Shift shift = Shift.create(train);
        assertInstanceOf(FastShift.class, shift);
        assertEquals("move fast with 150km/h speed", shift.move());
    }

    @Test
    public void testShiftCreateWithBulletTrain() {
        var train = new Train(TrainType.BULLET, 300);
        Shift shift = Shift.create(train);
        assertInstanceOf(FastShift.class, shift);
        assertEquals("move fast with 300km/h speed", shift.move());
    }

    @Test
    public void testShiftCreateWithBike() {
        var bike = new Bike(20);
        Shift shift = Shift.create(bike);
        assertInstanceOf(SlowShift.class, shift);
        assertEquals("move slow with 20km/h speed", shift.move());
    }

    @Test
    public void testShiftCreateWithNullTrainType() {
        var train = new Train(null, 50);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Shift.create(train));
        assertEquals("Train type is null", exception.getMessage());
    }

}
