package com.hangman;

import java.util.HashSet;
import java.util.Set;

/**
 * Result Builder builds the string that shows the result of the player guessing,
 * it's initial value will be number of dashes equals to the size chosen by 
 * the player, it will also manipulate the word list to make it impossible for the
 * player to win, for normal hangman it will select a word randomly and compare the guesses
 * against that word. 
 * 
 * 
 *
 * 
 *
 * @param int
 *            accepts the a number of the initial size of the String
 * @param String
 *            updates the result string according to the player guess, do nothing if the guess is wrong
 * 
 *
 * @author Omar Alkaales
 */


	
	



public class ResultBuilder {
	
	
	
	private StringBuilder result; // the resut string returned to player
	private int count = 5; // number of mistakes allowed
	private String word; // the mysterious word the player have to guess
	private Set<String> choicesMade; // a list of letters choosen by player
	private boolean winner;  // check if the player won the game or not
	
	
	
	public ResultBuilder() {
		this(0, null);
	}
		
		
	/**this constructor used for initializing result string with only dashes
	 * 
	 * @param wordSize  mysterious word size chosen by player
	 * @param wordsList list of words with the chosen size 
	 */
	public ResultBuilder(int wordSize, Set<String> wordsList) {
		this.winner=false;
		result = new StringBuilder();
		for (int i=0;i<wordSize; i++) {
			result.append('-');
		}
		this.word = getRandomWord(wordsList);
		choicesMade = new HashSet<String>();
		
	}
	
	// select a random word the be themysterious word
	private String getRandomWord(Set<String> wordsList) {
		
			String newWord = "";
			int random = (int) (Math.random() * wordsList.size()) ;
			int counter =0;
			System.out.println(random +" "+wordsList.size());
			for (String word : wordsList) {
				if (counter == random) {
					newWord = word;
					break;
				}
				counter++;
			}
			return newWord;
	}


	/**this constructor is used for retreiving current result (the player progress in guessing
	 * the word
	 * 
	 * @param guess current player guess
	 */
	public ResultBuilder(String guess) {
		
		this.winner=false;
		result = new StringBuilder(guess);
		choicesMade = new HashSet<String>();
	}
	
	
	/**
	 * 
	 * @return String current guessed letters   
	 */
	
	public String getResult() {
		return result.toString();
	}
	
	/**
	 * 
	 * @return int mistakes left
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * 
	 * @return String the mysterous word
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * 
	 * @return boolean check if the player won the game
	 */
	public boolean isWinner() {
		return winner;
	}
	
	
	/**
	 * 
	 * @param newCount set a new value for mistakes count
	 */
	public void setCount(int newCount) {
		count = newCount;
	}
	
	
	
	
	/**
	 * @return a set of letter already chosen by the player
	 */
	public Set<String> getChoicesMade() {
		return choicesMade;
	}


	/**
	 * @param newChoicesMade add new values to the set of choices made
	 */
	public void setChoicesMade(Set<String> newChoicesMade) {
		this.choicesMade.addAll(newChoicesMade);
	}

	/**
	 * @param guess the letter the player is selected
	 * 
	 * @return true if guess already chosen before, false otherwise
	 */
	public boolean checkGuess(String guess) {
		//pick a random word and delete all other words in the set
		System.out.println(word);
		System.out.println(choicesMade);
		if (choicesMade.contains(guess)) {
			return true;
		}
		if (!word.contains(guess)) {
			this.count--;
		}else {
			for (int i=0;i<word.length();i++) {
				if (word.charAt(i) == guess.charAt(0)) {
					this.result.replace(i, i+1, guess);
				}
			}
			if (result.indexOf("-") == -1) {
				winner = true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param the mysterious word
	 */
	public void setWord(String word) {
		this.word = word;
		
	}
}
