package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void printIpAddress(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement pre = webDriver.findElement(By.tagName("pre"));

            String jsonText = pre.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonText);

            String ip = (String) obj.get("ip");
            System.out.println("IPv4-адрес клиента: " + ip);
        } catch (Exception e) {
            System.out.println("Ошибка в задании №2");
            System.out.println(e);
        } finally {
            webDriver.quit();
        }
    }
}
