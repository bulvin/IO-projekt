-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Lut 2022, 20:31
-- Wersja serwera: 10.1.36-MariaDB
-- Wersja PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `restauracja`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `menu`
--

CREATE TABLE `menu` (
  `id_przedmiotu` int(11) NOT NULL,
  `nazwa` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `cena` double(9,2) DEFAULT NULL,
  `kategoria` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `menu`
--

INSERT INTO `menu` (`id_przedmiotu`, `nazwa`, `cena`, `kategoria`) VALUES
(1, 'Pierogi ruskie', 10.99, 'Dania główne'),
(2, 'Rosół z makaronem', 9.00, 'Zupy'),
(3, 'Naleśniki z owocami ', 19.00, 'Dania główne'),
(4, 'Sernik', 20.00, 'Desery'),
(5, 'Woda mineralna', 5.00, 'Napoje'),
(8, 'Wuzetka', 20.00, 'Desery'),
(9, 'Pizza klasyczna ', 22.00, 'Dania główne'),
(10, 'Placek po węgiersku', 28.00, 'Dania główne'),
(11, 'Żurek', 12.00, 'Zupy'),
(12, 'Barszcz czerwony', 12.00, 'Zupy'),
(13, 'Zupa dyniowa', 14.00, 'Zupy'),
(14, 'Kotlet schabowy z ziemniakami', 20.00, 'Dania główne'),
(16, 'Mirinda', 10.00, 'Napoje'),
(17, 'Pepsi-Cola', 10.00, 'Napoje'),
(19, 'Oponki', 15.00, 'Dania główne'),
(20, 'Herbata czarna', 5.00, 'Napoje'),
(21, 'Herbata malinowa', 8.00, 'Napoje'),
(22, 'Gofry ze śmietaną', 20.00, 'Desery'),
(23, 'Spaghetti', 12.00, 'Dania główne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id_pracownika` int(11) NOT NULL,
  `nazwa_uzytkownika` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `haslo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `imie` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `nazwisko` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `typ_pracownika` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`id_pracownika`, `nazwa_uzytkownika`, `haslo`, `imie`, `nazwisko`, `typ_pracownika`) VALUES
(1, 'admin', 'admin', 'Janek', 'Dzbanek', 'Manager'),
(3, 'Macson', 'pracownik', 'Maciej', 'Rem', 'Pracownik'),
(5, 'miki', 'pracownik', 'Mikołaj', 'Pszenica', 'Pracownik'),
(94, 'johne', 'baro', 'Janusz', 'Kot', 'Pracownik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stolik`
--

CREATE TABLE `stolik` (
  `id_stolika` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `stolik`
--

INSERT INTO `stolik` (`id_stolika`, `status`) VALUES
(1, 0),
(2, 1),
(3, 0),
(4, 0),
(5, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowieniamenu`
--

CREATE TABLE `zamowieniamenu` (
  `id` int(11) NOT NULL,
  `id_zamowienia` int(11) NOT NULL,
  `id_przedmiotu` int(11) NOT NULL,
  `ilosc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `zamowieniamenu`
--

INSERT INTO `zamowieniamenu` (`id`, `id_zamowienia`, `id_przedmiotu`, `ilosc`) VALUES
(2, 2, 13, 2),
(3, 4, 10, 1),
(4, 6, 3, 1),
(5, 6, 16, 1),
(6, 2, 5, 1),
(7, 7, 9, 1),
(8, 5, 14, 1),
(9, 10, 8, 2),
(10, 5, 12, 2),
(11, 14, 16, 2),
(13, 15, 1, 2),
(15, 17, 4, 1),
(18, 21, 8, 1),
(19, 25, 16, 1),
(20, 25, 1, 1),
(21, 26, 1, 1),
(23, 26, 8, 1),
(24, 27, 16, 1),
(25, 27, 1, 1),
(26, 28, 13, 1),
(27, 28, 5, 1),
(28, 28, 14, 1),
(29, 29, 11, 1),
(30, 29, 1, 1),
(31, 29, 4, 1),
(33, 28, 13, 1),
(34, 28, 5, 1),
(35, 28, 14, 1),
(36, 28, 13, 1),
(37, 28, 5, 1),
(38, 28, 14, 1),
(39, 30, 16, 1),
(40, 30, 9, 1),
(41, 30, 14, 1),
(42, 30, 16, 1),
(43, 30, 9, 1),
(44, 30, 16, 1),
(45, 30, 9, 1),
(46, 30, 14, 1),
(47, 30, 16, 1),
(48, 30, 9, 1),
(54, 31, 3, 1),
(55, 31, 1, 1),
(56, 31, 10, 1),
(59, 33, 22, 2),
(61, 34, 12, 1),
(62, 35, 21, 1),
(63, 36, 19, 1),
(64, 36, 20, 1),
(65, 37, 20, 1),
(66, 37, 22, 1),
(69, 37, 12, 1),
(71, 37, 1, 1),
(72, 38, 14, 1),
(73, 38, 21, 1),
(74, 39, 21, 1),
(78, 39, 22, 2),
(79, 40, 22, 2),
(80, 40, 1, 2),
(81, 41, 22, 2),
(82, 41, 16, 1),
(83, 41, 10, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia_informacje`
--

CREATE TABLE `zamowienia_informacje` (
  `id_zamowienia` int(11) NOT NULL,
  `id_stolika` int(11) DEFAULT NULL,
  `data_zamowienia` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_pracownika` int(11) NOT NULL,
  `status_zamowienia` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `zamowienia_informacje`
--

INSERT INTO `zamowienia_informacje` (`id_zamowienia`, `id_stolika`, `data_zamowienia`, `id_pracownika`, `status_zamowienia`) VALUES
(1, 2, '2022-01-03 21:08:55', 1, 0),
(2, 3, '2022-01-04 10:56:40', 1, 0),
(3, 4, '2022-01-04 10:57:24', 1, 0),
(4, 3, '2022-01-04 12:02:17', 1, 0),
(5, 1, '2022-01-13 11:49:16', 3, 0),
(6, 1, '2022-01-13 11:49:16', 1, 0),
(7, 5, '2022-01-13 11:49:16', 3, 0),
(10, 2, '2022-01-13 11:50:13', 1, 0),
(11, 3, '2022-01-16 23:51:07', 1, 0),
(12, 1, '2022-01-16 23:53:44', 1, 0),
(13, 4, '2022-01-17 00:13:35', 1, 0),
(14, 3, '2022-01-17 00:15:42', 1, 0),
(15, 5, '2022-01-17 00:45:27', 1, 0),
(16, 3, '2022-01-17 00:49:06', 1, 0),
(17, 3, '2022-01-17 00:49:13', 1, 0),
(18, 4, '2022-01-17 00:57:46', 1, 0),
(19, 4, '2022-01-17 00:58:37', 1, 0),
(20, 4, '2022-01-17 00:58:44', 1, 0),
(21, 2, '2022-01-17 01:03:19', 1, 0),
(22, 2, '2022-01-19 21:05:27', 1, 0),
(23, 3, '2022-01-19 21:09:40', 1, 0),
(24, 2, '2022-01-19 21:10:57', 1, 0),
(25, 3, '2022-01-19 21:48:27', 1, 0),
(26, 2, '2022-01-19 21:58:28', 1, 0),
(27, 5, '2022-01-21 20:30:45', 1, 0),
(28, 2, '2022-01-23 19:15:24', 1, 0),
(29, 5, '2022-01-23 21:01:41', 1, 0),
(30, 4, '2022-01-23 22:46:59', 1, 0),
(31, 3, '2022-01-23 23:37:20', 1, 0),
(33, 1, '2022-01-23 23:51:50', 1, 0),
(34, 3, '2022-01-24 02:01:17', 1, 0),
(35, 4, '2022-01-24 02:01:50', 1, 0),
(36, 5, '2022-01-24 02:06:19', 1, 0),
(37, 2, '2022-01-30 12:47:19', 1, 0),
(38, 3, '2022-01-31 14:01:52', 1, 0),
(39, 4, '2022-02-07 12:09:43', 1, 1),
(40, 3, '2022-02-10 22:12:49', 94, 1),
(41, 1, '2022-02-11 18:23:01', 1, 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_przedmiotu`),
  ADD UNIQUE KEY `nazwa` (`nazwa`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id_pracownika`);

--
-- Indeksy dla tabeli `stolik`
--
ALTER TABLE `stolik`
  ADD PRIMARY KEY (`id_stolika`);

--
-- Indeksy dla tabeli `zamowieniamenu`
--
ALTER TABLE `zamowieniamenu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kluczobcy2` (`id_przedmiotu`),
  ADD KEY `kluczobcy3` (`id_zamowienia`);

--
-- Indeksy dla tabeli `zamowienia_informacje`
--
ALTER TABLE `zamowienia_informacje`
  ADD PRIMARY KEY (`id_zamowienia`),
  ADD KEY `FK8uiv3el2w0kwgmko33svkv5pp` (`id_pracownika`),
  ADD KEY `FKpwhupfds0w5hk9soomiefgr1w` (`id_stolika`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `menu`
--
ALTER TABLE `menu`
  MODIFY `id_przedmiotu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `id_pracownika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT dla tabeli `stolik`
--
ALTER TABLE `stolik`
  MODIFY `id_stolika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `zamowieniamenu`
--
ALTER TABLE `zamowieniamenu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT dla tabeli `zamowienia_informacje`
--
ALTER TABLE `zamowienia_informacje`
  MODIFY `id_zamowienia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `zamowieniamenu`
--
ALTER TABLE `zamowieniamenu`
  ADD CONSTRAINT `kluczobcy2` FOREIGN KEY (`id_przedmiotu`) REFERENCES `menu` (`id_przedmiotu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kluczobcy3` FOREIGN KEY (`id_zamowienia`) REFERENCES `zamowienia_informacje` (`id_zamowienia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `zamowienia_informacje`
--
ALTER TABLE `zamowienia_informacje`
  ADD CONSTRAINT `FK8uiv3el2w0kwgmko33svkv5pp` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`),
  ADD CONSTRAINT `FKpwhupfds0w5hk9soomiefgr1w` FOREIGN KEY (`id_stolika`) REFERENCES `stolik` (`id_stolika`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
