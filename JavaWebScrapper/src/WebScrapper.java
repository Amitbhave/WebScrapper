import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebScrapper {

	public static final String NAME_TO_FIND = "SANKET GARG";
	public WebDriver driver = new ChromeDriver();

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Path to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		int start = 26200000;
		int end = 26251525;
		WebScrapper webScrapper = new WebScrapper();
		webScrapper.openSite();
		for(int i = start; i< end; i++){
			webScrapper.submit(""+i);
			boolean flag = webScrapper.getText(i);
			if(flag){
				break;
			}
		}
	}

	/**
	 * Open the test website.
	 */
	public void openSite() {
		driver.navigate().to("http://resultsarchives.nic.in/cbseresults/cbseresults2010/aieee/cbseaieee.htm");
	}

	/**
	 * Fills the roll number and hits submit button
	 * @param username
	 */
	public void submit(String username) {

		CharSequence[] cs = new String[]{username};
		WebElement rollNumber_editbox = driver.findElement(By.name("regno"));
		WebElement submit_button = driver.findElement(By.xpath("//input[@value='Submit']"));

		//fill roll number
		rollNumber_editbox.sendKeys(cs);
		//click submit
		submit_button.click();

	}

	/**
	 * Gets the First name tag and returns true if the name is required name.
	 * @param id
	 * @return isNameFound
	 * @throws IOException
	 */
	public boolean getText(int id) throws IOException {
		String text = driver.findElement(By.xpath("(//td/font/b)[2]")).getText();
		if(text.contains(NAME_TO_FIND)){
			//Match found
			System.out.println("Name is: "+text);
			System.out.println("ID is: "+id);
			return true;
		}
		//this is not the required roll number so go back and refresh the page.
		driver.navigate().back();
		driver.navigate().refresh();
		return false;
	}


}
