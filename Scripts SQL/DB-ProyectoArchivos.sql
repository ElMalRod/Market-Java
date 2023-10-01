-- Database: chapin-db

-- DROP DATABASE IF EXISTS "chapin-db";

CREATE DATABASE "chapin-db"
    WITH
    OWNER = "emilio-market"
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA  ControlPersonal;
CREATE SCHEMA  ControlVentas;
CREATE SCHEMA  ControlEmpresas;

CREATE TABLE ControlEmpresas.Tienda(
    idTienda SERIAL PRIMARY KEY,
    nombreTienda VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL
);

CREATE TABLE ControlEmpresas.Producto(
    idProducto SERIAL PRIMARY KEY,
    nombreProducto VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    estado BOOLEAN NOT NULL, 
    pasillo INT , 
    idTienda INT NOT NULL,
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)

);


CREATE TABLE ControlPersonal.Empleado(
    idEmpleado SERIAL PRIMARY KEY,
    nombreEmpleado VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    dpi VARCHAR(13) NOT NULL,
    cajas INT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    idTienda INT NOT NULL,
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)
);

CREATE TABLE ControlPersonal.Cliente (
    idCliente SERIAL PRIMARY KEY,
    nitCliente VARCHAR(15) UNIQUE NOT NULL,
    nombreCliente VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    dpi VARCHAR(13) NOT NULL,
    tarjeta VARCHAR(20),
    puntos INT,
    descuento DECIMAL(10, 2),
    direccion VARCHAR(50) NOT NULL
);

CREATE TABLE ControlVentas.Venta(
    idVenta SERIAL NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    totalDescuento DECIMAL(10,2),
    idEmpleado INT NOT NULL,
    nitCliente VARCHAR(15)NOT NULL,
    idTienda INT ,
    PRIMARY KEY (idVenta),
    FOREIGN KEY (idEmpleado) REFERENCES ControlPersonal.Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (nitCliente) REFERENCES ControlPersonal.Cliente(nitCliente),
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)

);

CREATE TABLE ControlVentas.DetalleVenta (
    idDetalleVenta SERIAL NOT NULL,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (idDetalleVenta),
    FOREIGN KEY (idVenta) REFERENCES ControlVentas.Venta(idVenta) ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto)
);
