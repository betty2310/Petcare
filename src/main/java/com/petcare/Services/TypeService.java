package com.petcare.Services;
import com.petcare.Model.Pet;
import com.petcare.Model.Service;
import com.petcare.Model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.petcare.Constants.DBConstants.*;
public class TypeService {
    public static int addType(Type type) {
        String INSERT_QUERY = "INSERT INTO type (name, info) VALUES (?, ?);";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, type.getName());
            preparedStatement.setString(2, type.getInfo());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding type: " + type, e);
        }
    }
    public static List<String> getTypes(){
        String SELECT_QUERY = "SELECT name FROM type ";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            // Tạo đối tượng Statement
            Statement statement = conn.createStatement();

            // Thực thi câu truy vấn và nhận kết quả
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);

            // Tạo danh sách để lưu trữ dữ liệu
            List<String> danhSach = new ArrayList<>();

            // Lặp qua kết quả ResultSet và thêm dữ liệu vào danh sách
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                danhSach.add(name);
            }
            return danhSach;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving type ets ", e);
        }
    }
}
