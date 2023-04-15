USE [master]
GO
/****** Object:  Database [Proyectos]    Script Date: 31/03/2023 19:35:50 ******/
CREATE DATABASE [Proyectos]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Proyectos', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Proyectos.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Proyectos_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Proyectos_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Proyectos] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Proyectos].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Proyectos] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Proyectos] SET ARITHABORT OFF 
GO
ALTER DATABASE [Proyectos] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Proyectos] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Proyectos] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Proyectos] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Proyectos] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Proyectos] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Proyectos] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Proyectos] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Proyectos] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Proyectos] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Proyectos] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Proyectos] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Proyectos] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Proyectos] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Proyectos] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Proyectos] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Proyectos] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Proyectos] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Proyectos] SET  MULTI_USER 
GO
ALTER DATABASE [Proyectos] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Proyectos] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Proyectos] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Proyectos] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Proyectos] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Proyectos] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Proyectos] SET QUERY_STORE = ON
GO
ALTER DATABASE [Proyectos] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Proyectos]
GO
/****** Object:  Table [dbo].[Adscripciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Adscripciones](
	[ADS_Id_seccion] [tinyint] NOT NULL,
	[ADS_Id_recurso] [smallint] NOT NULL,
	[ADS_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ADS_Id_seccion] ASC,
	[ADS_Id_recurso] ASC,
	[ADS_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Asignaciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Asignaciones](
	[SGN_Id_subproyecto] [smallint] NOT NULL,
	[SGN_Id_recursoX] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SGN_Id_subproyecto] ASC,
	[SGN_Id_recursoX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Desarrollo]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Desarrollo](
	[DSR_Id_subproyecto] [smallint] NOT NULL,
	[DSR_Id_seccion] [tinyint] NOT NULL,
	[DSR_Id_recurso] [smallint] NOT NULL,
	[DSR_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DSR_Id_subproyecto] ASC,
	[DSR_Id_seccion] ASC,
	[DSR_Id_recurso] ASC,
	[DSR_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Direcciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Direcciones](
	[DGN_Id_dirgen] [smallint] NOT NULL,
	[DGN_Denominacion] [varchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[DGN_Id_dirgen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empresas]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empresas](
	[MPR_Id_empresa] [smallint] NOT NULL,
	[MPR_Denominacion] [varchar](80) NULL,
PRIMARY KEY CLUSTERED 
(
	[MPR_Id_empresa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estados]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estados](
	[STD_Id_estado] [smallint] NOT NULL,
	[STD_Denominacion] [varchar](25) NULL,
PRIMARY KEY CLUSTERED 
(
	[STD_Id_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Explotacion]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Explotacion](
	[XPL_Id_subproyecto] [smallint] NOT NULL,
	[XPL_Id_seccion] [tinyint] NOT NULL,
	[XPL_Id_recurso] [smallint] NOT NULL,
	[XPL_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[XPL_Id_subproyecto] ASC,
	[XPL_Id_seccion] ASC,
	[XPL_Id_recurso] ASC,
	[XPL_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proyectos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proyectos](
	[Id_proyecto] [smallint] NOT NULL,
	[DenominacionC] [varchar](50) NOT NULL,
	[DenominacionL] [varchar](250) NULL,
	[FechaInicio] [smalldatetime] NULL,
	[Id_servicio] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_proyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recursos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recursos](
	[RCR_Id_recurso] [smallint] NOT NULL,
	[RCR_nombre] [varchar](60) NOT NULL,
	[RCR_Id_seccion] [tinyint] NOT NULL,
	[RCR_NRPT] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[RCR_Id_recurso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RecursosX]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RecursosX](
	[RCX_Id_recursoX] [smallint] NOT NULL,
	[RCX_Nombre] [varchar](60) NOT NULL,
	[RCX_DNI] [char](9) NULL,
	[RCX_Id_empresa] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[RCX_Id_recursoX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Secciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Secciones](
	[SCC_Id_seccion] [tinyint] NOT NULL,
	[SCC_Denominacion] [varchar](80) NULL,
PRIMARY KEY CLUSTERED 
(
	[SCC_Id_seccion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Servicios]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Servicios](
	[SRV_Id_servicio] [smallint] NOT NULL,
	[SRV_Denominacion] [varchar](150) NULL,
	[SRV_Id_dirgen] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[SRV_Id_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Situaciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Situaciones](
	[STC_Id_subproyecto] [smallint] NOT NULL,
	[STC_Id_estado] [smallint] NOT NULL,
	[STC_FechaRef] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[STC_Id_subproyecto] ASC,
	[STC_Id_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subproyectos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subproyectos](
	[SBP_Id_subproyecto] [smallint] NOT NULL,
	[SBP_DenominacionC] [varchar](50) NOT NULL,
	[SBP_DenominacionL] [varchar](250) NULL,
	[SBP_FechaInicio] [smalldatetime] NULL,
	[SBP_Id_proyecto] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SBP_Id_subproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc]) VALUES (1, 1, CAST(N'2003-09-04T00:00:00' AS SmallDateTime))
GO
INSERT [dbo].[Desarrollo] ([DSR_Id_subproyecto], [DSR_Id_seccion], [DSR_Id_recurso], [DSR_FechaAdsc]) VALUES (1, 1, 1, CAST(N'2003-09-04T00:00:00' AS SmallDateTime))
GO
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (1, N'C/ Elvira Quintana 30')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (2, N'C/ Ana Finch 5')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (3, N'C/ Marquesa Pinares 3')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (4, N'C/ General Prim 78')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (5, N'C/ Rey Juan Carlos 33')
GO
INSERT [dbo].[Proyectos] ([Id_proyecto], [DenominacionC], [DenominacionL], [FechaInicio], [Id_servicio]) VALUES (1, N'Proyecto Informático Rural', N'Proyecto para llevar la informática a zonas rurales españolas', CAST(N'2003-06-04T00:00:00' AS SmallDateTime), 1)
GO
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (1, N'Juan Carlos Peguero', 1, 23)
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (2, N'Juan Arias', 1, 73)
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (3, N'Juan Ángel Contreras', 1, 36)
GO
INSERT [dbo].[Secciones] ([SCC_Id_seccion], [SCC_Denominacion]) VALUES (1, N'Sección de desarollo de base de datos')
GO
INSERT [dbo].[Servicios] ([SRV_Id_servicio], [SRV_Denominacion], [SRV_Id_dirgen]) VALUES (1, N'Montaje de computadoras', 1)
GO
INSERT [dbo].[Subproyectos] ([SBP_Id_subproyecto], [SBP_DenominacionC], [SBP_DenominacionL], [SBP_FechaInicio], [SBP_Id_proyecto]) VALUES (1, N'Instalación', N'Instalación de ordenadores en zonas comunes', CAST(N'2003-09-04T00:00:00' AS SmallDateTime), 1)
GO
ALTER TABLE [dbo].[Adscripciones]  WITH CHECK ADD FOREIGN KEY([ADS_Id_seccion])
REFERENCES [dbo].[Secciones] ([SCC_Id_seccion])
GO
ALTER TABLE [dbo].[Adscripciones]  WITH CHECK ADD FOREIGN KEY([ADS_Id_recurso])
REFERENCES [dbo].[Recursos] ([RCR_Id_recurso])
GO
ALTER TABLE [dbo].[Asignaciones]  WITH CHECK ADD FOREIGN KEY([SGN_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Asignaciones]  WITH CHECK ADD FOREIGN KEY([SGN_Id_recursoX])
REFERENCES [dbo].[RecursosX] ([RCX_Id_recursoX])
GO
ALTER TABLE [dbo].[Desarrollo]  WITH CHECK ADD FOREIGN KEY([DSR_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Desarrollo]  WITH CHECK ADD FOREIGN KEY([DSR_Id_seccion], [DSR_Id_recurso], [DSR_FechaAdsc])
REFERENCES [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc])
GO
ALTER TABLE [dbo].[Explotacion]  WITH CHECK ADD FOREIGN KEY([XPL_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Explotacion]  WITH CHECK ADD FOREIGN KEY([XPL_Id_seccion], [XPL_Id_recurso], [XPL_FechaAdsc])
REFERENCES [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc])
GO
ALTER TABLE [dbo].[Proyectos]  WITH CHECK ADD FOREIGN KEY([Id_servicio])
REFERENCES [dbo].[Servicios] ([SRV_Id_servicio])
GO
ALTER TABLE [dbo].[Recursos]  WITH CHECK ADD FOREIGN KEY([RCR_Id_seccion])
REFERENCES [dbo].[Secciones] ([SCC_Id_secciUSE [master]
GO
/****** Object:  Database [Proyectos]    Script Date: 31/03/2023 19:35:50 ******/
CREATE DATABASE [Proyectos]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Proyectos', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Proyectos.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Proyectos_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Proyectos_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Proyectos] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Proyectos].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Proyectos] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Proyectos] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Proyectos] SET ARITHABORT OFF 
GO
ALTER DATABASE [Proyectos] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Proyectos] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Proyectos] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Proyectos] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Proyectos] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Proyectos] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Proyectos] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Proyectos] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Proyectos] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Proyectos] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Proyectos] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Proyectos] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Proyectos] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Proyectos] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Proyectos] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Proyectos] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Proyectos] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Proyectos] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Proyectos] SET  MULTI_USER 
GO
ALTER DATABASE [Proyectos] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Proyectos] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Proyectos] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Proyectos] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Proyectos] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Proyectos] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Proyectos] SET QUERY_STORE = ON
GO
ALTER DATABASE [Proyectos] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Proyectos]
GO
/****** Object:  Table [dbo].[Adscripciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Adscripciones](
	[ADS_Id_seccion] [tinyint] NOT NULL,
	[ADS_Id_recurso] [smallint] NOT NULL,
	[ADS_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ADS_Id_seccion] ASC,
	[ADS_Id_recurso] ASC,
	[ADS_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Asignaciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Asignaciones](
	[SGN_Id_subproyecto] [smallint] NOT NULL,
	[SGN_Id_recursoX] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SGN_Id_subproyecto] ASC,
	[SGN_Id_recursoX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Desarrollo]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Desarrollo](
	[DSR_Id_subproyecto] [smallint] NOT NULL,
	[DSR_Id_seccion] [tinyint] NOT NULL,
	[DSR_Id_recurso] [smallint] NOT NULL,
	[DSR_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DSR_Id_subproyecto] ASC,
	[DSR_Id_seccion] ASC,
	[DSR_Id_recurso] ASC,
	[DSR_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Direcciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Direcciones](
	[DGN_Id_dirgen] [smallint] NOT NULL,
	[DGN_Denominacion] [varchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[DGN_Id_dirgen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empresas]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empresas](
	[MPR_Id_empresa] [smallint] NOT NULL,
	[MPR_Denominacion] [varchar](80) NULL,
PRIMARY KEY CLUSTERED 
(
	[MPR_Id_empresa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estados]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estados](
	[STD_Id_estado] [smallint] NOT NULL,
	[STD_Denominacion] [varchar](25) NULL,
PRIMARY KEY CLUSTERED 
(
	[STD_Id_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Explotacion]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Explotacion](
	[XPL_Id_subproyecto] [smallint] NOT NULL,
	[XPL_Id_seccion] [tinyint] NOT NULL,
	[XPL_Id_recurso] [smallint] NOT NULL,
	[XPL_FechaAdsc] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[XPL_Id_subproyecto] ASC,
	[XPL_Id_seccion] ASC,
	[XPL_Id_recurso] ASC,
	[XPL_FechaAdsc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proyectos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proyectos](
	[Id_proyecto] [smallint] NOT NULL,
	[DenominacionC] [varchar](50) NOT NULL,
	[DenominacionL] [varchar](250) NULL,
	[FechaInicio] [smalldatetime] NULL,
	[Id_servicio] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_proyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recursos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recursos](
	[RCR_Id_recurso] [smallint] NOT NULL,
	[RCR_nombre] [varchar](60) NOT NULL,
	[RCR_Id_seccion] [tinyint] NOT NULL,
	[RCR_NRPT] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[RCR_Id_recurso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RecursosX]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RecursosX](
	[RCX_Id_recursoX] [smallint] NOT NULL,
	[RCX_Nombre] [varchar](60) NOT NULL,
	[RCX_DNI] [char](9) NULL,
	[RCX_Id_empresa] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[RCX_Id_recursoX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Secciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Secciones](
	[SCC_Id_seccion] [tinyint] NOT NULL,
	[SCC_Denominacion] [varchar](80) NULL,
PRIMARY KEY CLUSTERED 
(
	[SCC_Id_seccion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Servicios]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Servicios](
	[SRV_Id_servicio] [smallint] NOT NULL,
	[SRV_Denominacion] [varchar](150) NULL,
	[SRV_Id_dirgen] [smallint] NULL,
PRIMARY KEY CLUSTERED 
(
	[SRV_Id_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Situaciones]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Situaciones](
	[STC_Id_subproyecto] [smallint] NOT NULL,
	[STC_Id_estado] [smallint] NOT NULL,
	[STC_FechaRef] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[STC_Id_subproyecto] ASC,
	[STC_Id_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subproyectos]    Script Date: 31/03/2023 19:35:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subproyectos](
	[SBP_Id_subproyecto] [smallint] NOT NULL,
	[SBP_DenominacionC] [varchar](50) NOT NULL,
	[SBP_DenominacionL] [varchar](250) NULL,
	[SBP_FechaInicio] [smalldatetime] NULL,
	[SBP_Id_proyecto] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SBP_Id_subproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc]) VALUES (1, 1, CAST(N'2003-09-04T00:00:00' AS SmallDateTime))
GO
INSERT [dbo].[Desarrollo] ([DSR_Id_subproyecto], [DSR_Id_seccion], [DSR_Id_recurso], [DSR_FechaAdsc]) VALUES (1, 1, 1, CAST(N'2003-09-04T00:00:00' AS SmallDateTime))
GO
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (1, N'C/ Elvira Quintana 30')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (2, N'C/ Ana Finch 5')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (3, N'C/ Marquesa Pinares 3')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (4, N'C/ General Prim 78')
INSERT [dbo].[Direcciones] ([DGN_Id_dirgen], [DGN_Denominacion]) VALUES (5, N'C/ Rey Juan Carlos 33')
GO
INSERT [dbo].[Proyectos] ([Id_proyecto], [DenominacionC], [DenominacionL], [FechaInicio], [Id_servicio]) VALUES (1, N'Proyecto Informático Rural', N'Proyecto para llevar la informática a zonas rurales españolas', CAST(N'2003-06-04T00:00:00' AS SmallDateTime), 1)
GO
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (1, N'Juan Carlos Peguero', 1, 23)
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (2, N'Juan Arias', 1, 73)
INSERT [dbo].[Recursos] ([RCR_Id_recurso], [RCR_nombre], [RCR_Id_seccion], [RCR_NRPT]) VALUES (3, N'Juan Ángel Contreras', 1, 36)
GO
INSERT [dbo].[Secciones] ([SCC_Id_seccion], [SCC_Denominacion]) VALUES (1, N'Sección de desarollo de base de datos')
GO
INSERT [dbo].[Servicios] ([SRV_Id_servicio], [SRV_Denominacion], [SRV_Id_dirgen]) VALUES (1, N'Montaje de computadoras', 1)
GO
INSERT [dbo].[Subproyectos] ([SBP_Id_subproyecto], [SBP_DenominacionC], [SBP_DenominacionL], [SBP_FechaInicio], [SBP_Id_proyecto]) VALUES (1, N'Instalación', N'Instalación de ordenadores en zonas comunes', CAST(N'2003-09-04T00:00:00' AS SmallDateTime), 1)
GO
ALTER TABLE [dbo].[Adscripciones]  WITH CHECK ADD FOREIGN KEY([ADS_Id_seccion])
REFERENCES [dbo].[Secciones] ([SCC_Id_seccion])
GO
ALTER TABLE [dbo].[Adscripciones]  WITH CHECK ADD FOREIGN KEY([ADS_Id_recurso])
REFERENCES [dbo].[Recursos] ([RCR_Id_recurso])
GO
ALTER TABLE [dbo].[Asignaciones]  WITH CHECK ADD FOREIGN KEY([SGN_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Asignaciones]  WITH CHECK ADD FOREIGN KEY([SGN_Id_recursoX])
REFERENCES [dbo].[RecursosX] ([RCX_Id_recursoX])
GO
ALTER TABLE [dbo].[Desarrollo]  WITH CHECK ADD FOREIGN KEY([DSR_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Desarrollo]  WITH CHECK ADD FOREIGN KEY([DSR_Id_seccion], [DSR_Id_recurso], [DSR_FechaAdsc])
REFERENCES [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc])
GO
ALTER TABLE [dbo].[Explotacion]  WITH CHECK ADD FOREIGN KEY([XPL_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Explotacion]  WITH CHECK ADD FOREIGN KEY([XPL_Id_seccion], [XPL_Id_recurso], [XPL_FechaAdsc])
REFERENCES [dbo].[Adscripciones] ([ADS_Id_seccion], [ADS_Id_recurso], [ADS_FechaAdsc])
GO
ALTER TABLE [dbo].[Proyectos]  WITH CHECK ADD FOREIGN KEY([Id_servicio])
REFERENCES [dbo].[Servicios] ([SRV_Id_servicio])
GO
ALTER TABLE [dbo].[Recursos]  WITH CHECK ADD FOREIGN KEY([RCR_Id_seccion])
REFERENCES [dbo].[Secciones] ([SCC_Id_seccion])
GO
ALTER TABLE [dbo].[RecursosX]  WITH CHECK ADD FOREIGN KEY([RCX_Id_empresa])
REFERENCES [dbo].[Empresas] ([MPR_Id_empresa])
GO
ALTER TABLE [dbo].[Servicios]  WITH CHECK ADD FOREIGN KEY([SRV_Id_dirgen])
REFERENCES [dbo].[Direcciones] ([DGN_Id_dirgen])
GO
ALTER TABLE [dbo].[Situaciones]  WITH CHECK ADD FOREIGN KEY([STC_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Situaciones]  WITH CHECK ADD FOREIGN KEY([STC_Id_estado])
REFERENCES [dbo].[Estados] ([STD_Id_estado])
GO
ALTER TABLE [dbo].[Subproyectos]  WITH CHECK ADD FOREIGN KEY([SBP_Id_proyecto])
REFERENCES [dbo].[Proyectos] ([Id_proyecto])
GO
USE [master]
GO
ALTER DATABASE [Proyectos] SET  READ_WRITE 
GODD FOREIGN KEY([SRV_Id_dirgen])
REFERENCES [dbo].[Direcciones] ([DGN_Id_dirgen])
GO
ALTER TABLE [dbo].[Situaciones]  WITH CHECK ADD FOREIGN KEY([STC_Id_subproyecto])
REFERENCES [dbo].[Subproyectos] ([SBP_Id_subproyecto])
GO
ALTER TABLE [dbo].[Situaciones]  WITH CHECK ADD FOREIGN KEY([STC_Id_estado])
REFERENCES [dbo].[Estados] ([STD_Id_estado])
GO
ALTER TABLE [dbo].[Subproyectos]  WITH CHECK ADD FOREIGN KEY([SBP_Id_proyecto])
REFERENCES [dbo].[Proyectos] ([Id_proyecto])
GO
USE [master]
GO
ALTER DATABASE [Proyectos] SET  READ_WRITE 
GO