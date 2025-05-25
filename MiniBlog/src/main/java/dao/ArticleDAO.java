package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Article;

/*データベースの articles テーブル に対して、

データの追加（insert）

すべてのデータの取得（select all）

特定の ID のデータ取得（select by id）
を行うための処理を提供*/

public class ArticleDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/miniblog";
	private final String DB_USER = "root";
	private final String DB_PASS = "52481001uk";

	public void insertArticle(Article article) {
		/*
		 insertArticle(Article article)
		目的：articles テーブルに新しい記事を挿入する。
		SQL：INSERT INTO articles (title, content, username) VALUES (?, ?, ?)
		引数：Article オブジェクト（タイトル・本文・ユーザ名が入っている）
		処理の流れ：
		1.DBに接続。
		2.PreparedStatement で SQL を準備。
		3.? の部分にデータをセット（setString()）。
		4.executeUpdate() で INSERT 実行。
		 */
		String sql = "INSERT INTO articles (title, content, username) VALUES (?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			/*この stmt.setString(1, ...) などの 数字 1, 2, 3 は、SQL文の中にある「?（プレースホルダ）」の順番 を表す*/
			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getContent());
			stmt.setString(3, article.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Article> getAllArticles() {
		/*
		 目的：articles テーブルのすべての投稿を取得してリストで返す。
		SQL：SELECT * FROM articles ORDER BY created_at DESC
		戻り値：List<Article>（投稿一覧）
		処理の流れ：
		1.DBに接続。
		2.PreparedStatement を準備。
		3.executeQuery() で検索。
		4.結果を1行ずつ読み取り Article に詰めて、リストに追加。
		5.最終的にリストを返す。
		 */

		List<Article> articles = new ArrayList<>();
		String sql = "SELECT * FROM articles ORDER BY created_at DESC";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setUsername(rs.getString("username"));
				article.setCreatedAt(rs.getTimestamp("created_at"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	public Article getArticleById(int id) {
		/*
		 目的：特定の ID の投稿を1件取得する。
		SQL：SELECT * FROM articles WHERE id = ?
		引数：記事の ID（int）
		戻り値：Article オブジェクト（該当データ1件） or null（該当なし）
		処理の流れ：
		1.DBに接続。
		2.SQL の ? に ID をセット。
		3.実行し、1行取得。
		4.Article に詰めて返す。
		 */
		String sql = "SELECT * FROM articles WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setUsername(rs.getString("username"));
				article.setCreatedAt(rs.getTimestamp("created_at"));
				return article;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
