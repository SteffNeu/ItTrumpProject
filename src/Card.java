import java.util.HashMap;
import java.util.Map;

/**
 * class that makes a card object
 * @author Stephanie
 *
 */
//public class Card 
//{
//	private String name;
//	private String[] categoryNames;
//	private int[] categoryValues;
//	
//	public Card()
//	{
//		
//	}
//	
//	public Card(String n, String[] categories, int[] values)
//	{
//		name = n;
//		categoryNames = categories;
//		categoryValues = values;
//	}
//	
//	public int getValueAtCategory(String ctgry)
//	{
//		for(int i = 0; i<categoryValues.length; i++)
//		{
//			if(categoryNames.equals(ctgry))
//					return categoryValues[i];
//		}
//		return 0;
//	}
//	
//	public int getHighestCategory()
//	{
//		return 0;
//	}
//	
//	public String getName() 
//	{
//		return name;
//	}
//
//}
public class Card 
{
	private String name;
	private HashMap<String, Integer> categories;
	/**
	 * default constructor
	 */
	public Card()
	{
		
	}
	/** 
	 * constructor
	 * @param n containing name of card
	 * @param categoryNames containing string array of category names
	 * @param values containing integer array of category values
	 */
	public Card(String n, String[] categoryNames, int[] values)
	{
		name = n;
		categories = new HashMap<String, Integer>();
		for(int i = 0; i < categoryNames.length; i++)
		{
			categories.put(categoryNames[i], values[i]);
		}
	}
	
	public int getValueAtCategory(String ctgry)
	{
		return categories.get(ctgry);
	}
	
	public String getHighestCategory()
	{
		int tempMax = 0;
		String tempKey = "";
		for (Map.Entry<String, Integer> entry : categories.entrySet())
		{
			if(entry.getValue() > tempMax)
			{
				tempMax = entry.getValue();
				tempKey = entry.getKey();
			}
		}
		return tempKey;
	}
	/**
	 * get method for name instant variable
	 * @return string containing name of card
	 */
	public String getName() 
	{
		return name;
	}
	
	public HashMap<String, Integer> getCategories()
	{
		return categories;
	}

//	TODO delete tests
//	public static void main (String[] args)
//	{
//		String[] cats = {"Health", "Sexiness", "Age", "strength"};
//		int[] vals = {2, 6, 2, 9};
//		
//		String nm = "Chaos";
//		
//		Card trie = new Card(nm, cats, vals);
//		
//		System.out.println(trie.getName());
//		System.out.println(trie.getHighestCategory());
//		System.out.println(trie.getValueAtCategory("Sexiness"));
//		System.out.println("Bye suckers.");
//	}
	
}