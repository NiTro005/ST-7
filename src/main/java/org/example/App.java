package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        String driverPath = System.getenv("CHROMEDRIVER_PATH");
        if (driverPath == null || driverPath.isBlank()) {
            driverPath = "./chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebElement passwordBox = webDriver.findElement(By.id("generated_password"));
            String password = passwordBox.getAttribute("value");
            System.out.println("Сгенерированный пароль: " + password);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        } finally {
            webDriver.quit();
        }

        Task2.printIpAddress(driverPath);
        Task3.printWeatherForecast(driverPath);
    }
}
