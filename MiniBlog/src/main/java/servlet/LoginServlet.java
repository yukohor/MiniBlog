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

/*ログイン処理を担当するサーブレット/「ユーザー認証（ログイン判定）」を行い、ログイン成功／失敗によって画面を分岐
  login.jsp から送られてきたユーザー名・パスワードを受け取って認証する。
  認証結果に応じて、セッションにログイン情報を保存したり、エラーメッセージを返したりする。
  ログイン成功後は記事一覧ページへリダイレクトする
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//login.jsp から送られてきたフォームの「ユーザー名」「パスワード」取得。取得
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//フォームから取得したユーザー情報を User オブジェクトに格納
		User loginUser = new User(username, password);
		//DAO（データアクセスオブジェクト）を生成。データベース接続と認証処理を任せるためのクラス
		UserDAO dao = new UserDAO();

		if (dao.isValidUser(loginUser)) {
			//ユーザー名とパスワードが正しいか、DB でチェック。合っていれば true、違えば false を返す
			//セッションにユーザー情報を保存（ログイン状態を管理）→投稿一覧ページへリダイレクト
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect("ArticleListServlet");
		} else {
			//失敗：エラーメッセージをリクエストに設定→エラーメッセージをリクエストに設定
			request.setAttribute("errorMsg", "ユーザー名またはパスワードが間違っています");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
