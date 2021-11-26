DROP TABLE IF EXISTS Patient;
CREATE TABLE Patient (
                id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(30) NOT NULL,
                last_name VARCHAR(30) NOT NULL,
                sex CHAR(1) NOT NULL,
                birthdate DATE NOT NULL,
                address VARCHAR(100) NOT NULL,
                phone_number CHAR(10),
                PRIMARY KEY (id)
);