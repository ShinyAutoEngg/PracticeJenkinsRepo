package Demo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;

import genericBaseCaseUtility.BaseClassUtility;

public class dummyScript extends BaseClassUtility
{
	@Test
	public void method1()
	{
		System.out.println("Main TEST SCRIPT 1");
	}
	
	
		@Test
		public void method2()
		{
			System.out.println("Main TEST SCRIPT 2");
		}
		
		@Test
		public void method3()
		{
			Assert.assertEquals(false, true);
			System.out.println("Main TEST SCRIPT 3");
		}
}
