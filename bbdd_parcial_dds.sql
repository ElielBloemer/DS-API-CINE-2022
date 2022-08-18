-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: mysql:3306
-- Generation Time: Aug 16, 2022 at 11:22 PM
-- Server version: 8.0.29
-- PHP Version: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bbdd_parcial_dds`
--

-- --------------------------------------------------------

--
-- Table structure for table `actor`
--

CREATE TABLE `actor` (
  `id` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_evento` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `avatar`
--

CREATE TABLE `avatar` (
  `id_avatar` int NOT NULL,
  `color_pelo` varchar(255) DEFAULT NULL,
  `apodo_camiseta` varchar(255) DEFAULT NULL,
  `camiseta_seleccion` varchar(255) DEFAULT NULL,
  `color_ojos` varchar(255) DEFAULT NULL,
  `numero_camiseta` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cuenta`
--

CREATE TABLE `cuenta` (
  `idCuenta` int NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_de_creacion` date DEFAULT NULL,
  `id_usuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cuenta`
--

INSERT INTO `cuenta` (`idCuenta`, `password`, `email`, `fecha_de_creacion`, `id_usuario`) VALUES
(1, 'ebc29121993', 'elielbloemercorrea@gmail.com', '2022-08-16', 1),
(2, 'agustin12345', 'agustindesouza@gmail.com', '2022-08-16', 2);

-- --------------------------------------------------------

--
-- Table structure for table `entrada`
--

CREATE TABLE `entrada` (
  `id_entrada` int NOT NULL,
  `fechaEvento` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `evento_id_evento` int DEFAULT NULL,
  `id_reserva` int DEFAULT NULL,
  `id_sala` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `equipo_partido`
--

CREATE TABLE `equipo_partido` (
  `Partido_id_evento` int NOT NULL,
  `equipos_id_seleccion` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evento`
--

CREATE TABLE `evento` (
  `id_evento` int NOT NULL,
  `calificacion` int DEFAULT NULL,
  `fecha` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tipoDeEvento` varchar(255) DEFAULT NULL,
  `id_sala` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `jugador`
--

CREATE TABLE `jugador` (
  `id_jugador` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `partido`
--

CREATE TABLE `partido` (
  `id_evento` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pelicula`
--

CREATE TABLE `pelicula` (
  `minutosPelicula` int DEFAULT NULL,
  `productora` varchar(255) DEFAULT NULL,
  `id_evento` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pelicula_actor`
--

CREATE TABLE `pelicula_actor` (
  `Pelicula_id_evento` int NOT NULL,
  `elenco_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Producto`
--

CREATE TABLE `Producto` (
  `tipo` varchar(31) NOT NULL,
  `id_producto` int NOT NULL,
  `precio` float DEFAULT NULL,
  `tamanio` varchar(255) DEFAULT NULL,
  `nombre_comercial` varchar(255) DEFAULT NULL,
  `edicion` varchar(255) DEFAULT NULL,
  `id_usuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int NOT NULL,
  `costo_total` double DEFAULT NULL,
  `esta_paga` bit(1) DEFAULT NULL,
  `numero_operacion` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `id_sala` int NOT NULL,
  `espacioDisponible` int DEFAULT NULL,
  `espacioTotal` int DEFAULT NULL,
  `id_evento` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seleccion`
--

CREATE TABLE `seleccion` (
  `id_seleccion` int NOT NULL,
  `continente` varchar(255) DEFAULT NULL,
  `mundialesGanado` int DEFAULT NULL,
  `nombrePais` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tarjeta_de_debito`
--

CREATE TABLE `tarjeta_de_debito` (
  `id_tarjeta_de_debito` int NOT NULL,
  `dinero_disponible` double DEFAULT NULL,
  `numero_de_tarjeta` int DEFAULT NULL,
  `titular_tarjeta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `id_avatar` int DEFAULT NULL,
  `id_cuenta` int DEFAULT NULL,
  `id_reserva` int DEFAULT NULL,
  `id_tarjeta_de_debito` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `apellido`, `dni`, `edad`, `nombre`, `nombre_usuario`, `id_avatar`, `id_cuenta`, `id_reserva`, `id_tarjeta_de_debito`) VALUES
(1, 'correa', '9501348556', 28, 'eliel bloemer', 'ebloemer', NULL, 1, NULL, NULL),
(2, 'de souza', '6523415', 22, 'agustin', 'agus', NULL, 2, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbehmwrrh9b487sf67wvnl36nd` (`id_evento`);

--
-- Indexes for table `avatar`
--
ALTER TABLE `avatar`
  ADD PRIMARY KEY (`id_avatar`);

--
-- Indexes for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`idCuenta`),
  ADD KEY `FKpuu4iaiq2yhyvn4ebxcj9uefk` (`id_usuario`);

--
-- Indexes for table `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`id_entrada`),
  ADD KEY `FK861tdv3drt2euijy9xoev745h` (`evento_id_evento`),
  ADD KEY `FKinuddjmy12b08dg94wmjy0bqq` (`id_reserva`),
  ADD KEY `FK5x4d495psgwxopmw2vc6kruds` (`id_sala`),
  ADD KEY `FK69gcslrpprfdj6e1pjrqtpban` (`id_usuario`);

--
-- Indexes for table `equipo_partido`
--
ALTER TABLE `equipo_partido`
  ADD KEY `FKico8986npiaokreruvmp6auco` (`equipos_id_seleccion`),
  ADD KEY `FK2542rw806qfktyv89034arlll` (`Partido_id_evento`);

--
-- Indexes for table `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id_evento`),
  ADD KEY `FKqnt843c765n8kbn0ml9rnmlwd` (`id_sala`);

--
-- Indexes for table `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id_jugador`);

--
-- Indexes for table `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id_evento`);

--
-- Indexes for table `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id_evento`);

--
-- Indexes for table `pelicula_actor`
--
ALTER TABLE `pelicula_actor`
  ADD UNIQUE KEY `UK_4vsboj52y0q2e3djyo6f77ckd` (`elenco_id`),
  ADD KEY `FKlbnhef72q6mnq6qh63x45t79s` (`Pelicula_id_evento`);

--
-- Indexes for table `Producto`
--
ALTER TABLE `Producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `FK94jn429svrslssklcmm0456mx` (`id_usuario`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id_sala`),
  ADD KEY `FKl2b4w0trchbw6sndn8951srmb` (`id_evento`);

--
-- Indexes for table `seleccion`
--
ALTER TABLE `seleccion`
  ADD PRIMARY KEY (`id_seleccion`);

--
-- Indexes for table `tarjeta_de_debito`
--
ALTER TABLE `tarjeta_de_debito`
  ADD PRIMARY KEY (`id_tarjeta_de_debito`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `FKrekg6oallpg9gv8rv0631l1pp` (`id_avatar`),
  ADD KEY `FK16y6ku0k2alscgp71om4gtm08` (`id_cuenta`),
  ADD KEY `FKh2abeqiumsgtui3h7auewjj1q` (`id_reserva`),
  ADD KEY `FKg9q31yip1snuk5pmi6c91f0dh` (`id_tarjeta_de_debito`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actor`
--
ALTER TABLE `actor`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `avatar`
--
ALTER TABLE `avatar`
  MODIFY `id_avatar` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `idCuenta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `entrada`
--
ALTER TABLE `entrada`
  MODIFY `id_entrada` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id_jugador` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Producto`
--
ALTER TABLE `Producto`
  MODIFY `id_producto` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sala`
--
ALTER TABLE `sala`
  MODIFY `id_sala` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `seleccion`
--
ALTER TABLE `seleccion`
  MODIFY `id_seleccion` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tarjeta_de_debito`
--
ALTER TABLE `tarjeta_de_debito`
  MODIFY `id_tarjeta_de_debito` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actor`
--
ALTER TABLE `actor`
  ADD CONSTRAINT `FKbehmwrrh9b487sf67wvnl36nd` FOREIGN KEY (`id_evento`) REFERENCES `pelicula` (`id_evento`);

--
-- Constraints for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `FKpuu4iaiq2yhyvn4ebxcj9uefk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `FK5x4d495psgwxopmw2vc6kruds` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`),
  ADD CONSTRAINT `FK69gcslrpprfdj6e1pjrqtpban` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FK861tdv3drt2euijy9xoev745h` FOREIGN KEY (`evento_id_evento`) REFERENCES `evento` (`id_evento`),
  ADD CONSTRAINT `FKinuddjmy12b08dg94wmjy0bqq` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`);

--
-- Constraints for table `equipo_partido`
--
ALTER TABLE `equipo_partido`
  ADD CONSTRAINT `FK2542rw806qfktyv89034arlll` FOREIGN KEY (`Partido_id_evento`) REFERENCES `partido` (`id_evento`),
  ADD CONSTRAINT `FKico8986npiaokreruvmp6auco` FOREIGN KEY (`equipos_id_seleccion`) REFERENCES `seleccion` (`id_seleccion`);

--
-- Constraints for table `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `FKqnt843c765n8kbn0ml9rnmlwd` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`);

--
-- Constraints for table `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `FKesx086859aauugo8pppkv0j2u` FOREIGN KEY (`id_jugador`) REFERENCES `seleccion` (`id_seleccion`);

--
-- Constraints for table `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `FK8rdum9uquigonvg8yisr9kvxt` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);

--
-- Constraints for table `pelicula`
--
ALTER TABLE `pelicula`
  ADD CONSTRAINT `FKp3s1xn897g0m2pyut3jt943h1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);

--
-- Constraints for table `pelicula_actor`
--
ALTER TABLE `pelicula_actor`
  ADD CONSTRAINT `FKlbnhef72q6mnq6qh63x45t79s` FOREIGN KEY (`Pelicula_id_evento`) REFERENCES `pelicula` (`id_evento`),
  ADD CONSTRAINT `FKmg9jb5jl5abw0r9wsrmb6k6xr` FOREIGN KEY (`elenco_id`) REFERENCES `actor` (`id`);

--
-- Constraints for table `Producto`
--
ALTER TABLE `Producto`
  ADD CONSTRAINT `FK94jn429svrslssklcmm0456mx` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `FKl2b4w0trchbw6sndn8951srmb` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK16y6ku0k2alscgp71om4gtm08` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`idCuenta`),
  ADD CONSTRAINT `FKg9q31yip1snuk5pmi6c91f0dh` FOREIGN KEY (`id_tarjeta_de_debito`) REFERENCES `tarjeta_de_debito` (`id_tarjeta_de_debito`),
  ADD CONSTRAINT `FKh2abeqiumsgtui3h7auewjj1q` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`),
  ADD CONSTRAINT `FKrekg6oallpg9gv8rv0631l1pp` FOREIGN KEY (`id_avatar`) REFERENCES `avatar` (`id_avatar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
