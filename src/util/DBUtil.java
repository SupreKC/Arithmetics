package util;

import java.sql.Connection;
import java.sql.DriverManager;

import jdk.internal.dynalink.beans.StaticClass;

public class DBUtil {
	public static String error = null;
	public static Connection getConnection() {
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String username = "sa";
		String password = "qwe1314521";
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s", "ssmzrlxhsxff.mysql.sae.sina.com.cn", 10114, "td_Snacks");
		
		try {
		    Class.forName(driver).newInstance();
		    con = DriverManager.getConnection(dbUrl, username, password);
		    error = dbUrl;
		} catch (Exception e) {
		    error = dbUrl;
		    e.printStackTrace();
		}
		return con;
	}
}
