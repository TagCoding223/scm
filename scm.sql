-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2025 at 08:21 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scm`
--

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `favorite` bit(1) NOT NULL,
  `linkedin_link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `picture` text DEFAULT NULL,
  `website_link` varchar(255) DEFAULT NULL,
  `user_user_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cloudinary_image_public_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `address`, `description`, `favorite`, `linkedin_link`, `name`, `phone_number`, `picture`, `website_link`, `user_user_id`, `email`, `cloudinary_image_public_id`) VALUES
('4ca4f0c7-34e4-449b-82cb-577aff9434a0', 'Lacknow', 'lack', b'0', 'luck.li', 'Lakhan', '8974562310', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/717b0b67-3aeb-4c8a-abe2-5778a26ec9c7?_a=DAGAACAVZAA0', 'luck.face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'lak@outlock.com', '717b0b67-3aeb-4c8a-abe2-5778a26ec9c7'),
('50a24d33-73ab-497a-9a71-84339af7de70', 'Hong Kong', 'Hot Dog', b'1', '', 'Dev Das K', '7412369855', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/39f46fe3-de75-46b7-8ce5-d8625296c306?_a=DAGAACAXZAA0', 'face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'dev@hotmail.com', '39f46fe3-de75-46b7-8ce5-d8625296c306'),
('7afe7972-7408-40fa-a366-b01ce966755b', 'lover pic yard', 'lover pic', b'1', 'https://in.linkedin.com/', 'lover img', '9638527410', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/0036ae64-1f8c-404d-b312-73fee21c97fc?_a=DAGAACAXZAA0', 'https://facebook.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'loveimg@gmail.com', '0036ae64-1f8c-404d-b312-73fee21c97fc'),
('a3c5f3e4-4918-4ce4-b35f-aef2846b9cc8', 'Dubai', 'Rich People', b'0', 'https://in.linkedin.com/', 'Purva', '9865327410', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/1efa1c4a-2063-4d66-bf79-ddfb70437de8?_a=DAGAACAXZAA0', 'https://facebook.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'p@gmail.com', '1efa1c4a-2063-4d66-bf79-ddfb70437de8'),
('a808c3a0-ab92-4f4b-8bc0-41d60a797a3c', 'fdd', '', b'1', '', 'Dev', '7412369855', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/6527087d-84fc-446e-8fd2-f315598a7f6a?_a=DAGAACAVZAA0', 'face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'k@gmail.com', '6527087d-84fc-446e-8fd2-f315598a7f6a'),
('b1bf7ee2-3880-49fb-b420-759a3954bafb', 'Lover Street', 'Lover loves love', b'1', 'https://in.linkedin.com/', 'My Love K', '9632147850', NULL, 'https://facebook.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'mylovek@gmail.com', NULL),
('cd2b660f-65f4-4455-8efc-17ea8732fba5', 'New York', 'Cool', b'0', NULL, 'Gopal', '5548796541', NULL, 'face.com', NULL, 'gopal@gmail.com', NULL),
('dbcbcf77-32ce-4d43-b571-e20114a02dd3', 'Pune', 'Puri', b'0', 'm.link.in', 'Mohit', '9632147850', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/ac2b4570-617a-4bfd-9b3d-49a4b62738ee?_a=DAGAACAVZAA0', 'm.face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'mohit@hotmail.com', 'ac2b4570-617a-4bfd-9b3d-49a4b62738ee'),
('e0ac13eb-196e-4f17-a2db-57c1ae45bc2f', 'Bihar', 'i\'m bihar boy.', b'1', 'https://in.linkedin.com/', 'Udit', '9638527410', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/30040114-e4d0-45c3-b6e4-22f6fcd43679?_a=DAGAACAXZAA0', 'www.facebook.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'udit@outlouck.com', '30040114-e4d0-45c3-b6e4-22f6fcd43679'),
('e8ecc7d2-1954-4752-a289-fa8d59a22c9b', 'Mumbai', 'Hello mumbai', b'1', NULL, 'Dev', '7412369855', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/3e188a2d-4f16-4fd7-bd55-dad60ca22215?_a=DAGAACAVZAA0', 'face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'dev@hotmail.com', NULL),
('f1a435cd-3084-4cb3-aaf0-a679999e4e7c', 'Delhi', 'Delhi wale...', b'0', NULL, 'Krishna', '7894561230', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/d081d801-5ab0-4d9d-8101-343089108d64?_a=DAGAACAVZAA0', 'face.com', '4ba4dac8-c45a-44be-874b-32d610952609', 'krishna@gmail.com', 'd081d801-5ab0-4d9d-8101-343089108d64'),
('f5e77624-4292-484d-a8e9-6b19a5c943ae', 'Nagpur', 'Nagpur', b'0', NULL, 'Amit', '9874563201', 'https://res.cloudinary.com/dcvg6aj9u/image/upload/c_fill,h_500,w_500/c4f0f990-6cca-4771-b2b4-7b39d85679ff?_a=DAGAACAVZAA0', '', '4ba4dac8-c45a-44be-874b-32d610952609', 'amit@gmail.com', 'c4f0f990-6cca-4771-b2b4-7b39d85679ff');

-- --------------------------------------------------------

--
-- Table structure for table `social_link`
--

CREATE TABLE `social_link` (
  `id` bigint(20) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `contact_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` varchar(255) NOT NULL,
  `about` text DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `email_verified` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `phone_verified` bit(1) NOT NULL,
  `profile_pic` text DEFAULT NULL,
  `provider` enum('GITHUB','GOOGLE','SELF') DEFAULT NULL,
  `provider_user_id` varchar(255) DEFAULT NULL,
  `email_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `about`, `email`, `email_verified`, `enabled`, `user_name`, `password`, `phone_number`, `phone_verified`, `profile_pic`, `provider`, `provider_user_id`, `email_token`) VALUES
('059ef890-8659-4574-bc3c-01891c0a5c09', 'Crazy Guy!', 'vishal@gmail.com', b'0', b'0', 'Vishal', '$2a$10$OZj/KIPBsMb9Qzrb6v/.AOjIwHyuivR4/.baCyL1J0rAPJovpeh.u', '7410852369', b'0', 'https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740', 'SELF', NULL, NULL),
('094a1158-e070-4c4f-8372-cabc8aa6dce5', 'I\'m Developer.', 'v@gmail.com', b'0', b'1', 'Vishal Chouhan', '$2a$10$WvomXBAlJLpzis50g00/AegrgSZvcnGONFlV5RpMOrWTJ1K.IO5kW', '741852963', b'0', 'https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740', 'SELF', NULL, NULL),
('20a7b574-a7cb-4570-afc0-8491da1a1717', 'This account is created using google.', 'vishalchouhanv.c22.3@gmail.com', b'1', b'1', 'Vishal Chouhan', '$2a$10$3ZX1uPmx6LP5rcINKzBCIuIi3X60Vm1gTJUtqIqlngRiItXClabDu', '00000000', b'0', 'https://lh3.googleusercontent.com/a/ACg8ocL7oA_Gk4gmWEPnr42H1AV1RG9vC8e0BBdXClLeruMbB1i9eQ=s96-c', 'GOOGLE', '100384356963293130754', NULL),
('3ec329c3-8802-4d91-8ca2-2efb4196767c', 'code test', 'codespacev.c22.3@gmail.com', b'1', b'1', 'Coder', '$2a$10$d4lsnqA6uqZfKTrviQQmcOXSYdb5DBkCVoRRFhvRbjXYCHBkFQ7oS', '7697625602', b'0', 'https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740', 'SELF', NULL, '6aafbbbc-9d99-44d7-b331-18a90b18bc7b'),
('4ba4dac8-c45a-44be-874b-32d610952609', 'a', 'k@gmail.com', b'0', b'1', 'Kumkum', '$2a$10$SCVkgm0Ztk7xVhpCeuk67e/6j.rnoMEjq4qIjlqlq8rvrBRNa9egS', '845632179', b'0', 'https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740', 'SELF', NULL, NULL),
('4f0f6b0a-15a7-4ffc-a8e0-0358b2a3a9ec', 'This account is created using github.', 'TagCoding223@github.com', b'1', b'1', 'TagCoding223', '$2a$10$//g4.uin28sycrdQ.ee4guHIpmEHWTVyVVkhLVINGASAait7qGH46', '00000000', b'0', 'https://avatars.githubusercontent.com/u/165742045?v=4', 'GITHUB', '165742045', NULL),
('dcacea26-dc24-45e9-bd55-d4edaff213b7', 'Udit is rockstar', 'udit@gmail.com', b'0', b'1', 'Udit', '$2a$10$NAIold9JoKxt1KT.lGbjducS.LbRrq4GcnnQLLNNvvjbLOGJh9a0C', '852369741', b'0', 'https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?t=st=1741882756~exp=1741886356~hmac=7a12d94f4e44e1e3e853e9806a23537176c7544c45322b2d651084badf473429&w=740', 'SELF', NULL, NULL),
('f854b352-4bdd-4ae4-8ae0-7f3e998270d8', 'This is dummy user created initially.', 'admin@gmail.com', b'1', b'1', 'admin', '$2a$10$tqOYMpFdkep.z5pmfkM1ceQ10WWf8G3praYpGjMdHa8owPbUxSV/G', NULL, b'1', NULL, 'SELF', NULL, 'b52d52ee-b8b4-4e1f-9f82-0c8b349c173b');

-- --------------------------------------------------------

--
-- Table structure for table `user_role_list`
--

CREATE TABLE `user_role_list` (
  `user_user_id` varchar(255) NOT NULL,
  `role_list` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_role_list`
--

INSERT INTO `user_role_list` (`user_user_id`, `role_list`) VALUES
('094a1158-e070-4c4f-8372-cabc8aa6dce5', 'ROLE_USER'),
('4ba4dac8-c45a-44be-874b-32d610952609', 'ROLE_USER'),
('dcacea26-dc24-45e9-bd55-d4edaff213b7', 'ROLE_USER'),
('20a7b574-a7cb-4570-afc0-8491da1a1717', 'ROLE_USER'),
('4f0f6b0a-15a7-4ffc-a8e0-0358b2a3a9ec', 'ROLE_USER'),
('059ef890-8659-4574-bc3c-01891c0a5c09', 'ROLE_USER'),
('3ec329c3-8802-4d91-8ca2-2efb4196767c', 'ROLE_USER'),
('f854b352-4bdd-4ae4-8ae0-7f3e998270d8', 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1965xd6bvri2cnjnyrwfd78pp` (`user_user_id`);

--
-- Indexes for table `social_link`
--
ALTER TABLE `social_link`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjgl9oakag3cpf4btscnvqjpan` (`contact_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);
ALTER TABLE `users` ADD FULLTEXT KEY `UK9q63snka3mdh91as4io72espi` (`phone_number`);

--
-- Indexes for table `user_role_list`
--
ALTER TABLE `user_role_list`
  ADD KEY `FKe4ohindc5lhgsu9c4ngq83ud0` (`user_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `social_link`
--
ALTER TABLE `social_link`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `FK1965xd6bvri2cnjnyrwfd78pp` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `social_link`
--
ALTER TABLE `social_link`
  ADD CONSTRAINT `FKjgl9oakag3cpf4btscnvqjpan` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`);

--
-- Constraints for table `user_role_list`
--
ALTER TABLE `user_role_list`
  ADD CONSTRAINT `FKe4ohindc5lhgsu9c4ngq83ud0` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
