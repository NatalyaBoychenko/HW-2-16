CREATE TABLE IF NOT EXISTS note (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(255),
    content VARCHAR(2000),
    access VARCHAR(20),
    date VARCHAR(20)

);