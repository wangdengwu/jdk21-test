package demo.example;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class VirtualThreadComparisonTest {
    private static final HttpBinClient httpBinClient = new HttpBinClient();
    private static final int taskCount = 1000;

    @Test
    void testThreadPool() throws InterruptedException {
        JvmMonitor.monit(JvmMonitor.MonitorType.ALL);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        Instant start = Instant.now();
        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                var code = httpBinClient.delay(1);
                if (code != 200) {
                    System.out.println(STR."code is \{code}");
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Thread used Time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
    }

    @Test
    void testVirtualThread() throws InterruptedException {
        JvmMonitor.monit(JvmMonitor.MonitorType.ALL);
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Instant start = Instant.now();
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    var code = httpBinClient.delay(1);
                    if (code != 200) {
                        System.out.println(STR."code is \{code}");
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("VirtualThread used Time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
        }
    }

    @Test
    void testPrint() throws InterruptedException {
        JvmMonitor.monit(JvmMonitor.MonitorType.THREAD);
        Thread.sleep(1000 * 60 * 2);
    }
}
