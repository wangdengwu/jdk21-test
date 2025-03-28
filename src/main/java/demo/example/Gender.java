package demo.example;

// @since jdk 16
public sealed interface Gender permits Male, Female {
    String speak();
}

final class Male implements Gender {
    public String speak() {
        return "I am a male";
    }
}

final class Female implements Gender {
    public String speak() {
        return "I am a female";
    }
}
