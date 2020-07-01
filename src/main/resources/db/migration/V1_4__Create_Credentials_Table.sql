CREATE TABLE IF NOT EXISTS CREDENTIALS
(
    credential_id INT PRIMARY KEY auto_increment,
    url           VARCHAR(100),
    user_name     VARCHAR(30),
    key           VARCHAR,
    password      VARCHAR,
    user_id       INT,
    foreign key (user_id) references USERS (user_id)
);
