package com.hangman;

import java.io.IOException;
import java.util.HashSet;
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//resuming session by retreiving result variable
		HttpSession session = request.getSession();
		String oldResult = (String) session.getAttribute("result");
		String guess = request.getParameter("guess");
		int oldMistakesLeft = (int) session.getAttribute("mistakesLeft");
		String word = (String) session.getAttribute("word");
		Set<String> updateChoicesMade = (HashSet<String>) session.getAttribute("choicesMade");
		System.out.println(":: "+updateChoicesMade);
		session.removeAttribute("result");
		session.removeAttribute("words");
		session.removeAttribute("choicesMade");
		
		
		//changing data according to player guess
		ResultBuilder newResult = new ResultBuilder(oldResult);
		newResult.setCount(oldMistakesLeft);
		newResult.setWord(word);
		if (updateChoicesMade == null) {
			updateChoicesMade = new HashSet<String>();
		}
		newResult.setChoicesMade(updateChoicesMade);
		boolean alreadySelected = newResult.checkGuess(guess);
		updateChoicesMade.add(guess);
		
		//if player guesses the whole word he wins
		if (newResult.isWinner()) {
			request.setAttribute("theWord", newResult.getWord());
			session.invalidate();
			RequestDispatcher dr = request.getRequestDispatcher("winner.jsp");
			dr.forward(request, response);
		}else if (newResult.getCount() <= 0) {
			session.invalidate();
			request.setAttribute("theWord", newResult.getWord());
			RequestDispatcher dr = request.getRequestDispatcher("loser.jsp");
			dr.forward(request, response);
		} else {
		
			//setting new attributes
			if (alreadySelected) {
				session.setAttribute("alreadyChosen", "You already chosen this letter");
			} else {
				session.setAttribute("alreadyChosen", "");
			}
			int mistakesLeft =  newResult.getCount();
			session.setAttribute("guess", guess);
			session.setAttribute("result", newResult.getResult());
			session.setAttribute("mistakesLeft", mistakesLeft);
			session.setAttribute("word", newResult.getWord());
			session.setAttribute("choicesMade", updateChoicesMade);
			RequestDispatcher dr = request.getRequestDispatcher("playing.jsp");
			dr.forward(request, response);
			
			}
	}
}
