package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    public static void printWeatherForecast(String driverPath) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();

        StringBuilder table = new StringBuilder();
        table.append(String.format("%-3s | %-16s | %-11s | %-11s%n", "№", "Дата/время", "Температура", "Осадки (мм)"));
        table.append("----+------------------+-------------+------------\n");

        try {
            webDriver.get(url);
            WebElement pre = webDriver.findElement(By.tagName("pre"));

            String jsonText = pre.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonText);
            JSONObject hourly = (JSONObject) obj.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            for (int i = 0; i < time.size(); i++) {
                table.append(String.format(
                        "%-3d | %-16s | %-11s | %-11s%n",
                        i + 1,
                        String.valueOf(time.get(i)),
                        String.valueOf(temperature.get(i)),
                        String.valueOf(rain.get(i))
                ));
            }

            System.out.println("Прогноз погоды на сутки (Нижний Новгород, 56, 44):");
            System.out.println(table);

            Path resultDir = Path.of("result");
            Files.createDirectories(resultDir);
            try (FileWriter writer = new FileWriter(resultDir.resolve("forecast.txt").toFile(), StandardCharsets.UTF_8)) {
                writer.write(table.toString());
            }
        } catch (Exception e) {
            System.out.println("Ошибка в задании №3");
            System.out.println(e);
        } finally {
            webDriver.quit();
        }
    }
}
