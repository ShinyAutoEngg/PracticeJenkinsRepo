package genericListenerUtility;

import java.time.LocalDate;
import java.util.Date;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener,ISuiteListener
{
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) 
	{ 
		LocalDate ld = LocalDate.now();
		spark=new ExtentSparkReporter("./Advanced/report.html");
		spark.config().setDocumentTitle("Document Title");
		spark.config().setReportName("Report Name");
		spark.config().setTheme(Theme.DARK);
		System.out.println("Listener ===>On Start Suite Level");

		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "WINDOWS");
		reports.setSystemInfo("BROWSER", "CHROME");
				
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		
		String testName=result.getMethod().getMethodName();
		test=reports.createTest(testName);
		test.log(Status.INFO, "<==TestStart==>"+testName+"");
		System.out.println("Listener ===>On Test Start Test Level");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		//TakesScreenshot ts = (TakesScreenshot)driver;
		String testName=result.getMethod().getMethodName();
		
		test.log(Status.FAIL, "<==TEST FAILURE==>"+testName+"");
		System.out.println("Listener ===>On Test Failure Test Level");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		String testName=result.getMethod().getMethodName();
	
		test.log(Status.PASS, "<==TestSUCCESS==>"+testName+"");
		System.out.println("Listener ===>On Test Success Test Level");
	}
	@Override
	public void onFinish(ISuite suite)
	{	
		reports.flush();
		test.log(Status.INFO, "<==SUITE FINISH==>");
		System.out.println("Listener ===>On Finish Suite Level");
	}

//	@Override
//	public void onStart(ITestContext context) 
//	{
//		
//		test.log(Status.INFO, "<==ON START ITESTCONTEXT==>");
//		System.out.println("Listener ===>On Start Test Level");
//	}
//
//	@Override
//	public void onFinish(ITestContext context) 
//	{
//		test.log(Status.INFO, "<==ON FINISH ITESTCONTEXT==>");
//		System.out.println("Listener ===>On Finish Test Level");
//	}
	

}
