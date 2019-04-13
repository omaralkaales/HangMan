package com.hangman;

import java.util.HashSet;
import java.util.Iterator;
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
 * TrieMaps may not contain null keys or values.
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
	
	
	
	private StringBuilder result;
	private int count=5;
	private Set<String> words;
	private boolean winner;
	public ResultBuilder() {
		this(0);
	}
		
		
	//this constructor used for initializing result string with only dashes
	public ResultBuilder(int wordSize) {
		this.winner=false;
		result = new StringBuilder();
		for (int i=0;i<wordSize; i++) {
			result.append('-');
		}
	}
	
	//this constructor is used for retreiving current result (the player progress in guessing
	//the word
	public ResultBuilder(String guess) {
		
		this.winner=false;
		result = new StringBuilder(guess);
	}
	
	
	/* GETTERS / ACCESSORS*/
	
	public String getResult() {
		return result.toString();
	}

	public int getCount() {
		return count;
	}
	
	public Set<String> getWords() {
		return this.words;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	
	/* SETTERS / MUTATORS */
	
	public void setCount(int newCount) {
		count = newCount;
	}
	
	public void setWords(Set<String> words) {
		this.words=words;
	}


	public void checkGuess(String level,String guess) {
		if (level.equals("easy")) {
			easy(guess);
		}else {
			
			
		}
		
		
	}

	
	/* HELPER METHODS */
	
	//pick a random word and delete all other words in the set
	private void easy(String guess) {
		if (words.size()>1) {	
			Set<String> newWords = new HashSet<String>();
			int random = (int) (Math.random() * words.size()) ;
			int counter =0;
			System.out.println(random +" "+words.size());
			for (String word : words) {
				if (counter == random) {
					newWords.add(word);
					break;
				}
				counter++;
			}
			words = newWords;
		}
		System.out.println(words.toString());
		Iterator<String> itr = words.iterator();
		String word = itr.next();
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
	}
}
