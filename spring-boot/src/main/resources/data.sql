DROP TABLE IF EXISTS users_tbl;

CREATE TABLE users_tbl (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    token VARCHAR(200) NOT NULL
);

INSERT INTO users_tbl (username, password, token) VALUES ('account1', 'password', 'token');
INSERT INTO users_tbl (username, password, token) VALUES ('account2', 'password', 'token');