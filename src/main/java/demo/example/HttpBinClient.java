package demo.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpBinClient {
    private static final HttpClient client = HttpClient
            .newBuilder()
            .executor(Executors.newFixedThreadPool(10, new HttpClientThreadFactory()))
            .connectTimeout(Duration.ofSeconds(10)).build();

    public int delay(int second) {
        var req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(STR."http://127.0.0.1:8080/delay/\{second}"))
                .build();
        try {
            return client.send(req, HttpResponse.BodyHandlers.ofString()).statusCode();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class HttpClientThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, STR."HttpClient-\{threadNumber.getAndIncrement()}");
        }
    }
}
