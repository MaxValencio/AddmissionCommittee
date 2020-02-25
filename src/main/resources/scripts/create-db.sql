DROP SCHEMA IF EXISTS  acdb;

CREATE DATABASE acdb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE acdb;

CREATE TABLE languages(
	language_id			BIGINT								AUTO_INCREMENT PRIMARY KEY,
    lable				VARCHAR(3)						UNIQUE NOT NULL,
    default_status		ENUM('true', 'false')			NOT NULL DEFAULT 'false' 
);

CREATE TABLE users(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    email				VARCHAR(320)					UNIQUE NOT NUll,
    password_hash		VARCHAR(79)						NOT NULL,
	role				ENUM('ADMIN', 'ENTRANT')		NOT NULL DEFAULT 'ENTRANT',
    activation_status	ENUM('ACTIVETED', 'INACTIVE')	NOT NULL DEFAULT 'INACTIVE', 
    registration_date 	DATETIME						NOT NULL DEFAULT NOW()
);

CREATE TABLE users_languages(
	user_id				BIGINT							NOT NULL,
    language_id			BIGINT								NOT NULL,
    PRIMARY KEY(user_id, language_id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(language_id) REFERENCES languages(language_id) ON DELETE CASCADE
);

CREATE TABLE users_last_actions_dates(
    user_id				BIGINT							NOT NULL PRIMARY KEY,
    last_action_date	TIMESTAMP						NOT NUll,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE entrants(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    user_id				BIGINT							UNIQUE NOT NULL,
	block_status		ENUM('BLOCKED', 'UNBLOCKED')	NOT NULL DEFAULT 'UNBLOCKED',
    first_name			VARCHAR(20)						NOT NULL,
    last_name			VARCHAR(50)						NOT NULL,
    city				VARCHAR(50)						NOT NULL,
    region				VARCHAR(50)						NOT NULL,
    scholl				VARCHAR(100)					NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE certificates_urls(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    entrant_id			BIGINT							NOT NULL,
    certificate_url		VARCHAR(250)					UNIQUE NOT NULL,
    FOREIGN KEY(entrant_id) REFERENCES entrants(user_id) ON DELETE CASCADE
);

CREATE TABLE faculties(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    title_id			BIGINT							UNIQUE NOT NULL,
    description_id		BIGINT							NOT NULL,
    total_seats			INT								NOT NULL,
    budget_seats		INT								NOT NULL 		
);

CREATE TABLE subjects(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    title_id			BIGINT							UNIQUE NOT NULL
);

CREATE TABLE faculties_subjects(
	faculty_id			BIGINT							NOT NULL,
    subject_id			BIGINT							NOT NULL,
    PRIMARY KEY (faculty_id, subject_id),
    FOREIGN KEY (faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

CREATE TABLE entrants_faculties(
	entrant_id			BIGINT							NOT NULL,
	faculty_id			BIGINT							NOT NULL,
    PRIMARY KEY (faculty_id, entrant_id),
    FOREIGN KEY (faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,
    FOREIGN KEY (entrant_id) REFERENCES entrants(user_id) ON DELETE CASCADE
);

CREATE TABLE entratns_subjects_ratings(
    entrant_id			BIGINT							NOT NULL,
    subject_id			BIGINT							NOT NULL,
    rating_value		INT								NOT NULL,
    PRIMARY KEY (entrant_id, subject_id),
    FOREIGN KEY (entrant_id) REFERENCES entrants(user_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

CREATE TABLE titles(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    value				VARCHAR(250)					UNIQUE NOT NULL
);

CREATE TABLE descriptions(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    value				VARCHAR(1000)					UNIQUE NOT NULL
);

CREATE TABLE languages_titles(
	language_id			BIGINT								NOT NULL,
    title_id			BIGINT							NOT NULL,
    PRIMARY KEY (language_id, title_id),
    FOREIGN KEY (language_id) REFERENCES languages(language_id) ON DELETE CASCADE,
    FOREIGN KEY (title_id) REFERENCES titles(id) ON DELETE CASCADE
);

CREATE TABLE languages_descriptions(
	language_id			BIGINT								NOT NULL,
    description_id		BIGINT							NOT NULL,
	PRIMARY KEY (language_id, description_id),
    FOREIGN KEY (language_id) REFERENCES languages(language_id) ON DELETE CASCADE,
    FOREIGN KEY (description_id) REFERENCES descriptions(id) ON DELETE CASCADE
);

CREATE TABLE entratns_averag_ratings(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
	entrant_id			BIGINT							NOT NULL,
    averag_rating		DECIMAL(3,3)					NOT NULL,
    FOREIGN KEY (entrant_id) REFERENCES entrants(user_id) ON DELETE CASCADE
);

CREATE TABLE entratns_faculties_averag_ratings(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
	entrant_id			BIGINT							NOT NULL,
    averag_rating		DECIMAL(3,3)					NOT NULL,
    FOREIGN KEY (entrant_id) REFERENCES entrants(user_id) ON DELETE CASCADE
);

CREATE TABLE results(
	id					BIGINT									AUTO_INCREMENT PRIMARY KEY,
    entrant_id			BIGINT									NOT NULL,
    faculty_id			BIGINT									NOT NULL,
    result_status		ENUM('BUDGET', 'CONTRACT', 'FAILED')    NOT NULL
);

INSERT INTO languages(lable, default_status) VALUE('EN', 'true');
INSERT INTO users(email, password_hash) VALUE ('email@gmail.com', '12345678');
INSERT INTO users_languages(user_id, language_id) VALUE (1,1);




