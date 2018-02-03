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
		cards = new ArrayList<Card>();
		numOfCards = 0;
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
	/**
	 * removes all cards from the pile
	 */
	public void removeAllCards()
	{
		cards.clear();
		numOfCards = 0;
	}
}