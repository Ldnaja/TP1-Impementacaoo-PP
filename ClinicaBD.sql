-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clinica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clinica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clinica` ;

-- -----------------------------------------------------
-- Table `clinica`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`animal` (
  `idanimal` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `idcliente` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idanimal`),
  INDEX `animal_ibfk_1` (`idcliente` ASC) VISIBLE,
  CONSTRAINT `animal_ibfk_1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `clinica`.`cliente` (`idcliente`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica`.`veterinario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`veterinario` (
  `idveterinario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `CRMV` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idveterinario`),
  UNIQUE INDEX `CRMV_UNIQUE` (`CRMV` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica`.`consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`consulta` (
  `idconsulta` INT NOT NULL AUTO_INCREMENT,
  `cpf_cliente` VARCHAR(15) NOT NULL,
  `crmv_veterinario` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idconsulta`),
  INDEX `consulta_ibfk_1` (`cpf_cliente` ASC) VISIBLE,
  INDEX `consulta_ibfk_2` (`crmv_veterinario` ASC) VISIBLE,
  CONSTRAINT `consulta_ibfk_1`
    FOREIGN KEY (`cpf_cliente`)
    REFERENCES `clinica`.`cliente` (`cpf`),
  CONSTRAINT `consulta_ibfk_2`
    FOREIGN KEY (`crmv_veterinario`)
    REFERENCES `clinica`.`veterinario` (`CRMV`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica`.`servico` (
  `idservico` INT NOT NULL AUTO_INCREMENT,
  `tiposervico` VARCHAR(45) NOT NULL,
  `especializacaovet` VARCHAR(45) NOT NULL,
  `idveterinario` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idservico`),
  INDEX `fk_servico_veterinario` (`idveterinario` ASC) VISIBLE,
  CONSTRAINT `fk_servico_veterinario`
    FOREIGN KEY (`idveterinario`)
    REFERENCES `clinica`.`veterinario` (`idveterinario`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
