package demo.example;

public sealed interface Transportation permits Train, Bike {
}

record Train(TrainType type, Integer speed) implements Transportation {
}

record Bike(Integer speed) implements Transportation {
}

enum TrainType {
    SLOW,
    FAST,
    BULLET
}

sealed interface Shift permits FastShift, SlowShift {
    String move();

    static Shift create(Transportation transportation) {
        return switch (transportation) {
            case Train train -> switch (train.type()) {
                case null -> throw new IllegalArgumentException("Train type is null");
                case SLOW -> new SlowShift(train.speed());
                case FAST, BULLET -> new FastShift(train.speed());
            };
            case Bike bike -> new SlowShift(bike.speed());
        };
    }
}

final class FastShift implements Shift {
    private final Integer speed;

    public FastShift(Integer speed) {
        this.speed = speed;
    }

    @SuppressWarnings("all")
    public String move() {
        return STR."move fast with \{speed}km/h speed";
    }
}

final class SlowShift implements Shift {
    private final Integer speed;

    public SlowShift(Integer speed) {
        this.speed = speed;
    }

    @SuppressWarnings("all")
    public String move() {
        return STR."move slow with \{speed}km/h speed";
    }
}