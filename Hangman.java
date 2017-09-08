package com.Hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
	public String secretWord;//random word from the bank 
	
	// Method to get a random word from our word bank 
	// Notice all of them are less than 7 letters since our hanging sequence has 7 steps 
	public String getRandomWord(){
		String words = "stack trump summit rhythm zephyr jazz bernie hilary vagina python annual iran iraq syria vulva agenda soccer jesus shiva macbook"
				   + "llama mole monkey moose mouse mule newt buzz fizz fuzz otter owl panda bear deer cobra badger tiger rhino catfish goat hen"
				   + "depth curve dance dozen drama boost broke fault filfth fifth herpes force newton glass grace grade guide java ass guest"
				   + "minor trade tirade accused aids arrive rim bishop became coffee ethnic expert format gender injury guilty kansas invest"
				   + "killed labour launch space spacex orbit texas merger moment proven random secure seller senior tenant unable valley";
		String[] arrayOfWords = words.split(" ");//Splits the string into words on the basis of space between them 
		int randomIndex = new Random().nextInt(arrayOfWords.length);//Returns the next pseudorandom, uniformly distributed int value 
		// between 0 (inclusive) and the specified value (exclusive)from this random number generator's sequence
		String randomWord = arrayOfWords[randomIndex];
		return randomWord;
	}
	
	// Graphical sequence of hanging ==================================================================================================
	String sequence0 = 
			  "\n +---+"
			 +"\n |   |"
			 +"\n     |"
			 +"\n     |"
			 +"\n     |"
			 +"\n     |"
		     +"\n========= ";
		String sequence1 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n     |"
	         +"\n     |"
	         +"\n     |"
	         +"\n========= ";
	String sequence2 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n/    |"
	         +"\n     |"
	         +"\n     |"
	         +"\n========= ";
	String sequence3 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n/|   |"
	         +"\n     |"
	         +"\n     |"
	         +"\n========= ";
	String sequence4 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n/|\\  |" // We need to do \\ instead of \ only due to invalid escape sequence 
	         +"\n     |"
	         +"\n     |"
	         +"\n========= ";
	String sequence5 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n/|\\  |" 
	         +"\n/    |"
	         +"\n     |"
	         +"\n========= ";
	String sequence6 = 
	          "\n +---+"
	         +"\n |   |"
	         +"\n 0   |"
	         +"\n/|\\  |" 
	         +"\n/ \\  |"
	         +"\n     |"
	         +"\n========= ";
	String[] hangingSequence = {sequence0,sequence1,sequence2,sequence3,sequence4,sequence5,sequence6}; //array of hanging sequence
	// End of the hangingSequence ===========================================================================================================

	// Main body where game is played 
	public void playGame(){
		
			System.out.println("==== Hangman (c) Sumit Pokhrel 2017 ====");
			int wrongGuess = 0;
			StringBuilder blanks = new StringBuilder("");//Will be filled up by dots of the same size as word length and subsequently filled correctly guessed letters
			//need to use String Builder since Strings are immutable
			Hangman player1 = new Hangman();
			player1.secretWord = player1.getRandomWord();//get a Random word from our bank
			
			// Giving the clue: printing the same number of dots as total number of letters of the secret word
			for(int i = 0; i < player1.secretWord.length(); i++){
				blanks = blanks.append(".");
				}
			System.out.println(blanks);
			
			//System.out.println(player1.secretWord); #ONLY FOR DEBUGGING PURPOSE
			Scanner userInput = new Scanner(System.in);//userInput object for guessed letter from the player
			while (wrongGuess < 7){ // Since 6 wrong attempts are given, starts from 0 
				int correctGuess = 0; // For each guess we need to update so that we know if we should increment wrongGuess
				//Print the hanging sequence based on wrong attempts
				System.out.println(hangingSequence[wrongGuess]);
				
				//Check if the game is over, if the player has exceeded 6 wrong attempts 
				if (wrongGuess == 6){
					System.out.println("== GAME OVER ==");
					System.out.println( "The correct word was: "+ player1.secretWord);
					break; //Breaks from the while loop of wrongGuess < 7 
					}
				System.out.print("Guess a letter --> ");
				String guess = userInput.nextLine();// returns the value as a string
				
				if (guess.length()== 1 && Character.isLowerCase(guess.charAt(0))){ //Check if input is single char lowercase alphabet 
					
					//Business logic: Looping across all the characters of the secretWord and checking with the guessedLetter
					// Filling up the correctly guessed letter into the blanks
					for(int i = 0; i < player1.secretWord.length(); i++){
						if(Character.toString(player1.secretWord.charAt(i)).equals(guess)){//comparing letters of secretWord with guessedLetter
							// Upcasting char to string otherwise we won't be able to compare
							blanks.replace(i,i+1,guess);//blanks becomes a word if all the letters are guessed correctly
							correctGuess +=1;
							}
					}
					System.out.println(blanks);
					if (correctGuess == 0){
						wrongGuess +=1;//Since they are mutually exclusive to each other
					}
				}
				else {
					System.out.println(blanks);
					System.out.println("Single lowercase alphabets Only");
					wrongGuess +=1;
				}
				//Check if the game is Won before exceeding 6 wrong attempts 
				if (blanks.toString().equals(player1.secretWord)){ // We need to convert StringBuffer blanks to String so that we can compare with another String 
					System.out.println("== YOU WON == ");
					break;//Breaks from the while loop of wrongGuess < 7 
				}	
			}
			userInput.close();// we need to close to avoid memory leak 
	}
 		
	public static void main(String[] args) {
		Hangman game = new Hangman();
		game.playGame();
		
	}

}
