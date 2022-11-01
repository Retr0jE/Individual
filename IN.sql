-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 29 2022 г., 13:12
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `IN`
--

-- --------------------------------------------------------

--
-- Структура таблицы `cheque`
--

CREATE TABLE `cheque` (
  `id` bigint(20) NOT NULL,
  `dateissue` date DEFAULT NULL,
  `tin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `cheque`
--

INSERT INTO `cheque` (`id`, `dateissue`, `tin`, `employee_id`) VALUES
(13, '2022-10-05', '22813371488', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patronymic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `employee`
--

INSERT INTO `employee` (`id`, `active`, `name`, `password`, `patronymic`, `surname`, `username`) VALUES
(3, b'1', 'Никита', '$2a$08$hIT/AWbA3jsZ7yfGMp/FjO1e.bQ.vkqvWNz.8He82L.cH0K.TMPNm', 'Сказал Дюжевой здрасьте', 'Астероид', '123'),
(5, b'1', 'Артём', '$2a$08$TxWtHV3G/.z8uGzSpurtS.AVKdXOwmK.m6l2h5mk65YtiGNef4eaq', 'Араратович', 'Кислый', '321'),
(6, b'1', 'Вадик', '$2a$08$KnU99BsfnMMOKkmawAmw3OTU/lVMuB10UmbUKW.knNmoYK0D.RHNW', '', 'Хаматов', '1234');

-- --------------------------------------------------------

--
-- Структура таблицы `employee_role`
--

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `roles` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `employee_role`
--

INSERT INTO `employee_role` (`employee_id`, `roles`) VALUES
(3, 'ADMIN'),
(5, 'SELLER'),
(6, 'STOREKEEPER');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(14);

-- --------------------------------------------------------

--
-- Структура таблицы `quantity`
--

CREATE TABLE `quantity` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  `weapons_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `supplier`
--

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `warehouse`
--

CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `weapons`
--

CREATE TABLE `weapons` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` int(11) NOT NULL,
  `weapon_model_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `weapons`
--

INSERT INTO `weapons` (`id`, `name`, `price`, `weapon_model_id`) VALUES
(10, 'Калаш', 5555, 9),
(12, 'Дубина', 1488, 11);

-- --------------------------------------------------------

--
-- Структура таблицы `weapons_cheque`
--

CREATE TABLE `weapons_cheque` (
  `weapons_id` bigint(20) NOT NULL,
  `cheque_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `weapons_cheque`
--

INSERT INTO `weapons_cheque` (`weapons_id`, `cheque_id`) VALUES
(10, 13),
(10, 13);

-- --------------------------------------------------------

--
-- Структура таблицы `weapon_model`
--

CREATE TABLE `weapon_model` (
  `id` bigint(20) NOT NULL,
  `material` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `weapon_model`
--

INSERT INTO `weapon_model` (`id`, `material`, `name_model`, `type`) VALUES
(9, 'Сталь', '47', 'Огнестрел'),
(11, 'Дерево', '228', 'Холодное оружие');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `cheque`
--
ALTER TABLE `cheque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq8vdxcdu3caw2mak3in27pbl2` (`employee_id`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `employee_role`
--
ALTER TABLE `employee_role`
  ADD KEY `FKo7rvk7ejtx29vru9cyhf7o68a` (`employee_id`);

--
-- Индексы таблицы `quantity`
--
ALTER TABLE `quantity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrhjgnbnmxx4m2xb8yuhntynao` (`warehouse_id`),
  ADD KEY `FKn0mjjdevckv9fd2y3pclxulqs` (`weapons_id`);

--
-- Индексы таблицы `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo2b0hjvqyltorsnonmh3gxuyr` (`supplier_id`);

--
-- Индексы таблицы `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKalrfniujjy29acpkbehcoumq8` (`weapon_model_id`);

--
-- Индексы таблицы `weapons_cheque`
--
ALTER TABLE `weapons_cheque`
  ADD KEY `FKbq2t18xhwr5h2ctyf22nd9tg7` (`cheque_id`),
  ADD KEY `FK4ww1g3kjpir0mr5t2c3qd63oy` (`weapons_id`);

--
-- Индексы таблицы `weapon_model`
--
ALTER TABLE `weapon_model`
  ADD PRIMARY KEY (`id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `cheque`
--
ALTER TABLE `cheque`
  ADD CONSTRAINT `FKq8vdxcdu3caw2mak3in27pbl2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee_role`
--
ALTER TABLE `employee_role`
  ADD CONSTRAINT `FKo7rvk7ejtx29vru9cyhf7o68a` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `quantity`
--
ALTER TABLE `quantity`
  ADD CONSTRAINT `FKn0mjjdevckv9fd2y3pclxulqs` FOREIGN KEY (`weapons_id`) REFERENCES `weapons` (`id`),
  ADD CONSTRAINT `FKrhjgnbnmxx4m2xb8yuhntynao` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`);

--
-- Ограничения внешнего ключа таблицы `warehouse`
--
ALTER TABLE `warehouse`
  ADD CONSTRAINT `FKo2b0hjvqyltorsnonmh3gxuyr` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Ограничения внешнего ключа таблицы `weapons`
--
ALTER TABLE `weapons`
  ADD CONSTRAINT `FKalrfniujjy29acpkbehcoumq8` FOREIGN KEY (`weapon_model_id`) REFERENCES `weapon_model` (`id`);

--
-- Ограничения внешнего ключа таблицы `weapons_cheque`
--
ALTER TABLE `weapons_cheque`
  ADD CONSTRAINT `FK4ww1g3kjpir0mr5t2c3qd63oy` FOREIGN KEY (`weapons_id`) REFERENCES `weapons` (`id`),
  ADD CONSTRAINT `FKbq2t18xhwr5h2ctyf22nd9tg7` FOREIGN KEY (`cheque_id`) REFERENCES `cheque` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
