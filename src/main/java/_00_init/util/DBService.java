package _00_init.util;

public class DBService {
	public static final String host = "127.0.0.1";
	public static final String DB_MYSQL = "MYSQL";
	public static final String nameMy = "java:comp/env/jdbc/MemberDB";

	private static final String DBURL_MySQL = "jdbc:mysql://" + host
			+ "/jspdb?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	public static final String USERID_MySQL = "root";
	public static final String PSWD_MySQL = "cathy1228";

	private static final String DROP_Member_MySQL = "DROP Table IF EXISTS Memberinfo ";

	private static final String CREATE_Member_MySQL = " CREATE TABLE memberinfo " + "(user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, "
			+ " user_name	VARCHAR(64) NOT NULL  UNIQUE," + "user_password 	VARCHAR(64) 	NOT NULL,"
			+ " user_gender		CHAR(1)		NOT NULL, " + " user_birthday	DATE 		NOT NULL, "
			+ " user_email		VARCHAR(64) 	NOT NULL  UNIQUE, " + "user_phone		CHAR(10) 	DEFAULT NULL, "
			+ " user_address	VARCHAR(64)  	DEFAULT NULL, " + " registerTime    	DateTime, "
			+ " user_create_time	DATETIME	NOT NULL, " + "user_picture	LONGBLOB	DEFAULT NULL, "
			+ " fileName     		varchar(20), " + "user_status		VARCHAR(20)	NOT NULL, "
			+ " user_permission	VARCHAR(24)	NOT NULL "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";


	public static String getDropMember() {
		String drop = null;
		drop = DROP_Member_MySQL;
		return drop;
	}

	public static String getCreateMember() {
		String create = null;
		create = CREATE_Member_MySQL;
		return create;
	}

	public static String getDbUrl() {
		String url = null;
		url = DBURL_MySQL;
		System.out.println("url=" + url);
		return url;
	}

	public static String getUser() {
		String user = null;
		user = USERID_MySQL;
		return user;
	}

	public static String getPassword() {
		String pswd = null;
		pswd = PSWD_MySQL;
		return pswd;
	}

}
