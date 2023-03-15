package dev.thiago;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class TestGpt {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre o prompt para o ChatGPT: ");
        String seachString = sc.nextLine();

        String input = """
                    {
                       "model" : "text-davinci-001",
                       "prompt": "%s",
                       "temperature" : 1,
                       "max-tokens" : 100
                    }
                """.formatted(seachString);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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")
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
