SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fp_db
-- -----------------------------------------------------
DROP database IF EXISTS `fp_db`;

-- -----------------------------------------------------
-- Schema fp_db
-- -----------------------------------------------------
CREATE schema IF NOT EXISTS `fp_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `fp_db`;

-- -----------------------------------------------------
-- Table `fp_db`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`company`;

CREATE TABLE IF NOT EXISTS `fp_db`.`company`
(
    `id`              INT         NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(20) NOT NULL,
    `foundation_date` DATE        NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `fp_db`.`liner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`liner`;

CREATE TABLE IF NOT EXISTS `fp_db`.`liner`
(
    `id`                 INT          NOT NULL AUTO_INCREMENT,
    `liner_name`         VARCHAR(45)  NULL DEFAULT NULL,
    `liner_photo`        VARCHAR(100) NULL DEFAULT NULL,
    `passenger_capacity` INT          NOT NULL,
    `company_id`         INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_liner_company_idx` (`company_id` ASC) VISIBLE,
    CONSTRAINT `fk_liner_company`
        FOREIGN KEY (`company_id`)
            REFERENCES `fp_db`.`company` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `fp_db`.`cruise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`cruise`;

CREATE TABLE IF NOT EXISTS `fp_db`.`cruise`
(
    `id`              INT AUTO_INCREMENT NOT NULL,
    `cruise_photo`    VARCHAR(100)       NULL DEFAULT NULL,
    `number_of_ports` INT                NOT NULL,
    `price`           INT                NOT NULL,
    `start_date`      DATETIME           NULL DEFAULT NULL,
    `end_date`        DATETIME           NULL DEFAULT NULL,
    `liner_id`        INT                NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_cruise_liner1_idx` (`liner_id` ASC) VISIBLE,
    CONSTRAINT `fk_cruise_liner1`
        FOREIGN KEY (`liner_id`)
            REFERENCES `fp_db`.`liner` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `fp_db`.`translation_cruise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fp_db`.`translation_cruise`
(
    `cruise_id`   INT          NOT NULL,
    `lang`        VARCHAR(2)   NOT NULL,
    `cruise_name` VARCHAR(100) NULL,
    `description` VARCHAR(200) NULL,
    PRIMARY KEY (lang, cruise_id),
    INDEX `fk_translation_cruise_cruise1_idx` (`cruise_id` ASC) VISIBLE,
    CONSTRAINT `fk_translation_cruise_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `fp_db`.`cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fp_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`role`;

CREATE TABLE IF NOT EXISTS `fp_db`.`role`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fp_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`user`;

CREATE TABLE IF NOT EXISTS `fp_db`.`user`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `login`        VARCHAR(40)  NOT NULL UNIQUE,
    `password`     VARCHAR(100)  NOT NULL,
    `name`         VARCHAR(20)  NULL DEFAULT NULL,
    `surname`      VARCHAR(20)  NULL DEFAULT NULL,
    `url_document` VARCHAR(255) NULL DEFAULT NULL,
    `role_id`      INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `fp_db`.`role` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fp_db`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fp_db`.`request`;

CREATE TABLE IF NOT EXISTS `fp_db`.`request`
(
    `id`        INT NOT NULL AUTO_INCREMENT,
    `amount`    INT NOT NULL,
    `cruise_id` INT NOT NULL,
    `user_id`   INT NOT NULL,
    `status`    ENUM ('OPENED', 'CONFIRMED', 'PAID', 'CLOSED'),
    PRIMARY KEY (`id`),
    INDEX `fk_request_cruise1_idx` (`cruise_id` ASC) VISIBLE,
    INDEX `fk_request_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_request_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `fp_db`.`cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_request_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `fp_db`.`user` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
-- -----------------------------------------------------
-- Table `fp_db`.`port`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fp_db`.`port`
(
    `id`      INT         NOT NULL,
    `lang`    VARCHAR(3)  NOT NULL,
    `city`    VARCHAR(45) NOT NULL,
    `country` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`, `lang`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fp_db`.`cruise_has_port`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fp_db`.`cruise_has_port`
(
    `cruise_id`       INT      NOT NULL,
    `port_id`         INT      NOT NULL,
    `sequence_number` INT      NOT NULL,
    `arrival_time`    DATETIME NULL,
    PRIMARY KEY (`cruise_id`, `port_id`, `sequence_number`),
    INDEX `fk_cruise_has_port_port1_idx` (`port_id` ASC) VISIBLE,
    INDEX `fk_cruise_has_port_cruise1_idx` (`cruise_id` ASC) VISIBLE,
    CONSTRAINT `fk_cruise_has_port_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `fp_db`.`cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cruise_has_port_port1`
        FOREIGN KEY (`port_id`)
            REFERENCES `fp_db`.`port` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

/*-- -----------
  ------------------------------------------
-- Table `fp_db`.`translation_port`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fp_db`.`translation_port`
(
    `port_id`      INT         NOT NULL,
    `land`         VARCHAR(3)  NOT NULL,
    `city_land`    VARCHAR(45) NULL,
    `country_land` VARCHAR(45) NULL,
    INDEX `fk_translation_port_port1_idx` (`port_id` ASC) VISIBLE,
    PRIMARY KEY (`land`,port_id),
    CONSTRAINT `fk_translation_port_port1`
        FOREIGN KEY (`port_id`)
            REFERENCES `fp_db`.`port` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;*/