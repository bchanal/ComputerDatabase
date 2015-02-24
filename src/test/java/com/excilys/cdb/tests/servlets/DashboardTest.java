package com.excilys.cdb.tests.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardTest {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() {
		// On instancie notre driver, et on configure notre temps d'attente

		String line;
		try {
			Process p = Runtime.getRuntime().exec("./src/test/resources/sql/script.sh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/computer-database/dashboard";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// On se connecte au site
		driver.get(baseUrl);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testSearchByName() {
		WebElement resultTab = driver.findElement(By.id("results"));
		List<WebElement> results = resultTab.findElements(By.tagName("tr"));
		WebElement searchbox = driver.findElement(By.id("searchbox"));
		WebElement searchsubmit = driver.findElement(By.id("searchsubmit"));
		Assert.assertEquals(100, results.size());

		searchbox.clear();
		searchbox.sendKeys("test");
		searchsubmit.click();
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));

		Assert.assertEquals(5, results.size());
	}

	@Test
	public void testPagination() {
		WebElement resultTab = driver.findElement(By.id("results"));
		List<WebElement> results = resultTab.findElements(By.tagName("tr"));

		WebElement paginationBtn10 = driver.findElement(By.linkText("10"));
		Assert.assertEquals(100, results.size());
		paginationBtn10.click();
		Assert.assertEquals(10, results.size());

		WebElement paginationBtn50 = driver.findElement(By.linkText("50"));
		paginationBtn50.click();
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));
		Assert.assertEquals(50, results.size());

		WebElement paginationBtn100 = driver.findElement(By.linkText("100"));
		paginationBtn100.click();
		resultTab = driver.findElement(By.id("results"));
		results = resultTab.findElements(By.tagName("tr"));
		Assert.assertEquals(100, results.size());
	}
}
