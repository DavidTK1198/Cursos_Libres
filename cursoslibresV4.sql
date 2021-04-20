-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CursosLibres
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CursosLibres
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CursosLibres` DEFAULT CHARACTER SET utf8 ;
USE `CursosLibres` ;

-- -----------------------------------------------------
-- Table `CursosLibres`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Usuario` (
  `Rol` VARCHAR(45) NOT NULL,
  `Id_Usu` INT NOT NULL,
  `Clave` VARCHAR(45) NULL,
  PRIMARY KEY (`Id_Usu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Estudiante` (
  `idEstudiante` INT NOT NULL,
  `Nom_Est` VARCHAR(45) NULL,
  `Tel_Est` VARCHAR(45) NULL,
  `Correo_Est` VARCHAR(45) NULL,
  `Usuario_Id_Usu` INT NOT NULL,
  PRIMARY KEY (`idEstudiante`),
  INDEX `fk_Estudiante_Usuario1_idx` (`Usuario_Id_Usu` ASC) VISIBLE,
  CONSTRAINT `fk_Estudiante_Usuario1`
    FOREIGN KEY (`Usuario_Id_Usu`)
    REFERENCES `CursosLibres`.`Usuario` (`Id_Usu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Profesor` (
  `id_Profe` INT NOT NULL,
  `Nom_Profe` VARCHAR(45) NOT NULL,
  `Tel_Profe` VARCHAR(45) NULL,
  `Correo_Profe` VARCHAR(45) NULL,
  `Usuario_Id_Usu` INT NOT NULL,
  `Especialidad` VARCHAR(45) NULL,
  PRIMARY KEY (`id_Profe`),
  INDEX `fk_Profesor_Usuario1_idx` (`Usuario_Id_Usu` ASC) VISIBLE,
  CONSTRAINT `fk_Profesor_Usuario1`
    FOREIGN KEY (`Usuario_Id_Usu`)
    REFERENCES `CursosLibres`.`Usuario` (`Id_Usu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Curso` (
  `NRC` INT NOT NULL AUTO_INCREMENT,
  `Nom_Cur` VARCHAR(45) NULL,
  `Des_Cur` VARCHAR(45) NULL,
  `Oferta` TINYINT NULL,
  `Precio` FLOAT NULL,
  PRIMARY KEY (`NRC`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Grupo` (
  `num_Grup` INT NOT NULL,
  `Profesor_id_Profe` INT NOT NULL,
  `Horario` VARCHAR(100) NULL,
  `Curso_NRC` INT NOT NULL,
  PRIMARY KEY (`num_Grup`),
  INDEX `fk_Grupo_Profesor1_idx` (`Profesor_id_Profe` ASC) VISIBLE,
  INDEX `fk_Grupo_Curso1_idx` (`Curso_NRC` ASC) VISIBLE,
  CONSTRAINT `fk_Grupo_Profesor1`
    FOREIGN KEY (`Profesor_id_Profe`)
    REFERENCES `CursosLibres`.`Profesor` (`id_Profe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_Curso1`
    FOREIGN KEY (`Curso_NRC`)
    REFERENCES `CursosLibres`.`Curso` (`NRC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Inscripcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Inscripcion` (
  `Estudiante_id` INT NOT NULL,
  `Grupo_num_Grup` INT NOT NULL,
  `Nota` INT NULL,
  `Sec_Inscripcion` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_Grupo-Estudiante_Estudiante1_idx` (`Estudiante_id` ASC) VISIBLE,
  INDEX `fk_Grupo-Estudiante_Grupo1_idx` (`Grupo_num_Grup` ASC) VISIBLE,
  PRIMARY KEY (`Sec_Inscripcion`),
  CONSTRAINT `fk_Grupo-Estudiante_Estudiante1`
    FOREIGN KEY (`Estudiante_id`)
    REFERENCES `CursosLibres`.`Estudiante` (`idEstudiante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo-Estudiante_Grupo1`
    FOREIGN KEY (`Grupo_num_Grup`)
    REFERENCES `CursosLibres`.`Grupo` (`num_Grup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CursosLibres`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CursosLibres`.`Administrador` (
  `id_admin` INT NOT NULL,
  `nombre_admin` VARCHAR(45) NULL,
  `clave_admin` VARCHAR(45) NULL,
  `Usuario_Id_Usu` INT NOT NULL,
  PRIMARY KEY (`id_admin`),
  INDEX `fk_Administrador_Usuario1_idx` (`Usuario_Id_Usu` ASC) VISIBLE,
  CONSTRAINT `fk_Administrador_Usuario1`
    FOREIGN KEY (`Usuario_Id_Usu`)
    REFERENCES `CursosLibres`.`Usuario` (`Id_Usu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
