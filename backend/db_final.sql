-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Des 2024 pada 00.00
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_final`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bukus`
--

CREATE TABLE `bukus` (
  `id` int(10) NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `penulis` varchar(255) NOT NULL,
  `stok` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `bukus`
--

INSERT INTO `bukus` (`id`, `gambar`, `judul`, `penulis`, `stok`, `created_at`, `updated_at`) VALUES
(9, '1731673143_pintar.jpeg', 'Penyunting Naskah', 'Pamusuk Eneste', '10', '2024-11-15 05:19:03', '2024-11-16 16:17:31'),
(10, '1731673471_org.jpeg', 'Orang Orang Boaming', 'Budi Darma', '5', '2024-11-15 05:24:31', '2024-11-15 05:24:31'),
(11, '1731673578_spring.jpeg', 'Spring in Londong', 'Ilana Tan', '7', '2024-11-15 05:26:18', '2024-11-15 05:26:18'),
(12, '1731673752_pelangi.png', 'Pelangi Bahasa Sastra', 'Yuana Agus', '20', '2024-11-15 05:29:12', '2024-11-15 05:29:12'),
(13, '1731673961_ibu.jpeg', 'Aku Sayang Ibu', 'Watiek Ideo', '25', '2024-11-15 05:32:41', '2024-11-15 05:32:41'),
(15, '1732710208_loginbkg-1.png', 'ssaa', 'ssww', '22', '2024-11-27 05:23:28', '2024-11-27 05:23:28');

-- --------------------------------------------------------

--
-- Struktur dari tabel `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `connection` text NOT NULL,
  `queue` text NOT NULL,
  `payload` longtext NOT NULL,
  `exception` longtext NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(5, '2024_11_11_065947_create_bukus_table', 1),
(6, '2024_11_14_073527_create_peminjamen_table', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `tgl_pinjam` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `statuspinjam` enum('konfirmasi','disetujui','ditolak','dikembalikan') NOT NULL DEFAULT 'konfirmasi',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `id_user`, `id_buku`, `tgl_pinjam`, `tgl_kembali`, `jumlah`, `statuspinjam`, `created_at`, `updated_at`) VALUES
(4, 1, 9, '2024-11-15', '2024-11-16', 5, 'dikembalikan', '2024-11-15 22:02:04', '2024-11-15 22:02:43'),
(5, 2, 11, '2024-11-17', '2024-11-17', 5, 'dikembalikan', '2024-11-16 16:18:49', '2024-11-16 16:36:04');

--
-- Trigger `peminjaman`
--
DELIMITER $$
CREATE TRIGGER `kembali` AFTER UPDATE ON `peminjaman` FOR EACH ROW BEGIN
IF NEW.statuspinjam = 'dikembalikan' AND OLD.statuspinjam != NEW.statuspinjam THEN UPDATE bukus SET stok = stok + NEW.jumlah WHERE id = NEW.id_buku;
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `pinjam` AFTER UPDATE ON `peminjaman` FOR EACH ROW BEGIN
IF NEW.statuspinjam = 'disetujui' AND OLD.statuspinjam != NEW.statuspinjam THEN UPDATE bukus SET stok = stok - NEW.jumlah WHERE id = NEW.id_buku;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `token` varchar(64) NOT NULL,
  `abilities` text DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `personal_access_tokens`
--

INSERT INTO `personal_access_tokens` (`id`, `tokenable_type`, `tokenable_id`, `name`, `token`, `abilities`, `last_used_at`, `expires_at`, `created_at`, `updated_at`) VALUES
(1, 'App\\Models\\User', 1, 'auth_token', '353e45fd5970a9908650e70110c96dab0aaf3ead574ce2c6d09f49596ca1553b', '[\"*\"]', '2024-11-27 05:20:36', NULL, '2024-11-27 05:17:36', '2024-11-27 05:20:36');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `alamat`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Kwanzaa', 'arsal@gmail.com', 'Isekai', NULL, '$2y$10$iBHqHVRD0Gwqd8kLWmfzm.8sMFX1.tnwc0PLdt0VXiZFFikYDL1EO', NULL, '2024-11-11 00:23:42', '2024-11-14 05:49:16'),
(2, 'Arsal Fahrulloh', 'arsalfahrulloh61@gmail.com', 'Isekai', NULL, '$2y$10$rqHoVWN/icA6oyGaYi2mWeG59oLyP8Q2aBlaQa46OIMF9PMFZxQIq', NULL, '2024-11-11 00:54:59', '2024-11-11 00:54:59'),
(3, 'Eka Lasmanto Tarihoran', 'owaskita@yahoo.com', 'Jr. Gatot Subroto No. 670, Cimahi 35635, Jateng', NULL, '$2y$10$FxOpJlQBFDERBxp.E1Ba0uY1TA1.byRGOUscghktD6hL0K88rdPHa', NULL, '2024-11-14 01:18:19', '2024-11-14 01:18:19'),
(4, 'Sakti Saptono', 'handayani.nabila@gmail.co.id', 'Ki. Imam Bonjol No. 83, Pematangsiantar 98532, Jambi', NULL, '$2y$10$3vaanL9ldQUpxChcZ/MBfebIci8bRytNf.0Yj8XadW56ZXhH3pU1C', NULL, '2024-11-14 01:18:19', '2024-11-14 01:18:19'),
(5, 'Azalea Mulyani', 'respati71@hasanah.in', 'Jr. Ki Hajar Dewantara No. 361, Tangerang 72818, Banten', NULL, '$2y$10$EdpP4wlLi52/TWlXPLOA1eNoRNFBzZ/EjxZ9fVU/ZkCmNdA7IwVo.', NULL, '2024-11-14 01:18:19', '2024-11-14 01:18:19'),
(6, 'Kasusra Wacana', 'hariyah.bakianto@hardiansyah.desa.id', 'Jr. Suprapto No. 615, Palembang 15098, Lampung', NULL, '$2y$10$BfSrO9Ngfu5WFMlaaqNh3eFyXWVFXUcriQIQQ.K37P4WRZL0hgaGC', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(7, 'Rini Astuti', 'wastuti.zulfa@gunarto.sch.id', 'Jr. Bakti No. 316, Padang 49392, Malut', NULL, '$2y$10$mill0uiMw35gQisQ696D5uwz0Os6eASZj4jTG5oS9wbotbs8JJ7ni', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(8, 'Bakda Wacana', 'rusman36@yahoo.co.id', 'Jln. Reksoninten No. 548, Tanjungbalai 34268, Kepri', NULL, '$2y$10$lX3UDvbEVpe9RLdr/4fRQeNo8v03g0.H.fh0mj0jZCCwCz.XQOJhW', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(9, 'Dipa Adriansyah', 'qfirmansyah@lazuardi.mil.id', 'Jln. Cokroaminoto No. 315, Pekalongan 93484, Papua', NULL, '$2y$10$EXgprOHhhf4owvZPqPEyVOTe/zuj.QgylolwCQrswnhOKU/AKXUjS', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(10, 'Rina Zulfa Uyainah S.E.', 'sitompul.winda@yahoo.com', 'Jr. Suharso No. 622, Surakarta 23813, Kepri', NULL, '$2y$10$TM1aCXJMO09lzVtCj4/L.uGlNTRBui3Gfl1VzPyoM8KyNau.KgIhy', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(11, 'Jaiman Wibowo S.E.', 'namaga.paulin@saputra.org', 'Ds. Laswi No. 130, Kupang 74540, DKI', NULL, '$2y$10$GxsUd3b4DlK2cr.avP6KyuDm0iaxZt/Yjtx1zOysERpcfrP03r.Hq', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(12, 'Darimin Viman Latupono M.Kom.', 'dongoran.emil@yahoo.co.id', 'Jln. Babadak No. 113, Sibolga 62745, Jambi', NULL, '$2y$10$YsDSAJuc/wPba2XP3MU8AOKIVgdITGK/aEDeeRJArkO03OW0Es4IC', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(13, 'Enteng Niyaga Marpaung', 'hartati.genta@gmail.com', 'Psr. Basoka Raya No. 177, Bandar Lampung 88236, Sumbar', NULL, '$2y$10$CpGHswqk09z1sdfZsPSdluUd7/ssabuOfZnzyYUjJSTUjcSKJJfFq', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(14, 'Cemplunk Marbun S.Kom', 'mulyanto05@gmail.com', 'Ds. Yos Sudarso No. 987, Bima 95559, Jabar', NULL, '$2y$10$Xi5npmbzy66ZCbrrjX9INO357Cx8ZDMLOGNM0wO3.0xc/pfmrAvkS', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(15, 'Lidya Purwanti S.Pd', 'halim.gawati@nuraini.id', 'Dk. Surapati No. 761, Bandung 60416, Kepri', NULL, '$2y$10$M61pdVW3MiVT/NsdaUTpIeQMz9QtAmnmUbQoGcl.mZLwl0NT7r4hm', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(16, 'Koko Endra Natsir', 'ufujiati@yahoo.co.id', 'Gg. Gatot Subroto No. 362, Tual 77212, Maluku', NULL, '$2y$10$NnYIfWbC4DrWFSgcO6i41OMFyunAWDkEpU0Tb6q.w77Hb3P6CIILW', NULL, '2024-11-14 01:18:20', '2024-11-14 01:18:20'),
(17, 'Atmaja Kurnia Anggriawan S.Farm', 'iswahyudi.lalita@gmail.com', 'Psr. Bayan No. 100, Padangpanjang 27901, Sumut', NULL, '$2y$10$DPW.pW0QZJn/FZNvTBnk1.7lUglkhVu3ryk80HmR6N//x0FZVBbq6', NULL, '2024-11-14 01:18:21', '2024-11-14 01:18:21'),
(18, 'Raihan Pranowo S.T.', 'kenes74@pranowo.sch.id', 'Jln. Abdul. Muis No. 525, Pekanbaru 11048, Sulteng', NULL, '$2y$10$DmvtuhraKy4TjpWtj2I9V.GkaSMKvjbuzyUFAExCF27N61NTzHxKa', NULL, '2024-11-14 01:18:21', '2024-11-14 01:18:21');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bukus`
--
ALTER TABLE `bukus`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indeks untuk tabel `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `password_resets`
--
ALTER TABLE `password_resets`
  ADD PRIMARY KEY (`email`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `peminjaman_id_user_foreign` (`id_user`),
  ADD KEY `peminjaman_id_buku_foreign` (`id_buku`);

--
-- Indeks untuk tabel `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `bukus`
--
ALTER TABLE `bukus`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_id_buku_foreign` FOREIGN KEY (`id_buku`) REFERENCES `bukus` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `peminjaman_id_user_foreign` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
