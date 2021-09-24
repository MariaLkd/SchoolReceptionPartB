USE school;
INSERT INTO students
VALUES
(1, 'First Name Student1','Last Name Student1','1995-03-10',2500),
(2, 'First Name Student2','Last Name Student2','1993-06-14',2250),
(3, 'First Name Student3','Last Name Student3','1982-11-23',1850),
(4, 'First Name Student4','Last Name Student4','2000-10-15',2000),
(5, 'First Name Student5','Last Name Student5','2000-09-08',2500);

INSERT INTO trainers
VALUES
(1,'First Name Trainer1', 'Last Name Trainer1', 'Subject'),
(2,'First Name Trainer2', 'Last Name Trainer2', 'Subject'),
(3,'First Name Trainer3', 'Last Name Trainer3', 'Subject');

INSERT INTO courses
VALUES
(1, 'CB13', 'Java', 'Part Time','2021-02-15','2021-09-15'),
(2, 'CB12', 'C#','Full Time','2021-03-15','2021-10-15');

INSERT INTO assignments (assignmentId, title, description, subDateTime, courseId)
VALUES
(1,'Assignment 1','description text','2021-04-15',1),
(2,'Assignment 2','description text','2021-06-15',1),
(3,'Assignment 3','description text','2021-05-23',2);

INSERT INTO courses_have_trainers
VALUES
(1,1),(1,2),(2,3);

INSERT INTO courses_have_students
VALUES
(1,2),(1,3),(1,4),
(2,1),(2,2),(2,5);