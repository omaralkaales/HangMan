package com.hangman;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Playing
 */
@WebServlet("/Hangman/PlayingHangMan")
public class Playing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//resuming session by retreiving result variable
		HttpSession session = request.getSession();
		String oldResult = (String) session.getAttribute("result");
		String guess = request.getParameter("guess");
		int oldMistakesLeft = (int) session.getAttribute("mistakesLeft");
		@SuppressWarnings("unchecked")
		Set<String> words = (Set<String>) session.getAttribute("words");
		session.removeAttribute("result");
		session.removeAttribute("words");
		
		
		//changing data according to player guess
		ResultBuilder newResult = new ResultBuilder(oldResult);
		newResult.setCount(oldMistakesLeft);
		newResult.setWords(words);
		newResult.checkGuess("easy",guess);
		
		//if player guesses the whole word he wins
		if (newResult.isWinner()) {
			request.setAttribute("theWord", newResult.getWords().toString());
			session.invalidate();
			RequestDispatcher dr = request.getRequestDispatcher("winner.jsp");
			dr.forward(request, response);
		}else if (newResult.getCount() <= 0) {
			session.invalidate();
			request.setAttribute("theWord", newResult.getWords().toString());
			RequestDispatcher dr = request.getRequestDispatcher("loser.jsp");
			dr.forward(request, response);
		}
		
		
		//setting new session attributes
		int mistakesLeft =  newResult.getCount();
		session.setAttribute("guess", guess);
		session.setAttribute("result", newResult.getResult());
		session.setAttribute("mistakesLeft", mistakesLeft);
		session.setAttribute("words", newResult.getWords());
		RequestDispatcher dr = request.getRequestDispatcher("playing.jsp");
		dr.forward(request, response);
		
	}

}
