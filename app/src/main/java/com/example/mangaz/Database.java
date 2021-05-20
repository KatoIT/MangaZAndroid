/*
 * Coder: Nguyen Van An
 * Date: 5-5-2021
 * Content: Tạo Database
 *
 *
 * */
package com.example.mangaz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mangaz.Model.CategoryManga;
import com.example.mangaz.Model.Chapter;
import com.example.mangaz.Model.ChapterImage;
import com.example.mangaz.Model.Manga;
import com.example.mangaz.Model.User;
import com.example.mangaz.Model.UserChapter;
import com.example.mangaz.Model.UserManga;

import java.io.ByteArrayOutputStream;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASENAME = "data.sqlite";
    private static final int VERSION = 1;
    private SQLiteDatabase DB;
    private VarFinal mVarFinal = new VarFinal();
    private MangaZSharedPreferences mangaZSharedPreferences;
    private Context context;

    public Database(Context context) {
        super(context, DATABASENAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mangaZSharedPreferences = new MangaZSharedPreferences(context);
        if (!mangaZSharedPreferences.GetBooleanValue(mVarFinal.INSTALL_FOR_THE_FIRST_TIME)) {
            CreateTable(db);
            InsertData(db);
            mangaZSharedPreferences.putBooleanValue(mVarFinal.INSTALL_FOR_THE_FIRST_TIME, true);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Users VALUES('admin','manAn1207*','Admin',21,99,'',NULL,1)");
        db.execSQL("INSERT INTO Users VALUES('Katoit','123123','Katoit Man',21,1,'',NULL,0);");
        db.execSQL("INSERT INTO Users VALUES('BacNorth','123123','Tiến Bắc',21,1,'',NULL,0);");
        db.execSQL("INSERT INTO Users VALUES('echkeutroimua','123123','Ếch kêu trời mưa',21,1,'',NULL,0);");
        db.execSQL("INSERT INTO Users VALUES('daroemon','123123','Daroemon',21,1,'',NULL,0);");
        db.execSQL("INSERT INTO Category VALUES('Huyền huyễn');");
        db.execSQL("INSERT INTO Category VALUES('Học đường');");
        db.execSQL("INSERT INTO Category VALUES('Tổng tài');");
        db.execSQL("INSERT INTO Category VALUES('Xuyên không');");
        db.execSQL("INSERT INTO Category VALUES('Kinh dị');");
        db.execSQL("INSERT INTO Category VALUES('Harem');");
        db.execSQL("INSERT INTO Category VALUES('Hành động');");
        db.execSQL("INSERT INTO Category VALUES('Tình yêu');");
        db.execSQL("INSERT INTO Category VALUES('Hài hước');");
        db.execSQL("INSERT INTO Category VALUES('Viễn tưởng');");
        db.execSQL("INSERT INTO Category VALUES('Mạo hiểm');");
        db.execSQL("INSERT INTO Category VALUES('Đô thị');");
        db.execSQL("INSERT INTO Category VALUES('Tu tiên');");
        db.execSQL("INSERT INTO Category VALUES('Võ thuật');");
        db.execSQL("INSERT INTO Category VALUES('Đối kháng');");
    }

    // Tạo bảng
    public void CreateTable(SQLiteDatabase db) {
        // -1- Bảng Users
        //db.execSQL("INSERT INTO Users VALUES('admin','manAn1207*','Admin',21,99,'',NULL,1)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                "    UserName NVARCHAR(20) PRIMARY KEY, " + // tên đăng nhập **(Khóa chính)**
                "    PassWord NVARCHAR(20), " +             // Mật khẩu
                "    Nickname NVARCHAR(20), " +             // Tên hiển thị trên app
                "    Age INTEGER, " +                       // tuổi
                "    Level INTEGER, " +                     // cấp độ
                "    UrlImage TEXT, " +                     // Đường dẫn ảnh
                "    Avatar BLOB, " +                       // ảnh đại diện
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
                "    Avatar BLOB, " +// ảnh đại diện
                "    FOREIGN KEY (Author) REFERENCES Users(UserName)" +
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
                "    DateUpdate Datetime," +
                "    Manga_MangaName NVARCHAR(250)," +
                "    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) " +// Tên truyện
                ")");
        // -8- Bảng Comment
        db.execSQL("CREATE TABLE IF NOT EXISTS Comment(" +
                "    IdComment NVARCHAR(370) PRIMARY KEY, " +// IdComment = UserName+IdChap **(Khóa chính)**
                "    ContentComment TEXT, " +// Nội dung Bình luận
                "    AnswerIdComment NVARCHAR(100), " +// ID bình luận được trả lời
                "    DateComment DateTime," +
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
                "    Image BLOB, " +// Ảnh
                "    UrlImage TEXT, " +// Đường dẫn ảnh
                "    Chapter_IdChapter NVARCHAR(270)," +
                "    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) " +// Chapter truyện
                ")");
        // -11- Bảng UserChapter
        db.execSQL("CREATE TABLE IF NOT EXISTS UserChapter(" +
                "    TimeReading DATETIME, " +// Thời gian lần cuối đọc chap DateTime.Now
                "    IsLike BOOLEAN, " +// Có thích Chap
                "    User_UserName NVARCHAR(20)," +
                "    Chapter_IdChapter NVARCHAR(270)," +
                "    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), " +// Người Tương tác Chap
                "    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) " +// id Chapter
                ")");
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

    /*
     * Insert Model
     *
     */
    // Insert Manga
    public void INSERT_MANGA(Manga manga) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO Manga VALUES(?,?,?,?,?,'',?)";
        // bitmap --> byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        manga.getAvatar().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        SQLiteStatement mSqLiteStatement = sqLiteDatabase.compileStatement(sql);
        mSqLiteStatement.clearBindings();
        mSqLiteStatement.bindString(1, manga.getMangaName());
        mSqLiteStatement.bindString(2, manga.getAuthor());
        mSqLiteStatement.bindString(3, manga.getManga().toString());
        mSqLiteStatement.bindString(4, manga.getIntroduce());
        mSqLiteStatement.bindString(5, manga.getAccept().toString());
        mSqLiteStatement.bindBlob(6, bytes);

        mSqLiteStatement.executeInsert();
    }

    // Insert Chapter
    public void INSERT_CHAPTER(Chapter chapter) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO Chapter VALUES(?,?,?,?,?)";

        SQLiteStatement mSqLiteStatement = sqLiteDatabase.compileStatement(sql);
        mSqLiteStatement.clearBindings();
        mSqLiteStatement.bindString(1, chapter.getIdChapter());
        mSqLiteStatement.bindLong(2, chapter.getChap());
        mSqLiteStatement.bindString(3, chapter.getContentChapter());
        mSqLiteStatement.bindString(4, chapter.getDateUpdate());
        mSqLiteStatement.bindString(5, chapter.getManga_MangaName());

        mSqLiteStatement.executeInsert();
    }

    // Insert CategoryManga
    public void INSERT_CATEGORYMANGA(CategoryManga categoryManga) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO CategoryManga VALUES(?,?)";

        SQLiteStatement mSqLiteStatement = sqLiteDatabase.compileStatement(sql);
        mSqLiteStatement.clearBindings();
        mSqLiteStatement.bindString(1, categoryManga.getCategory_CategoryName());
        mSqLiteStatement.bindString(2, categoryManga.getManga_MangaName());

        mSqLiteStatement.executeInsert();
    }

    // Insert ChapterImage
    public void INSERT_CHAPTERIMAGE(ChapterImage chapterImage) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO ChapterImage VALUES(?,?,?,?)";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        chapterImage.getImage().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        SQLiteStatement mSqLiteStatement = sqLiteDatabase.compileStatement(sql);
        mSqLiteStatement.clearBindings();
        mSqLiteStatement.bindLong(1, chapterImage.getImageNumber());
        mSqLiteStatement.bindBlob(2, bytes);
        mSqLiteStatement.bindString(3, chapterImage.getUrlImage());
        mSqLiteStatement.bindString(4, chapterImage.getChapter_IdChapter());

        mSqLiteStatement.executeInsert();
    }

    /*
     * get Model
     *
     */
    // get Manga
    public Manga GetManga(String mangaName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Manga WHERE MangaName = '" + mangaName + "';", null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return null;
        }
        Bitmap bitmap;
        if (cursor.getBlob(6) != null) {
            bitmap = CovertBytesToBitmap(cursor.getBlob(6));
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_anh_mac_dinh);
        }
        return new Manga(cursor.getString(0),
                cursor.getString(1),
                Boolean.valueOf(cursor.getString(2)),
                cursor.getString(3),
                Boolean.valueOf(cursor.getString(4)),
                cursor.getString(5),
                bitmap);
    }

    // get User
    public User GetUser(String userName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE Username = '" + userName + "';", null);
        cursor.moveToFirst();
        return new User(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getString(5),
                Boolean.valueOf(cursor.getString(7)));
    }

    // get UserChapter
    public UserChapter GetUserChapter(String username, String idchapter) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserChapter WHERE User_UserName = '" + username + "' AND Chapter_IdChapter='" + idchapter + "';", null);
        cursor.moveToFirst();
        return new UserChapter(cursor.getString(0),
                Boolean.valueOf(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3));
    }

    // get UserManga
    public UserManga GetUserManga(String username, String manganame) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserManga WHERE User_UserName = '" + username + "' AND Manga_MangaName='" + manganame + "';", null);
        cursor.moveToFirst();
        return new UserManga(Boolean.valueOf(cursor.getString(0)),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    /*
     * get Cursor Model
     *
     */
    // get list Manga by category
    public Cursor GetListMangaByCategory(String categoryName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT Manga.MangaName FROM CategoryManga JOIN Manga ON Manga.MangaName = CategoryManga.Manga_MangaName WHERE Category_CategoryName = '" + categoryName + "';", null);
    }

    // get list Category
    public Cursor GetListCategory() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Category;", null);
    }

    // get list Category By MangaName
    public Cursor GetListCategoryByMangaName(String mangaName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CategoryManga WHERE Manga_MangaName = '" + mangaName + "';", null);
    }

    // get number Chap By MangaName
    public Cursor GetChapterByMangaName(String mangaName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Chapter WHERE Manga_MangaName = '" + mangaName + "';", null);
    }

    // get list Manga
    public Cursor GetListManga() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Manga;", null);
    }

    // get list Manga mới cập nhật
    public Cursor GetListMangaNewUpdate() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT Manga_MangaName FROM Chapter ORDER BY DateUpdate DESC;", null);
    }

    // get list Manga được yêu thíc nhât
    public Cursor GetListMangaTopLike() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT MangaName, COUNT(User_UserName) FROM Manga JOIN Chapter ON Manga.MangaName = Chapter.Manga_MangaName JOIN UserChapter ON UserChapter.Chapter_IdChapter = Chapter.IdChapter WHERE IsLike <> 0 GROUP BY MangaName ORDER BY COUNT(User_UserName) DESC", null);
    }

    // get list Manga nhiều CMT nhất
    public Cursor GetListMangaTopComment() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT MangaName, COUNT(User_UserName) FROM Manga JOIN Chapter ON Manga.MangaName = Chapter.Manga_MangaName JOIN Comment ON Comment.Chapter_IdChapter = Chapter.IdChapter  GROUP BY MangaName ORDER BY COUNT(User_UserName) DESC", null);
    }

    // get list Manga nhiều Follow nhất
    public Cursor GetListMangaTopFollow() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT Manga_MangaName, COUNT(User_UserName) FROM UserManga WHERE IsFollow <> 0 GROUP BY Manga_MangaName ORDER BY COUNT(User_UserName) DESC", null);
    }

    // get list Manga nhiều View nhất
    public Cursor GetListMangaTopView() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT Manga_MangaName, COUNT(User_UserName) FROM UserManga GROUP BY Manga_MangaName ORDER BY COUNT(User_UserName) DESC", null);
    }

    /*
     * get type Model
     *
     */
    public Boolean isUserLike(String username, String idchapter) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserChapter WHERE User_UserName = '" + username + "' AND Chapter_IdChapter='" + idchapter + "';", null);

        if (Boolean.valueOf(cursor.getCount() == 0)) {
            return false;
        }
        return true;
    }

    public Boolean isUserManga(String username, String manganame) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserManga WHERE User_UserName = '" + username + "' AND Manga_MangaName='" + manganame + "';", null);

        if (Boolean.valueOf(cursor.getCount() == 0)) {
            return false;
        }
        return true;
    }

    public int getNumberChapbyChapId(String chapId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Chap FROM Chapter WHERE IdChapter = '" + chapId + "';", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String GetChapIdbyNumberChap(int numberChap, String mangaName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT IdChapter FROM Chapter WHERE Chap = " + numberChap + " AND Manga_MangaName = '" + mangaName + "';", null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }


    public Bitmap CovertBytesToBitmap(byte[] bytes) {
        // byte[] --> bitmat
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
