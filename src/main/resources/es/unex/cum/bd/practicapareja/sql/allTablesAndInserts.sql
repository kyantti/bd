
--Creación BD

CREATE DATABASE Proyectos

--Creación tablas

use Proyectos

CREATE TABLE Direcciones (
   DGN_Id_dirgen SMALLINT PRIMARY KEY,
   DGN_Denominacion VARCHAR(150)
);

CREATE TABLE Servicios (
   SRV_Id_servicio SMALLINT NOT NULL PRIMARY KEY,
   SRV_Denominacion varchar(150),
   SRV_Id_dirgen SMALLINT FOREIGN KEY REFERENCES Direcciones (DGN_Id_dirgen)
);

CREATE TABLE Proyectos (
   PRY_Id_proyecto SMALLINT NOT NULL PRIMARY KEY,
   PRY_DenominacionC varchar(50) NOT NULL,
   PRY_DenominacionL varchar(250),
   PRY_FechaInicio SMALLDATETIME,
   PRY_Id_servicio SMALLINT FOREIGN KEY REFERENCES Servicios (SRV_Id_servicio)
);

CREATE TABLE Secciones (
   SCC_Id_seccion TINYINT NOT NULL PRIMARY KEY,
   SCC_Denominacion VARCHAR(80)
);

CREATE TABLE Recursos (
   RCR_Id_recurso SMALLINT NOT NULL PRIMARY KEY,
   RCR_nombre varchar(60) NOT NULL,
   RCR_Id_seccion TINYINT NOT NULL,
   RCR_NRPT INT,
   FOREIGN KEY (RCR_Id_seccion) REFERENCES Secciones (SCC_Id_seccion) --esto esta mal
);

CREATE TABLE Adscripciones (
    ADS_Id_seccion TINYINT NOT NULL,
    ADS_Id_recurso SMALLINT NOT NULL,
    ADS_FechaAdsc SMALLDATETIME NOT NULL,
    PRIMARY KEY (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc),
    FOREIGN KEY (ADS_Id_seccion) REFERENCES Secciones (SCC_Id_seccion),
    FOREIGN KEY (ADS_Id_recurso) REFERENCES Recursos (RCR_Id_recurso)
);

CREATE TABLE Empresas (
    MPR_Id_empresa SMALLINT NOT NULL PRIMARY KEY,
    MPR_Denominacion VARCHAR(80)
);

CREATE TABLE RecursosX (
    RCX_Id_recursoX SMALLINT NOT NULL PRIMARY KEY,
    RCX_Nombre VARCHAR(60) NOT NULL,
    RCX_DNI CHAR(9),
	RCX_Id_empresa SMALLINT FOREIGN KEY REFERENCES Empresas (MPR_Id_empresa)
);

CREATE TABLE Estados (
    STD_Id_estado SMALLINT NOT NULL PRIMARY KEY,
	STD_Denominacion VARCHAR(25)
);

CREATE TABLE Subproyectos (
   SBP_Id_subproyecto SMALLINT NOT NULL PRIMARY KEY,
   SBP_DenominacionC VARCHAR(50) NOT NULL,
   SBP_DenominacionL VARCHAR(250),
   SBP_FechaInicio SMALLDATETIME,
   SBP_Id_recursoE SMALLINT,
   SBP_Id_recursoD SMALLINT,
   SBP_Id_proyecto SMALLINT NOT NULL FOREIGN KEY REFERENCES Proyectos (PRY_Id_proyecto)
);

CREATE TABLE Asignaciones (
	SGN_Id_subproyecto SMALLINT NOT NULL,
	SGN_Id_recursoX SMALLINT NOT NULL,
    PRIMARY KEY (SGN_Id_subproyecto, SGN_Id_recursoX),
    FOREIGN KEY (SGN_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
	FOREIGN KEY (SGN_Id_recursoX) REFERENCES RecursosX (RCX_Id_recursoX),
);

CREATE TABLE Desarrollo (
   DSR_Id_subproyecto SMALLINT NOT NULL,
   DSR_Id_seccion TINYINT NOT NULL,
   DSR_Id_recurso SMALLINT NOT NULL,
   DSR_FechaAdsc SMALLDATETIME NOT NULL,
   PRIMARY KEY (DSR_Id_subproyecto, DSR_Id_seccion, DSR_Id_recurso, DSR_FechaAdsc),
   FOREIGN KEY (DSR_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (DSR_Id_seccion, DSR_Id_recurso, DSR_FechaAdsc) REFERENCES Adscripciones (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc)
);

CREATE TABLE Explotacion (
   XPL_Id_subproyecto SMALLINT NOT NULL,
   XPL_Id_seccion TINYINT NOT NULL,
   XPL_Id_recurso SMALLINT NOT NULL,
   XPL_FechaAdsc SMALLDATETIME NOT NULL,
   PRIMARY KEY (XPL_Id_subproyecto, XPL_Id_seccion, XPL_Id_recurso, XPL_FechaAdsc),
   FOREIGN KEY (XPL_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (XPL_Id_seccion, XPL_Id_recurso, XPL_FechaAdsc) REFERENCES Adscripciones (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc)
);

CREATE TABLE Situaciones (
   STC_Id_subproyecto SMALLINT NOT NULL,
   STC_Id_estado SMALLINT NOT NULL,
   STC_FechaRef SMALLDATETIME NOT NULL,
   PRIMARY KEY (STC_Id_subproyecto, STC_Id_estado),
   FOREIGN KEY (STC_Id_subproyecto) REFERENCES Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (STC_Id_estado) REFERENCES Estados (STD_Id_estado)
);

--Insertar Datos

--Direcciones
INSERT INTO Direcciones VALUES (1,'C/ Elvira Quintana 30')
INSERT INTO Direcciones VALUES (2,'C/ Ana Finch 5')
INSERT INTO Direcciones VALUES (3,'C/ Marquesa Pinares 3')
INSERT INTO Direcciones VALUES (4,'C/ General Prim 78')
INSERT INTO Direcciones VALUES (5,'C/ Rey Juan Carlos 33')

--Servicios
INSERT INTO Servicios VALUES(1, 'Servicio de informática', 1)
INSERT INTO Servicios VALUES(2, 'Servicio de seguridad', 1)
INSERT INTO Servicios VALUES(3, 'Servicio de sanidad', 2)
INSERT INTO Servicios VALUES(4, 'Servicio de cableado', 3)
INSERT INTO Servicios VALUES(5, 'Servicio de compatiblidad', 4)

--Proyectos
INSERT INTO Proyectos VALUES (1, 'Proyecto Informático Rural', 'Proyecto para llevar la informática a zonas rurales españolas','2003-06-04',1)
INSERT INTO Proyectos VALUES (2, 'Proyecto de dispositivos inteligentes', 'Proyecto para automatizar todos los organismos públicos','2005-11-04',1)
INSERT INTO Proyectos VALUES (3, 'Proyecto de Educación', 'Proyecto para enseñar las bases de la informática a todos los españoles','2007-06-08',1)
INSERT INTO Proyectos VALUES (4, 'Proyecto de Sanidad', 'Proyecto para actualizar las bases de datos sanitarias en los hospitales','2003-01-09',1)
INSERT INTO Proyectos VALUES (5, 'Proyecto Campus Virtual', 'Proyecto para crear una web que consiga ayudar a los estudiantes y docentes','2009-01-01',1)

--Secciones
INSERT INTO Secciones VALUES(1, 'Sección de desarollo de base de datos')
INSERT INTO Secciones VALUES(2, 'Sección de educación')
INSERT INTO Secciones VALUES(3, 'Sección de informática')
INSERT INTO Secciones VALUES(4, 'Sección de electrónica')
INSERT INTO Secciones VALUES(5, 'Sección de programación')
INSERT INTO Secciones VALUES(6, 'Sección de redes')

--Recursos

INSERT INTO Recursos VALUES(1, 'Juan Carlos Peguero', 4, 23)
INSERT INTO Recursos VALUES(2, 'Juan Arias', 6, 73)
INSERT INTO Recursos VALUES(3, 'Juan Ángel Contreras', 1, 36)
INSERT INTO Recursos VALUES(4, 'Fran Montero Sánchez', 3, 42)
INSERT INTO Recursos VALUES(5, 'Pablo Setrakian', 5, 28)
INSERT INTO Recursos VALUES(6, 'Ana Montero', 2, 14)
INSERT INTO Recursos VALUES(7, 'Victor Gallardo', 5, 4)


--Subproyectos
INSERT INTO Subproyectos VALUES(1, 'Instalación', 'Instalación de ordenadores en zonas rurales', '2003-09-04', 1, null, 1)
INSERT INTO Subproyectos VALUES(2, 'Reparación', 'Reparación de ordenadores en zonas rurales', '2003-09-04', 1, null, 1)
INSERT INTO Subproyectos VALUES(3, 'Distribución', 'Distribución de ordenadores en zonas comunes', '2003-08-04', null, 2, 1)
INSERT INTO Subproyectos VALUES(4, 'Instalación', 'Instalación de dispositivos inteligentes', '2005-14-04', 3, null, 2)
INSERT INTO Subproyectos VALUES(5, 'Distribución', 'Distribucion de dispositivos inteligentes', '2005-12-04', null, 4, 2)
INSERT INTO Subproyectos VALUES(6, 'Enseñanza', 'Varios técnicos enseñaran a los médicos sobre la informática', '2007-06-08', null, 2, 3)
INSERT INTO Subproyectos VALUES(7, 'Revisión', 'Se llevará a cabo la revisión de las bases de datos actuales', '2003-01-09', 1, null, 4)
INSERT INTO Subproyectos VALUES(8, 'Actualización', 'Se actualizarán las bases de datos tras la revisión', '2003-08-09', null, 5, 5)
INSERT INTO Subproyectos VALUES(9, 'Desarrollo web', 'Se desarrollará la web del campus virtual', '2009-01-01', 1, null, 5)

--Adscripciones
INSERT INTO Adscripciones VALUES(1, 4, '2003-01-04')
INSERT INTO Adscripciones VALUES(2, 6, '2003-01-04')
INSERT INTO Adscripciones VALUES(3, 1, '2003-01-04')
INSERT INTO Adscripciones VALUES(4, 3, '2003-01-04')
INSERT INTO Adscripciones VALUES(5, 5, '2003-01-04')
INSERT INTO Adscripciones VALUES(6, 2, '2003-01-04')
INSERT INTO Adscripciones VALUES(5, 3,'2003-01-04')



--Desarrollo
INSERT INTO Desarrollo VALUES(1, 1, 4, '2003-01-04')
INSERT INTO Desarrollo VALUES(2, 2, 6, '2003-01-04')
INSERT INTO Desarrollo VALUES(3, 3, 1, '2003-01-04')
INSERT INTO Desarrollo VALUES(4, 4, 3, '2003-01-04')
INSERT INTO Desarrollo VALUES(5, 5, 5, '2003-01-04')
INSERT INTO Desarrollo VALUES(6, 5, 5, '2003-01-04')
INSERT INTO Desarrollo VALUES(7, 6, 2, '2003-01-04')
INSERT INTO Desarrollo VALUES(1, 5, 3, '2003-01-04')

--Empresas
INSERT INTO Empresas VALUES(1, 'Programadores S.L.')

--RecursoX
INSERT INTO RecursosX VALUES(1, 'Marta Paredes', '12345678Q', 1)
INSERT INTO RecursosX VALUES(2, 'Christhild Jessenia', '42343678A', 1)
INSERT INTO RecursosX VALUES(3, 'Miguel Cendrero', '52341678B', 1)
INSERT INTO RecursosX VALUES(4, 'David Gil', '62345678C', 1)

--Asignaciones
INSERT INTO Asignaciones VALUES(1,1)
INSERT INTO Asignaciones VALUES(1,3)
INSERT INTO Asignaciones VALUES(2,4)
INSERT INTO Asignaciones VALUES(3,2)