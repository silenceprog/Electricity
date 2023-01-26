package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Schedules schedule = new Schedules();

        UnaryOperator<String> parser = url -> {
            try {
                Document document = Jsoup.connect(url)
                        .get();
                Element body = document.body();
                schedule.setDate(body.select("span").text());
                schedule.setInfo(body.select("h5").text());
                schedule.setfSchedule(body.select("label.text-danger").text());
                schedule.setsSchedule(body.select("label.text-warning").text());
                return gson.toJson(schedule);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

    }
}





