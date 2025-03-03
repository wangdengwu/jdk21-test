package demo.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequencedCollectionDemoTest {
    @Test
    void testSequencedCollectionMethods() {
        // 创建一个 List
        List<String> list = new ArrayList<>(List.of("Apple", "Banana", "Cherry"));

        // 测试 getFirst 和 getLast
        assertEquals("Apple", list.getFirst());
        assertEquals("Cherry", list.getLast());

        // 测试 addFirst 和 addLast
        list.addFirst("Apricot");
        list.addLast("Date");
        assertEquals("Apricot", list.getFirst());
        assertEquals("Date", list.getLast());

        // 测试 reversed
        SequencedCollection<String> reversedList = list.reversed();
        assertEquals("Date", reversedList.getFirst());
        assertEquals("Apricot", reversedList.getLast());
    }
}
