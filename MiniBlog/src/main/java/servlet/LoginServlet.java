package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		User loginUser = new User(username,password);
		UserDAO dao = new UserDAO();
		
		boolean isLoginOK = dao.isValidUser(loginUser);
		
		if (isLoginOK) {
			//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginSuccess.jsp");
			dispatcher.forward(request, response);
		}else {
	request.setAttribute("errorMsg", "ユーザー名またはパスワードが間違っています");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request,  response);
		}
	
	}

}
