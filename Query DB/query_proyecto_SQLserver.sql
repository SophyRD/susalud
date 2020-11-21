

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE DATABASE PROYECTO_SUSALUD

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

USE PROYECTO_SUSALUD ;

-- -----------------------------------------------------
-- Table Autoevalucion
-- -----------------------------------------------------
CREATE TABLE  Autoevalucion (
  idAutoevalucion INT NOT NULL IDENTITY,
  A_avance VARCHAR(45) NOT NULL,
  Mes_idMes INT NOT NULL,
  UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  Estado_idEstado INT NOT NULL,
  Verificador_idVerificador INT NOT NULL,
  Verificador_Maestraverificador_idMaestraverificador INT NOT NULL,
  PRIMARY KEY (idAutoevalucion, Mes_idMes, UsuariosXevaluacion_idUsuariosXevaluacion, Estado_idEstado, Verificador_idVerificador, Verificador_Maestraverificador_idMaestraverificador))


-- -----------------------------------------------------
-- Table AutoevalucionXproceso
-- -----------------------------------------------------
CREATE TABLE  AutoevalucionXproceso (
  idAutoevalucionXproceso INT NOT NULL,
  AP_fecha_creacion DATE NOT NULL,
  AP_fecha_modificacion DATE NOT NULL,
  AP_comentario VARCHAR(45) NOT NULL,
  AP_puntuacion INT NOT NULL,
  Autoevalucion_idAutoevalucion INT NOT NULL,
  Autoevalucion_Mes_idMes INT NOT NULL,
  Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  Autoevalucion_Estado_idEstado INT NOT NULL,
  Verificadorl_idVerificador INT NOT NULL,
  PRIMARY KEY (idAutoevalucionXproceso, Autoevalucion_idAutoevalucion, Autoevalucion_Mes_idMes, Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion, Autoevalucion_Estado_idEstado, Verificadorl_idVerificador))

-- -----------------------------------------------------
-- Table Criterios_Evaluacion
-- -----------------------------------------------------

CREATE TABLE  Criterios_Evaluacion (
  idCriterios_Evaluacion INT NOT NULL IDENTITY,
  CE_nombre VARCHAR(45) NOT NULL,
  CE_fecha_creacion DATE NOT NULL,
  CE_fecha_modificacion DATE NOT NULL,
  AutoevalucionXproceso_idAutoevalucionXproceso INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_idAutoevalucion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Mes_idMes INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Estado_idEstado INT NOT NULL,
  AutoevalucionXproceso_Verificadorl_idVerificador INT NOT NULL,
  Criterios_evaluacionXItem_idCriterios_evaluacionXItem INT NOT NULL,
  PRIMARY KEY (idCriterios_Evaluacion, AutoevalucionXproceso_idAutoevalucionXproceso, AutoevalucionXproceso_Autoevalucion_idAutoevalucion, AutoevalucionXproceso_Autoevalucion_Mes_idMes, AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion, AutoevalucionXproceso_Autoevalucion_Estado_idEstado, AutoevalucionXproceso_Verificadorl_idVerificador, Criterios_evaluacionXItem_idCriterios_evaluacionXItem))




-- -----------------------------------------------------
-- Table Criterios_evaluacionXItem
-- -----------------------------------------------------

CREATE TABLE  Criterios_evaluacionXItem (
  idCriterios_evaluacionXItem INT NOT NULL IDENTITY,
  CEI_fecha_creacion DATE NOT NULL,
  CEI_fecha_modificacion DATE NOT NULL,
  CEI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idCriterios_evaluacionXItem))




-- -----------------------------------------------------
-- Table Estado
-- -----------------------------------------------------
CREATE TABLE  Estado (
  idEstado INT NOT NULL IDENTITY,
  E_estado VARCHAR(45) NOT NULL,
  Verificador_idVerificador INT NOT NULL,
  Verificador_Maestraverificador_idMaestraverificador INT NOT NULL,
  PRIMARY KEY (idEstado, Verificador_idVerificador, Verificador_Maestraverificador_idMaestraverificador))
-- -----------------------------------------------------
-- Table Fuente_ReferenciaXItem
-- -----------------------------------------------------

CREATE TABLE  Fuente_ReferenciaXItem (
  idFuente_ReferenciaXItem INT NOT NULL IDENTITY,
  FRI_fecha_creacion DATE NOT NULL,
  FRI_fecha_modificacion DATE NOT NULL,
  FRI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idFuente_ReferenciaXItem))

-- -----------------------------------------------------
-- Table Fuente_Referencial
-- -----------------------------------------------------

CREATE TABLE  Fuente_Referencial (
  idFuente_Referencial INT NOT NULL IDENTITY,
  FR_nombre VARCHAR(45) NOT NULL,
  FR_fecha_creacion DATE NOT NULL,
  FR_fecha_modificacion DATE NOT NULL,
  AutoevalucionXproceso_idAutoevalucionXproceso INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_idAutoevalucion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Mes_idMes INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Estado_idEstado INT NOT NULL,
  AutoevalucionXproceso_Verificadorl_idVerificador INT NOT NULL,
  Fuente_ReferenciaXItem_idFuente_ReferenciaXItem INT NOT NULL,
  PRIMARY KEY (idFuente_Referencial, AutoevalucionXproceso_idAutoevalucionXproceso, AutoevalucionXproceso_Autoevalucion_idAutoevalucion, AutoevalucionXproceso_Autoevalucion_Mes_idMes, AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion, AutoevalucionXproceso_Autoevalucion_Estado_idEstado, AutoevalucionXproceso_Verificadorl_idVerificador, Fuente_ReferenciaXItem_idFuente_ReferenciaXItem))

-- ----------------------------------------------------
-- Table Macroproceso
-- -----------------------------------------------------
CREATE TABLE  Macroproceso (
  idMacroproceso INT NOT NULL IDENTITY,
  MA_fecha_creacion DATE NOT NULL,
  MA_fecha_modificacion DATE NOT NULL,
  Verificadorl_idVerificador INT NOT NULL,
  PRIMARY KEY (idMacroproceso, Verificadorl_idVerificador))




-- -----------------------------------------------------
-- Table Maestraverificador
-- -----------------------------------------------------

CREATE TABLE  Maestraverificador (
  idMaestraverificador INT NOT NULL IDENTITY,
  MV_Fecha DATE NOT NULL,
  MV_fecha_modificacion DATE NOT NULL,
  PRIMARY KEY (idMaestraverificador))

-- -----------------------------------------------------
-- Table Mes
-- -----------------------------------------------------

CREATE TABLE  Mes (
  idMes INT NOT NULL IDENTITY,
  M_mes INT NOT NULL,
  M_año INT NOT NULL,
  PRIMARY KEY (idMes))

-- -----------------------------------------------------
-- Table Perfil
-- -----------------------------------------------------

CREATE TABLE  Perfil (
  idPerfil INT NOT NULL IDENTITY,
  PE_descripcion VARCHAR(45) NOT NULL,
  PRIMARY KEY (idPerfil))

-- -----------------------------------------------------
-- Table Periodo
-- -----------------------------------------------------
CREATE TABLE  Periodo (
  idPeriodo INT NOT NULL IDENTITY,
  P_periodo VARCHAR(45) NOT NULL,
  Mes_idMes INT NOT NULL,
  PRIMARY KEY (idPeriodo, Mes_idMes))

-- -----------------------------------------------------
-- Table Proceso
-- -----------------------------------------------------

CREATE TABLE  Proceso (
  idProceso INT NOT NULL IDENTITY,
  PR_descripcion VARCHAR(45) NOT NULL,
  Macroproceso_idMacroproceso INT NOT NULL,
  Macroproceso_Verificadorl_idVerificador INT NOT NULL,
  PRIMARY KEY (idProceso, Macroproceso_idMacroproceso, Macroproceso_Verificadorl_idVerificador))

-- -----------------------------------------------------
-- Table Subproceso
-- -----------------------------------------------------

CREATE TABLE  Subproceso (
  idSubproceso INT NOT NULL IDENTITY,
  SP_descripcion VARCHAR(45) NOT NULL,
  Proceso_idProceso INT NOT NULL,
  Proceso_Macroproceso_idMacroproceso INT NOT NULL,
  Proceso_Macroproceso_Verificadorl_idVerificador INT NOT NULL,
  PRIMARY KEY (idSubproceso, Proceso_idProceso, Proceso_Macroproceso_idMacroproceso, Proceso_Macroproceso_Verificadorl_idVerificador))

-- -----------------------------------------------------
-- Table TecnicasEvaluativas
-- -----------------------------------------------------

CREATE TABLE  TecnicasEvaluativas (
  idTecnicasEvaluativas INT NOT NULL IDENTITY,
  TE_nombre VARCHAR(45) NOT NULL,
  TE_fecha_creacion DATE NOT NULL,
  TE_fecha_modificacion DATE NOT NULL,
  AutoevalucionXproceso_idAutoevalucionXproceso INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_idAutoevalucion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Mes_idMes INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  AutoevalucionXproceso_Autoevalucion_Estado_idEstado INT NOT NULL,
  AutoevalucionXproceso_Verificadorl_idVerificador INT NOT NULL,
  TecnicasEvaluativasXItem_idTecnicasEvaluativasXItem INT NOT NULL,
  PRIMARY KEY (idTecnicasEvaluativas, AutoevalucionXproceso_idAutoevalucionXproceso, AutoevalucionXproceso_Autoevalucion_idAutoevalucion, AutoevalucionXproceso_Autoevalucion_Mes_idMes, AutoevalucionXproceso_Autoevalucion_UsuariosXevaluacion_idUsuariosXevaluacion, AutoevalucionXproceso_Autoevalucion_Estado_idEstado, AutoevalucionXproceso_Verificadorl_idVerificador, TecnicasEvaluativasXItem_idTecnicasEvaluativasXItem))




-- -----------------------------------------------------
-- Table TecnicasEvaluativasXItem
-- -----------------------------------------------------
CREATE TABLE  TecnicasEvaluativasXItem (
  idTecnicasEvaluativasXItem INT NOT NULL IDENTITY,
  TI_fecha_creacion DATE NOT NULL,
  TI_fecha_modificacion DATE NOT NULL,
  TI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idTecnicasEvaluativasXItem))




-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------

CREATE TABLE  Usuario (
  idUsuario INT NOT NULL IDENTITY,
  US_usuario VARCHAR(45) NOT NULL,
  US_contraseña VARCHAR(45) NOT NULL,
  Usuarios_idUsuarios INT NOT NULL,
  Usuarios_Perfil_idPerfil INT NOT NULL,
  PRIMARY KEY (idUsuario, Usuarios_idUsuarios, Usuarios_Perfil_idPerfil))




-- -----------------------------------------------------
-- Table UsuarioXEvaluacioXEntidad
-- -----------------------------------------------------

CREATE TABLE  UsuarioXEvaluacioXEntidad (
  idUsuarioXEvaluacioXEntidad INT NOT NULL IDENTITY,
  UEE_fecha_creacion DATE NOT NULL,
  UEE_fecha_modificacion DATE NOT NULL,
  UsuariosXevaluacion_idUsuariosXevaluacion INT NOT NULL,
  PRIMARY KEY (idUsuarioXEvaluacioXEntidad, UsuariosXevaluacion_idUsuariosXevaluacion))




-- -----------------------------------------------------
-- Table Usuarios
-- -----------------------------------------------------
CREATE TABLE  Usuarios (
  idUsuarios INT NOT NULL IDENTITY,
  US_identificacion INT NOT NULL,
  US_email INT NOT NULL,
  US_nombre VARCHAR(45) NOT NULL,
  US_apellido VARCHAR(45) NOT NULL,
  US_email1 VARCHAR(45) NOT NULL,
  Perfil_idPerfil INT NOT NULL,
  PRIMARY KEY (idUsuarios, Perfil_idPerfil))

-- -----------------------------------------------------
-- Table UsuariosXevaluacion
-- -----------------------------------------------------
CREATE TABLE  UsuariosXevaluacion (
  idUsuariosXevaluacion INT NOT NULL IDENTITY,
  U_fecha_modificacion DATE NULL,
  U_fecha_creacion DATE NULL,
  PRIMARY KEY (idUsuariosXevaluacion))

-- -----------------------------------------------------
-- Table Verificador
-- -----------------------------------------------------

CREATE TABLE  Verificador (
  idVerificador INT NOT NULL IDENTITY,
  V_descripcion VARCHAR(45) NOT NULL,
  V_estado VARCHAR(45) NOT NULL,
  V_tipo_sistema VARCHAR(45) NOT NULL,
  Maestraverificador_idMaestraverificador INT NOT NULL,
  PRIMARY KEY (idVerificador, Maestraverificador_idMaestraverificador))

-- -----------------------------------------------------
-- Table Verificadorl
-- -----------------------------------------------------

CREATE TABLE  Verificadorl (
  idVerificador INT NOT NULL IDENTITY,
  Vl_fecha_creacion DATE NOT NULL,
  Vl_fecha_modificacion DATE NOT NULL,
  PRIMARY KEY (idVerificador))