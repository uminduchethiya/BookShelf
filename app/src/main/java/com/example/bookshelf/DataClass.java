package com.example.bookshelf;

public class DataClass {
    private  String bookCategories;
    private  String bookTitle;
    private  String bookAuthor;
    private  String bookIsbn;
    private  String bookImage;

    private String key;

    private String bookprice;
    private String bookquntity;
    private String bookdescription;

    public String getBookprice() {
        return bookprice;
    }

    public String getBookquntity() {
        return bookquntity;
    }

    public String getBookdescription() {
        return bookdescription;
    }

    public String getBookCategories() {
        return bookCategories;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getBookImage() {
        return bookImage;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }


    public DataClass(String bookCategories, String bookTitle, String bookAuthor, String bookIsbn, String bookImage ,String bookprice,String bookquntity,String bookdescription) {
        this.bookCategories = bookCategories;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.bookImage = bookImage;
        this.bookprice = bookprice;
        this.bookquntity = bookquntity;
        this.bookdescription = bookdescription;
    }
    public DataClass(){

    }
}
