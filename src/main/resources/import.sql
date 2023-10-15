-- db.clientes definition

-- db.clientes definition

CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `region_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clientes_UN` (`email`),
  KEY `FKf1kwxd4whuje3nv9yxddld4c3` (`region_id`),
  CONSTRAINT `FKf1kwxd4whuje3nv9yxddld4c3` FOREIGN KEY (`region_id`) REFERENCES `regiones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.regiones definition

CREATE TABLE `regiones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.usuarios definition

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.roles definition

CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.producto definition

CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `create_at` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.usuarios_roles definition

CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  UNIQUE KEY `UKe1lxmwsk1900d01f7r2buc7p7` (`usuario_id`,`rol_id`),
  KEY `usuarios_roles_FK` (`rol_id`),
  CONSTRAINT `usuarios_roles_FK` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `usuarios_roles_FK_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.facturas definition

CREATE TABLE `facturas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `observacion` varchar(100) DEFAULT NULL,
  `cliente_id` bigint NOT NULL,
  `create_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `facturas_FK` (`cliente_id`),
  CONSTRAINT `facturas_FK` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- db.facturas_items definition

CREATE TABLE `facturas_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `producto_id` bigint NOT NULL,
  `factura_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `facturas_items_FK` (`factura_id`),
  KEY `facturas_items_FK_1` (`producto_id`),
  CONSTRAINT `facturas_items_FK` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`),
  CONSTRAINT `facturas_items_FK_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into db.regiones (nombre) values('Sudamerica');
insert into db.regiones (nombre) values('Centroamerica');
insert into db.regiones (nombre) values('Norteamerica');
insert into db.regiones (nombre) values('Asia');
insert into db.regiones (nombre) values('Europa');
insert into db.regiones (nombre) values('Africa');


insert into db.clientes(nombre,apellido,email,create_at) values('Allan','Suarez','arsc86@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Roberto','Suarez','robertos@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Dome','Suarez','dome@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Maria','Suarez','maria@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Lolo','Baba','loba@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Allan','Suarez','arsc86@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Roberto','Suarez','robertos@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Dome','Suarez','dome@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Maria','Suarez','maria@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Lolo','Baba','loba@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Allan','Suarez','arsc86@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Roberto','Suarez','robertos@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Dome','Suarez','dome@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Maria','Suarez','maria@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Lolo','Baba','loba@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Allan','Suarez','arsc86@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Roberto','Suarez','robertos@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Dome','Suarez','dome@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Maria','Suarez','maria@gmail.com',CURRENT_TIMESTAMP);
insert into db.clientes(nombre,apellido,email,create_at) values('Lolo','Baba','loba@gmail.com',CURRENT_TIMESTAMP);

INSERT into usuarios (username,password,enabled,nombre,apellidos,email) values('allan','allan1',1,'Allan','Suarez','arsc86@gmail.com');
INSERT into usuarios (username,password,enabled,nombre,apellidos,email) values('jose','jose1',1,'Jose','Piguave','pigua@gmail.com');

INSERT INTO roles (nombre) values('ROLE_USER');
INSERT INTO roles (nombre) values('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (2,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (1,2);

insert into db.producto (nombre,precio,create_at) values('Samsung LED',1500,NOW());
insert into db.producto (nombre,precio,create_at) values('Samsung LED -45',1200,NOW());
insert into db.producto (nombre,precio,create_at) values('HP 1000',1000,NOW());
insert into db.producto (nombre,precio,create_at) values('Macbook M1',2000,NOW());
insert into db.producto (nombre,precio,create_at) values('IPHONE 15',900,NOW());
insert into db.producto (nombre,precio,create_at) values('Lenovo Think',1100,NOW());

insert into facturas (descripcion,observacion,cliente_id,create_at) values ('Factura equipos de oficina',null,13,now());

insert into facturas_items (cantidad, factura_id, producto_id) values (1,1,1);
insert into facturas_items (cantidad, factura_id, producto_id) values (2,1,2);
insert into facturas_items (cantidad, factura_id, producto_id) values (1,1,3);
insert into facturas_items (cantidad, factura_id, producto_id) values (1,1,4);