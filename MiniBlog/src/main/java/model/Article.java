package model;

import java.sql.Timestamp;

public class Article {
    private int id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public Article() {}

    public Article(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

   public String getTitle() {
	   return title;
   }
   
   public void setTitle(String title) {
	   this.title = title;
   }
   
   public String getContent() {
	   return content;
   }
   
   public void setContent(String content) {
	   this.content = content;
   }
   
   public String getUsername() {
	   return username;
   }
   
   public void setUsername(String username) {
	   this.username = username;
   }
   
   public Timestamp getCreatedAt() {
	   return createdAt;
   }
   
   public void setCreatedAt(Timestamp createdAt) {
	   this.createdAt = createdAt;
   }
   
   
   
   
   
}
