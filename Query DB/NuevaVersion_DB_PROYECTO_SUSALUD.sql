

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE DATABASE PROYECTO_SUSALUD
GO
USE PROYECTO_SUSALUD 
GO
-- -----------------------------------------------------
-- Table Verificador
-- -----------------------------------------------------
CREATE TABLE Verificador (
  idVerificador INT NOT NULL IDENTITY,
  Vl_fecha_creacion DATE NOT NULL,
  Vl_fecha_modificacion DATE NOT NULL,
  PRIMARY KEY (idVerificador))
;


-- -----------------------------------------------------
-- Table Maestraverificador
-- -----------------------------------------------------
CREATE TABLE Maestraverificador (
  idMaestraverificador INT NOT NULL IDENTITY,
  MV_Fecha DATE NOT NULL,
  MV_fecha_modificacion DATE NOT NULL,
  idVerificador INT NOT NULL,
  PRIMARY KEY (idMaestraverificador, idVerificador),
  INDEX fk_Maestraverificador_Verificadorl1_idx (idVerificador ASC) 
	)
;
ALTER TABLE Maestraverificador  WITH CHECK ADD
  CONSTRAINT fk_Maestraverificador_Verificadorl1
    FOREIGN KEY (idVerificador)
    REFERENCES Verificador (idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE ;

-- -----------------------------------------------------
-- Table Mes
-- -----------------------------------------------------
CREATE TABLE Mes (
  idMes INT NOT NULL IDENTITY,
  M_mes INT NOT NULL,
  M_año INT NOT NULL,
  PRIMARY KEY (idMes))
;

-- -----------------------------------------------------
-- Table Periodo
-- -----------------------------------------------------
CREATE TABLE Periodo (
  idPeriodo INT NOT NULL IDENTITY,
  P_periodo VARCHAR(45) NOT NULL,
  Mes_idMes INT NOT NULL,
  PRIMARY KEY (idPeriodo, Mes_idMes),
  INDEX fk_Periodo_Mes1_idx (Mes_idMes ASC));


ALTER TABLE Periodo  WITH CHECK ADD
CONSTRAINT fk_Periodo_Mes1
    FOREIGN KEY (Mes_idMes)
    REFERENCES Mes (idMes)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table UsuariosXevaluacion
-- -----------------------------------------------------
CREATE TABLE UsuariosXevaluacion (
  idUsuariosXevaluacion INT NOT NULL IDENTITY,
  U_fecha_modificacion DATE NULL,
  U_fecha_creacion DATE NULL,
  PRIMARY KEY (idUsuariosXevaluacion))
;


-- -----------------------------------------------------
-- Table Perfil
-- -----------------------------------------------------
CREATE TABLE Perfil (
  idPerfil INT NOT NULL IDENTITY,
  PE_descripcion VARCHAR(45) NOT NULL,
  PRIMARY KEY (idPerfil))
;


-- -----------------------------------------------------
-- Table Estado
-- -----------------------------------------------------
CREATE TABLE Estado (
  idEstado INT NOT NULL IDENTITY,
  E_estado VARCHAR(45) NOT NULL,
  idVerificador INT NOT NULL,
  PRIMARY KEY (idEstado, idVerificador),
  INDEX fk_Estado_Verificadorl1_idx (idVerificador ASC)
 )
;

ALTER TABLE Estado  WITH CHECK ADD
 CONSTRAINT fk_Estado_Verificadorl1
    FOREIGN KEY (idVerificador)
    REFERENCES Verificador (idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table Autoevalucion
-- -----------------------------------------------------
CREATE TABLE Autoevalucion (
  idAutoevalucion INT NOT NULL IDENTITY,
  A_avance VARCHAR(45) NOT NULL,
  idMes INT NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  idEstado INT NOT NULL,
  PRIMARY KEY (idAutoevalucion, idMes, idUsuariosXevaluacion, idEstado),
  INDEX fk_Autoevalucion_Mes_idx (idMes ASC),
  INDEX fk_Autoevalucion_UsuariosXevaluacion1_idx (idUsuariosXevaluacion ASC) ,
  INDEX fk_Autoevalucion_Estado1_idx (idEstado ASC)
  );

  ALTER TABLE Autoevalucion  WITH CHECK ADD
    CONSTRAINT fk_Autoevalucion_Mes
    FOREIGN KEY (idMes)
    REFERENCES Mes (idMes)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Autoevalucion_UsuariosXevaluacion1
    FOREIGN KEY (idUsuariosXevaluacion)
    REFERENCES UsuariosXevaluacion (idUsuariosXevaluacion)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table Usuarios
-- -----------------------------------------------------
CREATE TABLE Usuarios (
  idUsuarios INT NOT NULL IDENTITY,
  US_identificacion INT NOT NULL,
  US_email INT NOT NULL,
  US_nombre VARCHAR(45) NOT NULL,
  US_apellido VARCHAR(45) NOT NULL,
  US_email1 VARCHAR(45) NOT NULL,
  Perfil_idPerfil INT NOT NULL,
  PRIMARY KEY (idUsuarios, Perfil_idPerfil),
  INDEX fk_Usuarios_Perfil1_idx (Perfil_idPerfil ASC)
);
	ALTER TABLE Usuarios  WITH CHECK ADD
	CONSTRAINT fk_Usuarios_Perfil1
		FOREIGN KEY (Perfil_idPerfil)
		REFERENCES Perfil (idPerfil)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------
CREATE TABLE Usuario (
  idUsuario INT NOT NULL IDENTITY,
  US_usuario VARCHAR(45) NOT NULL,
  US_contraseña VARCHAR(45) NOT NULL,
  Usuarios_idUsuarios INT NOT NULL,
  Usuarios_Perfil_idPerfil INT NOT NULL,
  PRIMARY KEY (idUsuario, Usuarios_idUsuarios, Usuarios_Perfil_idPerfil),
  INDEX fk_Usuario_Usuarios1_idx (Usuarios_idUsuarios ASC, Usuarios_Perfil_idPerfil ASC)
);
	ALTER TABLE Usuario  WITH CHECK ADD
	CONSTRAINT fk_Usuario_Usuarios1
	    FOREIGN KEY (Usuarios_idUsuarios , Usuarios_Perfil_idPerfil)
	    REFERENCES Usuarios (idUsuarios , Perfil_idPerfil)
		ON DELETE CASCADE
		ON UPDATE CASCADE;
-- -----------------------------------------------------
-- Table AutoevalucionXproceso
-- -----------------------------------------------------
CREATE TABLE AutoevalucionXproceso (
  idAutoevalucionXproceso INT NOT NULL,
  AP_fecha_creacion DATE NOT NULL,
  AP_fecha_modificacion DATE NOT NULL,
  AP_comentario VARCHAR(45) NOT NULL,
  AP_puntuacion INT NOT NULL,
  idAutoevalucion INT NOT NULL,
  idMes INT NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  idEstado INT NOT NULL,
  idVerificador INT NOT NULL,
  PRIMARY KEY (idAutoevalucionXproceso, idAutoevalucion, idMes, idUsuariosXevaluacion, idEstado, idVerificador),
  INDEX fk_AutoevalucionXproceso_Autoevalucion1_idx (idAutoevalucion ASC, idMes ASC, idUsuariosXevaluacion ASC, idEstado ASC) ,
  INDEX fk_AutoevalucionXproceso_Verificadorl1_idx (idVerificador ASC) ,
);
	ALTER TABLE AutoevalucionXproceso  WITH CHECK ADD	
	CONSTRAINT fk_AutoevalucionXproceso_Autoevalucion1
    FOREIGN KEY (idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado)
    REFERENCES Autoevalucion (idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT fk_AutoevalucionXproceso_Verificadorl1
    FOREIGN KEY (idVerificador)
    REFERENCES Verificador (idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table TecnicasEvaluativasXItem
-- -----------------------------------------------------
CREATE TABLE TecnicasEvaluativasXItem (
  idTecnicasEvaluativasXItem INT NOT NULL IDENTITY,
  TI_fecha_creacion DATE NOT NULL,
  TI_fecha_modificacion DATE NOT NULL,
  TI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idTecnicasEvaluativasXItem))
;


-- -----------------------------------------------------
-- Table TecnicasEvaluativas
-- -----------------------------------------------------
CREATE TABLE TecnicasEvaluativas (
  idTecnicasEvaluativas INT NOT NULL IDENTITY,
  TE_nombre VARCHAR(45) NOT NULL,
  TE_fecha_creacion DATE NOT NULL,
  TE_fecha_modificacion DATE NOT NULL,
  idAutoevalucionXproceso INT NOT NULL,
  idAutoevalucion INT NOT NULL,
  idMes INT NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  idEstado INT NOT NULL,
  idVerificador INT NOT NULL,
  idTecnicasEvaluativasXItem INT NOT NULL,
  PRIMARY KEY (idTecnicasEvaluativas, idAutoevalucionXproceso, idAutoevalucion, idMes, idUsuariosXevaluacion, idEstado, idVerificador, idTecnicasEvaluativasXItem),
  INDEX fk_TecnicasEvaluativas_AutoevalucionXproceso1_idx (idAutoevalucionXproceso ASC, idAutoevalucion ASC, idMes ASC, idUsuariosXevaluacion ASC, idEstado ASC, idVerificador ASC) ,
  INDEX fk_TecnicasEvaluativas_TecnicasEvaluativasXItem1_idx (idTecnicasEvaluativasXItem ASC));

  ALTER TABLE TecnicasEvaluativas  WITH CHECK ADD	
  CONSTRAINT fk_TecnicasEvaluativas_AutoevalucionXproceso1
    FOREIGN KEY (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    REFERENCES AutoevalucionXproceso (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_TecnicasEvaluativas_TecnicasEvaluativasXItem1
    FOREIGN KEY (idTecnicasEvaluativasXItem)
    REFERENCES TecnicasEvaluativasXItem (idTecnicasEvaluativasXItem)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table Macroproceso
-- -----------------------------------------------------
CREATE TABLE Macroproceso (
  idMacroproceso INT NOT NULL IDENTITY,
  MA_fecha_creacion DATE NOT NULL,
  MA_fecha_modificacion DATE NOT NULL,
  Verificadorl_idVerificador INT NOT NULL,
  PRIMARY KEY (idMacroproceso, Verificadorl_idVerificador),
  INDEX fk_Macroproceso_Verificadorl1_idx (Verificadorl_idVerificador ASC),
);
ALTER TABLE Macroproceso  WITH CHECK ADD	
  CONSTRAINT fk_Macroproceso_Verificadorl1
    FOREIGN KEY (Verificadorl_idVerificador)
    REFERENCES Verificador (idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE
	;

-- -----------------------------------------------------
-- Table Proceso
-- -----------------------------------------------------
CREATE TABLE Proceso (
  idProceso INT NOT NULL IDENTITY,
  PR_descripcion VARCHAR(45) NOT NULL,
  idMacroproceso INT NOT NULL,
  idVerificador INT NOT NULL,
  PRIMARY KEY (idProceso, idMacroproceso, idVerificador),
  INDEX fk_Proceso_Macroproceso1_idx (idMacroproceso ASC, idVerificador ASC))
;

ALTER TABLE Proceso  WITH CHECK ADD	
CONSTRAINT fk_Proceso_Macroproceso1
    FOREIGN KEY (idMacroproceso , idVerificador)
    REFERENCES Macroproceso (idMacroproceso , Verificadorl_idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table Subproceso
-- -----------------------------------------------------
CREATE TABLE Subproceso (
  idSubproceso INT NOT NULL IDENTITY,
  SP_descripcion VARCHAR(45) NOT NULL,
  idProceso INT NOT NULL,
  idMacroproceso INT NOT NULL,
  idVerificador INT NOT NULL,
  PRIMARY KEY (idSubproceso, idProceso, idMacroproceso, idVerificador),
  INDEX fk_Subproceso_Proceso1_idx (idProceso ASC, idMacroproceso ASC, idVerificador ASC),
)
;

ALTER TABLE Subproceso  WITH CHECK ADD
  CONSTRAINT fk_Subproceso_Proceso1
    FOREIGN KEY (idProceso , idMacroproceso , idVerificador)
    REFERENCES Proceso (idProceso , idMacroproceso , idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table Fuente_ReferenciaXItem
-- -----------------------------------------------------
CREATE TABLE Fuente_ReferenciaXItem (
  idFuente_ReferenciaXItem INT NOT NULL IDENTITY,
  FRI_fecha_creacion DATE NOT NULL,
  FRI_fecha_modificacion DATE NOT NULL,
  FRI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idFuente_ReferenciaXItem))
;


-- -----------------------------------------------------
-- Table Fuente_Referencial
-- -----------------------------------------------------
CREATE TABLE Fuente_Referencial (
  idFuente_Referencial INT NOT NULL IDENTITY,
  FR_nombre VARCHAR(45) NOT NULL,
  FR_fecha_creacion DATE NOT NULL,
  FR_fecha_modificacion DATE NOT NULL,
  idAutoevalucionXproceso INT NOT NULL,
  idAutoevalucion INT NOT NULL,
  idMes INT NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  idEstado INT NOT NULL,
  idVerificador INT NOT NULL,
  idFuente_ReferenciaXItem INT NOT NULL,
  PRIMARY KEY (idFuente_Referencial, idAutoevalucionXproceso, idAutoevalucion, idMes, idUsuariosXevaluacion, idEstado, idVerificador, idFuente_ReferenciaXItem),
  INDEX fk_Fuente_Referencial_AutoevalucionXproceso1_idx (idAutoevalucionXproceso ASC, idAutoevalucion ASC, idMes ASC, idUsuariosXevaluacion ASC, idEstado ASC, idVerificador ASC),
  INDEX fk_Fuente_Referencial_Fuente_ReferenciaXItem1_idx (idFuente_ReferenciaXItem ASC))
;
ALTER TABLE Fuente_Referencial  WITH CHECK ADD
  CONSTRAINT fk_Fuente_Referencial_AutoevalucionXproceso1
    FOREIGN KEY (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    REFERENCES AutoevalucionXproceso (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Fuente_Referencial_Fuente_ReferenciaXItem1
    FOREIGN KEY (idFuente_ReferenciaXItem)
    REFERENCES Fuente_ReferenciaXItem (idFuente_ReferenciaXItem)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table Criterios_evaluacionXItem
-- -----------------------------------------------------
CREATE TABLE Criterios_evaluacionXItem (
  idCriterios_evaluacionXItem INT NOT NULL IDENTITY,
  CEI_fecha_creacion DATE NOT NULL,
  CEI_fecha_modificacion DATE NOT NULL,
  CEI_nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idCriterios_evaluacionXItem))
;


-- -----------------------------------------------------
-- Table Criterios_Evaluacion
-- -----------------------------------------------------
CREATE TABLE Criterios_Evaluacion (
  idCriterios_Evaluacion INT NOT NULL IDENTITY,
  CE_nombre VARCHAR(45) NOT NULL,
  CE_fecha_creacion DATE NOT NULL,
  CE_fecha_modificacion DATE NOT NULL,
  idAutoevalucionXproceso INT NOT NULL,
  idAutoevalucion INT NOT NULL,
  idMes INT NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  idEstado INT NOT NULL,
  idVerificador INT NOT NULL,
  idCriterios_evaluacionXItem INT NOT NULL,
  PRIMARY KEY (idCriterios_Evaluacion, idAutoevalucionXproceso, idAutoevalucion, idMes, idUsuariosXevaluacion, idEstado, idVerificador, idCriterios_evaluacionXItem),
  INDEX fk_Criterios_Evaluacion_AutoevalucionXproceso1_idx (idAutoevalucionXproceso ASC, idAutoevalucion ASC, idMes ASC, idUsuariosXevaluacion ASC, idEstado ASC, idVerificador ASC),
  INDEX fk_Criterios_Evaluacion_Criterios_evaluacionXItem1_idx (idCriterios_evaluacionXItem ASC))

  ALTER TABLE Criterios_Evaluacion  WITH CHECK ADD
    CONSTRAINT fk_Criterios_Evaluacion_AutoevalucionXproceso1
    FOREIGN KEY (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    REFERENCES AutoevalucionXproceso (idAutoevalucionXproceso , idAutoevalucion , idMes , idUsuariosXevaluacion , idEstado , idVerificador)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Criterios_Evaluacion_Criterios_evaluacionXItem1
    FOREIGN KEY (idCriterios_evaluacionXItem)
    REFERENCES Criterios_evaluacionXItem (idCriterios_evaluacionXItem)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table UsuarioXEvaluacioXEntidad
-- -----------------------------------------------------
CREATE TABLE UsuarioXEvaluacioXEntidad (
  idUsuarioXEvaluacioXEntidad INT NOT NULL IDENTITY,
  UEE_fecha_creacion DATE NOT NULL,
  UEE_fecha_modificacion DATE NOT NULL,
  idUsuariosXevaluacion INT NOT NULL,
  PRIMARY KEY (idUsuarioXEvaluacioXEntidad, idUsuariosXevaluacion),
  INDEX fk_UsuarioXEvaluacioXEntidad_UsuariosXevaluacion1_idx (idUsuariosXevaluacion ASC)
)
;

ALTER TABLE UsuarioXEvaluacioXEntidad  WITH CHECK ADD
  CONSTRAINT fk_UsuarioXEvaluacioXEntidad_UsuariosXevaluacion1
    FOREIGN KEY (idUsuariosXevaluacion)
    REFERENCES UsuariosXevaluacion (idUsuariosXevaluacion)
    ON DELETE CASCADE
    ON UPDATE CASCADE;