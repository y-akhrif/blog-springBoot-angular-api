-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2019 at 05:31 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blogdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` varchar(3000) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `title` varchar(1000) NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blog`
--

INSERT INTO `blog` (`id`, `author`, `content`, `created_at`, `title`, `updated_at`) VALUES
(1, 'Akhrif Younes', 'Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital Mega Shop Digital ', '2019-04-09 02:44:04', 'Mega Shop Digital ', '2019-04-09 02:44:04'),
(2, 'Akhrif Younes', 'Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer\nMega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer Mega Shop Fashion Dealer  ', '2019-04-09 02:48:00', 'Mega Shop Fashion Dealer ', '2019-04-09 03:41:49'),
(3, 'Blogger  Author', 'Choose From 100s Of Templates & Create Your Own Blog Today. Start Blogging Online Now! Learn How To Create Your Own Blog and Start Publishing A Blog Online. Try Today!', '2019-04-09 02:58:10', 'C\'est quoi un blog', '2019-04-09 02:58:10'),
(4, 'Blogger  Author', 'Un blog, anglicisme pouvant être francisé en blogue ,, carnet Web, cybercarnet ou bloc-notes, est un type de site web — ou une partie d\'un site web — utilisé ...', '2019-04-09 03:00:00', 'Un blog, anglicisme ', '2019-04-09 03:01:23'),
(5, 'Akhrif Younes', 'Mega Shop Fashion Dealer2 Mega Shop Fashion Dealer2Mega Shop Fashion Dealer2Mega Shop Fashion Dealer2 Mega Shop Fashion Dealer2Mega Shop Fashion Dealer2Mega Shop Fashion Dealer2Mega Shop Fashion Dealer2', '2019-04-09 03:34:00', 'Mega Shop Fashion Dealer', '2019-04-09 03:40:13'),
(6, 'Hotline Hotline ', 'Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline Hotline ', '2019-04-09 03:35:35', 'Hotline Hotline Hotline ', '2019-04-09 03:35:35'),
(7, 'Akhrif Younes', 'BLOG BLOG BLOG', '2019-04-09 04:11:40', 'New Blog', '2019-04-09 04:11:40');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `id_blog` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `created_at`, `email`, `message`, `updated_at`, `website`, `id_blog`) VALUES
(2, '2019-04-09 03:01:00', 'younes.akhrif@gmail.com', 'My first comment was about the conception of the. KJSKJSK ', '2019-04-09 03:02:10', 'oferta.ma', 1),
(4, '2019-04-09 03:42:00', 'Leonardo.da.vinci@gmail.com', 'My first comment was about the conception of the ', '2019-04-09 03:48:48', 'www.oferta.ma', 2),
(5, '2019-04-09 03:43:53', 'akhrif.younes@gmail.com', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 03:43:53', 'oferta.ma', 2),
(6, '2019-04-09 03:44:01', 'akh.younes@hotmail.fr', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 03:44:01', ' bhbhbhbhbh', 2),
(7, '2019-04-09 04:13:25', 'akhrif.younes@gmail.com', 'This my fist comment in the blog number', '2019-04-09 04:13:25', 'mywebsite.com', 1),
(8, '2019-04-09 04:28:29', 'akh.younes@hotmail.fr', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 04:28:29', 'KJSHKJHSKJHSKJSH', 1),
(9, '2019-04-09 04:28:41', 'mohammad.salah@leverpool.com', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 04:28:41', ' bhbhbhbhbh', 3),
(10, '2019-04-09 04:28:50', 'younes.akhrif@gmail.com', 'gvhgvhg gh  hg', '2019-04-09 04:28:50', ' bhbhbhbhbh', 3),
(11, '2019-04-09 04:28:57', 'akh.younes@hotmail.fr', 'My first comment was about the conception of the ', '2019-04-09 04:28:57', '', 3),
(12, '2019-04-09 04:30:52', 'akh.younes@hotmail.fr', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 04:30:52', 'www.website.com', 5),
(13, '2019-04-09 04:30:58', 'mohammad.salah@leverpool.com', 'My first comment was about the conception of the. KJSKJSKJSKJSdsdfsdfsdfdsfsdf', '2019-04-09 04:30:58', 'KJSHKJHSKJHSKJSH', 5),
(14, '2019-04-09 04:31:06', 'younes.akhrif@gmail.com', 'TEST ETSSTSTSTSTS', '2019-04-09 04:31:06', ' bhbhbhbhbh', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd88fbtv2v04mfv8bwah9714p` (`id_blog`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
