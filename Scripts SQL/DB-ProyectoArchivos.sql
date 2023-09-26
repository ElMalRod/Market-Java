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


CREATE TABLE ControlInventario.Inventario(
    idInventario SERIAL PRIMARY KEY,
    cantidad INT NOT NULL,
    estado BOOLEAN NOT NULL,
    pasillo INT ,
    idProducto INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto)
);


CREATE TABLE ControlVentas.Venta(
    idVenta SERIAL NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    idEmpleado INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    nitCliente VARCHAR(15)NOT NULL,
    idTienda INT NOT NULL,
    PRIMARY KEY (idVenta),
    FOREIGN KEY (idEmpleado) REFERENCES ControlPersonal.Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto),
    FOREIGN KEY (nitCliente) REFERENCES ControlPersonal.Cliente(nitCliente),
    FOREIGN KEY (idTienda) REFERENCES ControlEmpresas.Tienda(idTienda)

);

CREATE TABLE ControlVentas.DetalleVenta(
    idDetalleVenta SERIAL PRIMARY KEY,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    FOREIGN KEY (idVenta) REFERENCES ControlVentas.Venta(idVenta),
    FOREIGN KEY (idProducto) REFERENCES ControlEmpresas.Producto(idProducto)
);
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Centro', 'Direccion1', '12345678');
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Norte', 'Direccion2', '12345678');
INSERT INTO ControlEmpresas.Tienda(nombreTienda, direccion, telefono) VALUES ('Sucursal Sur', 'Direccion3', '12345678');

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Emilio', '12345678', 'Administrador', '1234567891011', 'emilio', '123456', 1,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Juan', '12345678', 'Cajero', '1234567891011', 'juan', '123456', 1, 1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Pedro', '12345678', 'Bodega', '1234567891011', 'pedro', '123456', 1,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Maria', '12345678', 'Inventario', '1234567891011', 'maria', '123456', 1,NULL);

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Luis', '12345678', 'Administrador', '1234567891011', 'luis', '123456', 2,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Jose', '12345678', 'Cajero', '1234567891011', 'jose', '123456', 2,1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Carlos', '12345678', 'Bodega', '1234567891011', 'carlos', '123456', 2,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Ana', '12345678', 'Inventario', '1234567891011', 'ana', '123456', 2,NULL);

INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Mario', '12345678', 'Administrador', '1234567891011', 'mario', '123456', 3,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Luisa', '12345678', 'Cajero', '1234567891011', 'luisa', '123456', 3,1);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Pedro', '12345678', 'Bodega', '1234567891011', 'pedro', '123456', 3,NULL);
INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi, username, password, idTienda,cajas) VALUES ('Maria', '12345678', 'Inventario', '1234567891011', 'maria', '123456', 3,NULL);



INSERT INTO ControlPersonal.Cliente (nitCliente, nombreCliente, telefono, dpi, tarjeta, puntos, descuento, direccion) VALUES ('12345', 'Ale', '12345678', '1234567891011', '1234567891011', 0, 0, 'Direccion1');
INSERT INTO ControlPersonal.Cliente (nitCliente, nombreCliente, telefono, dpi, tarjeta, puntos, descuento, direccion) VALUES ('123', 'Alan', '12345678', '1234567891012', '1234567891012', 0, 0, 'Direccion2');

