package de.spinscale.elasticsearch;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyRestHandlerIT {

    @Test
    public void testPluginEndpoint() throws Exception {
        final Path dockerFilePath =
                Paths.get(System.getenv("PWD"), "Dockerfile");
        final ImageFromDockerfile image =
                new ImageFromDockerfile().withDockerfile(dockerFilePath);

        try (GenericContainer container = new GenericContainer(image)) {
            container.addEnv("discovery.type", "single-node");
            container.addExposedPorts(9200);
            container.start();

            String endpoint = String.format(
                    "http://localhost:%s/my-rest-handler",
                    container.getMappedPort(9200)
            );

            HttpRequest request =  HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(endpoint))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    httpClient.send(request, BodyHandlers.ofString());

            assertEquals(response.body(), "my-rest-handler");
            assertEquals(response.statusCode(), 200);
        }
    }
}
