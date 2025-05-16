package com.example.kt_modul3.controller;

import com.example.kt_modul3.model.PaymentMethod;
import com.example.kt_modul3.model.Room;
import com.example.kt_modul3.service.RoomDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "RoomServlet", urlPatterns = "/ManageRoom")
public class RoomServlet extends HttpServlet {
    private RoomDAO roomDAO = new RoomDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                createRoom(request, response);
                break;

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                    case "delete":
                        deleteRoom(request, response);
                        break;
                default: ShowHome(request, response);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ShowHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Room> rooms;
        if (keyword != null && !keyword.trim().isEmpty()) {
            rooms = roomDAO.searchRooms(keyword.trim());
        } else {
            rooms = roomDAO.getAllRooms();
        }
        List<PaymentMethod> methods = roomDAO.getAllPaymentMethods();
        request.setAttribute("methods", methods);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/view/ManageRoom.jsp").forward(request, response);
    }


    private void createRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenKH = request.getParameter("ten_khach_hang");
        String soDT = request.getParameter("so_dien_thoai");
        String ngayStr = request.getParameter("ngay_thue");
        String ghiChu = request.getParameter("ghi_chu");
        int idTT = Integer.parseInt(request.getParameter("id_tt"));

        // Tự tạo mã phòng: VD: "PT" + timestamp hoặc số ngẫu nhiên
        String maPT = "PT" + System.currentTimeMillis();

        // VALIDATION phía server
        boolean isValid = true;
        String error = "";

        if (!tenKH.matches("^[a-zA-ZÀ-ỹ\\s]{5,50}$")) {
            isValid = false;
            error = "Tên khách hàng không hợp lệ.";
        } else if (!soDT.matches("^\\d{10}$")) {
            isValid = false;
            error = "Số điện thoại phải đủ 10 chữ số.";
        }

        LocalDate ngayThue = null;
        try {
            ngayThue = LocalDate.parse(ngayStr);
            if (ngayThue.isBefore(LocalDate.now())) {
                isValid = false;
                error = "Ngày thuê không được ở quá khứ.";
            }
        } catch (Exception e) {
            isValid = false;
            error = "Ngày thuê không hợp lệ.";
        }

        if (!isValid) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("room/add.jsp").forward(request, response);
            return;
        }

        Room room = new Room();
        room.setMa_pt(maPT);
        room.setTen_kh(tenKH);
        room.setSo_dt(soDT);
        room.setNgay_thue(ngayThue);
        room.setGhi_chu(ghiChu);
        room.setId_tt(idTT);
        roomDAO.insertRoom(room);
        response.sendRedirect("ManageRoom");

    }
    private void deleteRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pt_id = Integer.parseInt(request.getParameter("pt_id"));
        roomDAO.deleteRoom(pt_id);
        response.sendRedirect("ManageRoom");

}
}
