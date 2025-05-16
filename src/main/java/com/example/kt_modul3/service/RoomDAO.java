package com.example.kt_modul3.service;

import com.example.kt_modul3.model.PaymentMethod;
import com.example.kt_modul3.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.kt_modul3.service.DBConnection.getConnection;

public class RoomDAO {
    private final String GET_ALL_ROOMS =
            "SELECT pt.pt_id, pt.ma_pt, pt.ten_khach_hang, pt.so_dien_thoai, pt.ngay_thue, pt.nghi_chu, ht.hinh_thuc_tt " +
                    "FROM phong_tro pt " +
                    "JOIN Hinh_thuc_tt ht ON pt.id_tt = ht.id_tt";
    private final String GET_ALL_Method = "Select * from  Hinh_thuc_tt ";

    private  final   String INSERT_ROOM = "INSERT INTO phong_tro (ma_pt, ten_khach_hang, so_dien_thoai, ngay_thue, nghi_chu, id_tt) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private  final    String DELETE_ROOM = "DELETE FROM phong_tro";

    private Connection connection;
    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ROOMS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getInt("pt_id"));
                room.setMa_pt(resultSet.getString("ma_pt"));
                room.setTen_kh(resultSet.getString("ten_khach_hang"));
                room.setSo_dt(resultSet.getString("so_dien_thoai"));
                room.setNgay_thue(resultSet.getDate("ngay_thue").toLocalDate());
                room.setGhi_chu(resultSet.getString("nghi_chu"));
                room.setHinh_thuc(resultSet.getString("hinh_thuc_tt")); // lấy tên hình thức thanh toán
                rooms.add(room);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rooms;
    }
    public List<PaymentMethod> getAllPaymentMethods(){
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_Method);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(resultSet.getInt("id_tt"));
                paymentMethod.setHinh_thuc(resultSet.getString("hinh_thuc_tt"));
                paymentMethods.add(paymentMethod);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return paymentMethods;
    }
    public void insertRoom(Room room) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_ROOM)) {

            ps.setString(1, room.getMa_pt());
            ps.setString(2, room.getTen_kh());
            ps.setString(3, room.getSo_dt());
            ps.setDate(4, Date.valueOf(room.getNgay_thue()));
            ps.setString(5, room.getGhi_chu());
            ps.setInt(6, room.getId_tt());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Room> searchRooms(String keyword) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT r.pt_id, r.ma_pt, r.ten_khach_hang, r.so_dien_thoai, r.ngay_thue, r.nghi_chu, r.id_tt, pm.hinh_thuc_tt " +
                "FROM Phong_tro r " +  // Chú ý dấu cách cuối chuỗi
                "JOIN Hinh_thuc_tt pm ON r.id_tt = pm.id_tt " +
                "WHERE r.ten_khach_hang LIKE ? OR r.ma_pt LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("pt_id"));
                room.setMa_pt(rs.getString("ma_pt"));
                room.setTen_kh(rs.getString("ten_khach_hang"));
                room.setSo_dt(rs.getString("so_dien_thoai"));
                room.setNgay_thue(rs.getDate("ngay_thue").toLocalDate());
                room.setGhi_chu(rs.getString("nghi_chu"));
                room.setId_tt(rs.getInt("id_tt"));
                room.setHinh_thuc(rs.getString("hinh_thuc_tt"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    public void deleteRoom(int pt_id) {
        String sql = "DELETE FROM Phong_tro WHERE pt_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pt_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}




