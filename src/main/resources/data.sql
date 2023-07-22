INSERT INTO questions (content, correct_answers, exam_times) VALUES ('______ the better team, we lost the match.','C', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('Would you mind ______ the window?','A', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('Is Jo ______ Chris?','C', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('"Where\'s the ______ post office, please?"','D', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('If only I ______ richer.','B', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('The tree ______ by lightning.','C', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('He\'s interested ______ learning Spanish','C', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('They have put speed bumps on the road to ______ accidents.','C', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('You should ______ your homework','B', 0);
INSERT INTO questions (content, correct_answers, exam_times) VALUES ('She looks ______ she\'s going to be sick.','A', 0);

INSERT INTO answers (question_id, answer_key, content) VALUES (1,'A','Despite of being');
INSERT INTO answers (question_id, answer_key, content) VALUES (2,'A','closing');
INSERT INTO answers (question_id, answer_key, content) VALUES (3,'A','taller that');
INSERT INTO answers (question_id, answer_key, content) VALUES (4,'A','most near');
INSERT INTO answers (question_id, answer_key, content) VALUES (5,'A','am');
INSERT INTO answers (question_id, answer_key, content) VALUES (6,'A','was flashed');
INSERT INTO answers (question_id, answer_key, content) VALUES (7,'A','on');
INSERT INTO answers (question_id, answer_key, content) VALUES (8,'A','avoid');
INSERT INTO answers (question_id, answer_key, content) VALUES (9,'A','make');
INSERT INTO answers (question_id, answer_key, content) VALUES (10,'A','as if');
INSERT INTO answers (question_id, answer_key, content) VALUES (1,'B','Despite');
INSERT INTO answers (question_id, answer_key, content) VALUES (2,'B','close');
INSERT INTO answers (question_id, answer_key, content) VALUES (3,'B','taller');
INSERT INTO answers (question_id, answer_key, content) VALUES (4,'B','near');
INSERT INTO answers (question_id, answer_key, content) VALUES (5,'B','were');
INSERT INTO answers (question_id, answer_key, content) VALUES (6,'B','struck');
INSERT INTO answers (question_id, answer_key, content) VALUES (7,'B','to');
INSERT INTO answers (question_id, answer_key, content) VALUES (8,'B','prohibit');
INSERT INTO answers (question_id, answer_key, content) VALUES (9,'B','do');
INSERT INTO answers (question_id, answer_key, content) VALUES (10,'B','as');
INSERT INTO answers (question_id, answer_key, content) VALUES (1,'C','Despite being');
INSERT INTO answers (question_id, answer_key, content) VALUES (2,'C','to close');
INSERT INTO answers (question_id, answer_key, content) VALUES (3,'C','as tall as');
INSERT INTO answers (question_id, answer_key, content) VALUES (4,'C','more near');
INSERT INTO answers (question_id, answer_key, content) VALUES (5,'C','would be');
INSERT INTO answers (question_id, answer_key, content) VALUES (6,'C','was struck');
INSERT INTO answers (question_id, answer_key, content) VALUES (7,'C','in');
INSERT INTO answers (question_id, answer_key, content) VALUES (8,'C','prohibit');
INSERT INTO answers (question_id, answer_key, content) VALUES (9,'C','work');
INSERT INTO answers (question_id, answer_key, content) VALUES (10,'C','likes');
INSERT INTO answers (question_id, answer_key, content) VALUES (1,'D','Although');
INSERT INTO answers (question_id, answer_key, content) VALUES (2,'D','closed');
INSERT INTO answers (question_id, answer_key, content) VALUES (3,'D','more tall');
INSERT INTO answers (question_id, answer_key, content) VALUES (4,'D','nearest');
INSERT INTO answers (question_id, answer_key, content) VALUES (5,'D','will be');
INSERT INTO answers (question_id, answer_key, content) VALUES (6,'D','flashed');
INSERT INTO answers (question_id, answer_key, content) VALUES (7,'D','for');
INSERT INTO answers (question_id, answer_key, content) VALUES (8,'D','forbid');
INSERT INTO answers (question_id, answer_key, content) VALUES (9,'D','give');
INSERT INTO answers (question_id, answer_key, content) VALUES (10,'D','if');

INSERT INTO system_users (username, password, first_name, last_name, authority) VALUES ('student1','$2a$12$m/8juUH6JdCLSWAXEDIyKOH24zJRS03r7Lb2TZCJdVTHQebx65teW','An','Nguyen Van','STUDENT');
INSERT INTO system_users (username, password, first_name, last_name, authority) VALUES ('student2','$2a$12$m/8juUH6JdCLSWAXEDIyKOH24zJRS03r7Lb2TZCJdVTHQebx65teW','Binh','Nguyen Van','STUDENT');
INSERT INTO system_users (username, password, first_name, last_name, authority) VALUES ('teacher1','$2a$12$m/8juUH6JdCLSWAXEDIyKOH24zJRS03r7Lb2TZCJdVTHQebx65teW','Hoa','Nguyen Thi','TEACHER');
INSERT INTO system_users (username, password, first_name, last_name, authority) VALUES ('teacher2','$2a$12$m/8juUH6JdCLSWAXEDIyKOH24zJRS03r7Lb2TZCJdVTHQebx65teW','Van','Nguyen Thi','TEACHER');

INSERT INTO classes (name, create_by, school_year) VALUES ('10A5','teacher1', 2023);

INSERT INTO student_class_relation (class_id, student_id) VALUES (1,'student1');
INSERT INTO student_class_relation (class_id, student_id) VALUES (1,'student2');

INSERT INTO exams (name, exam_times, open_time, close_time, max_duration, max_retry, class_id, create_by, status) VALUES ('ĐỀ THI CUỐI HỌC KÌ 2 2023',0,'2023-07-01 17:51:04.777','2023-07-30 17:51:04.777',2400000,5, 1,'teacher1','PUBLISHED');
INSERT INTO exams (name, exam_times, open_time, close_time, max_duration, max_retry, class_id, create_by, status) VALUES ('ĐỀ THI OLYMPIC 2023',0,'2023-08-01 00:00:00','2023-08-15 00:00:00',2400000,5, 1,'teacher1','PUBLISHED');
INSERT INTO exams (name, exam_times, open_time, close_time, max_duration, max_retry, class_id, create_by, status) VALUES ('ĐỀ THI ĐẦU NĂM HỌC 2024',0,'2023-07-01 00:00:00','2023-07-30 00:00:00',2400000,5, 1,'teacher1','UNPUBLISHED');

INSERT INTO exam_question_relation (exam_id, question_id) VALUES (1, 1);
INSERT INTO exam_question_relation (exam_id, question_id) VALUES (1, 2);
INSERT INTO exam_question_relation (exam_id, question_id) VALUES (1, 3);
INSERT INTO exam_question_relation (exam_id, question_id) VALUES (1, 4);
INSERT INTO exam_question_relation (exam_id, question_id) VALUES (1, 5);

INSERT INTO mapping_rules (rule) VALUES ('ABCD,ABCD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BACD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CABD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,ACBD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BCAD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CBAD');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CBDA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BCDA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DCBA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CDBA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BDCA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DBCA');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DACB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,ADCB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CDAB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DCAB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,ACDB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,CADB');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BADC');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,ABDC');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DBAC');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,BDAC');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,ADBC');
INSERT INTO mapping_rules (rule) VALUES ('ABCD,DABC');





