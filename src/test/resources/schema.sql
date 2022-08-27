

CREATE TABLE IF NOT EXISTS `company`
(
    `id`              INT         NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(20) NOT NULL,
    `foundation_date` DATE        NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `liner`
(
    `id`                 INT          NOT NULL AUTO_INCREMENT,
    `liner_name`         VARCHAR(45)  NULL DEFAULT NULL,
    `liner_photo`        VARCHAR(100) NULL DEFAULT NULL,
    `passenger_capacity` INT          NOT NULL,
    `company_id`         INT          NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_liner_company`
        FOREIGN KEY (`company_id`)
            REFERENCES `company` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `cruise`
(
    `id`              INT AUTO_INCREMENT NOT NULL,
    `cruise_photo`    VARCHAR(100)       NULL DEFAULT NULL,
    `number_of_ports` INT                NOT NULL,
    `price`           INT                NOT NULL,
    `start_date`      DATETIME           NULL DEFAULT NULL,
    `end_date`        DATETIME           NULL DEFAULT NULL,
    `liner_id`        INT                NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_cruise_liner1`
        FOREIGN KEY (`liner_id`)
            REFERENCES `liner` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);



CREATE TABLE IF NOT EXISTS `translation_cruise`
(
    `cruise_id`   INT          NOT NULL,
    `lang`        VARCHAR(2)   NOT NULL,
    `cruise_name` VARCHAR(100) NULL,
    `description` VARCHAR(200) NULL,
    PRIMARY KEY (lang, cruise_id),
    CONSTRAINT `fk_translation_cruise_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);







CREATE TABLE IF NOT EXISTS `role`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);







CREATE TABLE IF NOT EXISTS `user`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `login`        VARCHAR(40)  NOT NULL UNIQUE,
    `password`     VARCHAR(20)  NOT NULL,
    `name`         VARCHAR(20)  NULL DEFAULT NULL,
    `surname`      VARCHAR(20)  NULL DEFAULT NULL,
    `url_document` VARCHAR(255) NULL DEFAULT NULL,
    `role_id`      INT          NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `role` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);







CREATE TABLE IF NOT EXISTS `request`
(
    `id`        INT NOT NULL AUTO_INCREMENT,
    `amount`    INT NOT NULL,
    `cruise_id` INT NOT NULL,
    `user_id`   INT NOT NULL,
    `status`    ENUM ('OPENED', 'CONFIRMED', 'PAID', 'CLOSED'),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_request_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_request_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);



CREATE TABLE IF NOT EXISTS `port`
(
    `id`      INT         NOT NULL,
    `lang`    VARCHAR(3)  NOT NULL,
    `city`    VARCHAR(45) NOT NULL,
    `country` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE(`id`, `lang`)
);





CREATE TABLE IF NOT EXISTS `cruise_has_port`
(
    `cruise_id`       INT      NOT NULL,
    `port_id`         INT      NOT NULL,
    `sequence_number` INT      NOT NULL,
    `arrival_time`    DATETIME NULL,
    PRIMARY KEY (`cruise_id`, `port_id`, `sequence_number`),
    CONSTRAINT `fk_cruise_has_port_cruise1`
        FOREIGN KEY (`cruise_id`)
            REFERENCES `cruise` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cruise_has_port_port1`
        FOREIGN KEY (`port_id`)
            REFERENCES `port` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
