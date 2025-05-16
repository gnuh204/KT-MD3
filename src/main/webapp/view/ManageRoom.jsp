<%--
  Created by IntelliJ IDEA.
  User: ignuh
  Date: 5/16/2025
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Quan ly</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous">
  <!-- Option 1: Include in HTML -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="container mt-4">
  <h2 class="mb-4 text-center">📋 Quản lý phòng trọ</h2>

  <!-- Nút thêm phòng -->
  <div class="mb-3 text-end">
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addRoomModal">+ Thêm phòng</button>
  </div>
  <form class="d-flex mb-3" method="get" action="ManageRoom">
    <input class="form-control me-2" type="search" name="keyword" placeholder="Tìm kiếm theo tên hoặc mã phòng" aria-label="Search" value="${param.keyword != null ? param.keyword : ''}">
    <button class="btn btn-outline-success" type="submit">Tìm kiếm</button>
  </form>
  <!-- Bảng danh sách phòng -->
  <div class="table-responsive">
    <table class="table table-bordered table-hover bg-white">
      <thead class="table-dark">
      <tr>
        <th>STT</th>
        <th>Mã phòng</th>
        <th>Tên người thuê</th>
        <th>Số điện Thoại</th>
        <th>Ngày bắt đầu thuê</th>
        <th>hình thức thanh toán</th>
        <th>ghi chú</th>
        <th>Xóa</th>
        <th>chọn</th>

      </tr>
      </thead>
      <tbody>
      <c:forEach var="room" items="${rooms}" >
        <tr>
          <td>${room.id}</td>
          <td>${room.ma_pt}</td>
          <td>${room.ten_kh}</td>
          <td>${room.so_dt}</td>
          <td>${room.ngay_thue}</td>
          <td><span class="badge bg-success">${room.hinh_thuc}</span></td>
          <td>${room.ghi_chu}</td>
          <td>
            <a href="ManageRoom?action=delete&pt_id=${room.id}"
               class="btn btn-sm btn-danger"
               onclick="return confirm('Bạn có chắc muốn xóa phòng này không?');">Xóa</a>
          </td>
          <td><input type="checkbox" id="check"></td>
        </tr>
      </c:forEach>

      </tbody>
    </table>
  </div>
</div>

<!-- Modal thêm phòng -->
<div class="modal fade" id="addRoomModal" tabindex="-1" aria-labelledby="addRoomModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addRoomModalLabel">Thêm phòng mới</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label class="form-label">Tên khách hàng</label>
          <input type="text" class="form-control" required>
        </div>
        <div class="mb-3">
          <label class="form-label">Số điện thoại</label>
          <input type="text" class="form-control" required>
        </div>
        <div class="mb-3">
          <label class="form-label">Ngày thuê</label>
          <input type="text" class="form-control" required>
        </div>
        <div class="mb-3">
          <label class="form-label">Hình thức</label>
          <select class="form-select">
            <c:forEach var="method" items="${methods}">
              <option>${method.hinh_thuc}</option>
            </c:forEach>


          </select>
        </div>
        <div class="mb-3">
          <label class="form-label">Nghi chú</label>
          <input type="text" class="form-control" required>
        </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Lưu</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
      </div>
    </form>


  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
