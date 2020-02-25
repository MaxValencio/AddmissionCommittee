DROP SCHEMA IF EXISTS  acdb;

CREATE DATABASE acdb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE acdb;

CREATE TABLE languages(
	language_id			BIGINT							AUTO_INCREMENT PRIMARY KEY,
    label				VARCHAR(3)						UNIQUE NOT NULL
);

CREATE TABLE accounts(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    email				VARCHAR(320)					UNIQUE NOT NUll,
    password_hash		VARCHAR(79)						NOT NULL,
	role				ENUM('ADMIN', 'ENTRANT')		NOT NULL DEFAULT 'ENTRANT',
    activation_status	ENUM('ACTIVETED', 'INACTIVE')	NOT NULL DEFAULT 'INACTIVE', 
    registration_date 	TIMESTAMP						NOT NULL DEFAULT NOW(),
    language_id			BIGINT							NOT NULL DEFAULT 1,
    FOREIGN KEY (language_id) REFERENCES languages(language_id)
);

CREATE TABLE accounts_last_actions_dates(
    account_id			BIGINT							PRIMARY KEY,
    last_action_date	TIMESTAMP						NOT NUll,
    FOREIGN KEY(account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE profiles(
	account_id			BIGINT							PRIMARY KEY,
	block_status		ENUM('BLOCKED', 'UNBLOCKED')	NOT NULL DEFAULT 'UNBLOCKED',
    first_name			VARCHAR(20)						NOT NULL,
    last_name			VARCHAR(50)						NOT NULL,
    city				VARCHAR(50)						NOT NULL,
    region				VARCHAR(50)						NOT NULL,
    scholl				VARCHAR(100)					NOT NULL,
    FOREIGN KEY(account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE faculties(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    total_seats			INT								NOT NULL,
    budget_seats		INT								NOT NULL
);

CREATE TABLE subjects(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY
);


CREATE TABLE faculties_titles(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
	faculty_id			BIGINT							NOT NULL,
	language_id			BIGINT							NOT NULL,
    title				VARCHAR(250)					NOT NULL,
    FOREIGN KEY(faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY(language_id) REFERENCES languages(language_id) ON DELETE CASCADE
);

CREATE TABLE subjects_titles(
	subject_id			BIGINT							NOT NULL,
	language_id			BIGINT							NOT NULL,
    title				VARCHAR(250)					NOT NULL,
    PRIMARY KEY(subject_id, language_id),
    FOREIGN KEY(subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    FOREIGN KEY(language_id) REFERENCES languages(language_id) ON DELETE CASCADE
);

CREATE TABLE faculties_descriptions(
	faculty_id			BIGINT							NOT NULL,
	language_id			BIGINT							NOT NULL,
    description			VARCHAR(1000)					NOT NULL,
    PRIMARY KEY(faculty_id, language_id),
    FOREIGN KEY(faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY(language_id) REFERENCES languages(language_id) ON DELETE CASCADE
);

CREATE TABLE faculties_subjects(
	faculty_id			BIGINT							NOT NULL,
    subject_id			BIGINT							NOT NULL,
    PRIMARY KEY (faculty_id, subject_id),
    FOREIGN KEY (faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

CREATE TABLE profiles_certificates_images_names(
	profile_id			BIGINT							PRIMARY KEY,
    image_name			VARCHAR(250)					UNIQUE NOT NULL,
    FOREIGN KEY(profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE
);

CREATE TABLE profiles_certificates_subjects_ratings(
	profile_id			BIGINT							PRIMARY KEY,
    subject_id			BIGINT							NOT NULL,
    rating				INT								NOT NULL,
    FOREIGN KEY(profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE,
    FOREIGN KEY(subject_id)	REFERENCES subjects(id) ON DELETE CASCADE
);

CREATE TABLE profiles_faculties(
	profile_id			BIGINT							NOT NULL,
	faculty_id			BIGINT							NOT NULL,
    PRIMARY KEY(faculty_id, profile_id),
    FOREIGN KEY(faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY(profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE
);

CREATE TABLE profiles_exam_subject_ratings(
	profile_id			BIGINT							PRIMARY KEY,
    exam_subject_id		BIGINT							NOT NULL,
    rating				INT								NOT NULL,
    FOREIGN KEY(profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE,
    FOREIGN KEY(exam_subject_id)	REFERENCES subjects(id) ON DELETE CASCADE
);

CREATE TABLE profiles_averag_ratings(
	profile_id			BIGINT							PRIMARY KEY,
    averag_rating		DECIMAL(3,3)					NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE
);

CREATE TABLE profiles_faculties_averag_ratings(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
	profile_id			BIGINT							NOT NULL,
    averag_rating		DECIMAL(3,3)					NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(account_id) ON DELETE CASCADE
);

CREATE TABLE results(
	id					BIGINT									AUTO_INCREMENT PRIMARY KEY,
    profile_id			BIGINT									NOT NULL,
    faculty_id			BIGINT									NOT NULL,
    result_status		ENUM('BUDGET', 'CONTRACT', 'FAILED')    NOT NULL
);

DELIMITER $$
CREATE DEFINER=`MaxValencio`@`%` PROCEDURE `insert_and_select_created_account`(email VARCHAR(320), password_hash VARCHAR(79), language_id BIGINT)
BEGIN
INSERT INTO accounts(email, password_hash, language_id) VALUES ( email, password_hash, language_id);
SELECT 
    accounts.id,
    accounts.email,
    accounts.password_hash,
    accounts.role,
    accounts.activation_status,
    accounts.registration_date,
    accounts.language_id,
    languages.label
FROM
    accounts
        INNER JOIN
    languages ON accounts.language_id = languages.language_id
WHERE
    accounts.email = email LIMIT 1;
END$$

DELIMITER $$
CREATE DEFINER=`MaxValencio`@`%` PROCEDURE `update_and_select_updated_account`(in_email VARCHAR(320), in_password_hash VARCHAR(79), in_activation_status ENUM('ACTIVETED','INACTIVE'), in_language_id BIGINT, in_id BIGINT)
BEGIN
UPDATE  accounts SET email = in_email, password_hash = in_password_hash, activation_status = in_activation_status, language_id = in_language_id WHERE accounts.id = in_id;
SELECT 
    accounts.id,
    accounts.email,
    accounts.password_hash,
    accounts.role,
    accounts.activation_status,
    accounts.registration_date,
    accounts.language_id,
    languages.label
FROM
    accounts
        INNER JOIN
    languages ON accounts.language_id = languages.language_id
WHERE
    accounts.id = in_id LIMIT 1;
END$$

DELIMITER $$
CREATE DEFINER=`MaxValencio`@`%` PROCEDURE `select_faculty`(in_id BIGINT)
BEGIN
SELECT 
    faculties.id AS faculty_id,
    faculties.total_seats AS total_seats,
    faculties.budget_seats AS budget_seats,
    faculties_titles.language_id AS language_id,
    languages.label AS language_label,
    faculties_titles.title AS title,
    faculties_descriptions.description AS description
    
FROM
    faculties
		 INNER JOIN faculties_titles 
         INNER JOIN faculties_descriptions ON faculties_descriptions.faculty_id = faculties.id AND faculties_titles.faculty_id = faculties_descriptions.faculty_id
         INNER JOIN languages ON faculties_titles.language_id = languages.language_id AND faculties_titles.language_id = faculties_descriptions.language_id
WHERE
    faculties.id = in_id ;
END$$

DELIMITER $$
CREATE DEFINER=`MaxValencio`@`%` PROCEDURE `select_all_accounts`()
BEGIN
SELECT 
    accounts.id,
    accounts.email,
    accounts.password_hash,
    accounts.role,
    accounts.activation_status,
    accounts.registration_date,
    accounts.language_id,
    languages.label
FROM
    accounts
        INNER JOIN
    languages ON accounts.language_id = languages.language_id;
END$$

DELIMITER $$
CREATE DEFINER=`MaxValencio`@`%` PROCEDURE `select_all_faculties`()
BEGIN
SELECT 
    faculties.id AS faculty_id,
    faculties.total_seats AS total_seats,
    faculties.budget_seats AS budget_seats,
    faculties_titles.language_id AS language_id,
    languages.label AS language_label,
    faculties_titles.title AS title,
    faculties_descriptions.description AS description
    
FROM
    faculties
		 INNER JOIN faculties_titles 
         INNER JOIN faculties_descriptions ON faculties_descriptions.faculty_id = faculties.id AND faculties_titles.faculty_id = faculties_descriptions.faculty_id
         INNER JOIN languages ON faculties_titles.language_id = languages.language_id AND faculties_titles.language_id = faculties_descriptions.language_id;
END$$

INSERT INTO languages(label) VALUE('EN');
INSERT INTO languages(label) VALUE('RU');
INSERT INTO accounts(email, password_hash, role, activation_status, language_id) VALUE ('email@gmail.com', '12345678', 'ADMIN', 'ACTIVETED', 1);




