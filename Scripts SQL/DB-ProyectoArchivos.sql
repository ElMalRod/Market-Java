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
CREATE SCHEMA  ControlInventario;
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
    precio DECIMAL(10,2) NOT NULL,
    idTienda INT NOT NULL,
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)
);

CREATE TABLE ControlPersonal.Empleado(
    idEmpleado SERIAL PRIMARY KEY,
    nombreEmpleado VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    dpi VARCHAR(13) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    idTienda INT NOT NULL,
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)
);

CREATE TABLE ControlPersonal.Cliente(
    nitCliente SERIAL PRIMARY KEY,
    nombreCliente VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    dpi VARCHAR(13) NOT NULL,
    descuento DECIMAL(10,2) NOT NULL,
    direccion VARCHAR(50) NOT NULL
);

CREATE TABLE ControlInventario.Inventario(
    idInventario SERIAL PRIMARY KEY,
    cantidad INT NOT NULL,
    idProducto INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto)
);

CREATE TABLE ControlVentas.Venta(
    idVenta SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    idEmpleado INT NOT NULL,
    idProducto INT NOT NULL,
    nitCliente INT NOT NULL,
    idTienda INT NOT NULL,
    FOREIGN KEY (idEmpleado) REFERENCES ControlPersonal.Empleado(idEmpleado),
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto),
    FOREIGN KEY (nitCliente) REFERENCES ControlPersonal.Cliente(nitCliente),
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)

);
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Tienda 1', 'Direccion 1', '12345678');
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Emilio', '12345678', 'Administrador', '1234567891011', 'emilio', '123456', 1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Juan', '12345678', 'Cajero', '1234567891011', 'juan', '123456', 1);