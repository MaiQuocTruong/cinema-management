CREATE DATABASE  IF NOT EXISTS `rapphim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rapphim`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: rapphim
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cthoadondichvu`
--

DROP TABLE IF EXISTS `cthoadondichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthoadondichvu` (
  `MaHD` varchar(30) NOT NULL,
  `MaDichVu` varchar(30) NOT NULL,
  `TenDichVu` varchar(50) NOT NULL,
  `SoLuongDichVu` int NOT NULL,
  `DonGiaDichVu` double NOT NULL,
  KEY `MaHD` (`MaHD`),
  KEY `MaDichVu` (`MaDichVu`),
  CONSTRAINT `cthoadondichvu_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  CONSTRAINT `cthoadondichvu_ibfk_2` FOREIGN KEY (`MaDichVu`) REFERENCES `dichvuanuong` (`MaDichVu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthoadondichvu`
--

LOCK TABLES `cthoadondichvu` WRITE;
/*!40000 ALTER TABLE `cthoadondichvu` DISABLE KEYS */;
INSERT INTO `cthoadondichvu` VALUES ('HD001','DV001','BapNuoc',3,300),('HD001','DV002','Binh Nuoc Avatar',3,300);
/*!40000 ALTER TABLE `cthoadondichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cthoadonve`
--

DROP TABLE IF EXISTS `cthoadonve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cthoadonve` (
  `MaHD` varchar(30) NOT NULL,
  `MaVe` varchar(100) NOT NULL,
  `MaGhe` varchar(30) NOT NULL,
  `MaPhongChieu` varchar(30) NOT NULL,
  `MaXuat` varchar(30) NOT NULL,
  `GioChieu` time NOT NULL,
  `MaPhim` varchar(30) NOT NULL,
  `TenPhim` varchar(50) NOT NULL,
  `DonGiaPhim` double NOT NULL,
  PRIMARY KEY (`MaVe`),
  KEY `MaHD` (`MaHD`),
  KEY `MaPhongChieu` (`MaPhongChieu`),
  KEY `MaPhim` (`MaPhim`),
  KEY `MaXuat` (`MaXuat`),
  CONSTRAINT `cthoadonve_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  CONSTRAINT `cthoadonve_ibfk_2` FOREIGN KEY (`MaPhongChieu`) REFERENCES `phongchieuphim` (`MaPhongChieu`),
  CONSTRAINT `cthoadonve_ibfk_3` FOREIGN KEY (`MaPhim`) REFERENCES `phim` (`MaPhim`),
  CONSTRAINT `cthoadonve_ibfk_4` FOREIGN KEY (`MaXuat`) REFERENCES `xuatchieu` (`MaXuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cthoadonve`
--

LOCK TABLES `cthoadonve` WRITE;
/*!40000 ALTER TABLE `cthoadonve` DISABLE KEYS */;
INSERT INTO `cthoadonve` VALUES ('HD001','V001','H7','PC001','X001','16:20:00','P001','Doraemon',40000),('HD001','V002','H7','PC002','X002','16:25:00','P002','Conan',45000);
/*!40000 ALTER TABLE `cthoadonve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvuanuong`
--

DROP TABLE IF EXISTS `dichvuanuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvuanuong` (
  `MaDichVu` varchar(30) NOT NULL,
  `TenDichVu` varchar(50) NOT NULL,
  `TrangThai` varchar(30) NOT NULL,
  `LoaiDichVu` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `SoLuong` int NOT NULL,
  PRIMARY KEY (`MaDichVu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvuanuong`
--

LOCK TABLES `dichvuanuong` WRITE;
/*!40000 ALTER TABLE `dichvuanuong` DISABLE KEYS */;
INSERT INTO `dichvuanuong` VALUES ('DV001','Banh Oreo','Dang Ban','Banh Keo',300),('DV002','Pepsi','Dang Ban','NuocGiaiKhat',300);
/*!40000 ALTER TABLE `dichvuanuong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giadichvu`
--

DROP TABLE IF EXISTS `giadichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giadichvu` (
  `MaDichVu` varchar(30) NOT NULL,
  `DonGia` double NOT NULL,
  `KichThuoc` varchar(20) NOT NULL,
  `TrangThaiSize` varchar(20) NOT NULL,
  KEY `MaDichVu` (`MaDichVu`),
  CONSTRAINT `giadichvu_ibfk_1` FOREIGN KEY (`MaDichVu`) REFERENCES `dichvuanuong` (`MaDichVu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giadichvu`
--

LOCK TABLES `giadichvu` WRITE;
/*!40000 ALTER TABLE `giadichvu` DISABLE KEYS */;
INSERT INTO `giadichvu` VALUES ('DV001',30000,'Nho','Dang Ban'),('DV002',60000,'Lon','Dang Ban');
/*!40000 ALTER TABLE `giadichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHD` varchar(30) NOT NULL,
  `MaKhachHang` varchar(30) NOT NULL,
  `MaNhanVien` varchar(30) NOT NULL,
  `TongSoLuongVe` int NOT NULL,
  `TongTien` double NOT NULL,
  `VAT` double NOT NULL,
  `NgayLapHD` datetime NOT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `MaKhachHang` (`MaKhachHang`),
  KEY `MaNhanVien` (`MaNhanVien`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES ('HD001','KH001','NV001',3,300,0.8,'2023-11-06 00:00:00');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` varchar(30) NOT NULL,
  `TenKH` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `NgaySinh` datetime NOT NULL,
  `GioiTinh` bit(1) NOT NULL,
  `LoaiKhachHang` varchar(20) NOT NULL,
  `SDT` char(30) NOT NULL,
  `DiemHienCo` int NOT NULL,
  `CMND` char(30) NOT NULL,
  `DiemDaSuDung` int NOT NULL,
  `TongChiTieu` double NOT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('KH001','Trưởng','maiqtruong2403@gmail.com','2003-03-24 00:00:00',_binary '','VIP','0398911257',1000,'0123',600,0),('KH002','Hai','hainguyendoanngoc@gmail.com','2003-03-24 00:00:00',_binary '','Thuong','0398911257',1000,'0123',600,0);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(30) NOT NULL,
  `TenNhanVien` varchar(50) NOT NULL,
  `NgaySinh` datetime NOT NULL,
  `DiaChi` varchar(50) NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `ChucVu` varchar(20) NOT NULL,
  `GioiTinh` bit(1) NOT NULL,
  `TrangThai` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV001','Hai','2003-11-06 00:00:00','HCM','0961236005','hainguyendoanngoc@gmail.com','KH',_binary '',_binary ''),('NV002','Quoc Truong ','2003-03-20 15:41:56','HCM','0912312','mqt@gmail.com','Nhân Viên',_binary '',_binary '\0'),('NV003','Ho Thi Thuong','2002-03-15 15:43:38','HaNoi','09123','htt@gmail.com','Quản Lý',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_insert_NhanVien` AFTER INSERT ON `nhanvien` FOR EACH ROW BEGIN

    insert into	TaiKhoan(MaNhanVien , Email ,  TrangThai)
    values(NEW.MaNhanVien , NEW.Email , 1);
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phim` (
  `MaPhim` varchar(30) NOT NULL,
  `MaXuat` varchar(30) NOT NULL,
  `TenPhim` varchar(30) NOT NULL,
  `LoaiPhim` varchar(20) NOT NULL,
  `NgayChieu` datetime NOT NULL,
  `NgayHetHan` datetime NOT NULL,
  `GiaTien` double NOT NULL,
  `SoLuongVe` int NOT NULL,
  `HinhPhim` blob NOT NULL,
  `GioiHanTuoi` int NOT NULL,
  `ThoiLuong` int NOT NULL,
  `NgonNgu` varchar(30) NOT NULL,
  `QuocGia` varchar(30) NOT NULL,
  `TrangThaiPhim` varchar(20) NOT NULL,
  PRIMARY KEY (`MaPhim`),
  KEY `MaXuat` (`MaXuat`),
  CONSTRAINT `phim_ibfk_1` FOREIGN KEY (`MaXuat`) REFERENCES `xuatchieu` (`MaXuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES ('P001','X001','Conan','HoatHinh','2023-11-06 00:00:00','2023-12-12 00:00:00',400,300,_binary 'https://codex-themes.com/thegem/sites/shop-bakery',13,120,'TV','Japan','Đang Chiếu'),('P002','X001','Avatar','HanhDong','2023-11-06 00:00:00','2023-12-12 00:00:00',400,300,_binary 'https://codex-themes.com/thegem/sites/shop-bakery',13,120,'TV','America','Đang Chiếu');
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phongchieuphim`
--

DROP TABLE IF EXISTS `phongchieuphim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phongchieuphim` (
  `MaPhongChieu` varchar(30) NOT NULL,
  `MaXuat` varchar(30) NOT NULL,
  `TenPhongChieu` varchar(50) NOT NULL,
  `SucChua` int NOT NULL,
  PRIMARY KEY (`MaPhongChieu`),
  KEY `MaXuat` (`MaXuat`),
  CONSTRAINT `phongchieuphim_ibfk_1` FOREIGN KEY (`MaXuat`) REFERENCES `xuatchieu` (`MaXuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phongchieuphim`
--

LOCK TABLES `phongchieuphim` WRITE;
/*!40000 ALTER TABLE `phongchieuphim` DISABLE KEYS */;
INSERT INTO `phongchieuphim` VALUES ('PC001','X001','P1',30),('PC002','X001','P2',30);
/*!40000 ALTER TABLE `phongchieuphim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `MaNhanVien` varchar(30) NOT NULL,
  `MatKhau` varchar(20) NOT NULL DEFAULT '1111',
  `Email` varchar(50) NOT NULL,
  `TrangThai` bit(1) NOT NULL,
  KEY `MaNhanVien` (`MaNhanVien`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('NV001','1111','hainguyendoanngoc@gmail.com',_binary ''),('NV002','1111','mqt@gmail.com',_binary ''),('NV003','1111','htt@gmail.com',_binary '');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuatchieu`
--

DROP TABLE IF EXISTS `xuatchieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xuatchieu` (
  `MaXuat` varchar(30) NOT NULL,
  `DinhDang` varchar(30) NOT NULL,
  `NgayChieu` datetime NOT NULL,
  `GioChieu` time NOT NULL,
  `GioKetThuc` time NOT NULL,
  `TrangThai` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaXuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuatchieu`
--

LOCK TABLES `xuatchieu` WRITE;
/*!40000 ALTER TABLE `xuatchieu` DISABLE KEYS */;
INSERT INTO `xuatchieu` VALUES ('X001','3D','2023-12-12 00:00:00','12:30:45','13:40:00','Dang Chiếu'),('X002','3D','2023-12-12 00:00:00','12:30:45','14:50:00','Dang Chiếu');
/*!40000 ALTER TABLE `xuatchieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'rapphim'
--

--
-- Dumping routines for database 'rapphim'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-19 16:31:36
