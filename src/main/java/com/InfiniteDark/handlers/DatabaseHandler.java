package com.InfiniteDark.handlers;

import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;
import com.ExtendedDoc.HTMLHandlers.ExtendedDocFactory;
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
		String sql1 = "INSERT INTO sessions(SessionID, DateCreated, CurrentStage) VALUES(?,?,?)";
		String sql2 = "INSERT INTO page(SessionID, JSONDoc) VALUES(?,?)";
		String generatedString = RandomStringUtils.randomAlphanumeric(25);
		while (checkIfSessionExists(generatedString)) generatedString = RandomStringUtils.randomAlphanumeric(25);
		try {
			assert conn != null;
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, generatedString);
			pstmt1.setString(2, (new DateTime()).toString());
			pstmt1.setInt(3, 0);
			pstmt1.executeUpdate();
			pstmt1.close();
			ExtendedDoc doc = (new ExtendedDocFactory()).createExtendedDoc("src/main/webpage/index.html");
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, generatedString);
			pstmt2.setString(2, ((new ExtendedDocFactory()).getJsonExtendedDoc(doc)));
			pstmt2.executeUpdate();
			pstmt2.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedString;
	}

	/**
	 * Checks if the session already exists in the database "sessions" table
	 * @param checkSessionID String session ID to be checked
	 * @return A boolean value set to true if the session was found, and false otherwise
	 */
	public static boolean checkIfSessionExists(String checkSessionID) {
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

	/**
	 * Checks if the session already exists in the database "sessions" table
	 * @return A boolean value set to true if the session was found, and false otherwise
	 */
	public static void setSessionPage(String sessionID, String JSONDoc) {
		String sql = "UPDATE page SET JSONDoc=? WHERE SessionID=?";
		try {
			assert conn != null;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, JSONDoc);
			pstmt.setString(2, sessionID);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the session already exists in the database "sessions" table
	 * @return A boolean value set to true if the session was found, and false otherwise
	 */
	public static void incrementStage(String sessionID) {
		String sql = "UPDATE sessions SET CurrentStage = CurrentStage + 1 WHERE SessionID=?";
		try {
			assert conn != null;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionID);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
