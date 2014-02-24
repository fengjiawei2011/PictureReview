package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionHelper {
	
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "111111";
	private String URL = "jdbc:mysql://192.168.1.55:3306/Rideo";
//	private String password = "root";
//	private String URL = "jdbc:mysql://localhost/Rideo";
	private String table = "Published_ImRep_Wanying";
	
//	private String URL_ContentDiary = "jdbc:mysql://192.168.1.55:3306/ContentDiary";
//	private String table_ContentDiary = "movie";
	
	
	public Connection connectDatabase() {
		Connection connection = null;
		try {
			Class.forName(dbDriver).newInstance();
			connection = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

//	public Connection connectDatabase_ContentDiary() {
//		Connection connection = null;
//		try {
//			Class.forName(dbDriver).newInstance();
//			connection = DriverManager.getConnection(URL_ContentDiary, username, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	
//	public String getTable_ContentDiary() {
//		return table_ContentDiary;
//	}

	public String getTable() {
		return table;
	}
}
