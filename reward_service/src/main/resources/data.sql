CREATE TABLE LOYAL_USER (USER_NUM int, USER_NAME VARCHAR2(50) );
CREATE TABLE TRANSACTION (TRX_NUM int,USER_NUM int ,TRX_DATE DATE,TOTAL int);
INSERT INTO LOYAL_USER(USER_NUM,USER_NAME) values (111,'user1');
INSERT INTO LOYAL_USER(USER_NUM,USER_NAME) values (112,'user2');
INSERT INTO TRANSACTION(TRX_NUM,USER_NUM,TRX_DATE,TOTAL) VALUES (120002,111,'2023-01-12',120);
COMMIT;