DROP TABLE IF EXISTS `membermgmtdb`.`account`;
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`account` (
    `account_sk` VARCHAR(36) NOT NULL,
    `account_id` VARCHAR(100) NOT NULL,
    `line_of_business_type_code` VARCHAR(45) NOT NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    PRIMARY KEY (`account_sk`))
    ENGINE = InnoDB
    COMMENT = 'The root account table for the entire account'