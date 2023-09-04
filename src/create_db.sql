--DELETE FROM Transacciones;
--DELETE FROM Categorias;
--DELETE FROM GestorDeCuenta;
--DELETE FROM TipodeCuenta;
--DELETE FROM TipoOperacion;

--DROP FOREIGN KEY FK_TipodeCuenta;
DROP ALL OBJECTS;

DROP TABLE IF EXISTS TipoOperacion;
CREATE TABLE IF NOT EXISTS TipoOperacion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    operacion VARCHAR(1) NOT NULL
);

INSERT INTO TipoOperacion (id, nombre, operacion) VALUES
(1, 'Ingreso', '+'),
(2, 'Egreso', '-') ;


DROP TABLE IF EXISTS TipodeCuenta;
CREATE TABLE TipodeCuenta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

INSERT INTO TipodeCuenta (id, nombre) VALUES
(1, 'Efectivo'),
(2, 'Cuentas'),
(3, 'Ahorros'),
(4, 'Inveriones') ;


DROP TABLE IF EXISTS GestorDeCuenta;
CREATE TABLE GestorDeCuenta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipoDeCuentaId INT,
    descripcion VARCHAR(20),
    saldoInicial DOUBLE
);

ALTER TABLE GestorDeCuenta
ADD CONSTRAINT FK_TipodeCuenta
FOREIGN KEY (tipoDeCuentaId) REFERENCES TipodeCuenta(id);

INSERT INTO GestorDeCuenta (id, tipoDeCuentaId, descripcion, saldoInicial) VALUES
(1, 1, 'Efectivo', 0.0),
(2, 2, 'Pablo', 250.50),
(3, 3, 'Ahorros', 0.0),
(4, 4, 'Inveriones', 0.0);


DROP TABLE IF EXISTS Categorias;
CREATE TABLE Categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tipoOperacionId INT,

    FOREIGN KEY (tipoOperacionId) REFERENCES TipoOperacion(id)
);

INSERT INTO Categorias (nombre, tipoOperacionId) VALUES
('Sueldo', 1),
('Otros', 1),
('Comida', 2),
('Entretenimiento', 2);

DROP TABLE IF EXISTS Transacciones;
CREATE TABLE Transacciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    gestorDeCuentaId INT,
    categoriaId INT,
    fecha VARCHAR(20),
    monto DOUBLE,

    FOREIGN KEY (gestorDeCuentaId) REFERENCES GestorDeCuenta(id),
    FOREIGN KEY (categoriaId) REFERENCES Categorias(id)
);

INSERT INTO Transacciones (id, gestorDeCuentaId, categoriaId, fecha, monto) VALUES
(1, 2, 1, '01-08-2023', 1000.00),
(2, 2, 2, '02-08-2023', 50.00),
(3, 2, 1, '03-08-2023', 1500.00),
(4, 2, 2, '04-08-2023', 70.00);


--DROP TABLE Transacciones;
--DROP TABLE Categorias;
--DROP TABLE GestorDeCuenta;
--DROP TABLE TipodeCuenta;
--DROP TABLE TipoOperacion;
