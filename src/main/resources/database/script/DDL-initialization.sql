CREATE TABLE USERS
(
    ID       INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME     VARCHAR(50)                        NOT NULL,
    SURNAME  VARCHAR(50)                        NOT NULL,
    EMAIL    VARCHAR(50)                        NOT NULL,
    LOGIN    VARCHAR(50)                        NOT NULL,
    PASSWORD VARCHAR(50)                        NOT NULL
);

CREATE TABLE ROLES
(
    ID      INTEGER AUTO_INCREMENT NOT NULL,
    ROLE    VARCHAR(50)            NOT NULL,
    USER_ID INTEGER                NOT NULL
);


CREATE TABLE BOOKS
(
    ID     INTEGER AUTO_INCREMENT NOT NULL,
    TITLE  VARCHAR(50)            NOT NULL,
    GENRE  VARCHAR(50)            NOT NULL,
    AUTHOR VARCHAR(50)            NOT NULL
);

CREATE table test
(
    id       int auto_increment primary key,
    data     date not null,
    mark     int  not null,
    user_id  int  not null,
    duration time not null,
    comment  text,
    foreign key (user_id) references USERS (ID) on delete no action on update cascade
);

select NAME, SURNAME, MARK, DATA, DURATION, COMMENT from USERS join TEST on USERS.ID = test.user_id;

select NAME, SURNAME, MARK, DATA, DURATION, COMMENT from USERS join TEST on USERS.ID = test.user_id where USERS.ID=7;