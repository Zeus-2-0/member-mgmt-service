DROP TABLE IF EXISTS `membermgmtdb`.`member_attribute`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_address`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_identifier`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_email`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_language`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_phone`;
DROP TABLE IF EXISTS `membermgmtdb`.`member_premiums`;
DROP TABLE IF EXISTS `membermgmtdb`.`alternate_contact`;
DROP TABLE IF EXISTS `membermgmtdb`.`member`;
DROP TABLE IF EXISTS `membermgmtdb`.`premium_span`;
DROP TABLE IF EXISTS `membermgmtdb`.`enrollment_span`;
DROP TABLE IF EXISTS `membermgmtdb`.`account_attributes`;
DROP TABLE IF EXISTS `membermgmtdb`.`attribute`;
DROP TABLE IF EXISTS `membermgmtdb`.`broker`;
DROP TABLE IF EXISTS `membermgmtdb`.`sponsor`;
DROP TABLE IF EXISTS `membermgmtdb`.`payer`;
DROP TABLE IF EXISTS `membermgmtdb`.`account`;
DROP TABLE IF EXISTS `membermgmtdb`.`payload_tracker_detail`;
DROP TABLE IF EXISTS `membermgmtdb`.`payload_tracker`;
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`account` (
  `account_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the account',
  `acct_number` VARCHAR(50) NOT NULL COMMENT 'A unique account number that is created for each account. This is the customer facing number.',
  `line_of_business_type_code` VARCHAR(45) NOT NULL COMMENT 'The line of business of the account',
  `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the account',
  `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
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
  `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the account',
  `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
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
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`broker` (
    `broker_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key of the table',
    `account_sk` VARCHAR(36) NOT NULL COMMENT 'The account to which the broker is associated',
    `broker_code` VARCHAR(50) NOT NULL COMMENT 'The unique code for the broker in MMS',
    `broker_name` VARCHAR(100) NOT NULL COMMENT 'The name of the broker',
    `broker_id` VARCHAR(100) NOT NULL COMMENT 'The id of the broker',
    `agency_name` VARCHAR(100) NULL COMMENT 'The agency name of the broker',
    `agency_id` VARCHAR(50) NULL COMMENT 'The agency id of the broker',
    `account_number_1` VARCHAR(50) NULL COMMENT 'The first account number of the broker',
    `account_number_2` VARCHAR(50) NULL COMMENT 'The second account number of the broker',
    `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the broker',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATE NOT NULL COMMENT 'The start date of the broker',
    `end_date` DATE NULL COMMENT 'The end date of the broker',
    `created_date` DATETIME NULL COMMENT 'The date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
    PRIMARY KEY (`broker_sk`),
    INDEX `fk_broker_account1_idx` (`account_sk` ASC) VISIBLE,
    CONSTRAINT `fk_broker_account1`
    FOREIGN KEY (`account_sk`)
    REFERENCES `membermgmtdb`.`account` (`account_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The brokers associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`sponsor` (
    `sponsor_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key of the table',
    `account_sk` VARCHAR(36) NOT NULL COMMENT 'The account to which the sponsor is associated',
    `sponsor_code` VARCHAR(50) NOT NULL COMMENT 'The unique code for the sponsor in MMS',
    `sponsor_id` VARCHAR(50) NOT NULL COMMENT 'The id of the sponsor',
    `sponsor_name` VARCHAR(50) NOT NULL COMMENT 'The name of the sponsor',
    `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the sponsor',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATE NOT NULL COMMENT 'The start date of the sponsor',
    `end_date` DATE NULL COMMENT 'The end date of the sponsor',
    `created_date` DATETIME NULL COMMENT 'The date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
    PRIMARY KEY (`sponsor_sk`),
    INDEX `fk_sponsor_account1_idx` (`account_sk` ASC) VISIBLE,
    CONSTRAINT `fk_sponsor_account1`
    FOREIGN KEY (`account_sk`)
    REFERENCES `membermgmtdb`.`account` (`account_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The sponsors associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`payer` (
    `payer_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key of the table',
    `account_sk` VARCHAR(36) NOT NULL COMMENT 'The account to which the payer is associated',
    `payer_code` VARCHAR(50) NOT NULL COMMENT 'The unique code assigned to the payer in MMS',
    `payer_name` VARCHAR(100) NOT NULL COMMENT 'The name of the payer',
    `payer_id` VARCHAR(50) NULL COMMENT 'The id of the payer',
    `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the payer',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATE NOT NULL COMMENT 'The start date of the payer',
    `end_date` DATE NULL COMMENT 'The end date of the payer',
    `created_date` DATETIME NULL COMMENT 'The date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
    PRIMARY KEY (`payer_sk`),
    INDEX `fk_payer_account1_idx` (`account_sk` ASC) VISIBLE,
    CONSTRAINT `fk_payer_account1`
    FOREIGN KEY (`account_sk`)
    REFERENCES `membermgmtdb`.`account` (`account_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The payers that are associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`enrollment_span` (
    `enrlmnt_span_sk` VARCHAR(36) NOT NULL,
    `enrollment_span_code` VARCHAR(50) NOT NULL COMMENT 'A unique code created for the enrollment span',
    `account_sk` VARCHAR(36) NOT NULL,
    `state_type_code` VARCHAR(45) NOT NULL,
    `marketplace_type_code` VARCHAR(45) NOT NULL,
    `business_unit_type_code` VARCHAR(50) NOT NULL,
    `enrollment_type` VARCHAR(50) NOT NULL COMMENT 'Indicates if the enrollment was a passive or active enrollment',
    `coverage_type_code` VARCHAR(50) NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    `exchange_subscriber_id` VARCHAR(50) NOT NULL COMMENT 'Exchange subscriber id associated with the enrollment span',
    `plan_id` VARCHAR(100) NOT NULL,
    `group_policy_id` VARCHAR(100) NOT NULL,
    `product_type_code` VARCHAR(100) NOT NULL,
    `effectuation_date` DATETIME NULL,
    `delinq_ind` BOOLEAN NOT NULL DEFAULT 0 COMMENT 'Indicates if the enrollment span is delinquent.',
    `paid_through_date` DATE NULL COMMENT 'The paid through date associated with the enrollment span',
    `claim_paid_through_date` DATE NULL COMMENT 'The claim paid through date associated with the enrollment span',
    `status_type_code` VARCHAR(50) NOT NULL COMMENT 'The status of the enrollment span',
    `effective_reason` VARCHAR(150) NULL COMMENT 'The effective reason of the enrollment span',
    `term_reason` VARCHAR(150) NULL COMMENT 'The term reason of the enrollment span',
    `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the enrollment span',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `created_date` DATETIME NULL COMMENT 'The date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
    PRIMARY KEY (`enrlmnt_span_sk`),
    INDEX `acct_enrlmnt_fk_idx` (`account_sk` ASC) VISIBLE,
    UNIQUE INDEX `enrollment_span_code_UNIQUE` (`enrollment_span_code` ASC) VISIBLE,
    CONSTRAINT `acct_enrlmnt_fk`
    FOREIGN KEY (`account_sk`)
    REFERENCES `membermgmtdb`.`account` (`account_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Enrollment spans that are associated with the account';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`premium_span` (
    `premium_span_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
    `premium_span_code` VARCHAR(50) NOT NULL COMMENT 'Unique premium span code created for the premium span',
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the premium span',
    `enrollment_span_sk` VARCHAR(36) NULL COMMENT 'The enrollment span that the premium span belongs to',
    `start_date` DATETIME NOT NULL,
    `end_date` DATETIME NOT NULL,
    `status_type_code` VARCHAR(50) NOT NULL COMMENT 'The status of the premium span',
    `sequence` INT NOT NULL COMMENT 'The sequence in which the premium span was created',
    `csr_variant` VARCHAR(10) NOT NULL,
    `total_prem_amt` DECIMAL(10,2) NOT NULL COMMENT 'The total premium amount per month for the plan chosen by the member',
    `total_resp_amt` DECIMAL(10,2) NOT NULL COMMENT 'Total amount that the member is responsible for payment towards the premium',
    `aptc_amt` DECIMAL(10,2) NULL COMMENT 'Federal contribution towards the premium',
    `other_pay_amt` DECIMAL(10,2) NULL COMMENT 'The amounts contributed by other sources (like the state) towards the premium',
    `csr_amt` DECIMAL(10,2) NULL COMMENT 'The Cost Sharing Reduction amount',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `created_Date` DATETIME NULL COMMENT 'Date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`premium_span_sk`),
    INDEX `prem_enrlmnt_fk_idx` (`enrollment_span_sk` ASC) VISIBLE,
    UNIQUE INDEX `premium_span_code_UNIQUE` (`premium_span_code` ASC) VISIBLE,
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
                                                       `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the member',
                                                       `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
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
  `ztcn` VARCHAR(50) NULL COMMENT 'The ZTCN of the transaction that created the account',
  `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
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
    `member_address_code` VARCHAR(50) NOT NULL COMMENT 'Unique member address code for the address',
    `member_sk` VARCHAR(36) NOT NULL,
    `address_type_code` VARCHAR(20) NOT NULL COMMENT 'The type of address',
    `address_line_1` VARCHAR(100) NOT NULL COMMENT 'Address line 1 of the address',
    `address_line_2` VARCHAR(100) NULL COMMENT 'Address line 2 of the address',
    `city` VARCHAR(100) NOT NULL COMMENT 'City of the address',
    `state_type_code` VARCHAR(20) NOT NULL COMMENT 'State of the address',
    `fips_code` VARCHAR(10) NOT NULL COMMENT 'County code of the address',
    `zip_code` VARCHAR(10) NOT NULL COMMENT 'Zip code of the address',
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the address',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATETIME NOT NULL COMMENT 'Start date of the address',
    `end_date` DATETIME NULL,
    `created_date` DATETIME NULL,
    `updated_date` DATETIME NULL,
    PRIMARY KEY (`member_address_sk`),
    INDEX `member_address_fk_idx` (`member_sk` ASC) VISIBLE,
    UNIQUE INDEX `member_address_code_UNIQUE` (`member_address_code` ASC) VISIBLE,
    CONSTRAINT `member_address_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Addresses associated with the member.';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_identifier` (
    `member_identifier_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
    `member_identifier_code` VARCHAR(50) NOT NULL COMMENT 'Unique member identifier code created for the identifier',
    `member_sk` VARCHAR(36) NOT NULL COMMENT 'Foreign key of the member table',
    `identifier_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of identifier',
    `identifier_value` VARCHAR(50) NOT NULL COMMENT 'Value of the identifier',
    `is_active` BOOLEAN NULL COMMENT 'Identifies if the identifier is active',
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the identifier',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `created_date` DATETIME NULL COMMENT 'Date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`member_identifier_sk`),
    INDEX `member_identifier_fk_idx` (`member_sk` ASC) VISIBLE,
    UNIQUE INDEX `member_identifier_code_UNIQUE` (`member_identifier_code` ASC) VISIBLE,
    CONSTRAINT `member_identifier_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Identifiers associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_email` (
    `member_email_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
    `member_email_code` VARCHAR(50) NOT NULL COMMENT 'Unique member email code that is created for the email',
    `member_sk` VARCHAR(36) NOT NULL COMMENT 'Foreign key to the member table',
    `email_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of email (e.g. personal, work, school)',
    `email` VARCHAR(100) NOT NULL COMMENT 'The email',
    `is_primary` BOOLEAN NOT NULL,
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the email',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATETIME NOT NULL COMMENT 'The start date of the email',
    `end_date` DATETIME NULL COMMENT 'The end date of the email',
    `created_date` DATETIME NULL COMMENT 'Date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`member_email_sk`),
    INDEX `member_email_fk_idx` (`member_sk` ASC) VISIBLE,
    UNIQUE INDEX `member_email_code_UNIQUE` (`member_email_code` ASC) VISIBLE,
    CONSTRAINT `member_email_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Emails associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_language` (
    `member_language_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
    `member_language_code` VARCHAR(50) NOT NULL COMMENT 'Unique code created for the language',
    `member_sk` VARCHAR(36) NULL COMMENT 'Foreign key to the member table',
    `language_type_code` VARCHAR(20) NULL COMMENT 'The type of language (like Written and Spoken)',
    `language_code` VARCHAR(30) NULL COMMENT 'The language (i.e. English, Spanish etc)',
    `ztcn` VARCHAR(50) NOT NULL COMMENT 'The transaction control number that created the language',
    `source` VARCHAR(50) NULL COMMENT 'The source of the data',
    `start_date` DATETIME NOT NULL COMMENT 'Date when the language was effective ',
    `end_date` DATETIME NULL,
    `created_date` DATETIME NULL COMMENT 'Date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`member_language_sk`),
    INDEX `member_lang_fk_idx` (`member_sk` ASC) VISIBLE,
    UNIQUE INDEX `member_language_code_UNIQUE` (`member_language_code` ASC) VISIBLE,
    CONSTRAINT `member_lang_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Languages associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`member_phone` (
    `member_phone_sk` VARCHAR(36) NOT NULL COMMENT 'Primary key of the table',
    `member_phone_code` VARCHAR(50) NOT NULL COMMENT 'Unique member phone code created for the phone',
    `member_sk` VARCHAR(36) NULL COMMENT 'Foreign key to the member table',
    `phone_type_code` VARCHAR(20) NOT NULL COMMENT 'Type of phone number',
    `phone_number` VARCHAR(30) NOT NULL COMMENT 'The phone number of the member',
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the phone number',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATETIME NOT NULL COMMENT 'Date when the phone was effective',
    `end_date` DATETIME NULL,
    `created_date` DATETIME NULL COMMENT 'Date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
    PRIMARY KEY (`member_phone_sk`),
    INDEX `member_phone_fk_idx` (`member_sk` ASC) VISIBLE,
    UNIQUE INDEX `member_phone_code_UNIQUE` (`member_phone_code` ASC) VISIBLE,
    CONSTRAINT `member_phone_fk`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'Phone numbers associated with the member';
CREATE TABLE IF NOT EXISTS `membermgmtdb`.`alternate_contact` (
    `alternate_contact_sk` VARCHAR(36) NOT NULL COMMENT 'The primary key of the table',
    `member_sk` VARCHAR(36) NOT NULL COMMENT 'The member to whom the alternate contact is associated',
    `alternate_contact_code` VARCHAR(50) NOT NULL COMMENT 'The unique code of the alternate contact',
    `alternate_contact_type_code` VARCHAR(50) NOT NULL COMMENT 'The type of alternate contact',
    `first_name` VARCHAR(100) NULL COMMENT 'The first name of the alternate contact',
    `middle_name` VARCHAR(50) NULL COMMENT 'The middle name of the alternate contact',
    `last_name` VARCHAR(100) NOT NULL COMMENT 'The last name of the alternate contact',
    `identifier_type_code` VARCHAR(50) NULL COMMENT 'The identifier type of the identifier for the alternate contact',
    `identifier_value` VARCHAR(100) NULL COMMENT 'The identifier alternate contact',
    `phone_type_code` VARCHAR(50) NULL COMMENT 'The type of phone number of the alternate contact',
    `phone_number` VARCHAR(50) NULL COMMENT 'The phone number of the alternate contact',
    `email` VARCHAR(100) NULL COMMENT 'The email of the alternate contact',
    `address_line_1` VARCHAR(100) NULL COMMENT 'The address line 1 of the alternate contact address',
    `address_line_2` VARCHAR(50) NULL COMMENT 'The address line 2 of the alternate contact address',
    `city` VARCHAR(50) NULL COMMENT 'The city of the alternate contact address',
    `state_type_code` VARCHAR(50) NULL COMMENT 'The state of the alternate contact address',
    `zip_code` VARCHAR(50) NULL COMMENT 'The zip code of the alternate contact address',
    `ztcn` VARCHAR(50) NULL COMMENT 'The transaction control number that created the alternate contact',
    `source` VARCHAR(50) NOT NULL COMMENT 'The source of the data',
    `start_date` DATE NULL COMMENT 'The start date of the alternate contact',
    `end_date` DATE NULL COMMENT 'The end date of the alternate contact',
    `created_date` DATETIME NULL COMMENT 'The date when the record was created',
    `updated_date` DATETIME NULL COMMENT 'The date when the record was updated',
    PRIMARY KEY (`alternate_contact_sk`),
    INDEX `fk_alternate_contact_member1_idx` (`member_sk` ASC) VISIBLE,
    CONSTRAINT `fk_alternate_contact_member1`
    FOREIGN KEY (`member_sk`)
    REFERENCES `membermgmtdb`.`member` (`member_sk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
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
                                                                       `response_payload_id` VARCHAR(45) NOT NULL,
                                                                       `payload_direction_type_code` VARCHAR(45) NOT NULL COMMENT 'Identifies the direction of the payload INBOUND or OUTBOUND',
                                                                       `src_dest` VARCHAR(100) NOT NULL COMMENT 'Identifies the source of the  payload if direction is inbound and destination if the direction is outbound',
                                                                       `created_date` DATETIME NULL COMMENT 'Date when the record was created',
                                                                       `updated_date` DATETIME NULL COMMENT 'Date when the record was updated',
                                                                       PRIMARY KEY (`payload_tracker_detail_sk`),
                                                                       INDEX `payload_tracker_fk_idx` (`payload_tracker_sk` ASC) VISIBLE,
                                                                       CONSTRAINT `payload_tracker_fk`
                                                                           FOREIGN KEY (`payload_tracker_sk`)
                                                                               REFERENCES `membermgmtdb`.`payload_tracker` (`payload_tracker_sk`)
                                                                               ON DELETE NO ACTION
                                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The payload tracker detail table, that tracks all the responses received for an outbound payload and all the responses sent for an inbound payload';