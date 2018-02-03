package game;

import java.util.HashMap;
import java.util.Map;

/**
 * class that makes a card object
 * @author Stephanie Neubauer 
 */
public class Card 
{
	/**name of the card*/
	private String name;
	/** contains category labels and values */
	private HashMap<String, Integer> categories;
	/**
	 * default constructor
	 */
	public Card()
	{
		categories = new HashMap<String, Integer>();
		name = "";
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
	/**
	 * gets value at a specified category 
	 * @param ctgry String specifying which category
	 * @return integer of value at the category
	 */
	public int getValueAtCategory(String ctgry)
	{
		return categories.get(ctgry);
	}
	/**
	 * searches category with the highest value
	 * @return String containing name of category with highest value
	 */
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
	/**
	 * gets Map of categories and their respective values
	 * @return
	 */
	public HashMap<String, Integer> getCategories()
	{
		return categories;
	}
	/** 
	 * checks if card has a category of the name 
	 * @param categoryToCheck
	 * @return boolean, true = card has category in its map
	 */
	public boolean hasCategory(String categoryToCheck)
	{
		return categories.containsKey(categoryToCheck);
	}
}