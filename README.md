# Online Library WebSite (Java Web Application Development Assignment)

## 💡 Introduction

The Online Library Website that allows members to borrow and return books quickly and conveniently. And more especially during this COVID 19 pandemic.

## 👋 Background

Assignment for `Java Web Application Development (PRJ301)` subject at FPT University.

## ⚙️ Technology

- Front End
  - `HTML/CSS`
  - `Bootstrap 4`
  - `JSTL` - Standard Tag Library for JSP
- Backend
  - `Servlet` - Java programming language class that is used to extend the capabilities of servers
  - `JDBC` - A Java API to connect and execute the query with the database
  - `MS SQL` - A relational database management system developed by Microsoft

## Preview images

![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/login.PNG)
![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/register.PNG)
![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/home_member.png)
![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/view_detial.PNG)
![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/cart.PNG)
![Source code](https://github.com/SE151251/Online_Library_Website/blob/main/img_show/home_admin.png)

## 🏃‍♂️ How to run project

- To run this project, you should use Netbeans IDE to run easily, another IDE like IntelliJ, Eclipse,... You need to find the way to config this project before using.
- After import project to IDE, please import `JSTL Library` and `external library` from `lib` folder.
- At DBUtils.java, you need to change password to access Database:

```java
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_SE1612_LA_OnlineLibraryWebsite2";
            Connection con = DriverManager.getConnection(url, "sa", "25032001"); // change with your password here
```

- Set up database by running the `script.sql` file.
- Finally, let's try to go website.

## ⚠️ Note

- To use `Admin` role with this project, please login with `username: vip; password: 123`
- To use `User` role with this project, please login with `username: member; password: 123` or use `Sign up` to create account.

## License & copyright

© Group LA: 
- Nguyễn Hồ Tiến Đạt - SE151251
- Lê Minh Thiện - SE151226
- Nguyễn Trọng Nguyên - SE151227
- Đỗ Cao Tường - SE151377

