package com.InfiniteDark.Controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.sql.*;

@RestController
public class SessionController {

    Connection conn;

    public SessionController() {
        File db = new File("src/main/database");
        String url = "jdbc:sqlite:" + db.getAbsolutePath() + "\\data.db";

        try {
            this.conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getSession")
    public String initializeSession(@CookieValue(name = "sessionID") String sessionID) {
        return generateSessionID();
    }


    private String generateSessionID() {
        String sql = "INSERT INTO sessions(SessionID, DateCreated, CurrentStage) VALUES(?,?,?)";
        String generatedString = RandomStringUtils.randomAlphanumeric(25);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, generatedString);
            pstmt.setString(2, (new DateTime()).toString());
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            generatedString = generateSessionID();
        }
        return generatedString;
    }

}
