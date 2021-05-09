/* Bảng Người dùng */
CREATE TABLE   Users (
    UserName NVARCHAR(20) PRIMARY KEY, -- tên đăng nhập **(Khóa chính)**
    PassWord NVARCHAR(20), -- Mật khẩu
    Nickname NVARCHAR(20), -- Tên hiển thị trên app
    Age INTEGER, -- tuổi
    Level INTEGER, -- cấp độ
    UrlImage TEXT, -- Đường dẫn ảnh
    Avatar BLOG -- ảnh đại diện
);
/* Bảng Theo dõi */
CREATE TABLE   Follow (
    UserNameFollow NVARCHAR(20) PRIMARY KEY, -- UserName Người theo dõi **(Khóa chính)**
    FOREIGN KEY (Users_UserName) REFERENCES Users(UserName) -- User Người được theo dõi
);
/* Bảng Thể loại */
CREATE TABLE   Category(
    CategoryName NVARCHAR(250) PRIMARY KEY -- Tên Thể loại **(Khóa chính)**
);
/* Bảng Thể Truyện */
CREATE TABLE   Manga(
    MangaName NVARCHAR(250) PRIMARY KEY, -- Tên truyện **(Khóa chính)**
    Author NVARCHAR(100), -- tác giả
    IsManga BOOLEAN, -- Là Manga (Truyện tranh, Manga,...) hay story (Tiểu thuyết, truyện chữ ,...)
    Introduce NVARCHAR(500), -- Giới thiệu Nội Dung truyện
    Accept BOOLEAN, -- Đã được phép đăng
    UrlImage TEXT, -- Đường dẫn ảnh
    Avatar BLOG -- ảnh đại diện
    
);
/* Bảng User Tương tác với Manga */
CREATE TABLE   UserManga(
    IsFollow BOOLEAN, -- Đang theo dõi 
    Vote INTEGER, -- Đánh giá Truyện Min 1 Max 5
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Khóa ngoại với bảng Người dùng
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Khóa ngoại với bảng Truyện
);
/* Bảng các Thể loại của truyện */
CREATE TABLE   CategoryManga(
    IdCatagoryManga NVARCHAR(100) PRIMARY KEY, -- IdCatagoryManga = CategoryNameMangaName **(Khóa chính)**
    FOREIGN KEY (Category_CategoryName) REFERENCES Category(CategoryName), -- Khóa ngoại với bảng Người dùng
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Khóa ngoại với bảng Truyện
);
/* Bảng Chapter*/
CREATE TABLE   Chapter(
    IdChapter NVARCHAR(100) PRIMARY KEY, -- IdChapter = MangaName + Chap **(Khóa chính)**
    Chap INTEGER, -- Số thứ tự Chương
    ContentChapter TEXT, -- nội dung của chap truyện (Tối đa 15000 ký tự)
    FOREIGN KEY (Manga_MangaName) REFERENCES Manga(MangaName) -- Tên truyện
);
/* Bảng Bình luận*/
CREATE TABLE   Comment(
    IdComment NVARCHAR(100) PRIMARY KEY, -- IdComment = UserName+MangaName+IdChap **(Khóa chính)**
    ContentComment TEXT, -- Nội dung Bình luận
    AnswerIdComment NVARCHAR(100), -- ID bình luận được trả lời
    FOREIGN KEY (AuthorComment) REFERENCES Users(UserName), -- Người viết Bình luận
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- Chapter được bình luận
);
/* Bảng Like Bình luận*/
CREATE TABLE   LikeToComment(
    IsLike BOOLEAN, -- Đã like
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Người like Bình luận
    FOREIGN KEY (Comment_IdComment) REFERENCES Comment(IdComment) -- Bình luận được like
);
/* Bảng Ảnh Chapter*/
CREATE TABLE   ChapterImage(
    ImageNumber INTEGER, -- Số thứ tự Chương
    Image BLOG, -- Ảnh
    UrlImage TEXT, -- Đường dẫn ảnh 
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- Chaoter truyện
);
/* Bảng User Tương tác với Chapter*/
CREATE TABLE   UserChapter(
    IdUserChapter NVARCHAR(100) PRIMARY KEY, -- IdUserChapter = UserName + Chapter **(Khóa chính)**
    TimeReading DATETIME, -- Thời gian lần cuối đọc chap DateTime.Now
    IsLike BOOLEAN, -- Có thích Chap
    FOREIGN KEY (User_UserName) REFERENCES Users(UserName), -- Người Tương tác Chap
    FOREIGN KEY (Chapter_IdChapter) REFERENCES Chapter(IdChapter) -- id Chapter
);
