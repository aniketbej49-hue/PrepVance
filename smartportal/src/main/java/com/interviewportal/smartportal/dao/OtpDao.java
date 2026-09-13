package com.interviewportal.smartportal.dao;
import com.interviewportal.smartportal.database.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.sql.ResultSet;

@Repository
public class OtpDao {

   public void saveOtp(String email, String otp, LocalDateTime expiryTime) {

        String sql = "INSERT INTO password_reset_otp (email, otp, expiry_time) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, otp);
            statement.setObject(3, expiryTime);

            statement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public boolean verifyOtp(String email, String otp) {

    String sql = """
            SELECT otp, expiry_time
            FROM password_reset_otp
            WHERE email = ?
            ORDER BY id DESC
            LIMIT 1
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            String storedOtp = resultSet.getString("otp");
            LocalDateTime expiryTime =
                    resultSet.getObject("expiry_time", LocalDateTime.class);

            if (!storedOtp.equals(otp)) {
                return false;
            }

            if (LocalDateTime.now().isAfter(expiryTime)) {
                return false;
            }

            return true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
} 

