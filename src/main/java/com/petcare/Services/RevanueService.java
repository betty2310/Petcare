package com.petcare.Services;

import com.petcare.Model.Pet;
import com.petcare.Model.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.petcare.Constants.DBConstants.*;

public class RevanueService {
    public static ResultSet getRevanue(){

        String SELECT_QUERY = "SELECT Owner_ID, Type, Price, Date, Start_Time, End_Time FROM service WHERE State = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, "done");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets ", e);
        }
    }
    public static ResultSet getRevanue2(){

        String SELECT_QUERY = "SELECT Price, End_Time FROM service WHERE State = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, "done");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets ", e);
        }
    }
}
