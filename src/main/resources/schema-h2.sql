CREATE TABLE `spyglass`.`goal` (
  `goals_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `picture` VARCHAR(45) NULL,
  `target_date` DATE NOT NULL,
  `target_amount` INT NOT NULL,
  `current_amount` INT ZEROFILL NULL,
  `account_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`goals_id`));
  
  CREATE TABLE `spyglass`.`account` (
  `account_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `dob` DATE NOT NULL,
  PRIMARY KEY (`account_id`));
  
ALTER TABLE `spyglass`.`goal`
ADD INDEX `account_id_idx` (`account_id` ASC) VISIBLE;

ALTER TABLE `spyglass`.`goal` 
ADD CONSTRAINT `account_id`
  FOREIGN KEY (`account_id`)
  REFERENCES `spyglass`.`account` (`account_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;