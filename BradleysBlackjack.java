import java.util.Scanner;
import java.util.Random;

public class BradleysBlackjack
{
	public static void main(String[] args) 
	{
		// Start the game :)
		playGame();
	}

	public static String convertCard( int cardToConvert)
	{	
		//Sets of strings to display the card units, and the values of ten
		String[] units = {	"", "Ace", "Two", "Three", "Four", "Five",
							"Six", "Seven", "Eight", "Nine" };
		String[] tens = {	"", "Ten", "Jack", "Queen", "King" };
		
		String wordVersion = "";
	
		// A switch statement for if the card value is an ace or a ten
		switch(cardToConvert)
		{
		case 1: 
		case 2: 
		case 3: 
		case 4: 
		case 5: 
		case 6: 
		case 7: 
		case 8: 
		case 9: wordVersion = units[cardToConvert];
				break;
		case 10: 
				// need to pick random card if a 10
				Random cardSelection = new Random();
				int cardType = cardSelection.nextInt(3)+1;
				
				switch (cardType)
				{
				case 1:	
				case 2: 
				case 3:	
				case 4: wordVersion = tens[cardType];
						break;
				}
				break;
		}
		
		// return the word version of the card if the value is from 2-9
		return wordVersion;
	}

	public static void restartGame() {
		Scanner in = new Scanner(System.in);
		String playAgain;
		System.out.println("Would you like to 'Play Again' or 'Stop'? ");
		playAgain = in.nextLine();

		if (playAgain.equalsIgnoreCase("Play Again")) {
			playGame();
		} else if (playAgain.equalsIgnoreCase("Stop")) {
			System.out.println("Thanks For Playing");
		} else {
			restartGame();
		}
	}
	
	public static void playGame(){
		//A string for the suit values, along with a random generator randomise a suit
		String suit[] = new String [5];
		{
			suit [1] = " of Spades";
			suit [2] = " of Diamonds";
			suit [3] = " of Clubs";
			suit [4] = " of Hearts";
		}
		
		for (int index = 0; index<suit.length; index++);
		Random getRandomSuit = new Random();
	 
	
	 	
		// Declaring the variables
		int computerTarget = 16;
		int computerTotal = 0;
		int userTotal = 0;
		int dealtCard;				// only need one variable to hold the next card, just reuse
		
		// Creating a random generator
		Random cardGenerator = new Random();
		
		
		
		// This deals random cards using the generator above and displaying 2 cards for the user
		System.out.println("Here are your first two cards:");
		for (int cnt = 0;cnt < 2;cnt++)
		{
			//The Suit generator, for a random suit every time.
			int s = getRandomSuit.nextInt(4)+1;
			//The card generator
			dealtCard = cardGenerator.nextInt(9)+1;
			//Adding the generated card (dealtCard) to the userTotal 
			userTotal += dealtCard;
			System.out.println("Your Card is " + convertCard(dealtCard) + suit[s]);
		
		}
		System.out.println("You have " + userTotal);
		
		//Creating a scanner for the user input of stick or twist
		Scanner in = new Scanner(System.in);
		String stickOrTwist;

		// A do loop for the users question
		do
		{
			// The stick or twist question and saving it inside "stickOrTwist"
			System.out.println("Would you like to 'Stick' or 'Twist?' ");
			stickOrTwist = in.nextLine();
		
			// An If loop if the user input "Twist" or "TWIST" or "twist", so they are dealt another card
			
			if (stickOrTwist.equalsIgnoreCase("Twist"))
			{	
				//The Suit generator, for a random suit every time.
				int s = getRandomSuit.nextInt(4)+1;
				//The card generator
				dealtCard = cardGenerator.nextInt(9)+1;
			
			
				// Keep running total and display
				userTotal += dealtCard;
				System.out.println("Card is " + convertCard(dealtCard)+ suit[s]);
				System.out.println("Your total is :" + userTotal);
			
				//Another loop to check that the user is still under 21, if not, then it will stop the loop of asking
				//to twist again, and do the computer cards
		
				if (userTotal > 21)
				{
					break;
				}
			}
		}
		
		while (stickOrTwist.equalsIgnoreCase("Twist") && userTotal < 21);

		// Now onto the computers cards
		System.out.println("Here are the Computers first two cards:");
		// Loop twice (for loop is best loop for a known number of cycles
		for (int cnt = 0;cnt < 2;cnt++)
		{
			int s = getRandomSuit.nextInt(4)+1;
			
			dealtCard = cardGenerator.nextInt(9)+1;
			// Add to total
			computerTotal += dealtCard;
			// Print out card
			System.out.println("Card is " + convertCard(dealtCard) + suit[s]);
		
		
		}
		
			
		// Print computer total after 2 cards
		System.out.println("Computer has " + computerTotal);

		// If user goes bust, computer wins
		
		if (userTotal > 21)
		{
		System.out.println("Your bust, computer wins");
		}
		else
		{
			// Otherwise the computer can now deal again
			System.out.println("Computer deals again ...");
			
			// Computer now deals up to its limit of 16 or above
			while (computerTotal < computerTarget)
			{
				//The Suit generator, for a random suit every time.
				int s = getRandomSuit.nextInt(4)+1;
				//The card generator
				dealtCard = cardGenerator.nextInt(9)+1;
				
				//Adding the generated card (dealtCard) to the computerTotal 
				computerTotal += dealtCard;
				System.out.println("Card is " + (convertCard(dealtCard) + suit[s]) + ": Total is " + computerTotal );
			}			
		
			// If statements to display who wins
			if (computerTotal > 21)
			{
				System.out.println("You win, computer goes bust.");
			}
			else if (userTotal < computerTotal)
			{
				System.out.println("Computer wins.");
			}
			else if (userTotal > computerTotal)
			{
				System.out.println("You win.");
			}
			else
			{	
				System.out.println("Draw.");
			}
		}
		restartGame();
	}
}