package com.hangman;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class lanuchGame
 */
@WebServlet("/Hangman/launchHangMan")
public class LaunchGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "alice.txt";
	private Set<String> words;
    private int wordSize;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		wordSize = Integer.parseInt(request.getParameter("wordSize"));
		FileReader fr = new FileReader(wordSize, FILE_NAME);
		this.words = fr.getWords();
		
		//setting initial data
		ResultBuilder result = new ResultBuilder(wordSize);
		HttpSession session = request.getSession();
		int mistakesLeft = result.getCount();
		
		
		//setting initil session attributes
		session.setAttribute("words", words);
		session.setAttribute("result", result.getResult());
		session.setAttribute("mistakesLeft", mistakesLeft);
		response.sendRedirect("playing.jsp");

	}
	
	

}
