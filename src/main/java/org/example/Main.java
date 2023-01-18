package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<String> parser = url -> {
            try {
                Document document = Jsoup.connect(url)
                        .get();
                return document.body().text();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

    }
}