


INSERT INTO lectors (name, degree, salary) VALUES ('John Doe', 0, 50000);
INSERT INTO lectors (name, degree, salary) VALUES ('Jane Smith', 1, 70000);
INSERT INTO lectors (name, degree, salary) VALUES ('Alice Johnson', 2, 90000);
INSERT INTO lectors (name, degree, salary) VALUES ('Bob Brown', 0, 55000);
INSERT INTO lectors (name, degree, salary) VALUES ('Charlie Black', 1, 95000);

INSERT INTO departments (head_id, name) VALUES (1, 'Computer Science');
INSERT INTO departments (head_id, name) VALUES (3, 'Mathematics');

INSERT INTO lectors_departments (lectors_id, departments_name) VALUES (1, 'Computer Science');
INSERT INTO lectors_departments (lectors_id, departments_name) VALUES (2, 'Computer Science');
INSERT INTO lectors_departments (lectors_id, departments_name) VALUES (3, 'Mathematics');
INSERT INTO lectors_departments (lectors_id, departments_name) VALUES (4, 'Mathematics');
INSERT INTO lectors_departments (lectors_id, departments_name) VALUES (5, 'Computer Science');