package com.msgsender.msg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

public class WhatsAppAutomation {
    public static void main(String[] args) {
        // Caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "CC:\\Users\\Pedro Duvanel\\Downloads\\chromedriver-win64");

        // Configuração do Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/caminho/para/dados-de-usuario");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Abrir o WhatsApp Web
        driver.get("https://web.whatsapp.com");

        // Esperar que o WhatsApp Web carregue e o usuário faça login manualmente
        try {
            Thread.sleep(20000); // Espera 20 segundos para que o usuário escaneie o QR code
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Localizar o campo de pesquisa de chats
        WebElement searchBox = driver.findElement(By.xpath("//div[@contenteditable='true']"));
        searchBox.click();
        searchBox.sendKeys("Nome do contato");

        // Esperar o chat aparecer e clicar nele
        WebElement chat = driver.findElement(By.xpath("//span[@title='Nome do contato']"));
        chat.click();

        // Localizar o campo de mensagem e enviar
        WebElement messageBox = driver.findElement(By.xpath("//div[@contenteditable='true'][@data-tab='1']"));
        messageBox.sendKeys("Olá, esta é uma mensagem automática!");

        // Enviar a mensagem pressionando ENTER
        messageBox.submit();
        
        // Fechar o navegador
        driver.quit();
    }
}
