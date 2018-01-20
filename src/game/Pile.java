package game; 

import java.util.*;

/**
 * contains a pile object
 * @author Stephanie
 *
 */
public class Pile 
{
	private ArrayList<Card> cards;
	private int numOfCards;
	/** 
	 * default constructor
	 */
	public Pile() {
		
	}
	/**
	 * constructor
	 * @param startCards contains a set of cards for the pile
	 */
	public Pile(ArrayList<Card> startCards)
	{
		cards = startCards;
		numOfCards = startCards.size();
	}
	/**
	 * gets number of cards
	 * @return integer, number of cards in pile
	 */
	public int getNumOfCards()
	{
		return numOfCards;
	}
	/**
	 * gets all cards
	 * @return ArrayList of all cards
	 */
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	/**
	 * gets upper card of the pile
	 * @return
	 */
	public Card getTopCard()
	{
		return cards.get(0);
	}
	/** 
	 * adds pile of cards at the end of list
	 * @param newPile Pile to be added
	 */
	public void addPile(Pile newPile)
	{
		cards.addAll(newPile.getCards());
		numOfCards += newPile.getNumOfCards();
	}
	/**
	 * adds card at end of list
	 * @param newCard Card to be added
	 */
	public void addCard(Card newCard)
	{
		cards.add(newCard);
		numOfCards++;
	}
	/**
	 * removes the top card of the pile after round
	 */
	public void removeTopCard()
	{
		cards.remove(0);
		numOfCards--;
	}
	
	public void removeAllCards()
	{
		cards.clear();
		numOfCards = 0;
	}
//	TODO delete, test
//	public static void main (String[] args)
//	{
//		ArrayList<Card> testList = new ArrayList<Card>();
//		String[] cats = {"Health", "Sexiness", "Age", "strength"};
//		int[] vals = {2, 6, 2, 9};
//		
//		String nm = "Chaos";
//		
//		Card trie = new Card(nm, cats, vals);
//		testList.add(trie);
//		int[] vals2 = {1, 3, 4, 3};
//		
//		String nm2 = "Trump";
//		
//		Card trie2 = new Card(nm, cats, vals);
//		
//		int[] vals3 = {1, 2, 8, 3};
//		
//		String nm3 = "Shit";		
//		Card trie3 = new Card(nm, cats, vals);
//		
//		testList.add(trie2);
//		testList.add(trie3);
//		
//		Pile testPile = new Pile(testList);
//		
//		System.out.println(testPile.getNumOfCards());
//		
//		System.out.println(testPile.getCards());
//		System.out.println(testPile.getTopCard());
//		testPile.removeTopCard();
//		System.out.println(testPile.getCards());
//		testPile.addCard(trie);
//		testPile.addPile(testPile);
//		System.out.println(testPile.getCards());
//		System.out.println("Bye suckers.");
//	}
	
}
