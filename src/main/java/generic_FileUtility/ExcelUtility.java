package generic_FileUtility;

import java.util.Arrays;
import java.util.OptionalInt;

import org.testng.annotations.Test;

public class ExcelUtility 
{
	@Test
	public void getDataFromExcel()
	{
		int[] arr= {1,4,2,6,1};
		int max=Arrays.stream(arr).max().getAsInt();
		System.out.println(max);

	}
}
