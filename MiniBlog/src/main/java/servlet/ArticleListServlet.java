package servlet;

import java.io.IOException;
import java.util.List;

import dao.ArticleDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Article;

//投稿された記事の一覧を取得して、JSPに渡すためのサーブレット

//「/ArticleListServlet という URL が呼ばれたらこのサーブレットが動く→ このURLにリクエストが来ると doGet() メソッドが実行
@WebServlet("/ArticleListServlet")
public class ArticleListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ArticleDAOクラスをよびだして 新たにArticleDAO作成
		//データベース操作（記事の取得など）を担う ArticleDAO のインスタンスを生成
		ArticleDAO dao = new ArticleDAO();

		//getAllArticles() は DAOクラスのメソッドで、DBから記事を全件取得し、Listにして返す処理
		//List<Article> は「複数の記事（Article オブジェクト）のリスト」で、それを articleList に格納
		List<Article> articleList = dao.getAllArticles();
		
		// JSP に値（リスト）を渡す処理
		//index.jsp で ${articleList} とすれば記事一覧が参照できるようになる
		request.setAttribute("articleList", articleList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

}
