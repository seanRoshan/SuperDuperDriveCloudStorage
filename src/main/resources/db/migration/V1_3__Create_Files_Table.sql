CREATE TABLE IF NOT EXISTS FILES
(
    file_Id INT PRIMARY KEY auto_increment,
    file_name VARCHAR,
    file_size VARCHAR,
    file_data BLOB,
    content_type VARCHAR,
    user_id INT,
    foreign key (user_id) references USERS(user_id)
);
