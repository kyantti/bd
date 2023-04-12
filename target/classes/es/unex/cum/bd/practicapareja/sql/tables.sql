CREATE DATABASE practica;

USE practica;

CREATE TABLE PRY_Direcciones (
   DGN_Id_dirgen SMALLINT IDENTITY(1,1) PRIMARY KEY,
   DGN_Denominacion VARCHAR(150)
);

CREATE TABLE PRY_Servicios (
   SRV_Id_servicio SMALLINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
   SRV_Denominacion varchar(150),
   SRV_Id_dirgen SMALLINT FOREIGN KEY REFERENCES PRY_Direcciones (DGN_Id_dirgen)
);

CREATE TABLE PRY_Proyectos (
   PRY_Id_proyecto SMALLINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
   PRY_DenominacionC varchar(50) NOT NULL,
   PRY_DenominacionL varchar(250),
   PRY_FechaInicio SMALLDATETIME,
   PRY_Id_servicio SMALLINT FOREIGN KEY REFERENCES PRY_Servicios (SRV_Id_servicio)
);

CREATE TABLE PRY_Empresas (
    MPR_Id_empresa SMALLINT NOT NULL  IDENTITY(1,1) PRIMARY KEY,
    MPR_Denominacion VARCHAR(80)
);

CREATE TABLE PRY_RecursosX (
    RCX_Id_recursoX SMALLINT NOT NULL  IDENTITY(1,1) PRIMARY KEY,
    RCX_Nombre VARCHAR(60) NOT NULL,
    RCX_DNI CHAR(9),
	RCX_Id_empresa SMALLINT FOREIGN KEY REFERENCES PRY_Empresas (MPR_Id_empresa)
);

CREATE TABLE PRY_Estados (
    STD_Id_estado SMALLINT NOT NULL  IDENTITY(1,1) PRIMARY KEY,
	STD_Denominacion VARCHAR(25)
);

CREATE TABLE PRY_Subproyectos (
   SBP_Id_subproyecto SMALLINT NOT NULL  IDENTITY(1,1) PRIMARY KEY,
   SBP_DenominacionC VARCHAR(50) NOT NULL,
   SBP_DenominacionL VARCHAR(250),
   SBP_FechaInicio SMALLDATETIME,
   SBP_Id_proyecto SMALLINT NOT NULL FOREIGN KEY REFERENCES PRY_Proyectos (PRY_Id_proyecto)
);

CREATE TABLE PRY_Asignaciones (
	SGN_Id_subproyecto SMALLINT NOT NULL,
	SGN_Id_recursoX SMALLINT NOT NULL,
   PRIMARY KEY (SGN_Id_subproyecto, SGN_Id_recursoX),
   FOREIGN KEY (SGN_Id_subproyecto) REFERENCES PRY_Subproyectos (SBP_Id_subproyecto),
	FOREIGN KEY (SGN_Id_recursoX) REFERENCES PRY_RecursosX (RCX_Id_recursoX),
);

CREATE TABLE PRY_Situaciones (
   STC_Id_subproyecto SMALLINT NOT NULL,
   STC_Id_estado SMALLINT NOT NULL,
   STC_FechaRef SMALLDATETIME NOT NULL,
   PRIMARY KEY (STC_Id_subproyecto, STC_Id_estado),
   FOREIGN KEY (STC_Id_subproyecto) REFERENCES PRY_Subproyectos (SBP_Id_subproyecto),
   FOREIGN KEY (STC_Id_estado) REFERENCES PRY_Estados (STD_Id_estado)
);