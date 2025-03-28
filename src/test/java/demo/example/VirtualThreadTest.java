package demo.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertThrows;

class VirtualThreadTest {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static final ExecutorService noNameExecutor = Executors.newVirtualThreadPerTaskExecutor();
    private static final ExecutorService nameExecutor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
            .name("executor-with-name", 1)
            .factory());

    @Test
    void testUse() {
        Thread.startVirtualThread(new PrintNameThread());
        Thread.ofVirtual().start(new PrintNameThread());

        var of = Thread.ofVirtual().name("start-with-name", 1);
        of.start(new PrintNameThread());
        of.start(new PrintNameThread());

        noNameExecutor.execute(new PrintNameThread());

        nameExecutor.execute(new PrintNameThread());
        nameExecutor.execute(new PrintNameThread());

    }

    @Test
    void testException() {
        Thread.startVirtualThread(new ThrowExceptionThread("模拟异常1"));
        Thread.ofVirtual().start(new ThrowExceptionThread("模拟异常2"));
        noNameExecutor.execute(new ThrowExceptionThread("模拟异常3"));
        nameExecutor.execute(new ThrowExceptionThread("模拟异常4"));
        assertThrows(ExecutionException.class, () ->
                nameExecutor.submit(() -> {
                    throw new RuntimeException("模拟异常5");
                }).get()
        );
    }

    @Test
    void testVirtualPool() throws InterruptedException {
        JvmMonitor.monit(JvmMonitor.MonitorType.ALL);
        for (int i = 0; i < 100; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(1000 * 6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println(ANSI_YELLOW + "start all done" + ANSI_RESET);
        Thread.sleep(1000 * 60);
    }

    @Test
    void testThreadPool() throws InterruptedException {
        JvmMonitor.monit(JvmMonitor.MonitorType.ALL);
        var pool = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(1000 * 6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println(ANSI_YELLOW + "start all done" + ANSI_RESET);
        Thread.sleep(1000 * 60);
    }

}

class PrintNameThread implements Runnable {
    @Override
    public void run() {
        var name = Thread.currentThread().getName();
        if (name != null && !name.isBlank()) {
            System.out.println(Thread.currentThread().getName());
        } else {
            System.out.println("no name");
        }
    }
}

class ThrowExceptionThread implements Runnable {
    private final String msg;

    ThrowExceptionThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        throw new RuntimeException(msg);
    }
}