CREATE TABLE IF NOT EXISTS subject(
    code         VARCHAR(20) PRIMARY KEY,
    subject_name VARCHAR(200)  NOT NULL,
    gpa          DECIMAL(3, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS grade(
    grade_letter VARCHAR(5) PRIMARY KEY,
    upper_mark   INT           NOT NULL,
    lower_mark   INT           NOT NULL,
    points       DECIMAL(3, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS student(
    id         VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS subject_enroll(
    student_id   VARCHAR(20),
    subject_code VARCHAR(20),
    CONSTRAINT pk_student_subject PRIMARY KEY (student_id, subject_code),
    CONSTRAINT student_fk FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    CONSTRAINT subject_fk FOREIGN KEY (subject_code) REFERENCES subject (code) ON DELETE CASCADE
);



