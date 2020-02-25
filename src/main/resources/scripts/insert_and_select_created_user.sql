CREATE PROCEDURE `insert_and_select_created_user`(email VARCHAR(320), password_hash VARCHAR(79), lable VARCHAR(3))
BEGIN
INSERT INTO users(email, password_hash, language_id) VALUES ( email, password_hash, (SELECT languages.language_id FROM languages WHERE languages.lable = lable));
SELECT users.id, users.email, users.password_hash, users.role, users.activation_status, users.registration_date, languages.lable FROM users INNER JOIN languages ON users.language_id = languages.language_id WHERE users.email = email ;
END