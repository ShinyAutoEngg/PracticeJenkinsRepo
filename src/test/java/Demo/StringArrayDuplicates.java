package Demo;
import java.util.*;
public class StringArrayDuplicates 
{
	public static void main(String[] args) 
	{
		String[] str= {"flight","fly","flyover"};
		StringBuffer sbr = new StringBuffer();
		for(String s: str)
		{
			sbr.append(s);
		}
		System.out.println(sbr);
		
		HashMap<Character, Integer> hmap = new HashMap<>();
		for(int i=0;i<sbr.length();i++)
		{
			
		if(hmap.containsKey(sbr.charAt(i)))
		{
			hmap.put(sbr.charAt(i), hmap.get(sbr.charAt(i))+1);
		}
		
		else
		{
			hmap.put(sbr.charAt(i), 1);
		}
		
		}
		System.out.println(hmap);
		System.out.println();
		for(Map.Entry<Character, Integer> data:hmap.entrySet())
		{
			if(data.getValue()>1)
			{
				System.out.print(data.getKey());
			}
		}
		
	}
}
