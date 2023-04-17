--Creación BD

CREATE DATABASE Proyectos

--Creación tablas

use Proyectos

CREATE TABLE Direcciones
(
   DGN_Id_dirgen SMALLINT PRIMARY KEY,
   DGN_Denominacion VARCHAR(150)
);

CREATE TABLE Servicios
(
   SRV_Id_servicio SMALLINT NOT NULL PRIMARY KEY,
   SRV_Denominacion varchar(150),
   SRV_Id_dirgen SMALLINT FOREIGN KEY REFERENCES Direcciones (DGN_Id_dirgen)
);

CREATE TABLE Proyectos
(
   Id_proyecto SMALLINT NOT NULL PRIMARY KEY,
   DenominacionC varchar(50) NOT NULL,
   DenominacionL varchar(250),
   FechaInicio SMALLDATETIME,
   Id_servicio SMALLINT FOREIGN KEY REFERENCES Servicios (SRV_Id_servicio)
);

CREATE TABLE Secciones
(
   SCC_Id_seccion TINYINT NOT NULL PRIMARY KEY,
   SCC_Denominacion VARCHAR(80)
);

CREATE TABLE Recursos
(
   RCR_Id_recurso SMALLINT NOT NULL PRIMARY KEY,
   RCR_nombre varchar(60) NOT NULL,
   RCR_Id_seccion TINYINT NOT NULL,
   RCR_NRPT INT,
   FOREIGN KEY (RCR_Id_seccion) REFERENCES Secciones (SCC_Id_seccion)
   --esto esta mal
);

CREATE TABLE Adscripciones
(
   ADS_Id_seccion TINYINT NOT NULL,
   ADS_Id_recurso SMALLINT NOT NULL,
   ADS_FechaAdsc SMALLDATETIME NOT NULL,
   PRIMARY KEY (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc),
   FOREIGN KEY (ADS_Id_seccion) REFERENCES Secciones (SCC_Id_seccion),
   FOREIGN KEY (ADS_Id_recurso) REFERENCES Recursos (RCR_Id_recurso)
);

CREATE TABLE Empresas
(
   MPR_Id_empresa SMALLINT NOT NULL PRIMARY KEY,
   MPR_Denominacion VARCHAR(80)
);

CREATE TABLE RecursosX
(
   RCX_Id_recursoX SMALLINT NOT NULL PRIMARY KEY,
   RCX_Nombre VARCHAR(60) NOT NULL,
   RCX_DNI CHAR(9),
   RCX_Id_empresa SMALLINT FOREIGN KEY REFERENCES Empresas (MPR_Id_empresa)
);

CREATE TABLE Estados
(
   STD_Id_estado SMALLINT NOT NULL PRIMARY KEY,
   STD_Denominacion VARCHAR(25)
);

CREATE TABLE Subproyectos
(
   SBP_Id_subproyecto SMALLINT NOT NULL PRIMARY KEY,
   SBP_DenominacionC VARCHAR(50) NOT NULL,
   SBP_DenominacionL VARCHAR(250),
   SBP_FechaInicio SMALLDATETIME,
   SBP_Id_proyecto SMALLINT NOT NULL FOREIGN KEY REFERENCES Proyectos (Id_proyecto)
);

CREATE TABLE Asignaciones
(
   SGN_Id_subproyecto SMALLINT NOT NULL,
   SGN_Id_recursoX SMALLINT NOT NULL,
   PRIMARY KEY (SGN_Id_subproyecto, SGN_Id_recursoX),
   FOREIGN KEY (SGN_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (SGN_Id_recursoX) REFERENCES RecursosX (RCX_Id_recursoX),
);

CREATE TABLE Desarrollo
(
   DSR_Id_subproyecto SMALLINT NOT NULL,
   DSR_Id_seccion TINYINT NOT NULL,
   DSR_Id_recurso SMALLINT NOT NULL,
   DSR_FechaAdsc SMALLDATETIME NOT NULL,
   PRIMARY KEY (DSR_Id_subproyecto, DSR_Id_seccion, DSR_Id_recurso, DSR_FechaAdsc),
   FOREIGN KEY (DSR_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (DSR_Id_seccion, DSR_Id_recurso, DSR_FechaAdsc) REFERENCES Adscripciones (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc)
);

CREATE TABLE Explotacion
(
   XPL_Id_subproyecto SMALLINT NOT NULL,
   XPL_Id_seccion TINYINT NOT NULL,
   XPL_Id_recurso SMALLINT NOT NULL,
   XPL_FechaAdsc SMALLDATETIME NOT NULL,
   PRIMARY KEY (XPL_Id_subproyecto, XPL_Id_seccion, XPL_Id_recurso, XPL_FechaAdsc),
   FOREIGN KEY (XPL_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (XPL_Id_seccion, XPL_Id_recurso, XPL_FechaAdsc) REFERENCES Adscripciones (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc)
);

CREATE TABLE Situaciones
(
   STC_Id_subproyecto SMALLINT NOT NULL,
   STC_Id_estado SMALLINT NOT NULL,
   STC_FechaRef SMALLDATETIME NOT NULL,
   PRIMARY KEY (STC_Id_subproyecto, STC_Id_estado),
   FOREIGN KEY (STC_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (STC_Id_estado) REFERENCES Estados (STD_Id_estado)
);

--Insertar Datos

--Direcciones
INSERT INTO Direcciones
VALUES
   (1, 'C/ Elvira Quintana 30')
INSERT INTO Direcciones
VALUES
   (2, 'C/ Ana Finch 5')
INSERT INTO Direcciones
VALUES
   (3, 'C/ Marquesa Pinares 3')
INSERT INTO Direcciones
VALUES
   (4, 'C/ General Prim 78')
INSERT INTO Direcciones
VALUES
   (5, 'C/ Rey Juan Carlos 33')

--Proyectos
INSERT INTO Servicios
VALUES(1, 'Montaje de computadoras', 1)
INSERT INTO Proyectos
VALUES
   (1, 'Proyecto Informático Rural', 'Proyecto para llevar la informática a zonas rurales españolas', '2003-04-06', 1)

--Recursos
INSERT INTO Secciones
VALUES(1, 'Sección de desarollo de base de datos')
INSERT INTO Recursos
VALUES(1, 'Juan Carlos Peguero', 1, 23)
INSERT INTO Recursos
VALUES(2, 'Juan Arias', 1, 73)
INSERT INTO Recursos
VALUES(3, 'Juan Ángel Contreras', 1, 36)

--Desarrollo
INSERT INTO Subproyectos
VALUES(1, 'Instalación', 'Instalación de ordenadores en zonas comunes', '2003-04-09', 1)
INSERT INTO Adscripciones
VALUES(1, 1, '2003-04-09')
INSERT INTO Desarrollo
VALUES(1, 1, 1, '2003-04-09')