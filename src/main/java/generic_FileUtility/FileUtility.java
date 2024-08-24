package generic_FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{
	public String getDataFromPropertiesFile(String key) throws IOException
	{
	FileInputStream fis = new FileInputStream("./src/test/resources/configEndData.properties");
	Properties prop = new Properties();
	prop.load(fis);
	String DATA =prop.getProperty(key);
	return DATA;
	}
}
