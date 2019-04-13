package com.hangman;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileReader {
	
	private Set<String> words;
	
	public FileReader (int wordSize, String fileName){
		words = new HashSet<String>();
		InputStream csv = 
				   LaunchGame.class.getResourceAsStream(fileName);
		Scanner scan = new Scanner(csv);
		
		
		
		while (scan.hasNext()) {
			String word = scan.next().toUpperCase();
			StringBuilder sb = new StringBuilder(word);
			int tempSize = word.length();
			for (int i=0; i<tempSize;i++) {
				if (sb.charAt(i) < 'A' || sb.charAt(i) > 'Z') {
					sb.deleteCharAt(i);
					tempSize--;
					i--;
				}
			}
			if (sb.length() == wordSize) {
				words.add(sb.toString());
			}
		}
		scan.close();
		
	}

	public Set<String> getWords() {
		return words;
	}

	public void setWords(Set<String> words) {
		this.words = words;
	}
	
	
	
}
