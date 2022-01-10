DROP TABLE IF EXISTS users_tbl;

CREATE TABLE users_tbl (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users_tbl (username, password) VALUES ('account1', 'password');
INSERT INTO users_tbl (username, password) VALUES ('account2', 'password');