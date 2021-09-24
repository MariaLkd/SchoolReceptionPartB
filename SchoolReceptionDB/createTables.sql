CREATE SCHEMA IF NOT EXISTS school;
USE school;

CREATE TABLE IF NOT EXISTS `courses` (
  `courseId` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `stream` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseId_UNIQUE` (`courseId`));

CREATE TABLE IF NOT EXISTS `assignments` (
  `assignmentId` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `subDateTime` date NOT NULL,
  `oralMark` int unsigned NOT NULL DEFAULT '0',
  `totalMark` int unsigned NOT NULL DEFAULT '0',
  `courseId` int unsigned NOT NULL,
  PRIMARY KEY (`assignmentId`),
  UNIQUE KEY `assignmentId_UNIQUE` (`assignmentId`),
  KEY `fk_Assignments_Courses1_idx` (`courseId`),
  CONSTRAINT `fk_Assignments_Courses1` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`));

CREATE TABLE IF NOT EXISTS `students` (
  `studentId` int unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `tuitionFees` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`studentId`),
  UNIQUE KEY `studentId_UNIQUE` (`studentId`));

CREATE TABLE IF NOT EXISTS `courses_have_students` (
  `courseId` int unsigned NOT NULL,
  `studentId` int unsigned NOT NULL,
  PRIMARY KEY (`courseId`,`studentId`),
  KEY `fk_studentId_students.studentId_idx` (`studentId`),
  CONSTRAINT `courseId` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`),
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `students` (`studentId`));
  
CREATE TABLE IF NOT EXISTS `trainers` (
  `trainerId` int unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `subject` varchar(20) NOT NULL,
  PRIMARY KEY (`trainerId`),
  UNIQUE KEY `trainerId_UNIQUE` (`trainerId`));
  
CREATE TABLE IF NOT EXISTS `courses_have_trainers` (
  `courseId` int unsigned NOT NULL,
  `trainerId` int unsigned NOT NULL,
  PRIMARY KEY (`courseId`,`trainerId`),
  KEY `fk_trainerId_trainers.trainerId_idx` (`trainerId`),
  CONSTRAINT `fk_courseId` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`),
  CONSTRAINT `fk_trainerId` FOREIGN KEY (`trainerId`) REFERENCES `trainers` (`trainerId`));


