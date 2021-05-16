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
        db.execSQL("INSERT INTO Category VALUES('Đô thị tu tiên');");
        db.execSQL("INSERT INTO Category VALUES('Trinh thám');");
        db.execSQL("INSERT INTO Manga VALUES('Tiên đế trở về','echkeutroimua',1,'Ba năm trước, Vân Thanh Nham bất ngờ từ nhân gian rơi vào tiên giới. Ba nghìn năm sau, hắn trở thành vân đế của tiên giới, phá được hư không, trở về nhân giới. Hắn phát hiện thời gian ở đây mới chỉ qua có 3 năm. \"Trước đây ta không có sức mạnh bảo vệ những người ta yêu thương, bây giờ, cả thế giới đều phải quỳ dưới chân ta',1,'2131165308',NULL);");
        db.execSQL("INSERT INTO Manga VALUES('Đấu la đại lục','echkeutroimua',1,'Đường tam học trộm tuyệt thế võ công nhưng lại nhảy xuống vách núi tự sát để chứng minh sự minh bạch của mình, sau khi chuyển thế không ngờ lại trở thành một cậu bé đáng yêu luôn được mọi người ngưỡng mộ?! Anh ấy mang theo song võ hồn, phách lực tiên thiên mãn trời sinh gia nhập học viện Nặc Đinh, gặp được tiểu vũ dễ thương, sau đó bắt đầu một câu chuyện nhi nữ tình trường, mưa máu gió tanh.',1,'2131165305',NULL);");
        db.execSQL("INSERT INTO Manga VALUES('Tận thế người trần','echkeutroimua',1,'Thế giới tương lai là thế giới xấu xí của nhân loại. 2 giờ chiều ngày 14 tháng 2 năm 2018, một viên thiên thạch rơi vào tầng khí quyển của trái đất...Trước khi rơi xuống, nó nổ thành vô số mảnh vỡ, rơi xuống khắp nơi trên trái đất, virus nó mang theo khi rơi nhanh chóng lan rộng! Hơn 70% số người đã biến dị thành quái vật, giống như zombies trong phim. Phương thức lây truyền cũng giống phim, người bị cắn sẽ biến thành zombies. Nhân loại rơi vào tình cảnh chủng tộc diệt hết, cướp bóc cả nơi, không có chính phụ quản lý. Có một vài người có thể kháng được loại đột biến đó, thậm chí kiểm soát nó để tiến hóa bản thân, thể lực của chúng ta có thể tăng mạnh.',1,'2131165307',NULL);");
        db.execSQL("INSERT INTO Manga VALUES('Wechat của tôi liên kết tam giới','echkeutroimua',1,'Một kẻ không tài cán, làm gì cũng bị xem như tên thất bại không được coi trong, ai ngờ đâu có sự giúp sức hậu thuẫn từ các thần tiên, đặc biệt là Tề Thiên Đại Thánh, kẻ bị coi thường lại biến thành người cứu rỗi nhân loại...',1,'2131165310',NULL);");
        db.execSQL("INSERT INTO Manga VALUES('Nhặt hoa khôi về làm vợ','echkeutroimua',1,'Khi cao thủ đã quyết định rửa tay gác kiểu,  ẩn dật quay về Đô Thành tiếp tục làm một học sinh cấp 3 bình thường. Nhưng hào quang nam chính không cho phép bị lu mờ, trở thành người nổi bật nhất trường, các hoa khôi vây quanh không rời... rắc rối cũn ùn ùn kéo đến',1,'2131165306',NULL);");
        db.execSQL("INSERT INTO Manga VALUES('Tổng tài tại thượng','echkeutroimua',1,'Cung Âu, một tên thiếu gia giàu có mắc bệnh hoang tưởng, và Thời Tiểu Niệm, một tác giả truyện tranh nghèo rớt mùng tơi và cô đơn đã bị buộc chặt lấy nhau trong một tình huống hết sức trớ trêu: Cung Âu nghĩ Thời Tiểu Niệm đã giấu nhẹm đứa con của hai người (không hề). Cùng theo dõi xem nam chính sẽ ngược nữ chính theo những cách nào, và làm thế nào mà họ yêu nhau...',1,'2131165309',NULL);");
        db.execSQL("INSERT INTO CategoryManga VALUES('Huyền huyễn','Tiên đế trở về');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Hành động','Tiên đế trở về');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Huyền huyễn','Đấu la đại lục');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Hành động','Đấu la đại lục');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Xuyên không','Đấu la đại lục');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Kinh dị','Tận thế người trần');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Hành động','Tận thế người trần');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Mạo hiểm','Tận thế người trần');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Đô thị tu tiên','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Học đường','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Hài hước','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Tình yêu','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Học đường','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Harem','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Hành động','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Tổng tài','Tổng tài tại thượng');");
        db.execSQL("INSERT INTO CategoryManga VALUES('Tình yêu','Tổng tài tại thượng');");
        db.execSQL("INSERT INTO Chapter VALUES('Tiên đế trở về-0',0,'','2020-04-15','Tiên đế trở về');");
        db.execSQL("INSERT INTO Chapter VALUES('Tiên đế trở về-1',1,'','2020-04-16','Tiên đế trở về');");
        db.execSQL("INSERT INTO Chapter VALUES('Tiên đế trở về-2',2,'','2020-04-17','Tiên đế trở về');");
        db.execSQL("INSERT INTO Chapter VALUES('Tiên đế trở về-3',4,'','2020-04-18','Tiên đế trở về');");
        db.execSQL("INSERT INTO Chapter VALUES('Tiên đế trở về-4',3,'','2020-04-19','Tiên đế trở về');");
        db.execSQL("INSERT INTO Chapter VALUES('Đấu la đại lục-0',0,'','2020-04-15','Đấu la đại lục');");
        db.execSQL("INSERT INTO Chapter VALUES('Đấu la đại lục-1',1,'','2020-04-16','Đấu la đại lục');");
        db.execSQL("INSERT INTO Chapter VALUES('Đấu la đại lục-2',2,'','2020-04-17','Đấu la đại lục');");
        db.execSQL("INSERT INTO Chapter VALUES('Đấu la đại lục-3',3,'','2020-04-18','Đấu la đại lục');");
        db.execSQL("INSERT INTO Chapter VALUES('Tận thế người trần-0',0,'','2020-04-15','Tận thế người trần');");
        db.execSQL("INSERT INTO Chapter VALUES('Tận thế người trần-1',1,'','2020-04-16','Tận thế người trần');");
        db.execSQL("INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-0',0,'','2020-04-15','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-1',1,'','2020-04-16','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-2',2,'','2020-04-17','Wechat của tôi liên kết tam giới');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-0',0,'','2020-04-15','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-1',1,'','2020-04-16','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-2',2,'','2020-04-17','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-3',3,'','2020-04-18','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-4',4,'','2020-04-19','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-5',5,'','2020-04-20','Nhặt hoa khôi về làm vợ');");
        db.execSQL("INSERT INTO Chapter VALUES('Tổng tài tại thượng-0',0,'','2020-04-15','Tổng tài tại thượng');");
        db.execSQL("INSERT INTO ChapterImage VALUES(0,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO ChapterImage VALUES(1,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO ChapterImage VALUES(2,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO ChapterImage VALUES(3,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO ChapterImage VALUES(4,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO ChapterImage VALUES(5,NULL,'','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200515010100','Main bá đạo','','2020-05-15','Katoit','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200514010100','Hay','','2020-05-14','Katoit','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200513010100','1 like','','2020-05-13','Katoit','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200512010100','Hay','','2020-05-12','Katoit','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('BacNorth-Tiên đế trở về-0-200516010100','Chuẩn','Katoit-Tiên đế trở về-0-200515010100','2020-05-16','BacNorth','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200510010100','like','Katoit-Tiên đế trở về-0-200513010100','2020-05-10','Katoit','Tiên đế trở về-0');");
        db.execSQL("INSERT INTO UserChapter VALUES('2020-05-15',0,'Katoit','Katoit-Tiên đế trở về-0-200515010100');");
        db.execSQL("INSERT INTO LikeToComment VALUES(1,'BacNorth','Katoit-Tiên đế trở về-0-200515010100');");
        db.execSQL("INSERT INTO UserManga VALUES(1,4,'BacNorth','Tiên đế trở về');");
        db.execSQL("INSERT INTO UserManga VALUES(0,5,'Katoit','Tiên đế trở về');");
        db.execSQL("INSERT INTO Follow VALUES('Katoit','BacNorth');"
        );
    }

    // Truy vấn có trả kết quả: CREATE, INSERT, UPDATE, DELETE,...
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
                "    Avatar BLOG, " +// ảnh đại diện
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
                "    IdComment NVARCHAR(370) PRIMARY KEY, " +// IdComment = UserName+MangaName+IdChap **(Khóa chính)**
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
                "    Image BLOG, " +// Ảnh
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

    // get list by category
    public Cursor GetListMangaByCategory(String categoryName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT MangaName, Author, IsManga, Introduce, UrlImage FROM CategoryManga JOIN Manga ON Manga.MangaName = CategoryManga.Manga_MangaName WHERE Category_CategoryName = '" + categoryName + "' AND Accept = 1;", null);
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
        return sqLiteDatabase.rawQuery("SELECT MangaName, Author, IsManga, Introduce, UrlImage FROM Manga;", null);
    }

}
