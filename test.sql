-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 22 Apr 2024 pada 20.59
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `idAdmin` int(10) NOT NULL,
  `adminName` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`idAdmin`, `adminName`, `email`, `password`) VALUES
(1, 'jovi', 'jovi@gmail.com', 'jovi'),
(2, 'evan', 'evan@gmail.com', 'evan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kota`
--

CREATE TABLE `kota` (
  `idKota` int(11) NOT NULL,
  `namaKota` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kota`
--

INSERT INTO `kota` (`idKota`, `namaKota`) VALUES
(1, 'Bandung'),
(2, 'Jakarta'),
(3, 'Depok'),
(4, 'Tanggerang'),
(5, 'Bekasi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `member`
--

CREATE TABLE `member` (
  `idMember` int(10) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `member`
--

INSERT INTO `member` (`idMember`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`) VALUES
(1, 'gibran', 'ikrar', 'gibran@gmail.com', 'gibran', '081284375374'),
(11, 'jodi', NULL, 'jodi@gmail.com', 'jodi', '0928348324');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mobil`
--

CREATE TABLE `mobil` (
  `idMobil` int(10) NOT NULL,
  `banyakKursi` int(10) DEFAULT NULL,
  `jenisMobil` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mobil`
--

INSERT INTO `mobil` (`idMobil`, `banyakKursi`, `jenisMobil`) VALUES
(1, 6, 'van'),
(2, 14, 'bus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rute`
--

CREATE TABLE `rute` (
  `idRute` int(10) NOT NULL,
  `tujuan` int(11) DEFAULT NULL,
  `berangkat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `rute`
--

INSERT INTO `rute` (`idRute`, `tujuan`, `berangkat`) VALUES
(5, 1, 2),
(6, 3, 2),
(7, 2, 1),
(8, 3, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `seat`
--

CREATE TABLE `seat` (
  `idSeat` int(11) NOT NULL,
  `idTransaksiTiket` int(11) NOT NULL,
  `seat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `seat`
--

INSERT INTO `seat` (`idSeat`, `idTransaksiTiket`, `seat`) VALUES
(1, 2, 2),
(2, 2, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sewa`
--

CREATE TABLE `sewa` (
  `idSewa` int(10) NOT NULL,
  `idMobil` int(10) DEFAULT NULL,
  `hargaSewa` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tiket`
--

CREATE TABLE `tiket` (
  `idTiket` int(10) NOT NULL,
  `idRute` int(10) DEFAULT NULL,
  `idMobil` int(10) DEFAULT NULL,
  `jam` varchar(10) DEFAULT NULL,
  `tanggalTiket` date DEFAULT NULL,
  `hargaTiket` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tiket`
--

INSERT INTO `tiket` (`idTiket`, `idRute`, `idMobil`, `jam`, `tanggalTiket`, `hargaTiket`) VALUES
(1, 5, 1, '12.00', '2024-04-24', 80000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `idTransaksi` int(10) NOT NULL,
  `idMember` int(10) DEFAULT NULL,
  `totalPembayaran` double DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `caraPembayaran` varchar(100) DEFAULT NULL,
  `refund` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`idTransaksi`, `idMember`, `totalPembayaran`, `tanggal`, `caraPembayaran`, `refund`) VALUES
(1, 1, 160000, '2024-04-23', 'BCA', 0),
(2, 1, 160000, '2024-04-23', 'BCA', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_sewa`
--

CREATE TABLE `transaksi_sewa` (
  `idTransaksi` int(10) NOT NULL,
  `idSewa` int(10) NOT NULL,
  `tanggalSewa` date DEFAULT NULL,
  `lamaSewa` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_tiket`
--

CREATE TABLE `transaksi_tiket` (
  `idTransaksi` int(10) NOT NULL,
  `idTiket` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_tiket`
--

INSERT INTO `transaksi_tiket` (`idTransaksi`, `idTiket`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_voucher`
--

CREATE TABLE `transaksi_voucher` (
  `idTransaksi` int(10) NOT NULL,
  `idVoucher` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `voucher`
--

CREATE TABLE `voucher` (
  `idVoucher` int(10) NOT NULL,
  `hargaVoucher` double DEFAULT NULL,
  `banyakVoucher` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Indeks untuk tabel `kota`
--
ALTER TABLE `kota`
  ADD PRIMARY KEY (`idKota`);

--
-- Indeks untuk tabel `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`idMember`);

--
-- Indeks untuk tabel `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`idMobil`);

--
-- Indeks untuk tabel `rute`
--
ALTER TABLE `rute`
  ADD PRIMARY KEY (`idRute`);

--
-- Indeks untuk tabel `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`idSeat`);

--
-- Indeks untuk tabel `sewa`
--
ALTER TABLE `sewa`
  ADD PRIMARY KEY (`idSewa`),
  ADD KEY `idMobil` (`idMobil`);

--
-- Indeks untuk tabel `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`idTiket`),
  ADD KEY `idRute` (`idRute`),
  ADD KEY `idMobil` (`idMobil`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idTransaksi`),
  ADD KEY `idMember` (`idMember`);

--
-- Indeks untuk tabel `transaksi_sewa`
--
ALTER TABLE `transaksi_sewa`
  ADD PRIMARY KEY (`idTransaksi`,`idSewa`),
  ADD KEY `idSewa` (`idSewa`);

--
-- Indeks untuk tabel `transaksi_tiket`
--
ALTER TABLE `transaksi_tiket`
  ADD PRIMARY KEY (`idTransaksi`,`idTiket`),
  ADD KEY `idTiket` (`idTiket`);

--
-- Indeks untuk tabel `transaksi_voucher`
--
ALTER TABLE `transaksi_voucher`
  ADD PRIMARY KEY (`idTransaksi`,`idVoucher`),
  ADD KEY `idVoucher` (`idVoucher`);

--
-- Indeks untuk tabel `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`idVoucher`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `idAdmin` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `kota`
--
ALTER TABLE `kota`
  MODIFY `idKota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `member`
--
ALTER TABLE `member`
  MODIFY `idMember` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `mobil`
--
ALTER TABLE `mobil`
  MODIFY `idMobil` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `rute`
--
ALTER TABLE `rute`
  MODIFY `idRute` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `seat`
--
ALTER TABLE `seat`
  MODIFY `idSeat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `sewa`
--
ALTER TABLE `sewa`
  MODIFY `idSewa` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tiket`
--
ALTER TABLE `tiket`
  MODIFY `idTiket` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idTransaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `voucher`
--
ALTER TABLE `voucher`
  MODIFY `idVoucher` int(10) NOT NULL AUTO_INCREMENT;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `sewa`
--
ALTER TABLE `sewa`
  ADD CONSTRAINT `sewa_ibfk_1` FOREIGN KEY (`idMobil`) REFERENCES `mobil` (`idMobil`);

--
-- Ketidakleluasaan untuk tabel `tiket`
--
ALTER TABLE `tiket`
  ADD CONSTRAINT `tiket_ibfk_1` FOREIGN KEY (`idRute`) REFERENCES `rute` (`idRute`),
  ADD CONSTRAINT `tiket_ibfk_2` FOREIGN KEY (`idMobil`) REFERENCES `mobil` (`idMobil`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idMember`) REFERENCES `member` (`idMember`);

--
-- Ketidakleluasaan untuk tabel `transaksi_sewa`
--
ALTER TABLE `transaksi_sewa`
  ADD CONSTRAINT `transaksi_sewa_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`idTransaksi`),
  ADD CONSTRAINT `transaksi_sewa_ibfk_2` FOREIGN KEY (`idSewa`) REFERENCES `sewa` (`idSewa`);

--
-- Ketidakleluasaan untuk tabel `transaksi_tiket`
--
ALTER TABLE `transaksi_tiket`
  ADD CONSTRAINT `transaksi_tiket_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`idTransaksi`),
  ADD CONSTRAINT `transaksi_tiket_ibfk_2` FOREIGN KEY (`idTiket`) REFERENCES `tiket` (`idTiket`);

--
-- Ketidakleluasaan untuk tabel `transaksi_voucher`
--
ALTER TABLE `transaksi_voucher`
  ADD CONSTRAINT `transaksi_voucher_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`idTransaksi`),
  ADD CONSTRAINT `transaksi_voucher_ibfk_2` FOREIGN KEY (`idVoucher`) REFERENCES `voucher` (`idVoucher`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
