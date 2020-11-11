-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`event_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`event_type` (
  `id_event` INT NOT NULL AUTO_INCREMENT,
  `event_type` VARCHAR(45) NULL,
  PRIMARY KEY (`id_event`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `id` INT NOT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`event` (
  `id_event` INT NOT NULL AUTO_INCREMENT,
  `seat_type` VARCHAR(45) NULL,
  `seat_price` DOUBLE NULL,
  `max_ticket_bought_by1` INT NULL,
  `ddate` DATE NULL,
  `event_type_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id_event`),
  INDEX `fk_event_event_type1_idx` (`event_type_id` ASC),
  INDEX `fk_event_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_event_event_type1`
    FOREIGN KEY (`event_type_id`)
    REFERENCES `mydb`.`event_type` (`id_event`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `mydb`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `email` VARCHAR(30) NULL,
  `user_phone` VARCHAR(45) NULL,
  `id_role` INT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id_role` INT NOT NULL,
  `role` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id_role`),
  INDEX `fk_role_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_role_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`clients` (
  `id_client` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_client`),
  INDEX `fk_clients_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_clients_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`organizer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`organizer` (
  `id_organizer` INT NOT NULL AUTO_INCREMENT,
  `o_first_name` VARCHAR(45) NULL,
  `o_last_name` VARCHAR(45) NULL,
  `honorarium` DOUBLE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_organizer`),
  INDEX `fk_organizer_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_organizer_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`distributor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`distributor` (
  `id_distributor` INT NOT NULL AUTO_INCREMENT,
  `d_first_name` VARCHAR(45) NULL,
  `d_last_name` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `honorarium` DOUBLE NOT NULL,
  PRIMARY KEY (`id_distributor`),
  INDEX `fk_distributor_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_distributor_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`organizer-events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`organizer-events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organizer_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_organizer-events_organizer1_idx` (`organizer_id` ASC),
  INDEX `fk_organizer-events_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_organizer-events_organizer1`
    FOREIGN KEY (`organizer_id`)
    REFERENCES `mydb`.`organizer` (`id_organizer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_organizer-events_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `mydb`.`event` (`id_event`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`distributor-events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`distributor-events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `distributor_id` INT NOT NULL,
  `event_id_event` INT NOT NULL,
  `tickets_sold` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_distributor-events_distributor1_idx` (`distributor_id` ASC),
  INDEX `fk_distributor-events_event1_idx` (`event_id_event` ASC),
  CONSTRAINT `fk_distributor-events_distributor1`
    FOREIGN KEY (`distributor_id`)
    REFERENCES `mydb`.`distributor` (`id_distributor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_distributor-events_event1`
    FOREIGN KEY (`event_id_event`)
    REFERENCES `mydb`.`event` (`id_event`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`type-seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`type-seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`seats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `type-seat_id` INT NOT NULL,
  `all_seats` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seats_type-seat1_idx` (`type-seat_id` ASC),
  CONSTRAINT `fk_seats_type-seat1`
    FOREIGN KEY (`type-seat_id`)
    REFERENCES `mydb`.`type-seat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
