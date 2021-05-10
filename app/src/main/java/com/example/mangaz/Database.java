package com.example.mangaz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mangaz.Model.Users;


public class Database extends SQLiteOpenHelper {
    /*
     * By Nguyen Van An
     *
     *
     * */
    private static final String DATABASENAME = "data.sqlite";
    private static final int VERSION = 1;
    private SQLiteDatabase DB;

    public Database(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // -1- Bảng Users
        //db.execSQL("INSERT INTO Users VALUES('admin','manAn1207*','Admin',21,99,'',NULL,1)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                "    UserName NVARCHAR(20) PRIMARY KEY, " + // tên đăng nhập **(Khóa chính)**
                "    PassWord NVARCHAR(20), " +             // Mật khẩu
                "    Nickname NVARCHAR(20), " +             // Tên hiển thị trên app
                "    Age INTEGER, " +                       // tuổi
                "    Level INTEGER, " +                     // cấp độ
                "    UrlImage TEXT, " +                     // Đường dẫn ảnh
                "    Avatar BLOG, " +                       // ảnh đại diện
                "    IsAdmin BOOLEAN " +                    // Admin
                ")");
        // -2- Bảng Follow
        db.execSQL("CREATE TABLE IF NOT EXISTS Follow (" +
                "    UserNameFollow NVARCHAR(20) PRIMARY KEY, " +// UserName Người theo dõi **(Khóa chính)**
                "    User_UserName NVARCHAR(20)," +
                "    FOREIGN KEY(User_UserName) REFERENCES Users(UserName) " +// User Người được theo dõi
                ")");
        // -3- Bảng Category
        db.execSQL("CREATE TABLE IF NOT EXISTS Category(" +
                "    CategoryName NVARCHAR(250) PRIMARY KEY " +// Tên Thể loại **(Khóa chính)**
                ")");
        // -4- Bảng Manga
        db.execSQL("CREATE TABLE IF NOT EXISTS Manga(" +
                "    MangaName NVARCHAR(250) PRIMARY KEY, " +// Tên truyện **(Khóa chính)**
                "    Author NVARCHAR(100), " +// tác giả
                "    IsManga BOOLEAN, " +// Là Manga (Truyện tranh, Manga,...) hay story (Tiểu thuyết, truyện chữ ,...)
                "    Introduce NVARCHAR(500), " +// Giới thiệu Nội Dung truyện
                "    Accept BOOLEAN, " +// Đã được phép đăng
                "    UrlImage TEXT, " +// Đường dẫn ảnh
                "    Avatar BLOG " +// ảnh đại diện
                ")");
        // -5- Bảng UserManga
        db.execSQL("CREATE TABLE IF NOT EXISTS UserManga(" +
                "    IsFollow BOOLEAN, " +// Đang theo dõi
                "    Vote INTEGER, " +// Đánh giá Truyện Min 1 Max 5
                "    User_UserName NVARCHAR(20)," +
                "    Manga_MangaName NVARCHAR(250)," +
                "    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), " +// Khóa ngoại với bảng Người dùng
                "    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) " +// Khóa ngoại với bảng Truyện
                ")");
        // -6- Bảng CategoryManga
        db.execSQL("CREATE TABLE IF NOT EXISTS CategoryManga(" +
                "    IdCatagoryManga NVARCHAR(100) PRIMARY KEY, " + // IdCatagoryManga = CategoryNameMangaName **(Khóa chính)**
                "    Category_CategoryName NVARCHAR(250)," +
                "    Manga_MangaName NVARCHAR(250)," +
                "    FOREIGN KEY (Category_CategoryName) REFERENCES Category(CategoryName), " + // Khóa ngoại với bảng Người dùng
                "    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) " + // Khóa ngoại với bảng Truyện
                ")");
        // -7- Bảng Chapter
        db.execSQL("CREATE TABLE IF NOT EXISTS Chapter(" +
                "    IdChapter NVARCHAR(270) PRIMARY KEY, " +// IdChapter = MangaName + Chap **(Khóa chính)**
                "    Chap INTEGER, " +// Số thứ tự Chương
                "    ContentChapter TEXT, " +// nội dung của chap truyện (Tối đa 15000 ký tự)
                "    Manga_MangaName NVARCHAR(250)," +
                "    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) " +// Tên truyện
                ")");
        // -8- Bảng Comment
        db.execSQL("CREATE TABLE IF NOT EXISTS Comment(" +
                "    IdComment NVARCHAR(370) PRIMARY KEY, " +// IdComment = UserName+MangaName+IdChap **(Khóa chính)**
                "    ContentComment TEXT, " +// Nội dung Bình luận
                "    AnswerIdComment NVARCHAR(100), " +// ID bình luận được trả lời
                "    User_UserName NVARCHAR(20)," +
                "    Chapter_IdChapter NVARCHAR(270)," +
                "    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), " +// Người viết Bình luận
                "    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) " +// Chapter được bình luận
                ")");
        // -9- Bảng LikeToComment
        db.execSQL("CREATE TABLE IF NOT EXISTS LikeToComment(" +
                "    IsLike BOOLEAN, " +// Đã like
                "    User_UserName NVARCHAR(20)," +
                "    Comment_IdComment NVARCHAR(370)," +
                "    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), " +// Người like Bình luận
                "    FOREIGN KEY (Comment_IdComment) REFERENCES Comment(IdComment) " +// Bình luận được like
                ")");
        // -10- Bảng ChapterImage
        db.execSQL("CREATE TABLE IF NOT EXISTS ChapterImage(" +
                "    ImageNumber INTEGER, " +// Số thứ tự Chương
                "    Image BLOG, " +// Ảnh
                "    UrlImage TEXT, " +// Đường dẫn ảnh
                "    Chapter_IdChapter NVARCHAR(270)," +
                "    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) " +// Chapter truyện
                ")");
        // -11- Bảng UserChapter
        db.execSQL("CREATE TABLE IF NOT EXISTS UserChapter(" +
                "    IdUserChapter NVARCHAR(100) PRIMARY KEY, " +// IdUserChapter = UserName + Chapter **(Khóa chính)**
                "    TimeReading DATETIME, " +// Thời gian lần cuối đọc chap DateTime.Now
                "    IsLike BOOLEAN, " +// Có thích Chap
                "    User_UserName NVARCHAR(20)," +
                "    Chapter_IdChapter NVARCHAR(270)," +
                "    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), " +// Người Tương tác Chap
                "    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) " +// id Chapter
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Truy vấn có trả kết quả: CREATE, INSERT, UPDATE, DELETE,...
    public void QueryData(String str) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(str);
    }

    // Truy vấn có trả kết quả: SELECT
    public Cursor GetData(String str) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(str, null);
    }

    // Xóa
    public void Delete(String tableName, String where) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + tableName + " WHERE " + where + ";");
    }


    // CREATE Table
//        database.QueryData("CREATE TABLE IF NOT EXISTS User(Id NVARCHAR(25) PRIMARY KEY, UserName NVARCHAR(255), PhoneNumber NVARCHAR(25))");
    // Insert Data
//        try {
//            database.QueryData("INSERT INTO User VALUES(0,'Nguyen Van An', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(1,'Nguyen Van B', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(2,'Nguyen Van C', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(3,'Nguyen Van D', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(4,'Nguyen Van E', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(5,'Nguyen Van F', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(6,'Nguyen Van G', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(7,'Nguyen Van H', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(8,'Nguyen Van I', '0987191143')");
//            database.QueryData("INSERT INTO User VALUES(9,'Nguyen Van K', '0987191143')");
//        } catch (Exception e) {
//            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

}
