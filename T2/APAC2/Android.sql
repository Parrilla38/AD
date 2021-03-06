-- MySQL Script generated by MySQL Workbench
-- dimarts, 30 d’octubre de 2018, 13:40:49 CET
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering



-- -----------------------------------------------------
-- Schema BDJocs2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BDJocs2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BDJocs2` DEFAULT CHARACTER SET utf8 ;


-- -----------------------------------------------------
-- Table `BDJocs2`.`jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDJocs2`.`jugador` (
  `id` INT NOT NULL,
  `nick` VARCHAR(45) NULL,
  `dataRegistre` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDJocs`.`Genere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDJocs2`.`Genere` (
  `id` INT NOT NULL,
  `nom` VARCHAR(45) NULL,
  `descripció` VARCHAR(256) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDJocs2`.`Joc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDJocs2`.`Joc` (
  `id` INT NOT NULL,
  `nom` VARCHAR(45) NULL,
  `descripció` VARCHAR(256) NULL,
  `Genere_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Joc_Genere1_idx` (`Genere_id` ASC),
  CONSTRAINT `fk_Joc_Genere1`
    FOREIGN KEY (`Genere_id`)
    REFERENCES `BDJocs2`.`Genere` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BDJocs2`.`Puntuacions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BDJocs2`.`Puntuacions` (
  `jugador_id` INT NOT NULL,
  `Joc_id` INT NOT NULL,
  `puntuacio` INT NULL,
  PRIMARY KEY (`jugador_id`, `Joc_id`),
  INDEX `fk_jugador_has_Joc_Joc1_idx` (`Joc_id` ASC),
  INDEX `fk_jugador_has_Joc_jugador1_idx` (`jugador_id` ASC),
  CONSTRAINT `fk_jugador_has_Joc_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `BDJocs2`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_has_Joc_Joc1`
    FOREIGN KEY (`Joc_id`)
    REFERENCES `BDJocs2`.`Joc` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

