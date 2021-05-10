/*1 Bảng Người dùng */
CREATE TABLE IF NOT EXISTS Users (
    UserName NVARCHAR(20) PRIMARY KEY, -- tên đăng nhập **(Khóa chính)**
    PassWord NVARCHAR(20), -- Mật khẩu
    Nickname NVARCHAR(20), -- Tên hiển thị trên app
    Age INTEGER, -- tuổi
    Level INTEGER, -- cấp độ
    UrlImage TEXT, -- Đường dẫn ảnh
    Avatar BLOG, -- ảnh đại diện
    IsAdmin BOOLEAN -- Admin
);
/*2 Bảng Theo dõi */
CREATE TABLE IF NOT EXISTS Follow (
    UserNameFollow NVARCHAR(20) PRIMARY KEY, -- UserName Người theo dõi **(Khóa chính)**
    User_UserName NVARCHAR(20),
    FOREIGN KEY(User_UserName) REFERENCES Users(UserName) -- User Người được theo dõi
);
/*3 Bảng Thể loại */
CREATE TABLE IF NOT EXISTS Category(
    CategoryName NVARCHAR(250) PRIMARY KEY -- Tên Thể loại **(Khóa chính)**
);
/*4 Bảng Thể Truyện */
CREATE TABLE IF NOT EXISTS Manga(
    MangaName NVARCHAR(250) PRIMARY KEY, -- Tên truyện **(Khóa chính)**
    Author NVARCHAR(100), -- tác giả
    IsManga BOOLEAN, -- Là Manga (Truyện tranh, Manga,...) hay story (Tiểu thuyết, truyện chữ ,...)
    Introduce NVARCHAR(500), -- Giới thiệu Nội Dung truyện
    Accept BOOLEAN, -- Đã được phép đăng
    UrlImage TEXT, -- Đường dẫn ảnh
    Avatar BLOG -- ảnh đại diện
);
/*5 Bảng User Tương tác với Manga */
CREATE TABLE IF NOT EXISTS UserManga(
    IsFollow BOOLEAN, -- Đang theo dõi 
    Vote INTEGER, -- Đánh giá Truyện Min 1 Max 5
    User_UserName NVARCHAR(20),
    Manga_MangaName NVARCHAR(250),
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Khóa ngoại với bảng Người dùng
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Khóa ngoại với bảng Truyện
);
/*6 Bảng các Thể loại của truyện */
CREATE TABLE IF NOT EXISTS CategoryManga(
    IdCatagoryManga NVARCHAR(100) PRIMARY KEY, -- IdCatagoryManga = CategoryNameMangaName **(Khóa chính)**
    Category_CategoryName NVARCHAR(250),
    Manga_MangaName NVARCHAR(250),
    FOREIGN KEY (Category_CategoryName) REFERENCES Category(CategoryName), -- Khóa ngoại với bảng Người dùng
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Khóa ngoại với bảng Truyện
);
/*7 Bảng Chapter*/
CREATE TABLE IF NOT EXISTS Chapter(
    IdChapter NVARCHAR(270) PRIMARY KEY, -- IdChapter = MangaName + Chap **(Khóa chính)**
    Chap INTEGER, -- Số thứ tự Chương
    ContentChapter TEXT, -- nội dung của chap truyện (Tối đa 15000 ký tự)
    Manga_MangaName NVARCHAR(250),
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Tên truyện
);
/*8 Bảng Bình luận*/
CREATE TABLE IF NOT EXISTS Comment(
    IdComment NVARCHAR(370) PRIMARY KEY, -- IdComment = UserName+MangaName+IdChap **(Khóa chính)**
    ContentComment TEXT, -- Nội dung Bình luận
    AnswerIdComment NVARCHAR(100), -- ID bình luận được trả lời
    User_UserName NVARCHAR(20),
    Chapter_IdChapter NVARCHAR(270),
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Người viết Bình luận
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- Chapter được bình luận
);
/*9 Bảng Like Bình luận*/
CREATE TABLE IF NOT EXISTS LikeToComment(
    IsLike BOOLEAN, -- Đã like
    User_UserName NVARCHAR(20),
    Comment_IdComment NVARCHAR(370),
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Người like Bình luận
    FOREIGN KEY (Comment_IdComment) REFERENCES Comment(IdComment) -- Bình luận được like
);
/*10 Bảng Ảnh Chapter*/
CREATE TABLE IF NOT EXISTS ChapterImage(
    ImageNumber INTEGER, -- Số thứ tự Chương
    Image BLOG, -- Ảnh
    UrlImage TEXT, -- Đường dẫn ảnh 
    Chapter_IdChapter NVARCHAR(270),
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- Chaoter truyện
);
/*11 Bảng User Tương tác với Chapter*/
CREATE TABLE IF NOT EXISTS UserChapter(
    IdUserChapter NVARCHAR(100) PRIMARY KEY, -- IdUserChapter = UserName + Chapter **(Khóa chính)**
    TimeReading DATETIME, -- Thời gian lần cuối đọc chap DateTime.Now
    IsLike BOOLEAN, -- Có thích Chap
    User_UserName NVARCHAR(20),
    Chapter_IdChapter NVARCHAR(270),
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Người Tương tác Chap
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- id Chapter
);
