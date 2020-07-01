CREATE TABLE IF NOT EXISTS NOTES
(
    note_id          INT PRIMARY KEY auto_increment,
    note_title       VARCHAR(20),
    note_description VARCHAR(1000),
    user_id          INT,
    foreign key (user_id) references USERS (user_id)
);
