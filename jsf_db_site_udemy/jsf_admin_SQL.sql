SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `jsf_database`
--
CREATE DATABASE IF NOT EXISTS `jsf_database` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `jsf_database`;

-- --------------------------------------------------------

--
-- Table structure for table `admin_users`
--

CREATE TABLE `admin_users` (
  `id` int(11) NOT NULL,
  `user_type` enum('admin1','admin2','admin3','admin4') NOT NULL DEFAULT 'admin1',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin_users`
--

INSERT INTO `admin_users` (`id`, `user_type`, `username`, `password`, `name`, `surname`, `email`, `created`) VALUES
(1, 'admin1', 'admin', 'test', 'test', 'test', 'test', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(11) UNSIGNED NOT NULL,
  `parent_id` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `front_page` enum('yes','no') NOT NULL DEFAULT 'no',
  `title_hr` varchar(255) NOT NULL,
  `title_en` varchar(255) NOT NULL,
  `title_de` varchar(255) NOT NULL,
  `title_it` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `text_hr` text NOT NULL,
  `text_en` text NOT NULL,
  `text_de` text NOT NULL,
  `text_it` text NOT NULL,
  `created` datetime NOT NULL,
  `expires` datetime NOT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `orderby`, `parent_id`, `front_page`, `title_hr`, `title_en`, `title_de`, `title_it`, `image`, `text_hr`, `text_en`, `text_de`, `text_it`, `created`, `expires`, `modified`) VALUES
(32, 48, 0, 'no', 'Meteorology', 'Meteorology', ' Meteorologie', 'Meteorologia', '', '', '', '', '', '2015-02-05 11:06:29', '0000-00-00 00:00:00', '2015-02-05 14:23:31'),
(35, 35, 33, 'no', 'Video Wall', 'Video Wall', 'Video Wand', 'Video Muro', '', '', '', '', '', '2015-02-05 11:49:18', '0000-00-00 00:00:00', '2015-02-05 10:49:18'),
(36, 36, 33, 'no', 'Plasmas', 'Plasmas', 'Plasma', 'Plasma', '', '', '', '', '', '2015-02-05 11:50:40', '0000-00-00 00:00:00', '2015-02-09 12:06:57'),
(33, 50, 0, 'no', 'Multimedia', 'Multimedia', 'Multimedia', 'Multimedia', '', '', '', '', '', '2015-02-05 11:23:06', '0000-00-00 00:00:00', '2015-02-05 14:23:03'),
(34, 34, 32, 'no', 'Profiles', 'Profiles', 'Profiles', 'Profili', '', '<p>This is some text about profiles... This is some text about profiles... This is some text about profiles... This is some text about profiles... </p>\r\n', '', '', '', '2015-02-05 11:25:12', '0000-00-00 00:00:00', '2015-02-05 10:32:29'),
(37, 46, 0, 'no', 'Digital TV Receivers', 'Digital TV Receivers', ' Digital-TV-Empfänger', 'IPTV', '', '', '', '', '', '2015-02-05 11:55:28', '0000-00-00 00:00:00', '2015-02-05 14:23:48'),
(38, 44, 0, 'no', 'Digital Watches', 'Digital Watches', 'Digitaluhren', 'Orologi digitali', '', '', '', '', '', '2015-02-05 11:57:41', '0000-00-00 00:00:00', '2015-02-05 14:24:16'),
(39, 39, 38, 'no', 'Expand digital watches', 'Expand digital watches', 'Expand erweitern Digitaluhren', 'Expand ampliare orologi digitali', '', '', '', '', '', '2015-02-05 11:59:52', '0000-00-00 00:00:00', '2015-02-05 11:01:02'),
(40, 42, 0, 'no', 'Speakers', 'Speakers', 'Lautsprecher', 'Altoparlanti', '', '', '', '', '', '2015-02-05 12:17:42', '0000-00-00 00:00:00', '2015-02-05 14:24:30'),
(41, 41, 40, 'no', 'Expand speakers', 'Expand speakers', 'Expand Lautsprecher', 'Expand altoparlanti', '', '', '', '', '', '2015-02-05 12:19:55', '0000-00-00 00:00:00', '2015-02-05 11:21:05'),
(42, 40, 0, 'no', 'Audio Equipment', 'Audio Equipment', 'Audiogeräte', 'Attrezzature audio', '', '', '', '', '', '2015-02-05 13:06:53', '0000-00-00 00:00:00', '2015-02-05 14:25:16'),
(43, 43, 42, 'no', 'Expand audio equipment', 'Expand audio equipment', 'Expand audiogeräte', 'Expand ampliare orologi digitali', '', '', '', '', '', '2015-02-05 13:08:57', '0000-00-00 00:00:00', '2015-02-05 12:08:57'),
(44, 38, 0, 'no', 'Graphic card', 'Graphic card', 'Grafikkarte', 'Scheda grafica', '', '', '', '', '', '2015-02-05 13:24:03', '0000-00-00 00:00:00', '2015-02-05 14:25:16'),
(45, 45, 44, 'no', 'Expand graphic cards', 'Expand graphic cards', 'Expand Grafikkarte', 'Expand  Scheda grafica', '', '', '', '', '', '2015-02-05 13:25:34', '0000-00-00 00:00:00', '2015-02-05 12:25:34'),
(46, 37, 0, 'no', 'Mindodetectors', 'Mindodetectors', 'Mindodetektoren', 'Mindorivelatori', '', '', '', '', '', '2015-02-05 13:31:13', '0000-00-00 00:00:00', '2015-02-05 14:25:24'),
(47, 47, 46, 'no', 'Expand Mindodetectors', 'Expand Mindodetectors', 'Expand Mindodetektoren', 'Expand Mindorivelatori', '', '', '', '', '', '2015-02-05 13:32:26', '0000-00-00 00:00:00', '2015-02-05 12:32:38'),
(48, 33, 0, 'no', 'Special equipment', 'Special equipment', 'Sondermaschinen', 'macchine speciali', '', '', '', '', '', '2015-02-05 13:39:02', '0000-00-00 00:00:00', '2015-02-09 12:10:06'),
(49, 49, 48, 'no', 'Expand special equipment', 'Expand special equipment', ' Expand Sondermaschinen', 'Expand macchine speciali ', '', '', '', '', '', '2015-02-05 13:41:37', '0000-00-00 00:00:00', '2015-02-05 12:41:37'),
(50, 32, 0, 'no', 'Overvoltage protection', 'Overvoltage protection', 'Überspannungsschutz', 'Protezione yes Sovratensione', '', '', '', '', '', '2015-02-05 13:46:36', '0000-00-00 00:00:00', '2015-02-09 12:10:06'),
(51, 51, 50, 'no', 'Expand overvoltage protection', 'Expand overvoltage protection', 'Expand  Überspannungsschutz', 'Expand protezione yes sovratensione', '', '', '', '', '', '2015-02-05 13:48:24', '0000-00-00 00:00:00', '2015-02-05 12:48:24');

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `front_page` enum('no','yes') NOT NULL DEFAULT 'no',
  `categories_id` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `title_hr` varchar(255) DEFAULT '',
  `title_en` varchar(255) NOT NULL,
  `title_de` varchar(255) NOT NULL,
  `text1_hr` text,
  `text1_en` text,
  `text1_de` text,
  `text2_hr` text,
  `text2_en` text,
  `text2_de` text,
  `created` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `orderby`, `front_page`, `categories_id`, `title_hr`, `title_en`, `title_de`, `text1_hr`, `text1_en`, `text1_de`, `text2_hr`, `text2_en`, `text2_de`, `created`) VALUES
(1, 1, 'no', 0, '111a4455', '222b', '33c', '<p>\r\n	Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;Text about us.. hr&nbsp;</p>\r\n', '<p>\r\n	Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;Text about us.. en.&nbsp;</p>\r\n', '<p>\r\n	Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;Text about us.. de.&nbsp;</p>\r\n', '', '', '', '2015-05-01 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `front_page` enum('no','yes') NOT NULL DEFAULT 'no',
  `categories_id` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `title_hr` varchar(255) DEFAULT '',
  `title_en` varchar(255) NOT NULL DEFAULT '',
  `title_de` varchar(255) NOT NULL DEFAULT '',
  `text1_hr` text,
  `text1_en` text,
  `text1_de` text,
  `text2_hr` text,
  `text2_en` text,
  `text2_de` text,
  `created` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `orderby`, `front_page`, `categories_id`, `title_hr`, `title_en`, `title_de`, `text1_hr`, `text1_en`, `text1_de`, `text2_hr`, `text2_en`, `text2_de`, `created`) VALUES
(31, 0, 'no', 4, 'title2', '', '', '', '', '', '', '', '', '2015-01-12 11:12:12'),
(44, 0, 'no', 5, 'Profili 222', 'zzz', 'fgfdg', '', '', '', '', '', '', '2015-02-05 11:11:54'),
(45, 0, 'no', 4, 'PK-8420 Video-zid 84 3333', '', '', '', '', '', '', '', '', '2015-02-05 11:40:26'),
(46, 0, 'no', 5, 'MS-4220 Plazma panel 42', '', '', '', '', '', '', '', '', '2015-02-05 11:48:13'),
(50, 0, 'no', 6, 'LINEA 60 ', '', '', '', '', '', '', '', '', '2015-02-05 12:06:27'),
(47, 0, 'no', 0, 'MOSAIC06 ', '', '', '', '', '', '', '', '', '2015-02-05 11:58:45'),
(49, 0, 'no', 3, 'CDL ', '', '', '', '', '', '', '', '', '2015-02-05 12:04:07'),
(51, 0, 'no', 4, 'OLC ', '', '', '', '', '', '', '', '', '2015-02-05 12:08:58'),
(52, 0, 'no', 6, 'Spazio', '', '', '', '', '', '', '', '', '2015-02-05 12:13:52'),
(56, 0, 'no', 0, 'UniMIX ggg', 'fgfg', '', '', '', '', '', '', '', '2015-02-05 13:13:35'),
(461, 0, 'no', 3, 'gggdsdsd', 'sdfsdfdsf', 'gfdgdf', '<p>\r\n	gdfgdfg</p>\r\n', '', '', '', '', '', '2015-04-14 19:31:50'),
(462, 0, 'no', 0, 'uuuttr', 'zrtzrtz', 'trzrtz', '', '', '', '', '', '', '2015-04-14 19:57:44'),
(463, 0, 'no', 0, 'zrtzu', 'tzutzutzu', '', '', '', '', '', '', '', '2015-04-14 20:16:43'),
(464, 0, 'no', 0, 'sdfsdf', '', '', '', '', '', '', '', '', '2015-04-14 20:20:00'),
(465, 0, 'no', 0, 'gdfgfdgdf4466', '', '', '', '', '', '', '', '', '2015-04-14 20:21:45'),
(466, 0, 'no', 0, 'rreer', 'rrr', '', '', '', '', '', '', '', '2015-04-14 20:22:51'),
(467, 0, 'no', 0, 'zzzz4444', 'eeee', '', '', '', '', '', '', '', '2015-04-14 20:23:06'),
(468, 0, 'no', 0, 'ztuztutz', 'uztutzu', '', '', '', '', '', '', '', '2015-04-14 20:24:13'),
(469, 0, 'no', 0, 'tttttz', '', '', '', '', '', '', '', '', '2015-04-14 20:24:58'),
(470, 0, 'no', 0, '555', 'ztztzz', '', '', '', '', '', '', '', '2015-04-14 20:25:05'),
(471, 0, 'no', 0, 'uuu', 'eee', 'rrr', '', '', '', '', '', '', '2015-04-14 20:26:03'),
(472, 0, 'no', 0, 'zrzrtztrz', '', '', '', '', '', '', '', '', '2015-04-14 20:26:08'),
(473, 0, 'no', 0, 'rtztrz', '', '', '', '', '', '', '', '', '2015-04-14 20:26:19'),
(474, 0, 'no', 0, 'trztrzuztu', '', '', '', '', '', '', '', '', '2015-04-14 20:27:13'),
(475, 0, 'no', 0, '54656', '', '', '', '', '', '', '', '', '2015-04-14 20:27:48'),
(476, 0, 'no', 0, 'tzutzutz', '', '', '', '', '', '', '', '', '2015-04-14 20:28:03'),
(477, 0, 'no', 0, 'rtrt', '', '', '', '', '', '', '', '', '2015-04-14 20:29:04'),
(478, 0, 'no', 0, 'iuziuz', '', '', '', '', '', '', '', '', '2015-04-14 20:29:18'),
(479, 0, 'no', 0, 'zuzuttzu', '', '', '', '', '', '', '', '', '2015-04-14 20:29:29'),
(480, 0, 'no', 0, 'iiiuuuuu', '', '', '', '', '', '', '', '', '2015-04-14 20:29:35'),
(481, 0, 'no', 0, 'tttt', '', '', '', '', '', '', '', '', '2015-04-14 20:31:03'),
(482, 0, 'no', 0, 'uuu66', 'zrtz', 'rtzrt', '', '', '', '', '', '', '2015-04-14 20:31:13'),
(483, 0, 'no', 0, 'zzz23333', 'eee', '', '', '', '', '', '', '', '2015-04-14 20:35:09'),
(484, 0, 'no', 0, 'eeejjj', 'kkkkk', '', '', '', '', '', '', '', '2015-04-14 20:35:17'),
(485, 0, 'no', 0, '333tššššđđ', 'kjhkhjk', '', '', '', '', '', '', '', '2015-04-14 20:35:26'),
(486, 0, 'no', 0, 'mijauu', 'mmm', 'ffššš', '', '', '', '', '', '', '2015-04-14 20:35:33'),
(487, 487, 'yes', 44, 'yyy111aaaann3 xyy đščćž ŽĆČĐŠ24t', 'yyy22', 'yyy33', '<p>\r\n	444</p>\r\n', '<p>\r\n	55</p>\r\n', '<p>\r\n	66</p>\r\n', '<p>\r\n	77</p>\r\n', '<p>\r\n	88</p>\r\n', '<p>\r\n	99</p>\r\n', '2015-04-15 07:09:23'),
(488, 488, 'yes', 0, 'novi zunos test112d3', 'novi unost test 2tt', 'novi unos test 3', '<p>\r\n	dsfsdf &scaron;&scaron;&scaron;</p>\r\n', '<p>\r\n	gfdg</p>\r\n', '', '', '', '', '2015-04-15 07:47:18'),
(489, 489, 'no', 0, 'ddd333', 'gfdg', '', '', '', '', '', '', '', '2015-04-16 18:28:59'),
(490, 490, 'no', 0, 'fff3434', 'asdasd', '', '', '', '', '', '', '', '2015-04-16 18:35:56'),
(491, 491, 'no', 0, 'www344hhhh4 nnn223aa', 'www2 mmmmmmmmmm', 'www333', '<br />\r\n', '<br />\r\n', '<br />\r\n', '<br />\r\n', '<br />\r\n', '<br />\r\n', '2015-04-16 20:24:35'),
(496, 496, 'no', 0, 'zecc', 'ddd', '', '<p>\r\n	gdg</p>\r\n', '<p>\r\n	jjjjj</p>\r\n', '', '', '', '', '2015-05-31 21:50:03'),
(498, 498, 'yes', 33, 'eee111nngf3', 'ttt22ff', '333', '<p>\r\n	treter</p>\r\n', '', '', '', '', '', '2015-05-31 22:07:33'),
(495, 495, 'yes', 35, 'SufAZ 3433555e čg8aa', 'sdfsdfgbb', 'sdfdsccc', '<p>\r\n	dfgh555ddd</p>\r\n', '<p>\r\n	66eee</p>\r\n', '', '', '', '', '2015-04-19 20:39:21'),
(459, 0, 'yes', 33, 'Novi unos testiranje hr11111 aa', 'Nodsdvi unsos test en22', 'Novi unos testiranje de', '<p>\r\n	Novi unos testiranje hr tekst..</p>\r\n', '', '', '', '', '', '2015-04-13 21:22:57'),
(501, 501, 'no', 37, 'nnn543215eeeLULUW355 223', 'mmmm5333', '4', '<p>\r\n	333</p>\r\n', '<p>\r\n	222</p>\r\n', '<p>\r\n	111</p>\r\n', '<p>\r\n	77</p>\r\n', '<p>\r\n	88</p>\r\n', '<p>\r\n	999</p>\r\n', '2017-04-25 20:43:00');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `front_page` enum('no','yes') NOT NULL DEFAULT 'no',
  `categories_id` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `title_hr` varchar(255) NOT NULL DEFAULT '',
  `title_en` varchar(255) NOT NULL DEFAULT '',
  `title_de` varchar(255) NOT NULL DEFAULT '',
  `text1_hr` text,
  `text1_en` text,
  `text1_de` text,
  `text2_hr` text,
  `text2_en` text,
  `text2_de` text,
  `created` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `orderby`, `front_page`, `categories_id`, `title_hr`, `title_en`, `title_de`, `text1_hr`, `text1_en`, `text1_de`, `text2_hr`, `text2_en`, `text2_de`, `created`) VALUES
(1, 1, 'no', 0, 'aaa', 'bbb', '', '', '', '', '', '', '', '2015-05-31 22:49:26'),
(5, 5, 'no', 0, 'sdfsdfd333', '222', '333', '<p>\r\n	444</p>\r\n', '<p>\r\n	55</p>\r\n', '', '', '', '', '2017-05-02 19:43:14'),
(6, 6, 'no', 0, 'sdfsdfd333', '222', '333', '<p>\r\n	444</p>\r\n', '<p>\r\n	55</p>\r\n', '<p>\r\n	ztr</p>\r\n', '', '', '', '2017-05-02 19:43:37'),
(7, 7, 'no', 0, 'rrtr5454', 'trt', 'ertt', '<p>\r\n	ertret</p>\r\n', '', '', '', '', '', '2017-05-02 19:52:21'),
(8, 8, 'no', 0, 'uzuzt343', '56', '5435', '<p>\r\n	6456546</p>\r\n', '', '', '', '', '', '2017-05-02 19:53:00'),
(9, 9, 'no', 0, 'test4443d', 'fsdfsd', 'fdsfd34', '<p>\r\n	rere</p>\r\n', '<p>\r\n	344</p>\r\n', '', '', '', '', '2017-05-02 19:56:37'),
(10, 10, 'no', 0, 'hhh', 'rrr', 'erere', '<p>\r\n	45454</p>\r\n', '<p>\r\n	ztz</p>\r\n', '<p>\r\n	6566</p>\r\n', NULL, NULL, NULL, '2017-05-02 20:18:27'),
(11, 11, 'no', 0, 'bbbb76', 'bbbccc', 'dddd', '<p>\r\n	eee</p>\r\n', '<p>\r\n	ggggffff</p>\r\n', '', NULL, NULL, NULL, '2017-05-02 20:20:22'),
(12, 12, 'no', 0, 'last1', 'last2', 'last3', '', '<p>\r\n	last5</p>\r\n', '<p>\r\n	last6545</p>\r\n', NULL, NULL, NULL, '2017-05-02 20:24:20');

-- --------------------------------------------------------

--
-- Table structure for table `reference`
--

CREATE TABLE `reference` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `front_page` enum('no','yes') NOT NULL DEFAULT 'no',
  `categories_id` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `title_hr` varchar(255) DEFAULT '',
  `title_en` varchar(255) NOT NULL DEFAULT '',
  `title_de` varchar(255) NOT NULL DEFAULT '',
  `text1_hr` text,
  `text1_en` text,
  `text1_de` text,
  `text2_hr` text,
  `text2_en` text,
  `text2_de` text,
  `created` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reference`
--

INSERT INTO `reference` (`id`, `orderby`, `front_page`, `categories_id`, `title_hr`, `title_en`, `title_de`, `text1_hr`, `text1_en`, `text1_de`, `text2_hr`, `text2_en`, `text2_de`, `created`) VALUES
(1, 1, 'yes', 0, 'Ref 1 hr22', 'Ref 1 en', 'Ref 1 de', '<p>\r\n	44</p>\r\n', '<p>\r\n	55</p>\r\n', '<p>\r\n	66</p>\r\n', '', '', '', '2015-06-01 17:49:33'),
(2, 2, 'yes', 33, 'fsdfsdf1113321', 'sdffsdf222', 'fdgdfg333', '<p>\r\n	dfgdfgdfg</p>\r\n', '<p>\r\n	34344</p>\r\n', '<p>\r\n	iiiiiiiiiii</p>\r\n', '', '', '', '2015-06-01 18:23:35');

-- --------------------------------------------------------

--
-- Table structure for table `site_files`
--

CREATE TABLE `site_files` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `table_name` varchar(255) NOT NULL DEFAULT '',
  `table_id` int(11) UNSIGNED NOT NULL DEFAULT '0',
  `file_name` varchar(255) NOT NULL DEFAULT '',
  `title` varchar(255) NOT NULL DEFAULT '',
  `lng` char(2) NOT NULL DEFAULT '',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `site_photos`
--

CREATE TABLE `site_photos` (
  `id` int(11) UNSIGNED NOT NULL,
  `orderby` int(11) UNSIGNED NOT NULL,
  `table_name` varchar(255) NOT NULL,
  `table_id` int(11) UNSIGNED NOT NULL,
  `photo_name` varchar(255) NOT NULL,
  `title_hr` varchar(255) NOT NULL DEFAULT '',
  `title_en` varchar(255) NOT NULL DEFAULT '',
  `title_de` varchar(255) NOT NULL DEFAULT '',
  `static` tinyint(1) UNSIGNED NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Indexes for table `admin_users`
--
ALTER TABLE `admin_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reference`
--
ALTER TABLE `reference`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `site_files`
--
ALTER TABLE `site_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `site_photos`
--
ALTER TABLE `site_photos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_users`
--
ALTER TABLE `admin_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=502;
--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `reference`
--
ALTER TABLE `reference`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `site_files`
--
ALTER TABLE `site_files`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `site_photos`
--
ALTER TABLE `site_photos`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `stats_activities`
--