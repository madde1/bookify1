create database bookify;

create table users(
usersId int not null auto_increment,
usersName varchar(50),
usersEmail varchar(100),
usersPassword varchar(50),
primary key(usersId)
);

create table wantToRead(
wantToReadId int not null auto_increment,
wantToReadBookId int not null,
wantToReadUserId int not null,
primary key(wantToReadId),
FOREIGN KEY (wantToReadUserId) REFERENCES users(usersId),
FOREIGN KEY (wantToReadBookId) REFERENCES book(bookId)
);

#Old
/*create table haveRead(
haveReadId int not null auto_increment,
haveReadBookId int not null,
haveReadUserId int not null,
haveReadFavorite int not null,
primary key(haveReadId),
FOREIGN KEY (haveReadUserId) REFERENCES users(usersId),
FOREIGN KEY (haveReadBookId) REFERENCES book(bookId)
);*/

DROP TABLES IF EXISTS haveRead;

#New
create table haveRead(
haveReadBookId int not null,
haveReadUserId int not null,
haveReadFavorite int not null DEFAULT FALSE,
primary key(haveReadBookId, haveReadUserId),
FOREIGN KEY (haveReadUserId) REFERENCES users(usersId),
FOREIGN KEY (haveReadBookId) REFERENCES book(bookId)
);

create table review(
	reviewId int not null auto_increment,
	reviewUserId int not null, 
	reviewbookId int not null,
	reviewText text,
	reviewDate Date,
	primary key(reviewId),
	FOREIGN KEY (reviewUserId) REFERENCES users(usersId),
	FOREIGN KEY (reviewbookId) REFERENCES book(bookId)
	);

create table genre(
genreId int not null auto_increment,
genreName varchar(50),
primary key(genreId)
);

create table bookGen(
bookGenId int not null auto_increment,
bookGenBId int not null,
bookGenGId int not null,
primary key(bookGenId),
FOREIGN key(bookGenGId) REFERENCES genre (genreId),
FOREIGN key(bookGenBId) REFERENCES book(bookId)
);

create table book(
bookId int not null auto_increment,
bookTitel varchar(50),
bookAuthor varchar(50),
bookDate date,
primary key(bookId)
);

#Old
/*create table friends (
friendsId int not null auto_increment,
friendsUId1 int not null,
friendsUId2 int not null,
primary key (friendsId),
foreign key (friendsUId1)  references users (usersId),
foreign key (friendsUId2) references users (usersId)
);*/

DROP TABLE IF EXISTS friends;

#New
create table friends (
friendsUId1 int not null,
friendsUId2 int not null,
primary key (friendsUId1, friendsUId2),
foreign key (friendsUId1)  references users (usersId),
foreign key (friendsUId2) references users (usersId)
);

