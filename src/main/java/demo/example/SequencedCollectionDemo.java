package demo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class SequencedCollectionDemo {
    public static void main(String[] args) {
        // 创建一个 List 并演示 SequencedCollection 方法
        List<String> list = new ArrayList<>(List.of("Apple", "Banana", "Cherry"));
        System.out.println("Original List: " + list);

        // 使用 SequencedCollection 方法
        System.out.println("First element: " + list.getFirst()); // JDK 21 新方法
        System.out.println("Last element: " + list.getLast());   // JDK 21 新方法

        list.addFirst("Apricot"); // JDK 21 新方法
        list.addLast("Date");     // JDK 21 新方法
        System.out.println("Updated List: " + list);

        // 反转列表
        SequencedCollection<String> reversedList = list.reversed();
        System.out.println("Reversed List: " + reversedList);
    }
}
