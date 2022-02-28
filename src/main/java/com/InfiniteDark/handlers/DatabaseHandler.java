package com.InfiniteDark.handlers;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;

import java.io.File;
import java.sql.*;

public class DatabaseHandler {

	private static final Connection conn = getConnection();

	/**
	 * Private constructor for class that does not require instantiation
	 */
	private DatabaseHandler() {}

	/**
	 * Creates a {@link Connection} object to the SQLite database and returns that object.
	 * @return {@link Connection} object linked to data.bd
	 */
	private static @Nullable Connection getConnection() {
		File db = new File("src/main/database");
		String url = "jdbc:sqlite:" + db.getAbsolutePath() + "\\data.db";
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Generates a new session ID that is added to the database.
	 * @return A String containing the new session ID
	 */
	public static String createNewSession() {
		String sql = "INSERT INTO sessions(SessionID, DateCreated, CurrentStage) VALUES(?,?,?)";
		String generatedString = RandomStringUtils.randomAlphanumeric(25);
		while (checkIfSessionExists(generatedString)) generatedString = RandomStringUtils.randomAlphanumeric(25);
		try {
			assert conn != null;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, generatedString);
			pstmt.setString(2, (new DateTime()).toString());
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedString;
	}

	/**
	 * Checks if the session already exists in the database "sessions" table
	 * @return A boolean value set to true if the session was found, and false otherwise
	 */
	public static boolean checkIfSessionExists(String checkSessionID) {
		Connection conn = getConnection();
		String sql = "SELECT SessionID FROM sessions WHERE SessionID=?";
		try {
			assert conn != null;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkSessionID);
			ResultSet result = pstmt.executeQuery();
			boolean isPresent = result.next();
			pstmt.close();
			result.close();
			return isPresent;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
