##使用资料
[Sping文档]（https://spring.io/guides/gs/serving-web-content/#scratch）
[GitHub OAuth文档]（https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/） 
##工具
git
git

##脚本
```sql
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);
```