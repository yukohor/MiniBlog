package servlet;

import java.io.IOException;

import dao.ArticleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Article;
import model.User;

/*
 記事の投稿機能を処理するサーブレット
 ユーザーが記事のタイトルと本文をフォームに入力して送信すると、
 このサーブレットがそのデータを処理しデータベースに保存
 */

/*
 このサーブレットは /PostArticleServlet という URL にマッピングされている。
フォームの action がこの URL になっていればこのサーブレットが呼ばれる
 */
@WebServlet("/PostArticleServlet")
public class PostArticleServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		//投稿フォームから送られてきた タイトルと内容を取得
		//フォームの <input name="title"> や <textarea name="content"> に対応
		String content = request.getParameter("content");
		//ログイン中のユーザー情報をセッションから取り出し
		//セッションに loginUser が保存されていれば、それを使って投稿者名を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//ログインしていない場合の防止処理ログインしていなければ記事を投稿できないようにする
		if (loginUser != null) {

			/*
			 loginUser は User クラスのインスタンス
			User クラスには getUsername() という getterメソッドがあるので、ユーザー名を取り出し
			() はこのメソッドを「呼び出す」記号で「この関数を実行してください」という意味
			 */
			Article article = new Article(title, content, loginUser.getUsername());

			ArticleDAO dao = new ArticleDAO();

			//ArticleDAO クラスのメソッドを使って、記事をデータベースに保存
			dao.insertArticle(article);
			//投稿が成功したら、記事一覧ページにリダイレクト
			response.sendRedirect("ArticleListServlet");

			//もしログインしていない状態でアクセスした場合は、ログインページにリダイレクト
		} else {
			response.sendRedirect("login.jsp");
		}

	}

}
