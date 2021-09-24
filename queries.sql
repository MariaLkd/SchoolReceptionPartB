-- A list of all the students
SELECT * FROM students ORDER BY lastName asc;

-- A list of all the trainers
SELECT * FROM trainers ORDER BY lastName asc;

-- A list of all the assignments
SELECT * from assignments ORDER BY courseId, title;

-- A list of all the courses 
SELECT * from courses  order by title, stream, type asc;

-- All the students per course
SELECT * FROM students
JOIN courses_have_students as chs
ON chs.studentId = Students.studentId
WHERE chs.courseId=1;

-- All the trainers per course
SELECT * FROM trainers
JOIN courses_have_trainers as cht
ON cht.trainerId = Trainers.trainerId
WHERE cht.courseId=1;

-- All the assignments per course
SELECT * FROM assignments
WHERE assignments.courseId = 1;

-- All the assignments per course per student
SELECT * FROM assignments 
JOIN courses_have_students as chs 
ON chs.courseId = assignments.courseId 
WHERE chs.studentId = 2
ORDER BY assignments.courseId,assignments.title; 

-- A list of students that belong to more than one courses
SELECT * FROM students 
JOIN courses_have_students as chs 
ON chs.studentId = Students.studentId 
GROUP BY chs.studentId 
HAVING count(chs.studentId)>1;

-- Student with max id 
SELECT * FROM students 
WHERE studentid = (SELECT max(studentid) FROM students);

-- Trainer with max id
SELECT * FROM trainers 
WHERE trainerid = (SELECT max(trainerid) FROM trainers);

