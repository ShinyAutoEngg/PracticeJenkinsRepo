package generic_WebDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class webDriverUtility 
{
	public WebDriver driver=null;
	
	
	public void implicitlyWait(WebDriver driver)
	{
	   	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
	}
	
	public void waitForElementVisibility(WebDriver driver, WebElement element, int timeInSeconds)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForAlertToBePresent(WebDriver driver,int timeInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForVisibilityOfAllElements(WebDriver driver, List<WebElement> list, int timeInSeconds)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}
	
	/*
	 * ACTIONS CLASS - MOUSE HOVER AND KEYBOARD FUNCTIONS
	 */
	public void moveToElement(WebDriver driver, WebElement ele)
	{
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}
	
	public void clickAnElement(WebDriver driver,WebElement ele)
	{
		Actions act= new Actions(driver);
		act.click().perform();
	}
	
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(src, dest).perform();
	}
	
	public void doubleClickAnElement(WebDriver driver, WebElement ele)
	{
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}
	
	public void rightClickOnAnElement(WebDriver driver, WebElement ele)
	{
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
	}
	
	/*
	 * SELECT CLASS - SPECIFIC TO DROP DOWN | TAGNAME SHOULD BE SELECT IN THE DOM
	 */
	public void selectDDByIndex(WebDriver driver, WebElement ele, int index)
	{
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
		
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}
	
	public void selectDDByVisibleText(WebDriver driver, WebElement ele, String text)
	{
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
		
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}
	
	
	/*
	 * FRAMES 
	 */
	public void selectFrameByID(WebDriver driver, int id)
	{
		driver.switchTo().frame(id);
	}
	
	public void selectFrameByElement(WebDriver driver, WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	
	public void selectFrameByI(WebDriver driver, String frameName)
	{
		driver.switchTo().frame(frameName);
	}
	
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void switchToDefaultContent(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	
	/*
	 * WINDOW HANDLES
	 */
	
	public void switchToWindowByTitle(WebDriver driver, String expTitle)
	{
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			String title=driver.getTitle();
			if(title.contains(expTitle))
			{
				break;
			}
		}
	}
	
	public void switchToWindowByURL(WebDriver driver, String expURL)
	{
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			String URL=driver.getCurrentUrl();
			if(URL.contains(expURL))
			{
				break;
			}
		}
	}
	
	
}

