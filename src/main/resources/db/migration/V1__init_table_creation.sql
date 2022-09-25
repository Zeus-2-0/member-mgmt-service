DROP TABLE IF EXISTS `membermgmtdb`.`member_attribute`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_address`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_identifier`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_email`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_language`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_phone`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_premiums`;
DROP TABLE IF EXISTS `membermgmtdb`.`member`;
DROP TABLE IF EXISTS `membermgmtdb`.`premium_span`;
DROP TABLE IF EXISTS `membermgmtdb`.`enrollment_span`;
DROP TABLE IF EXISTS `membermgmtdb`.`account_attributes`;
DROP TABLE IF EXISTS `membermgmtdb`.`attribute`;
DROP TABLE IF EXISTS `membermgmtdb`.`account`;
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`account` (
  `account_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the account',
  `acct_number` VARCHAR(50) NOT NULL COMMENT 'A unique account number that is created for each account. This is the customer facing number.',
  `line_of_business_type_code` VARCHAR(45) NOT NULL COMMENT 'The line of business of the account',
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`account_sk`),
  UNIQUE INDEX `account_number_UNIQUE` (`acct_number` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'The account table. This table is the parent of all the tables in the schema';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`attribute` (
  `attribute_sk` VARCHAR(36) NOT NULL,
  `attribute_code` VARCHAR(50) NOT NULL,
  `attribute_name` VARCHAR(50) NOT NULL,
  `attribute_type` VARCHAR(50) NOT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`attribute_sk`))
ENGINE = InnoDB
COMMENT = 'Possible attributes that can be assigned to an account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`account_attributes` (
  `acct_attribute_sk` VARCHAR(36) NOT NULL COMMENT 'Primary Key of the table',
  `account_sk` VARCHAR(36) NOT NULL COMMENT 'Account foreign key',
  `attribute_sk` VARCHAR(36) NOT NULL,
  `attribute_value` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`acct_attribute_sk`),
  INDEX `acct_fk_idx` (`account_sk` ASC) VISIBLE,
  INDEX `attribute_fk_idx` (`attribute_sk` ASC) VISIBLE,
  CONSTRAINT `acct_fk`
    FOREIGN KEY (`account_sk`)
    REFERENCES `membermgmtdb`.`account` (`account_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `attribute_fk`
    FOREIGN KEY (`attribute_sk`)
    REFERENCES `membermgmtdb`.`attribute` (`attribute_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Contains account level attributes';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`enrollment_span` (
                                                                `enrlmnt_span_sk` VARCHAR(36) NOT NULL,
                                                                `account_sk` VARCHAR(36) NOT NULL,
                                                                `state_type_code` VARCHAR(45) NOT NULL,
                                                                `marketplace_type_code` VARCHAR(45) NOT NULL,
                                                                `business_unit_type_code` VARCHAR(50) NOT NULL,
                                                                `start_date` DATETIME NOT NULL,
                                                                `end_date` DATETIME NOT NULL,
                                                                `exchange_subscriber_id` VARCHAR(50) NOT NULL COMMENT 'Exchange subscriber id associated with the enrollment span',
                                                                `plan_id` VARCHAR(100) NOT NULL,
                                                                `group_policy_id` VARCHAR(100) NOT NULL,
                                                                `product_type_code` VARCHAR(100) NOT NULL,
                                                                `effectuation_date` DATETIME NULL,
                                                                `status_type_code` VARCHAR(50) NOT NULL,
                                                                `ztcn` VARCHAR(20) NOT NULL,
                                                                `created_date` DATETIME NULL,
                                                                `updated_date` DATETIME NULL,
                                                                PRIMARY KEY (`enrlmnt_span_sk`),
                                                                INDEX `acct_enrlmnt_fk_idx` (`account_sk` ASC) VISIBLE,
                                                                CONSTRAINT `acct_enrlmnt_fk`
                                                                    FOREIGN KEY (`account_sk`)
                                                                        REFERENCES `membermgmtdb`.`account` (`account_sk`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Enrollment spans that are associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`premium_span` (
  `premium_span_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `enrollment_span_sk` VARCHAR(36) NULL COMMENT 'The enrollment span that the premium span belongs to',
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `csr_variant` VARCHAR(10) NOT NULL,
  `total_prem_amt` DECIMAL(10,2) NOT NULL COMMENT 'The total premium amount per month for the plan chosen by the member',
  `total_resp_amt` DECIMAL(10,2) NOT NULL COMMENT 'Total amount that the member is responsible for payment towards the premium',
  `aptc_amt` DECIMAL(10,2) NULL COMMENT 'Federal contribution towards the premium',
  `other_pay_amt` DECIMAL(10,2) NULL COMMENT 'The amounts contributed by other sources (like the state) towards the premium',
  `csr_amt` DECIMAL(10,2) NULL COMMENT 'The Cost Sharing Reduction amount',
  `created_Date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`premium_span_sk`),
  INDEX `prem_enrlmnt_fk_idx` (`enrollment_span_sk` ASC) VISIBLE,
  CONSTRAINT `prem_enrlmnt_fk`
    FOREIGN KEY (`enrollment_span_sk`)
    REFERENCES `membermgmtdb`.`enrollment_span` (`enrlmnt_span_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Premium spans associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member` (
                                                       `member_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
                                                       `account_sk` VARCHAR(36) NOT NULL,
                                                       `member_code` VARCHAR(15) NOT NULL,
                                                       `first_name` VARCHAR(100) NOT NULL COMMENT 'First name of the member',
                                                       `middle_name` VARCHAR(100) NULL COMMENT 'The middle name of the member',
                                                       `last_name` VARCHAR(100) NOT NULL COMMENT 'The last name of the member',
                                                       `relationship_type_code` VARCHAR(20) NOT NULL COMMENT 'Relationship of the member with the head of the household',
                                                       `date_of_birth` DATETIME NULL COMMENT 'The date of birth of the member',
                                                       `gender_type_code` VARCHAR(50) NOT NULL COMMENT 'The gender of the member',
                                                       `height` DECIMAL(10,2) NULL COMMENT 'The height of the member',
                                                       `weight` DECIMAL(10,2) NULL COMMENT 'The weight of the member',
                                                       `created_date` DATETIME NULL COMMENT 'The date when the record was created',
                                                       `updated_date` DATETIME NULL COMMENT 'The date when the record was updated\n',
                                                       PRIMARY KEY (`member_sk`),
                                                       INDEX `acct_member_sk_idx` (`account_sk` ASC) VISIBLE,
                                                       UNIQUE INDEX `member_code_UNIQUE` (`member_code` ASC) VISIBLE,
                                                       CONSTRAINT `acct_member_sk`
                                                           FOREIGN KEY (`account_sk`)
                                                               REFERENCES `membermgmtdb`.`account` (`account_sk`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Members associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_attribute` (
  `member_attribute_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `member_sk` VARCHAR(36) NOT NULL COMMENT 'Foreign key of the member',
  `attribute_sk` VARCHAR(36) NOT NULL,
  `attribute_value` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`member_attribute_sk`),
  INDEX `member_fk_idx` (`member_sk` ASC) VISIBLE,
  INDEX `attr_fk_idx` (`attribute_sk` ASC) VISIBLE,
  CONSTRAINT `member_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `attr_fk`
    FOREIGN KEY (`attribute_sk`)
    REFERENCES `membermgmtdb`.`attribute` (`attribute_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Attributes associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_address` (
  `member_address_sk` VARCHAR(36) NOT NULL,
  `member_sk` VARCHAR(36) NOT NULL,
  `address_type_code` VARCHAR(20) NOT NULL COMMENT 'The type of address',
  `address_line_1` VARCHAR(100) NOT NULL COMMENT 'Address line 1 of the address',
  `address_line_2` VARCHAR(100) NULL COMMENT 'Address line 2 of the address',
  `city` VARCHAR(100) NOT NULL COMMENT 'City of the address',
  `state_type_code` VARCHAR(20) NOT NULL COMMENT 'State of the address',
  `zip_code` VARCHAR(10) NOT NULL COMMENT 'Zip code of the address',
  `start_date` DATETIME NOT NULL COMMENT 'Start date of the address',
  `end_date` DATETIME NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`member_address_sk`),
  INDEX `member_address_fk_idx` (`member_sk` ASC) VISIBLE,
  CONSTRAINT `member_address_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Addresses associated with the member.';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_identifier` (
  `member_identifier_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `member_sk` VARCHAR(36) NOT NULL COMMENT 'Foreign key of the member table',
  `identifier_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of identifier',
  `identifier_value` VARCHAR(50) NOT NULL COMMENT 'Value of the identifier',
  `created_date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`member_identifier_sk`),
  INDEX `member_identifier_fk_idx` (`member_sk` ASC) VISIBLE,
  CONSTRAINT `member_identifier_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Identifiers associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_email` (
  `member_email_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `member_sk` VARCHAR(36) NOT NULL COMMENT 'Foreign key to the member table',
  `email_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of email (e.g. personal, work, school)\n',
  `email` VARCHAR(100) NOT NULL COMMENT 'The email',
  `is_primary` BOOLEAN NOT NULL,
  `start_date` DATETIME NOT NULL COMMENT 'The start date of the email',
  `end_date` DATETIME NULL COMMENT 'The end date of the email',
  `created_date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`member_email_sk`),
  INDEX `member_email_fk_idx` (`member_sk` ASC) VISIBLE,
  CONSTRAINT `member_email_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Emails associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_language` (
  `member_language_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `member_sk` VARCHAR(36) NULL COMMENT 'Foreign key to the member table',
  `language_type_code` VARCHAR(20) NULL COMMENT 'The type of language (like Written and Spoken)',
  `language_code` VARCHAR(30) NULL COMMENT 'The language (i.e. English, Spanish etc)',
  `start_date` DATETIME NOT NULL COMMENT 'Date when the language was effective ',
  `end_date` DATETIME NULL,
  `created_date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`member_language_sk`),
  INDEX `member_lang_fk_idx` (`member_sk` ASC) VISIBLE,
  CONSTRAINT `member_lang_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Languages associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_phone` (
  `member_phone_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `member_sk` VARCHAR(36) NULL COMMENT 'Foreign key to the member table',
  `phone_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of phone number',
  `phone_number` VARCHAR(30) NOT NULL COMMENT 'The phone number of the member',
  `start_date` DATETIME NOT NULL COMMENT 'Date when the phone was effective',
  `end_date` DATETIME NULL,
  `created_date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`member_phone_sk`),
  INDEX `member_phone_fk_idx` (`member_sk` ASC) VISIBLE,
  CONSTRAINT `member_phone_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Phone numbers associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_premiums` (
                                                                `member_premium_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
                                                                `premium_span_sk` VARCHAR(36) NOT NULL COMMENT 'The premium span that the premium is associated with',
                                                                `member_sk` VARCHAR(36) NOT NULL COMMENT 'The member for who the premium is attributed',
                                                                `exchange_member_id` VARCHAR(50) NOT NULL,
                                                                `individual_premium_amount` DECIMAL(10,2) NOT NULL COMMENT 'The member’s individual premium amount',
                                                                `created_date` DATETIME NULL COMMENT 'Date when the record was created',
                                                                `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
                                                                PRIMARY KEY (`member_premium_sk`),
                                                                INDEX `premium_span_fk_idx` (`premium_span_sk` ASC) VISIBLE,
                                                                INDEX `member_fk_idx` (`member_sk` ASC) VISIBLE,
                                                                CONSTRAINT `premium_span_fk`
                                                                    FOREIGN KEY (`premium_span_sk`)
                                                                        REFERENCES `membermgmtdb`.`premium_span` (`premium_span_sk`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION,
                                                                CONSTRAINT `member_premium_fk`
                                                                    FOREIGN KEY (`member_sk`)
                                                                        REFERENCES `membermgmtdb`.`member` (`member_sk`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Members associated with the premium span';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`payload_tracker` (
  `payload_tracker_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key of the table',
  `payload_id` VARCHAR(45) NOT NULL COMMENT 'A Unique payload id that is sent or received in the payload',
  `acct_number` VARCHAR(50) NOT NULL COMMENT 'The account number for which the payload was sent or received',
  `payload` LONGTEXT NOT NULL COMMENT 'The actual payload that was sent of received',
  `payload_direction_type_code` VARCHAR(45) NOT NULL COMMENT 'Indicates if the payload is inbound or outbound from member management service point of view',
  `src_dest` VARCHAR(100) NOT NULL COMMENT 'The source of the payload when its an inbound payload and the destination of the payload when it is an outbound payload',
  `created_date` DATETIME NULL COMMENT 'The date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
  PRIMARY KEY (`payload_tracker_sk`))
ENGINE = InnoDB
COMMENT = 'This table tracks all the inbound and outbound payloads in the member management service';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`payload_tracker_detail` (
  `payload_tracker_detail_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
  `payload_tracker_sk` VARCHAR(36) NOT NULL COMMENT 'The foreign key of the payload tracker table',
  `response_type_code` VARCHAR(50) NOT NULL COMMENT 'The type of response like ACK, RESULT etc',
  `response_payload` LONGTEXT NOT NULL COMMENT 'The response payload that was received for an outbound payload and sent for an inbound payload',
  `created_date` DATETIME NULL COMMENT 'Date when the record was created',
  `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
  PRIMARY KEY (`payload_tracker_detail_sk`),
  INDEX `payload_tracker_fk_idx` (`payload_tracker_sk` ASC),
  CONSTRAINT `payload_tracker_fk`
    FOREIGN KEY (`payload_tracker_sk`)
    REFERENCES `membermgmtdb`.`payload_tracker` (`payload_tracker_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'The payload tracker detail table, that tracks all the responses received for an outbound payload and all the responses sent for an inbound payload';