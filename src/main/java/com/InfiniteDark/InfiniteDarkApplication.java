package com.InfiniteDark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class InfiniteDarkApplication {

	public static void main(String[] args) {
//		File db = new File("src/main/database");
//		String filePath = db.getAbsolutePath() + "\\data.db";
//		createNewDatabase(filePath);
		SpringApplication.run(InfiniteDarkApplication.class, args);
	}
//
//	public static void createNewDatabase(String fileName) {
//
//		String url = "jdbc:sqlite:" + fileName;
//
//		try (Connection conn = DriverManager.getConnection(url)) {
//			if (conn != null) {
//				DatabaseMetaData meta = conn.getMetaData();
//				System.out.println("The driver name is " + meta.getDriverName());
//				System.out.println("A new database has been created at" + url);
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}

}
