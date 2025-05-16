create database ql_phong_tro;
use ql_phong_tro;

create table Hinh_thuc_tt(
id_tt int auto_increment primary key,
hinh_thuc_tt varchar(50) not null
);
create table Phong_tro(
pt_id int auto_increment primary key,
ma_pt varchar(10) not null ,
ten_khach_hang varchar(50) not null ,
so_dien_thoai varchar(10) not null,
ngay_thue date not null,
nghi_chu varchar(200),
id_tt int not null,
foreign key (id_tt) references Hinh_thuc_tt(id_tt)
);

insert into Hinh_thuc_tt (hinh_thuc_tt) 
values('Tháng'),
('Qúy'),
('Năm');

INSERT INTO Phong_tro (ma_pt, ten_khach_hang, so_dien_thoai, ngay_thue, nghi_chu, id_tt) 
VALUES
('PT001', 'Nguyen Van A', '0909123456', '2025-05-01', 'Khách thuê dài hạn', 1),
('PT002', 'Tran Thi B', '0912345678', '2025-05-10', NULL, 2);
SELECT r.*, pm.hinh_thuc FROM  Phong_tro r JOIN Hinh_thuc_tt pm ON r.id_tt = pm.id_tt WHERE r.ten_kh LIKE Nguyen Van A
