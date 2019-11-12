-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema NP2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema NP2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `NP2` DEFAULT CHARACTER SET latin1 ;
USE `NP2` ;

-- -----------------------------------------------------
-- Table `NP2`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NP2`.`clientes` (
  `codCli` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `endereco` VARCHAR(60) NOT NULL,
  `estado` CHAR(2) NOT NULL,
  PRIMARY KEY (`codCli`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `NP2`.`notaFiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NP2`.`notaFiscal` (
  `numero` INT(11) NOT NULL AUTO_INCREMENT,
  `serie` CHAR(3) NOT NULL,
  `codCli` INT(11) NOT NULL,
  `data` DATE NOT NULL,
  `cancelada` CHAR(1) NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `codCli` (`codCli` ASC),
  CONSTRAINT `notaFiscal_ibfk_1`
    FOREIGN KEY (`codCli`)
    REFERENCES `NP2`.`clientes` (`codCli`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `NP2`.`pneus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NP2`.`pneus` (
  `codPneu` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(50) NOT NULL,
  `medidas` VARCHAR(15) NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `ativo` CHAR(1) NOT NULL,
  PRIMARY KEY (`codPneu`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `NP2`.`itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NP2`.`itens` (
  `item` SMALLINT(6) NOT NULL,
  `numero` INT(11) NOT NULL,
  `serie` CHAR(3) NOT NULL,
  `codPneu` INT(11) NOT NULL,
  `qtde` SMALLINT(6) NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  INDEX `numero` (`numero` ASC),
  INDEX `codPneu` (`codPneu` ASC),
  CONSTRAINT `itens_ibfk_1`
    FOREIGN KEY (`numero`)
    REFERENCES `NP2`.`notaFiscal` (`numero`),
  CONSTRAINT `itens_ibfk_2`
    FOREIGN KEY (`codPneu`)
    REFERENCES `NP2`.`pneus` (`codPneu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
