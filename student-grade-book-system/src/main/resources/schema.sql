CREATE TABLE IF NOT EXISTS subject
(
    code         VARCHAR(20) PRIMARY KEY,
    subject_name VARCHAR(200)  NOT NULL,
    gpa          DECIMAL(3, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS grade
(
    grade      VARCHAR(5) PRIMARY KEY,
    upper_mark INT NOT NULL,
    lower_mark INT NOT NULL
);

CREATE TABLE student
(
    id           VARCHAR(20) PRIMARY KEY,
    first_name   VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20),
    CONSTRAINT student_fk FOREIGN KEY (subject_code) REFERENCES subject (code) ON DELETE CASCADE
);

