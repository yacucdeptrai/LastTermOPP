CREATE DATABASE QuanLyNguoiDung;
USE QuanLyNguoiDung;

CREATE TABLE NguoiDung (
    maNguoiDung VARCHAR(50) PRIMARY KEY,
    hoTen VARCHAR(100),
    soDienThoai VARCHAR(20),
    email VARCHAR(100),
    diaChi VARCHAR(200),
    matKhau VARCHAR(256)
);
