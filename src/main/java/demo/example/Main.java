package demo.example;

public class Main {
    public static void main(String[] args) {
        // 测试 JDK 21 的新特性
        System.out.println("Hello, JDK 21!");

        // 例如，测试虚拟线程（Virtual Threads）
        Thread.startVirtualThread(() -> System.out.println("Running in a virtual thread!"));
    }
}
