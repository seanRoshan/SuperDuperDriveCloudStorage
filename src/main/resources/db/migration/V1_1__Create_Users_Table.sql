CREATE TABLE IF NOT EXISTS USERS
(
    user_id    INT PRIMARY KEY auto_increment,
    user_name  VARCHAR(20),
    salt       VARCHAR,
    password   VARCHAR,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);
