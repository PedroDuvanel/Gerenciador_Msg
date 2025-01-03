package com.msgsender.msg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class WhatsAppAutomation {
    public static void main(String[] args) {
        // Caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        // Configuração do Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Pedro/AppData/Local/Google/Chrome/User Data");
        options.addArguments("profile-directory=Default"); // Usando o perfil "Default" (ajuste se necessário)

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Abrir o WhatsApp Web
        driver.get("https://web.whatsapp.com");

        // Esperar até o campo de pesquisa aparecer
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30 segundos de espera
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@contenteditable='true']")));

        System.out.println("Campo de pesquisa encontrado.");

        // Localizar o campo de pesquisa de chats
        searchBox.click();
        searchBox.sendKeys("Cj"); // Substitua pelo nome do contato que deseja

        // Esperar o chat aparecer e clicar nele
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title, 'Cj')]")));
        
        // Rola até o contato para garantir que ele esteja visível
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chat);
        
        // Usando Actions para clicar
        Actions actions = new Actions(driver);
        actions.moveToElement(chat).click().perform();

        System.out.println("Chat selecionado.");

        // Esperar o campo de mensagem aparecer e ficar visível
        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@contenteditable='true' and @data-tab='1']")));
        
        // Rola a tela até o campo de mensagem
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", messageBox);
        
        // Aguarda o campo de mensagem ser interativo
        wait.until(ExpectedConditions.elementToBeClickable(messageBox));

        // Verifique se o campo de mensagem é interativo
        messageBox.click();  // Clica no campo de mensagem para garantir que ele tenha o foco
        System.out.println("Campo de mensagem focado.");

        // Digitar a mensagem
        messageBox.sendKeys("Olá, esta é uma mensagem automática!");

        // Verifique se o texto foi digitado corretamente
        System.out.println("Texto digitado: " + messageBox.getText());

        // Esperar um momento para garantir que a mensagem foi digitada
        try {
            Thread.sleep(1000);  // Espera 1 segundo para garantir que o texto foi digitado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enviar a mensagem pressionando ENTER (simulando o pressionamento de Enter)
        Actions sendAction = new Actions(driver);
        sendAction.moveToElement(messageBox).sendKeys("\n").perform();  // Simula pressionar Enter

        System.out.println("Mensagem enviada.");

        // Fechar o navegador
        driver.quit();
    }
}



