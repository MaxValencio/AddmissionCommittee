DROP DATABASE IF EXISTS  admission_committee_db;

CREATE DATABASE addmission_committee_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE addmission_committee_db;

CREATE TABLE languages(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    lable				VARCHAR(3)						NOT NULL
);

CREATE TABLE users(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    email				VARCHAR(320)					NOT NUll,
    password_hash		VARCHAR(79)						NOT NULL,
    role				ENUM('ADMIN', 'ENTRANT')		NOT NULL DEFAULT 'ENTRANT',
    block_status		ENUM('ACTIVETED', 'INACTIVE')	NOT NULL DEFAULT 'INACTIVE',
    registration_date   DATETIME						NOT NULL DEFAULT NOW(),
    last_visit_date   	DATETIME						NOT NULL DEFAULT NOW()
);

CREATE TABLE users_languages(
	user_id				BIGINT							REFERENCES users(id),
    language_id			BIGINT							REFERENCES languages(id),
    PRIMARY KEY(user_id, language_id),
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	FOREIGN KEY (language_id) REFERENCES languages(id) ON DELETE CASCADE
    );

CREATE TABLE profiles(
	
);

CREATE TABLE titles(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    value				VARCHAR(320)					NOT NULL
);

CREATE TABLE descriptions(
	id					BIGINT							AUTO_INCREMENT PRIMARY KEY,
    value				VARCHAR(1000)					NOT NULL
);




