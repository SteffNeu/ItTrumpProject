
/**
* Uses Fisher-Yates shuffle algorithm to shuffle the deck of 
* Card objects with as little bias as possible 
*/
public void shuffleCards() {
	
	Random rand = new Random();

	int index = deck.length - 1;
	while (index > 0) {

		int r = rand.nextInt(index);

		Card tempSwap = deck [index];
		deck [index] = deck [r];
		deck [r] = tempSwap;

		index --;
	}
}