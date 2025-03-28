package demo.example;

public sealed interface Shape permits Circle, Rectangle {
    default double area() {
        // @since jdk 16
        if (this instanceof Circle(double radius)) {
            return Math.PI * radius * radius;
        }
        if (this instanceof Rectangle(double width, double height)) {
            return width * height;
        }
        return 0;
    }
}

record Circle(double radius) implements Shape {
    public Circle {
        if (radius < 0) {
            throw new IllegalArgumentException("radius must be greater than zero");
        }
    }
}

record Rectangle(double width, double height) implements Shape {
    public Rectangle {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be greater than zero");
        }
    }
}