package com.example.kt_modul3.model;

import java.time.LocalDate;

public class Room {
 private int id;
 private String ma_pt;
 private String ten_kh;
 private  String so_dt;
 private LocalDate ngay_thue;
 private String hinh_thuc;
 private String ghi_chu;

 public int getId_tt() {
  return id_tt;
 }

 public void setId_tt(int id_tt) {
  this.id_tt = id_tt;
 }

 private int id_tt;

 public Room() {
  this.ma_pt = ma_pt;
  this.ten_kh = ten_kh;
  this.ngay_thue = ngay_thue;
  this.hinh_thuc = hinh_thuc;
  this.ghi_chu = ghi_chu;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getMa_pt() {
  return ma_pt;
 }

 public void setMa_pt(String ma_pt) {
  this.ma_pt = ma_pt;
 }

 public String getTen_kh() {
  return ten_kh;
 }

 public void setTen_kh(String ten_kh) {
  this.ten_kh = ten_kh;
 }

 public String getSo_dt() {
  return so_dt;
 }
 public void setSo_dt(String so_dt) {
  this.so_dt = so_dt;
 }

 public LocalDate getNgay_thue() {
  return ngay_thue;
 }

 public void setNgay_thue(LocalDate ngay_thue) {
  this.ngay_thue = ngay_thue;
 }

 public String getHinh_thuc() {
  return hinh_thuc;
 }

 public void setHinh_thuc(String hinh_thuc) {
  this.hinh_thuc = hinh_thuc;
 }

 public String getGhi_chu() {
  return ghi_chu;
 }

 public void setGhi_chu(String ghi_chu) {
  this.ghi_chu = ghi_chu;
 }
}
