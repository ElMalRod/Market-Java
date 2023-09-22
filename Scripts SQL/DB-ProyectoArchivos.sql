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
    telefono VARCHAR(10) NOT NULL,
    cajas INT NOT NULL
);

CREATE TABLE ControlEmpresas.Producto(
    idProducto SERIAL PRIMARY KEY,
    nombreProducto VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    estado BOOLEAN NOT NULL, /* 0-BODEGA 1-ESTANTERIA*/
    pasillo INT , /*SI NO TIENE NUMERO ES QUE ESTA EN BODEGA*/
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
    cajas INT,
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)
    FOREIGN KEY (cajas) REFERENCES ControlEmpresas.Tienda(cajas)
);

CREATE TABLE ControlPersonal.Cliente(
    nitCliente SERIAL PRIMARY KEY,
    nombreCliente VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    dpi VARCHAR(13) NOT NULL,
    tarjeta VARCHAR(20),
    puntos INT,
    descuento DECIMAL(10,2),
    direccion VARCHAR(50) NOT NULL
);

CREATE TABLE ControlInventario.Inventario(
    idInventario SERIAL PRIMARY KEY,
    cantidad INT NOT NULL,
    estado BOOLEAN NOT NULL,
    pasillo INT ,
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
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Central', 'Direccion 1', '75687909');
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Norte', 'Direccion 2', '35634509');
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Sur', 'Direccion 3', '53687309');

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Emilio', '12345678', 'Administrador', '1234567891011', 'emilio', '123456', 1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Juan', '12345678', 'Cajero', '1234567891011', 'juan', '123456', 1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Pedro', '12345678', 'Bodega', '1234567891011', 'pedro', '123456', 1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Maria', '12345678', 'Inventario', '1234567891011', 'maria', '123456', 1);

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Luis', '12345678', 'Administrador', '1234567891011', 'luis', '123456', 2);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Jose', '12345678', 'Cajero', '1234567891011', 'jose', '123456', 2);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Carlos', '12345678', 'Bodega', '1234567891011', 'carlos', '123456', 2);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Ana', '12345678', 'Inventario', '1234567891011', 'ana', '123456', 2);

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Mario', '12345678', 'Administrador', '1234567891011', 'mario', '123456', 3);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Luisa', '12345678', 'Cajero', '1234567891011', 'luisa', '123456', 3);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Pedro', '12345678', 'Bodega', '1234567891011', 'pedro', '123456', 3);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda) VALUES ('Maria', '12345678', 'Inventario', '1234567891011', 'maria', '123456', 3);

DELETE FROM ControlEmpresas.Tienda WHERE idTienda = 1;
DELETE FROM ControlEmpresas.Producto WHERE idProducto = 1;