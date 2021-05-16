/* 1 Users */
select * from users;
--
INSERT INTO Users VALUES('admin','manAn1207*','Admin',21,99,'',NULL,1);
INSERT INTO Users VALUES('Katoit','123123','Katoit Man',21,1,'',NULL,0);
INSERT INTO Users VALUES('BacNorth','123123','Tiến Bắc',21,1,'',NULL,0);
INSERT INTO Users VALUES('echkeutroimua','123123','Ếch kêu trời mưa',21,1,'',NULL,0);
/* 2 Category */
select * from Category;
--
INSERT INTO Category VALUES('Huyền huyễn');
INSERT INTO Category VALUES('Học đường');
INSERT INTO Category VALUES('Tổng tài');
INSERT INTO Category VALUES('Xuyên không');
INSERT INTO Category VALUES('Kinh dị');
INSERT INTO Category VALUES('Harem');
INSERT INTO Category VALUES('Hành động');
INSERT INTO Category VALUES('Tình yêu');
INSERT INTO Category VALUES('Hài hước');
INSERT INTO Category VALUES('Viễn tưởng');
INSERT INTO Category VALUES('Mạo hiểm');
INSERT INTO Category VALUES('Đô thị tu tiên');
INSERT INTO Category VALUES('Trinh thám');
/* 3 Manga */
select * from Manga;
--
INSERT INTO Manga VALUES('Tiên đế trở về','echkeutroimua',1,'Ba năm trước, Vân Thanh Nham bất ngờ từ nhân gian rơi vào tiên giới. Ba nghìn năm sau, hắn trở thành vân đế của tiên giới, phá được hư không, trở về nhân giới. Hắn phát hiện thời gian ở đây mới chỉ qua có 3 năm. "Trước đây ta không có sức mạnh bảo vệ những người ta yêu thương, bây giờ, cả thế giới đều phải quỳ dưới chân ta',1,'',NULL);
INSERT INTO Manga VALUES('Đấu la đại lục','echkeutroimua',1,'Đường tam học trộm tuyệt thế võ công nhưng lại nhảy xuống vách núi tự sát để chứng minh sự minh bạch của mình, sau khi chuyển thế không ngờ lại trở thành một cậu bé đáng yêu luôn được mọi người ngưỡng mộ?! Anh ấy mang theo song võ hồn, phách lực tiên thiên mãn trời sinh gia nhập học viện Nặc Đinh, gặp được tiểu vũ dễ thương, sau đó bắt đầu một câu chuyện nhi nữ tình trường, mưa máu gió tanh.',1,'',NULL);
INSERT INTO Manga VALUES('Tận thế người trần','echkeutroimua',1,'Thế giới tương lai là thế giới xấu xí của nhân loại. 2 giờ chiều ngày 14 tháng 2 năm 2018, một viên thiên thạch rơi vào tầng khí quyển của trái đất...Trước khi rơi xuống, nó nổ thành vô số mảnh vỡ, rơi xuống khắp nơi trên trái đất, virus nó mang theo khi rơi nhanh chóng lan rộng! Hơn 70% số người đã biến dị thành quái vật, giống như zombies trong phim. Phương thức lây truyền cũng giống phim, người bị cắn sẽ biến thành zombies. Nhân loại rơi vào tình cảnh chủng tộc diệt hết, cướp bóc cả nơi, không có chính phụ quản lý. Có một vài người có thể kháng được loại đột biến đó, thậm chí kiểm soát nó để tiến hóa bản thân, thể lực của chúng ta có thể tăng mạnh.',1,'',NULL);
INSERT INTO Manga VALUES('Wechat của tôi liên kết tam giới','echkeutroimua',1,'Một kẻ không tài cán, làm gì cũng bị xem như tên thất bại không được coi trong, ai ngờ đâu có sự giúp sức hậu thuẫn từ các thần tiên, đặc biệt là Tề Thiên Đại Thánh, kẻ bị coi thường lại biến thành người cứu rỗi nhân loại...',1,'',NULL);
INSERT INTO Manga VALUES('Nhặt hoa khôi về làm vợ','echkeutroimua',1,'Khi cao thủ đã quyết định rửa tay gác kiểu,  ẩn dật quay về Đô Thành tiếp tục làm một học sinh cấp 3 bình thường. Nhưng hào quang nam chính không cho phép bị lu mờ, trở thành người nổi bật nhất trường, các hoa khôi vây quanh không rời... rắc rối cũn ùn ùn kéo đến',1,'',NULL);
INSERT INTO Manga VALUES('Tổng tài tại thượng','echkeutroimua',1,'Cung Âu, một tên thiếu gia giàu có mắc bệnh hoang tưởng, và Thời Tiểu Niệm, một tác giả truyện tranh nghèo rớt mùng tơi và cô đơn đã bị buộc chặt lấy nhau trong một tình huống hết sức trớ trêu: Cung Âu nghĩ Thời Tiểu Niệm đã giấu nhẹm đứa con của hai người (không hề). Cùng theo dõi xem nam chính sẽ ngược nữ chính theo những cách nào, và làm thế nào mà họ yêu nhau...',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
INSERT INTO Manga VALUES('','echkeutroimua',1,'',1,'',NULL);
/* 4 Chapter */
select * from CategoryManga;
--
INSERT INTO CategoryManga VALUES('Huyền huyễn','Tiên đế trở về');
INSERT INTO CategoryManga VALUES('Hành động','Tiên đế trở về');
INSERT INTO CategoryManga VALUES('Huyền huyễn','Đấu la đại lục');
INSERT INTO CategoryManga VALUES('Hành động','Đấu la đại lục');
INSERT INTO CategoryManga VALUES('Xuyên không','Đấu la đại lục');
INSERT INTO CategoryManga VALUES('Kinh dị','Tận thế người trần');
INSERT INTO CategoryManga VALUES('Hành động','Tận thế người trần');
INSERT INTO CategoryManga VALUES('Mạo hiểm','Tận thế người trần');
INSERT INTO CategoryManga VALUES('Đô thị tu tiên','Wechat của tôi liên kết tam giới');
INSERT INTO CategoryManga VALUES('Học đường','Wechat của tôi liên kết tam giới');
INSERT INTO CategoryManga VALUES('Hài hước','Wechat của tôi liên kết tam giới');
INSERT INTO CategoryManga VALUES('Tình yêu','Nhặt hoa khôi về làm vợ');
INSERT INTO CategoryManga VALUES('Học đường','Nhặt hoa khôi về làm vợ');
INSERT INTO CategoryManga VALUES('Harem','Nhặt hoa khôi về làm vợ');
INSERT INTO CategoryManga VALUES('Hành động','Nhặt hoa khôi về làm vợ');
INSERT INTO CategoryManga VALUES('Tổng tài','Tổng tài tại thượng');
INSERT INTO CategoryManga VALUES('Tình yêu','Tổng tài tại thượng');
/* 5 Chapter */
select * from Chapter;
--
INSERT INTO Chapter VALUES('Tiên đế trở về-0',0,'','2020-04-15','Tiên đế trở về');
INSERT INTO Chapter VALUES('Tiên đế trở về-1',1,'','2020-04-16','Tiên đế trở về');
INSERT INTO Chapter VALUES('Tiên đế trở về-2',2,'','2020-04-17','Tiên đế trở về');
INSERT INTO Chapter VALUES('Đấu la đại lục-0',0,'','2020-04-15','Đấu la đại lục');
INSERT INTO Chapter VALUES('Đấu la đại lục-1',1,'','2020-04-16','Đấu la đại lục');
INSERT INTO Chapter VALUES('Đấu la đại lục-2',2,'','2020-04-17','Đấu la đại lục');
INSERT INTO Chapter VALUES('Tận thế người trần-0',0,'','2020-04-15','Tận thế người trần');
INSERT INTO Chapter VALUES('Tận thế người trần-1',1,'','2020-04-16','Tận thế người trần');
INSERT INTO Chapter VALUES('Tận thế người trần-2',2,'','2020-04-17','Tận thế người trần');
INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-0',0,'','2020-04-15','Wechat của tôi liên kết tam giới');
INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-1',1,'','2020-04-16','Wechat của tôi liên kết tam giới');
INSERT INTO Chapter VALUES('Wechat của tôi liên kết tam giới-2',2,'','2020-04-17','Wechat của tôi liên kết tam giới');
INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-0',0,'','2020-04-15','Nhặt hoa khôi về làm vợ');
INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-1',1,'','2020-04-16','Nhặt hoa khôi về làm vợ');
INSERT INTO Chapter VALUES('Nhặt hoa khôi về làm vợ-2',2,'','2020-04-17','Nhặt hoa khôi về làm vợ');
INSERT INTO Chapter VALUES('Tổng tài tại thượng-0',0,'','2020-04-15','Tổng tài tại thượng');
INSERT INTO Chapter VALUES('Tổng tài tại thượng-1',1,'','2020-04-16','Tổng tài tại thượng');
INSERT INTO Chapter VALUES('Tổng tài tại thượng-2',2,'','2020-04-17','Tổng tài tại thượng');
/* 6 ChapterImage */
select * from ChapterImage;
--
INSERT INTO ChapterImage VALUES(0,NULL,'','Tiên đế trở về-0');
INSERT INTO ChapterImage VALUES(1,NULL,'','Tiên đế trở về-0');
INSERT INTO ChapterImage VALUES(2,NULL,'','Tiên đế trở về-0');
INSERT INTO ChapterImage VALUES(3,NULL,'','Tiên đế trở về-0');
INSERT INTO ChapterImage VALUES(4,NULL,'','Tiên đế trở về-0');
INSERT INTO ChapterImage VALUES(5,NULL,'','Tiên đế trở về-0');

/* 7 Comment */
select * from Comment;
--
INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200515010100','Main bá đạo','','2020-05-15','Katoit','Tiên đế trở về-0');
INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200514010100','Hay','','2020-05-14','Katoit','Tiên đế trở về-0');
INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200513010100','1 like','','2020-05-13','Katoit','Tiên đế trở về-0');
INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200512010100','Hay','','2020-05-12','Katoit','Tiên đế trở về-0');
INSERT INTO Comment VALUES('BacNorth-Tiên đế trở về-0-200516010100','Chuẩn','Katoit-Tiên đế trở về-0-200515010100','2020-05-16','BacNorth','Tiên đế trở về-0');
INSERT INTO Comment VALUES('Katoit-Tiên đế trở về-0-200510010100','like','Katoit-Tiên đế trở về-0-200513010100','2020-05-10','Katoit','Tiên đế trở về-0');

/* 8 UserChapter */
select * from UserChapter;
--
INSERT INTO UserChapter VALUES('2020-05-15',0,'Katoit','Katoit-Tiên đế trở về-0-200515010100');

/* 9 LikeToComment */
select * from LikeToComment;
--
INSERT INTO LikeToComment VALUES(1,'BacNorth','Katoit-Tiên đế trở về-0-200515010100');

/* 10 UserManga */
select * from UserManga;
--
INSERT INTO UserManga VALUES(1,4,'BacNorth','Tiên đế trở về');
INSERT INTO UserManga VALUES(0,5,'Katoit','Tiên đế trở về');

/* 11 Follow */
select * from Follow;
--
INSERT INTO Follow VALUES('Katoit','BacNorth');

