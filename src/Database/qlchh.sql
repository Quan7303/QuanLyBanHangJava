CREATE DATABASE qlch;
USE qlch;

CREATE TABLE LoaiSanPham (
    MaloaiSP  VARCHAR(5) NOT NULL,
    TenloaiSP VARCHAR(100) NOT NULL,
    GhiChu    VARCHAR(100) NULL,
    PRIMARY KEY (MaloaiSP)
);

CREATE TABLE PhongBan (
    MaPB   VARCHAR(5) NOT NULL,
    TenPB  VARCHAR(100) NOT NULL,
    GhiChu VARCHAR(100) NULL,
    PRIMARY KEY (MaPB)
);

CREATE TABLE NhaCungCap (
    MaNCC     VARCHAR(5) NOT NULL,
    TenNCC    VARCHAR(100) NOT NULL,
    DiaChi    VARCHAR(100) NULL,
    DienThoai VARCHAR(15) NULL,
    Email     VARCHAR(100) NULL,
    PRIMARY KEY (MaNCC)
);

CREATE TABLE KhachHang (
    MaKH      VARCHAR (5)    NOT NULL,
    TenKH     VARCHAR (100) NOT NULL,
    DiaChi    VARCHAR (100) NULL,
    NgaySinh  DATETIME       NULL,
    DienThoai VARCHAR (15)  NULL,
    PRIMARY KEY (MaKH)
);

CREATE TABLE NhanVien (
    MaNV       VARCHAR(5) NOT NULL,
    HoNV       VARCHAR(30) NOT NULL,
    TenNV      VARCHAR(100) NOT NULL,
    GioiTinh   VARCHAR(10) NULL,
    NgaySinh   DATE NULL,
    DiaChi     VARCHAR(100) NULL,
    DienThoai  VARCHAR(15) NULL,
    NoiSinh    VARCHAR(30) NULL,
    NgayVaoLam DATE NULL,
    Email      VARCHAR(50) NULL,
    MaPB       VARCHAR(5) NULL,
    PRIMARY KEY (MaNV),
    CHECK (GioiTinh=N'Nu' OR GioiTinh=N'Nam'),
    FOREIGN KEY (MaPB) REFERENCES PhongBan(MaPB)
);

CREATE TABLE SanPham (
    MaSP      VARCHAR(5) NOT NULL,
    TenSP     VARCHAR(100) NOT NULL,
    SLTon     INT NULL,
    DonViTinh NVARCHAR(10) NULL,
    MaloaiSP  VARCHAR(5) NULL,
    PRIMARY KEY (MaSP),
    FOREIGN KEY (MaloaiSP) REFERENCES LoaiSanPham(MaloaiSP)
);


CREATE TABLE HoaDon (
    MaHD   VARCHAR(5) NOT NULL,
    MaKH   VARCHAR(5) NULL,
    MaNV   VARCHAR(5) NULL,
    NgayDH DATETIME NULL,
    NgayNH DATETIME NULL,
    PTTT   VARCHAR(100) NULL,
    PRIMARY KEY (MaHD),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE PhieuNhap (
    SoPN     VARCHAR(5) NOT NULL,
    NgayNhap DATETIME NULL,
    GhiChu   VARCHAR(100) NULL,
    MaNCC    VARCHAR(5) NULL,
    PRIMARY KEY (SoPN),
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC)
);

CREATE TABLE PhieuXuat (
    SoPX     VARCHAR(5) NOT NULL,
    NgayXuat DATETIME NULL,
    GhiChu   VARCHAR(100) NULL,
    MaNV     VARCHAR(5) NULL,
    PRIMARY KEY (SoPX),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE CTHoaDon (
    MaHD       VARCHAR(5) NOT NULL,
    MaSP       VARCHAR(5) NOT NULL,
    SoLuongDat INT NULL,
    DGBan      REAL NULL,
    PRIMARY KEY (MaHD, MaSP),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
);

CREATE TABLE CTPhieuNhap (
    SoPN    VARCHAR(5) NOT NULL,
    MaSP    VARCHAR(5) NOT NULL,
    SoLuong INT NULL,
    DGNhap  REAL NULL,
    PRIMARY KEY (SoPN, MaSP),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP),
    FOREIGN KEY (SoPN) REFERENCES PhieuNhap(SoPN)
);

CREATE TABLE CTPhieuXuat (
    SoPX    VARCHAR(5) NOT NULL,
    MaSP    VARCHAR(5) NOT NULL,
    SoLuong INT NULL,
    DGBan   REAL NULL,
    PRIMARY KEY (SoPX, MaSP),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP),
    FOREIGN KEY (SoPX) REFERENCES PhieuXuat(SoPX)
);

INSERT INTO LoaiSanPham (MaLoaiSP, TenLoaiSP, GhiChu) VALUES
('LSP01','Gong kinh',NULL),
('LSP02','Trong kinh',NULL),
('LSP03','Kinh mat',NULL),
('LSP04','Nuoc rua kinh',NULL),
('LSP05','Khac',NULL);

INSERT INTO PhongBan (MaPB, TenPB, GhiChu)
VALUES
('PB01', 'Ban giam doc', NULL),
('PB02', 'Ban quan ly', NULL),
('PB03', 'Ban nhan su', NULL),
('PB04', 'Ban ke toan', NULL),
('PB05', 'Ban ban hang', NULL),
('PB06', 'Ban khac', NULL);

INSERT INTO NhaCungCap (MaNCC, TenNCC, DiaChi, DienThoai, Email) VALUES
('NCC01','Cong Ty CP Thuong Mai Kavi Viet Nam','So 10, Ngo 31, Duong Doan Ke Thien','0915508999','sonhoanghoai@gmail.com'),
('NCC02','Cong Ty TNHH MTV Nhon My','1448, Duong 3/2','0908134772','contact@nhonmy.com'),
('NCC03','Cong Ty TNHH Thuong Mai Dich Vu Xuan Nguyen','19F/31B Dang Chat','02838510209','trannguyenltd@vnn.vn'),
('NCC04','Kinh Thuoc Dong Tien Optic','518A – 518B Thong Nhat','0938470617','anhthao2620@gmail.com'),
('NCC05','Cong Ty CP Dau Tu Khai Hoan Minh','1054 QL51','0917509111','minhchuyengk@gmail.com'),
('NCC06','Kinh Mat VNS','259 Doi Can','0949198866',NULL),
('NCC07','Cong Ty TNHH Mat Kinh Truong Hao Phat','258 Ly Tu Trong','02838247332','haophat@matkinh.com.vn'),
('NCC08','Cua Hang Mat Kinh Viet Long','So 183 Nguyen Dinh Chieu','0983456757','matkinhvietlong@gmail.com'),
('NCC09','Cong Ty TNHH Mat Viet Tin','345 P. Dich Vong','02437673168',NULL),
('NCC10','Cong Ty TNHH KSC VINA','122/58 Duong Quang Ham','0919950511','trang.kscvina@gmail.com'),
('NCC11','Cong Ty TNHH San Xuat Va Thuong Mai Khanh Linh','So 21 Vu Xuan Thieu','0946861123','info@khanhlinhoptic.vn'),
('NCC12','Cong Ty TNHH Thuong Mai Mat Kinh Nam Duy','26 Le Truc','0986808212','phamngkimduyen@gmail.com'),
('NCC13','Doanh Nghiep Tu Nhan Mat Kinh Khanh Hung','70 Nguyen Thai Hoc','02836289206','khanhhungdn70@gmail.com'),
('NCC14','Cong Ty TNHH San Xuat Thuong Mai Dich Vu Gia Huy','120/11-13 Xom Dat','02839633448','tgiahuy@hcm.vnn.vn'),
('NCC15','Cong Ty TNHH Kinh Mat Viet Nam','138B Giang Vo','02437365355','vinaoptic@fpt.vn'),
('NCC16','Cong Ty TNHH City Lens','So 77-79 Cao Trieu Phat','0917888725','citylens@gmail.com'),
('NCC17','Cong Ty TNHH Hiresun International','551/36 Phan Van Tri','0586261687','lixiao@hiresunvn.com'),
('NCC18','Lens Mat Wennie','Tien Hai Building, 106A Nguyen Hong Dao','0931328625','quoc@promail.life'),
('NCC19','Cua Hang Kinh Japan','96 Cach Mang Thang Tam','02839330793',NULL),
('NCC20','Cong Ty TNHH Kinh Mat Van Xuan','91 Nguyen Chi Thanh','02462750808',NULL);

INSERT INTO KhachHang (MaKH, TenKH, DiaChi, NgaySinh, DienThoai) VALUES
('KH01','Ngoc','123/6 bis Le Thanh Ton','2000-04-26','098123123'),
('KH02','Tuan','49/12B Nguyen Thi Minh Khai','2001-05-07','091321321'),
('KH03','Anh','Ngo 6, pho Thanh Xuan','2000-03-06','093213212'),
('KH04','Khanh','67 bis Nguyen Thuong Hien','1999-09-29','09812812'),
('KH05','Thinh','41 Xom Cui','2000-10-02','02116584446'),
('KH06','Minh','31 Nguyen Xi','2001-01-20','02116584447'),
('KH07','Khuong','1110 Phan Van Tri','1998-07-15','02116584448'),
('KH08','Duc','91 Thong Nhat','2003-07-26','02116584449'),
('KH09','Nhung','71 Quang Trung','2000-05-06','02116584441'),
('KH10','Hung','21 Chu Van An','2005-09-21','02116584442'),
('KH11','Linh','71 Huynh Thuc Khang','2005-04-25','0985278934'),
('KH12','Khanh','93 Quoc Lo 13','2001-01-17','0987558432'),
('KH13','Duy','455 Xo Viet Nghe Tinh','2002-09-28','0947898766'),
('KH14','Vy','12 Nguyen Oanh','1996-06-06','09867123'),
('KH15','Nguyen','34 Le Quang Dinh','2005-04-30','098123787'),
('KH16','Hong','16 Truong Dinh','2003-10-22','038145123'),
('KH17','Van','12 Nguyen Gia Tri','2000-11-30','098123345'),
('KH18','Phuong','54 Le Van Viet','2001-06-18','09867346'),
('KH19','Chau','89 La Xuan Oai','1992-05-10','098567345'),
('KH20','Lien','46 Vo Van Hat','1999-05-12','038678456');

INSERT INTO NhanVien (MaNV, HoNV, TenNV, GioiTinh, NgaySinh, DiaChi, DienThoai, NoiSinh, NgayVaoLam, Email,MaPB)
VALUES
('NV01', 'Phan', 'Thanh Duy', 'Nam', '1998-02-25', '5 Duong Quang Ham', '08858454182', 'Vung Tau', '2017-05-01', NULL,'PB01'),
('NV02', 'Lam', 'Dai Ngoc', 'Nu', '1999-03-06', '2/1A Quang Trung', '08354362205', 'Ca Mau', '2017-05-01', NULL,'PB02'),
('NV03', 'Tran', 'Chau Khoa', 'Nam', '1995-09-05', '10 QL 1A', '09181833333', 'Vinh Long', '2017-05-01', NULL,'PB03'),
('NV04', 'Le', 'Chi Kien', 'Nam', '1996-03-10', '564/1/3F Nguyen Kiem', '09131620000', 'Nghe An', '2017-08-01', NULL,'PB04'),
('NV05', 'Phan', 'Thanh Tam', 'Nam', '1996-03-03', '306 Nguyen Trong Tuyen', '09186322233', 'Dong Nai', '2017-08-01', NULL,'PB05'),
('NV06', 'Mai', 'Thi Luu', 'Nu', '1998-06-26', '256/96/4 Phan Dang Luu', '09188133444', 'TPHCM', '2017-03-01', NULL,'PB06'),
('NV07', 'Dao', 'Thi Hong', 'Nu', '1999-09-13', '764/94 Pham Van Hai', '09754322222', 'TPHCM', '2017-03-01', NULL,'PB01'),
('NV08', 'Phan', 'Thanh Nhan', 'Nam', '1998-07-08', '152 Nguyen Trong Tuyen', '09183214567', 'TPHCM', '2017-08-01', NULL,'PB02'),
('NV09', 'Nguyen', 'Anh Duong', 'Nu', '2000-12-01', '65 Nam Ky Khoi Nghia', '09182127673', 'Tien Giang', '2018-05-01', NULL,'PB03'),
('NV10', 'Phan', 'Anh Nguyet', 'Nu', '1997-12-28', '32/65/9 Tran Cao Van', '09183242355', 'Da Nang', '2018-05-01', NULL,'PB04'),
('NV11', 'Le', 'Thi Cuc', 'Nu', '2000-04-03', '334 Phan Van Tri', '09183294775', 'Vung Tau', '2018-05-01', NULL,'PB05'),
('NV12', 'Mai', 'Minh Man', 'Nam', '1995-12-15', '58 Tran Binh Trong', '09183215763', 'Nha Trang', '2019-06-01', NULL,'PB06'),
('NV13', 'Vo', 'Minh Hoang', 'Nam', '1998-01-01', '310 Le Quang Dinh', '0987422175', 'Long An', '2019-06-01', NULL,'PB01'),
('NV14', 'Le', 'Trung Kiet', 'Nam', '1998-09-10', '28 Nguyen An Ninh', '09189458531', 'Da Nang', '2019-06-01', NULL,'PB02'),
('NV15', 'Tran', 'Trong Duy', 'Nam', '2000-05-02', '258 Duong Quang Ham', '09186763447', 'Tien Giang', '2019-06-01', NULL,'PB03'),
('NV16', 'Phan', 'Nhat Ha', 'Nu', '2000-06-02', '470 Nguyen Thai Son', '08858454874', 'Vung Tau', '2019-07-09', NULL,'PB04'),
('NV17', 'Tran', 'Minh Thu', 'Nu', '1999-12-01', '174 Bui Dinh Tuy', '09183214776', 'Nha Trang', '2019-07-09', NULL,'PB05'),
('NV18', 'Nguyen', 'Bao Duy', 'Nam', '1998-06-06', '240 Nguyen Xi', '09613824084', 'Dong Nai', '2019-07-09', NULL,'PB06'),
('NV19', 'Trinh', 'Kim Chi', 'Nu', '1999-05-02', '791 Nguyen Kiem', '0853245489', 'TPHCM', '2019-07-09', NULL,'PB01'),
('NV20', 'Le', 'Ngoc Thanh', 'Nu', '1998-05-07', '267 Chu Van An', '08979254186', 'Da Nang', '2019-09-07', NULL,'PB02');

INSERT INTO SanPham (MaSP, TenSP, SLTon, DonViTinh, MaloaiSP)
VALUES
('SP01', 'Kinh mat nam', 200, 'cai', 'LSP03'),
('SP02', 'Kinh mat nu', 300, 'cai', 'LSP03'),
('SP03', 'Kinh mat em be', 150, 'cai', 'LSP03'),
('SP04', 'Trong can', 600, 'cap', 'LSP02'),
('SP05', 'Trong doi mau', 400, 'cap', 'LSP02'),
('SP06', 'Trong sieu mong', 200, 'cap', 'LSP02'),
('SP07', 'Gong tron', 400, 'cai', 'LSP01'),
('SP08', 'Trong chong tia UV', 200, 'cap', 'LSP02'),
('SP09', 'Trong chong choi', 200, 'cap', 'LSP02'),
('SP10', 'Trong vien', 500, 'cap', 'LSP02'),
('SP11', 'Trong tich hop can', 500, 'cap', 'LSP02'),
('SP12', 'Gong kim loai nhua', 500, 'cai', 'LSP01'),
('SP13', 'Nuoc lau shiny', 500, 'chai', 'LSP04'),
('SP14', 'Nuoc lau solis', 500, 'chai', 'LSP04'),
('SP15', 'Hop dung kinh', 500, 'hop', 'LSP05'),
('SP16', 'Khan lau kinh', 500, 'cai', 'LSP05'),
('SP17', 'Gong khong vien', 300, 'cai', 'LSP01'),
('SP18', 'Gong titan', 500, 'cai', 'LSP01'),
('SP19', 'Gong nhua', 400, 'cai', 'LSP01'),
('SP20', 'Gong kim loai', 400, 'cai', 'LSP01');

INSERT INTO HoaDon (MaHD, MaKH, MaNV, NgayDH, NgayNH, PTTT) VALUES
('HD01','KH06','NV04','2022-05-04','2022-05-05','Chuyen khoan'),
('HD02','KH01','NV09','2022-05-09','2022-05-11','Chuyen khoan'),
('HD03','KH03','NV08','2022-05-15','2022-05-17','Chuyen khoan'),
('HD04','KH09','NV01','2022-06-25','2022-06-26','Tien mat'),
('HD05','KH10','NV09','2022-07-04','2022-07-05','Chuyen khoan'),
('HD06','KH02','NV03','2022-07-20','2022-07-21','Chuyen khoan'),
('HD07','KH08','NV02','2022-08-03','2022-08-06','Chuyen khoan'),
('HD08','KH11','NV01','2022-08-27','2022-08-28','Chuyen khoan'),
('HD09','KH04','NV10','2022-09-08','2022-09-09','Tien mat'),
('HD10','KH07','NV19','2022-10-10','2022-10-11','Tien mat'),
('HD11','KH05','NV20','2022-10-22','2022-10-24','Chuyen khoan'),
('HD12','KH12','NV05','2022-11-06','2022-11-09','Chuyen khoan'),
('HD13','KH18','NV15','2022-12-03','2022-12-05','Chuyen khoan'),
('HD14','KH13','NV17','2022-12-18','2022-12-19','Tien mat'),
('HD15','KH15','NV18','2023-01-03','2023-01-05','Chuyen khoan'),
('HD16','KH14','NV14','2023-01-26','2023-01-29','Chuyen khoan'),
('HD17','KH16','NV10','2023-02-04','2023-02-06','Chuyen khoan'),
('HD18','KH19','NV13','2023-02-15','2023-02-17','Tien mat'),
('HD19','KH17','NV12','2023-02-20','2023-02-22','Chuyen khoan'),
('HD20','KH20','NV10','2023-03-01','2023-03-02','Chuyen khoan');

INSERT INTO PhieuNhap (SoPN, NgayNhap, GhiChu, MaNCC)
VALUES
('PN01', '2022-04-05', NULL, 'NCC01'),
('PN02', '2022-05-05', NULL, 'NCC06'),
('PN03', '2022-06-15', NULL, 'NCC08'),
('PN04', '2022-07-15', NULL, 'NCC01'),
('PN05', '2022-08-09', NULL, 'NCC04'),
('PN06', '2022-09-03', NULL, 'NCC06'),
('PN07', '2022-10-03', NULL, 'NCC09'),
('PN08', '2022-10-03', NULL, 'NCC02'),
('PN09', '2022-11-25', NULL, 'NCC01'),
('PN10', '2022-11-25', NULL, 'NCC13'),
('PN11', '2022-11-25', NULL, 'NCC04'),
('PN12', '2022-12-30', NULL, 'NCC05'),
('PN13', '2022-12-30', NULL, 'NCC18'),
('PN14', '2022-12-30', NULL, 'NCC10'),
('PN15', '2023-01-20', NULL, 'NCC03'),
('PN16', '2023-01-20', NULL, 'NCC12'),
('PN17', '2023-01-20', NULL, 'NCC07'),
('PN18', '2023-02-18', NULL, 'NCC18'),
('PN19', '2023-02-18', NULL, 'NCC15'),
('PN20', '2023-02-18', NULL, 'NCC02');

INSERT INTO PhieuXuat (SoPX, NgayXuat, GhiChu, MaNV)
VALUES
('PX01', '2022-05-13', NULL, 'NV01'),
('PX02', '2022-05-20', NULL, 'NV07'),
('PX03', '2022-06-10', NULL, 'NV16'),
('PX04', '2022-06-28', NULL, 'NV09'),
('PX05', '2022-07-15', NULL, 'NV04'),
('PX06', '2022-07-31', NULL, 'NV01'),
('PX07', '2022-08-15', NULL, 'NV02'),
('PX08', '2022-08-31', NULL, 'NV05'),
('PX09', '2022-09-15', NULL, 'NV05'),
('PX10', '2022-09-30', NULL, 'NV03'),
('PX11', '2022-10-16', NULL, 'NV14'),
('PX12', '2022-10-31', NULL, 'NV18'),
('PX13', '2022-11-14', NULL, 'NV10'),
('PX14', '2022-11-28', NULL, 'NV19'),
('PX15', '2022-12-18', NULL, 'NV20'),
('PX16', '2022-12-31', NULL, 'NV14'),
('PX17', '2023-01-19', NULL, 'NV10'),
('PX18', '2023-01-31', NULL, 'NV01'),
('PX19', '2023-02-14', NULL, 'NV02'),
('PX20', '2023-02-25', NULL, 'NV03');
INSERT INTO CTHoaDon (MaHD, MaSP, SoLuongDat, DGBan) VALUES
('HD01','SP02',3,150000),
('HD01','SP20',4,250000),
('HD02','SP05',1,400000),
('HD03','SP02',2,150000),
('HD03','SP04',2,250000),
('HD04','SP02',3,150000),
('HD05','SP18',5,300000),
('HD06','SP05',5,120000),
('HD07','SP01',5,120000),
('HD07','SP04',3,250000),
('HD08','SP10',3,120000),
('HD09','SP12',1,200000),
('HD10','SP09',3,250000),
('HD11','SP18',5,300000),
('HD12','SP01',5,120000),
('HD13','SP04',2,250000),
('HD14','SP15',4,70000),
('HD14','SP20',4,250000),
('HD15','SP12',2,200000),
('HD16','SP10',3,120000),
('HD17','SP17',2,250000),
('HD18','SP19',3,120000),
('HD19','SP09',3,250000),
('HD20','SP08',2,400000),
('HD20','SP19',4,120000);
INSERT INTO CTPhieuNhap (SoPN, MaSP, SoLuong, DGNhap) VALUES
('PN01','SP09',100,150000),
('PN01','SP15',50,50000),
('PN02','SP02',20,70000),
('PN02','SP05',400,300000),
('PN02','SP13',100,100000),
('PN03','SP01',50,100000),
('PN04','SP03',20,70000),
('PN05','SP03',20,70000),
('PN06','SP04',20,150000),
('PN07','SP04',500,200000),
('PN08','SP02',20,80000),
('PN09','SP02',20,70000),
('PN09','SP09',100,150000),
('PN10','SP19',100,90000),
('PN11','SP02',20,80000),
('PN12','SP12',100,100000),
('PN13','SP12',100,100000),
('PN14','SP03',20,70000),
('PN15','SP18',100,180000),
('PN16','SP20',100,150000),
('PN17','SP15',50,50000),
('PN18','SP18',100,180000),
('PN19','SP01',20,90000),
('PN19','SP17',50,150000),
('PN20','SP08',50,200000);
INSERT INTO CTPhieuXuat (SoPX, MaSP, SoLuong, DGBan) VALUES
('PX01','SP18',50,300000),
('PX01','SP19',100,120000),
('PX02','SP02',50,150000),
('PX02','SP05',250,400000),
('PX03','SP12',200,200000),
('PX04','SP02',50,150000),
('PX04','SP07',200,250000),
('PX05','SP04',250,250000),
('PX06','SP08',50,400000),
('PX07','SP09',100,250000),
('PX08','SP18',200,300000),
('PX09','SP10',200,120000),
('PX09','SP15',300,78000),
('PX09','SP09',200,250000),
('PX10','SP17',100,250000),
('PX11','SP20',150,250000),
('PX12','SP08',50,400000),
('PX13','SP14',200,120000),
('PX14','SP01',50,120000),
('PX15','SP18',200,300000),
('PX16','SP10',200,120000),
('PX17','SP01',50,120000),
('PX18','SP07',200,250000),
('PX19','SP01',50,120000),
('PX20','SP04',100,250000);







