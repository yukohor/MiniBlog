package servlet;

import java.io.IOException;

import dao.ArticleDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Article;

/*指定されたIDに紐づいている記事を表示させる働きをもつクラス
ブラウザから ShowArticleServlet?id=3 のようなリクエストが来る
1.id=3 を取得して int 型に変換
2.DAOで ID=3 の記事を取得
3.request に記事データをセット
4.show.jsp にフォワードして表示

*/
//URLで /ShowArticleServlet を指定されたらこのクラスが機能.Tomcat にこのURLが来たときに doGet() メソッドが呼ばれる
@WebServlet("/ShowArticleServlet")
public class ShowArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//idを取得する URLパラメータ（例: ...?id=3）から id の値を取得してる
		String idStr = request.getParameter("id");
		//取得した string 型の id を int 型へ変換
		int id = Integer.parseInt(idStr);

		//DB操作のためのインスタンス生成
		ArticleDAO dao = new ArticleDAO();
		//指定された id を検索.指定された ID を使って、該当する記事1件をDBから取得
		Article article = dao.getArticleById(id);
		//取得した内容を格納→JSP に渡すための処理。equest にセットして、show.jsp 内で ${article.title} のように表示
		request.setAttribute("article", article);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/show.jsp");
		dispatcher.forward(request, response);

	}

}
