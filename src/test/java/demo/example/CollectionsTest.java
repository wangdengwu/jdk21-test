package demo.example;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionsTest {
    // ==================== JDK9+ 集合工厂方法 ====================
    @Test
    @SuppressWarnings("all")
    void testImmutableFactories() {
        // JDK8需要手动创建不可变集合，JDK9+支持工厂方法
        List<String> jdk8List = Collections.unmodifiableList(Arrays.asList("a", "b"));
        List<String> jdk9List = List.of("a", "b");  // 更简洁

        Set<Integer> jdk9Set = Set.of(1, 2, 3);
        Map<String, Integer> jdk9Map = Map.of("Java", 8, "JDK21", 21);

        assertThrows(UnsupportedOperationException.class, () -> jdk9List.add("c")); // 不可修改
        assertEquals(2, jdk9List.size());

        List<String> modifiedList = new ArrayList<>(jdk9List);
        modifiedList.add("c");
        assertEquals(3, modifiedList.size());
    }

    // ==================== Stream API增强 ====================
    @Test
    @SuppressWarnings("all")
    void testStreamEnhancements() {
        // 1. JDK16+: Stream.toList() 替代 collect(Collectors.toList())
        List<String> list = Stream.of("a", "b", "c").toList();
        assertEquals(List.of("a", "b", "c"), list);

        // 2. JDK10+: toUnmodifiableList（不可变集合）
        List<String> unmodifiableList = Stream.of("x", "y")
                .collect(Collectors.toUnmodifiableList());
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.add("z"));

        // 3. JDK12+: Collectors.teeing() 合并两个收集器结果
        String result = Stream.of(1, 2, 3)
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),   // 第一个收集器：求和
                        Collectors.counting(),                     // 第二个收集器：计数
                        (sum, count) -> STR."Sum=\{sum}, Avg=\{sum / count}"
                ));
        assertEquals("Sum=6, Avg=2", result);
    }

    // ==================== JDK21 Sequenced Collections (JEP 431) ====================
    @Test
    void testSequencedCollections() {
        // 1. SequencedSet（有序集合）
        SequencedSet<String> set = new LinkedHashSet<>(List.of("a", "b", "c"));
        assertEquals("a", set.getFirst());     // JDK21新增
        assertEquals("c", set.getLast());
        set.addFirst("0");                     // 头部插入
        assertEquals(List.of("0", "a", "b", "c"), List.copyOf(set));

        // 2. SequencedMap（有序Map）
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("Java", 8);
        map.put("JDK21", 21);
        assertEquals("Java", map.firstEntry().getKey());
        map.putFirst("LTS", 17);               // 插入到头部
        assertEquals("LTS", map.firstEntry().getKey());
    }

    // ==================== JDK9+: Optional.stream() ====================
    @Test
    void testOptionalStream() {
        // 将Optional转为Stream（JDK9+）
        List<String> list = Stream.of(Optional.of("Java"), Optional.<String>empty())
                .flatMap(Optional::stream)     // 自动过滤空Optional
                .toList();
        assertEquals(List.of("Java"), list);
    }

    @Test
    @SuppressWarnings("all")
    void testNPE() {
        String n = null;
        n.isBlank();
    }
}
