CREATE TABLE IF NOT EXISTS subjects
(
    code         VARCHAR(20) PRIMARY KEY,
    subject_name VARCHAR(200) NOT NULL,
    gpa DECIMAL(3,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS students
(
    id         VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS grades
(
    grade      CHAR(1) PRIMARY KEY,
    upper_mark DECIMAL(3, 0) NOT NULL,
    lower_mark DECIMAL(3, 0) NOT NULL
);