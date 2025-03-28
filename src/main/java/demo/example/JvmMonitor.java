package demo.example;

import java.lang.management.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JvmMonitor {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void monit(MonitorType type) {
        scheduler.scheduleAtFixedRate(() -> JvmMonitor.printSystemInfo(type), 0, 2, TimeUnit.SECONDS);
    }

    private static void printSystemInfo(MonitorType type) {
        switch (type) {
            case ALL -> {
                printMemoryInfo();
                printThreadInfo();
            }
            case MEMORY -> printMemoryInfo();
            case THREAD -> printThreadInfo();
        }
        printSeparator();
    }

    private static void printMemoryInfo() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        System.out.println("=== Memory Usage ===");

        // 堆内存
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        System.out.printf("Heap: %s / %s (Max: %s)%n",
                formatBytes(heapUsage.getUsed()),
                formatBytes(heapUsage.getCommitted()),
                formatBytes(heapUsage.getMax()));

        // 各内存池详情
        ManagementFactory.getMemoryPoolMXBeans().forEach(pool -> {
            MemoryUsage usage = pool.getUsage();
            System.out.printf("Pool [%s]: %s (Peak: %s)%n",
                    pool.getName(),
                    formatBytes(usage.getUsed()),
                    formatBytes(pool.getPeakUsage().getUsed()));
        });
    }

    private static void printThreadInfo() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        System.out.println("\n=== Threads ===");
        System.out.printf("Live: %d | Peak: %d | Daemon: %d%n",
                threadBean.getThreadCount(),
                threadBean.getPeakThreadCount(),
                threadBean.getDaemonThreadCount());
        // 统计线程状态
        long[] threadIds = threadBean.getAllThreadIds();
        System.out.println("\nThread States:");
        for (long id : threadIds) {
            ThreadInfo info = threadBean.getThreadInfo(id);
            if (info != null) {
                System.out.printf("- %s (%s): %s%n",
                        info.getThreadName(),
                        info.getThreadId(),
                        info.getThreadState());
            }
        }
        // dump线程信息
//        try {
//            Class<?> threadContainers = Class.forName("jdk.internal.vm.ThreadDumper");
//            Method dumpThreadsToJson = threadContainers.getMethod("dumpThreadsToJson", String.class, boolean.class);
//            dumpThreadsToJson.invoke(null, "threads.json", true);
//        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
//                 InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
    }

    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int unit = (int) (Math.log(bytes) / Math.log(1024));
        return String.format("%.1f %sB",
                bytes / Math.pow(1024, unit),
                "KMGTPE".charAt(unit - 1));
    }

    private static void printSeparator() {
        System.out.println("\n" + "=".repeat(60) + "\n");
    }

    public enum MonitorType {
        ALL,
        MEMORY,
        THREAD
    }
}
