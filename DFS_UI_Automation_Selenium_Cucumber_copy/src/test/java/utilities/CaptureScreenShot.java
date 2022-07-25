package utilities;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;


public class CaptureScreenShot 
{
public  WebDriver driver;

	
	public CaptureScreenShot(WebDriver driver)
	{
		this.driver=driver;
	}
	public String getcurrentdateandtime(){
		String str = null;
		try{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		Date date = new Date();
		str= dateFormat.format(date);
		str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		}
		catch(Exception e){
		}
		return str;
		}
	
	public static String getCurrentDateandTime(){
		String str = null;
		try{
		DateFormat dateFormat = new SimpleDateFormat("d-MMM-YY HH-mm-ss");
		Date date = new Date();
		str= dateFormat.format(date);
		//str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		}
		catch(Exception e){
		}
		return str;
		}
	
	public void screenShotCapture() throws AWTException, IOException
	{
		Robot robot1=new Robot();
		
		Dimension screen_size=Toolkit.getDefaultToolkit().getScreenSize();
		
		Rectangle rect1=new Rectangle(screen_size);
		BufferedImage source=robot1.createScreenCapture(rect1);
		File destination=new File("./target/"+getcurrentdateandtime()+".png");
		ImageIO.write(source, "png", destination);
		
		
	}
	
	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest ="./target/"+getcurrentdateandtime()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}	
	
	public String getBase64Screenshot() throws IOException
	{
		String Base64StringOfScreenshot="";
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest ="./Screenshots/"+getcurrentdateandtime()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		
		byte[] fileContent=FileUtils.readFileToByteArray(src);
		Base64StringOfScreenshot="data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		return Base64StringOfScreenshot;
		
	}
	
	public byte[] getByteScreenshot() throws IOException
	{
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		final byte[] fileContent= FileUtils.readFileToByteArray(src);
		return fileContent;
				
	}

}
