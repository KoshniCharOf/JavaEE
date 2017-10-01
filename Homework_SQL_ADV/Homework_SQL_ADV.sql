/*1. Write a SQL query to find the average salary in the "Sales" department. Use AVG()*/


SELECT AVG(SALARY) AS Salary

FROM employees AS E

JOIN departments D

ON E.department_id = (SELECT D.department_id FROM departments D WHERE D.department_name in ('Sales'));

/*2. Write a SQL query to find the number of employees in
the "Sales" department. Use COUNT(*).*/

SELECT COUNT(E.employee_id) AS Count

FROM employees AS E

JOIN departments D

ON E.department_id = (SELECT D.department_id FROM departments D WHERE D.department_name = 'Sales');

/*3. Write a SQL query to find the number of all locations
where the company has an office.*/

SELECT COUNT(DISTINCT D.location_id) AS Count

FROM departments AS D;

/*4. Write a SQL query to find the number of all
departments that has manager.*/

SELECT COUNT(DISTINCT D.manager_id) AS Count

FROM departments AS D;

/*5. Write a SQL query to find the number of all
departments that has no manager.*/

SELECT 
(SELECT COUNT(*) FROM departments) - 

(SELECT COUNT(departments.manager_id) FROM departments WHERE departments.manager_id>=0) AS No_Manager_count;

/*6. Write a SQL query to find all departments' names and
the average salary for each of them.*/

SELECT AVG(salary) as salary, d.department_name
FROM employees e 
JOIN departments d
ON e.department_id = d.department_id
GROUP BY d.department_name;

/*7. Write a SQL query to find the count of all employees
in each department. Display the name, 
location and
number of employees for each department.
*/

SELECT count(e.employee_id) AS count, d.department_name, l.city
FROM employees e 
JOIN departments d 
ON e.department_id = d.department_id
JOIN locations l 
ON d.location_id = l.location_id
GROUP BY d.location_id;

/*8. Write a SQL query to find for each department and
for each manager the count of all corresponding
employees.
*/


SELECT count(e.employee_id) AS count, d.department_name, m.last_name
FROM employees e 
JOIN departments d 
ON e.department_id = d.department_id
JOIN employees  m 
ON d.manager_id = m.employee_id
GROUP BY d.manager_id;

/*9. Write a SQL query to find all managers that have
exactly 5 employees. Display their names and the
name and location of their department.
*/

SELECT COUNT(e.employee_id) AS employees, CONCAT(m.last_name,' ',m.first_name) AS manager, d.department_name, l.city
FROM employees e 
JOIN employees m
ON e.manager_id = m.employee_id
JOIN departments d 
ON d.manager_id = e.manager_id
JOIN locations l 
ON d.location_id = l.location_id
GROUP BY d.manager_id 
HAVING COUNT(e.employee_id) = 5;

/*10. Write a SQL query to find the total number of
employees for each region.
*/

SELECT COUNT(e.employee_id) AS employees, r.region_name
FROM employees e 
JOIN departments d 
ON e.department_id = d.department_id
JOIN locations l 
ON d.location_id = l.location_id
JOIN countries c 
ON l.country_id = c.country_id
JOIN regions r
ON c.region_id = r.region_id
GROUP BY r.region_id;

/*11. Write a SQL query to find for each department and
for each job title the total number of employees.
*/

SELECT d.department_name, j.job_title, COUNT(e.employee_id) AS employees
FROM employees e 
JOIN departments d 
ON e.department_id = d.department_id
JOIN jobs j
ON e.job_id = j.job_id
GROUP BY d.department_name, j.job_title;

/*12. Write a SQL query to find the names and salaries of
the employees that take the minimal salary in the
company. Use nested SELECT statement.

*/

SELECT concat(e.first_name, ' ', e.last_name) as Poor, e.salary
FROM employees e
WHERE e.salary = (select min(e.salary) from employees e);

/*13. Write a SQL query to find the names and salaries of
the employees that get a salary that is up to 10%
higher than the minimal salary for the company.

*/

SELECT concat(e.first_name, ' ', e.last_name) as above_bottom, e.salary
FROM employees e
WHERE e.salary < (select min(e.salary) from employees e) * 1.1;

/*14. Write a SQL that displays all departments and the
highest salary for each department along with the
name of the employee that takes it. If multiple
employees in the same department have highest
salary, display the first of them.

*/

SELECT  d.department_name, (concat(e.first_name,' ', e.last_name)) as employee,  max(e.salary) as salary
FROM departments d
JOIN employees e
ON e.department_id = d.department_id
GROUP BY d.department_name;

/*15. Write a SQL query to find the names of all employees
whose last name is exactly 5 characters long.

*/
SELECT e.first_name, e.last_name
FROM employees e
WHERE length(e.last_name) = 5;

/*16. Write a SQL query to find the names of all employees
whose first name and last name start with the same
letter. 

*/

SELECT e.first_name, e.last_name
FROM employees e
WHERE substring(e.first_name,1,1) = substring(e.last_name,1,1);

/*17. Display all departments names and their manager's
name. For departments without manager display "(No
manager)".

*/
SELECT d.department_name, COALESCE(concat(e.first_name,' ', e.last_name), "(No manager)") as manager
FROM departments d
LEFT OUTER JOIN employees e
ON d.manager_id = e.employee_id;

/*18. Display all employees along with their number of
directly managed people. For employees not
managing anybody display "Just and employee". For
employees managing only 1 employee display
"Junior manager". 

*/

SELECT e.first_name, e.last_name, if(count(e1.employee_id) < 2, 'junior', 'senior') as position, count(e1.employee_id)as workers
FROM employees e 
LEFT OUTER JOIN employees e1
ON e1.manager_id = e.employee_id
GROUP BY e1.manager_id;

/*19. Write a SQL query to print the current date and time
in the format " hour:minutes:seconds day-monthyear".
Display also the date coming after a week. 

*/

SELECT concat(hour(now()),':',minute(now()),':',second(now()),' ',day(now()),'-',month(now()),' ',year(now())) as 'current time',
date_add(current_date(),interval 1 week) as 'after Week';

/*20. Write a SQL statement to create a table USERS. Users
should have username, password, full name and last
login time. Choose appropriate data types for the
fields of the table. Define a primary key column with
a primary key constraint. ?Define a trigger to
automatically fill the full name column value before
inserting a record.

*/

CREATE TABLE USERS ( 
username NVARCHAR(30) NOT NULL,
password NVARCHAR(30) NOT NULL,
fullname NVARCHAR(60) NOT NULL,
login_time DATETIME,
CONSTRAINT USER_PK PRIMARY KEY(USERNAME)
);

/*21. Write a SQL statement to create a view that displays
the users from the USERS table that have been in the
system today. Test if the view works correctly.

*/

/*21. Write a SQL statement to create a view that displays
the users from the USERS table that have been in the
system today. Test if the view works correctly.

*/
CREATE VIEW LOGINS_TODAY AS 
SELECT * 
FROM users AS U
WHERE U.login_time >= date_sub(now(), interval 1 day);

insert into users (username, password, fullname, login_time)
values ('dodo', 'boko', 'kokov', date_sub(now(), interval 2 day));

/*22. Write a SQL statement to create a table GROUPS.
Groups should have unique name (use unique
constraint). Define primary key and a ?trigger for
populating it.

*/
CREATE TABLE GROUPS (
  group_id INT NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (group_id),
  UNIQUE INDEX idGroup_UNIQUE (group_id),
  UNIQUE INDEX group_name_UNIQUE (group_name));
  
  /*23. Write a SQL statement to add a column GROUP_ID to
the table USERS. Fill some data in this new column
and as well in the GROUPS table. Write a SQL
statement to add a foreign key constraint between
tables USERS and GROUPS.


*/
ALTER TABLE `hr`.`users` 
ADD COLUMN `group_id` INT NULL AFTER `login_time`;

INSERT INTO groups (group_name)  VALUES ('ludi');

INSERT INTO users
(`username`,`password`,`fullname`,`login_time`,`group_id`) 
VALUES ('koki','moki','doki',now(),1);

ALTER TABLE `hr`.`users` 
ADD CONSTRAINT `group_id`
  FOREIGN KEY (`group_id`)
  REFERENCES `hr`.`groups` (`group_id`);
  
  /*24. Write SQL statements to insert several records in the
USERS and GROUPS tables.
*/

INSERT INTO groups (group_name)  VALUES ('divi');
INSERT INTO groups (group_name)  VALUES ('javari');

INSERT INTO users
(`username`,`password`,`fullname`,`login_time`,`group_id`) 
VALUES ('gori','dori','fori',now(),2);

INSERT INTO users
(`username`,`password`,`fullname`,`login_time`,`group_id`) 
VALUES ('porko','morko','dorko',now(),3);

INSERT INTO users
(`username`,`password`,`fullname`,`login_time`,`group_id`) 
VALUES ('racho','pacho','nacho',now(),2);

/*25. Write SQL statements to insert in the USERS table the
names of all employees from the employees table.
Combine the first and last names as a full name. For
user name use the email column from employees.
Use blank password. ?why VALUES before select doasn't work why?
*/


INSERT INTO users
		(`username`,`password`,`fullname`,`login_time`,`group_id`) 
(SELECT e.email, '', concat(e.first_name,' ', e.last_name), now(), 2
FROM employees e);

/*26. Run the above 10 times to generate enough testing
data for the USERS table. 11 times :) for A+


27. Write a SQL statement that changes the password to
NULL for all USERS that have not been in the system
since 10.03.2006. Select table data to see the
changes. 

Error Code: 1175. You are using safe update mode and
you tried to update a table without a 
WHERE that uses a KEY column .

*/


UPDATE users AS u
SET password = null
WHERE u.login_time < '10.03.2006';

/*28. Write a SQL statement that deletes all users without
passwords (NULL or empty password). Select table
data to see the changes. 
*/


DELETE FROM users 
WHERE users.password = '' or users.password = null;

/*29. Write a SQL query to list all users whose username
starts with 's' and the number of groups for each of
them. 
*/


SELECT username, count(group_id) AS groups_num
FROM users 
WHERE username LIKE 's%'
GROUP BY users_id;

/*31. Define table WORKHOURS to store work reports for
each employee (date, task, hours, comments).
*/

CREATE TABLE workhours (
  task_id INT NOT NULL AUTO_INCREMENT,
  date DATETIME NULL,
  task VARCHAR(100) NULL,
  hours INT NULL,
  comments VARCHAR(100) NULL,
  PRIMARY KEY (task_id));
  
  /*32. Define foreign key between the tables WORKHOURS
and EMPLOYEE. Add additional column in the
employee table if needed. *generated* (and work)
*/

ALTER TABLE `hr`.`employees` 
ADD COLUMN `task_id` INT NULL AFTER `department_id`,
ADD INDEX `employees_task_id_idx` (`task_id` ASC);
ALTER TABLE `hr`.`employees` 
ADD CONSTRAINT `employees_task_id`
  FOREIGN KEY (`task_id`)
  REFERENCES `hr`.`workhours` (`task_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  /*33. Write several SQL statements to fill some data in the
WORKHOURS table.
*/

INSERT INTO workhours (date, task, hours , comments)
VALUES (now(), 'learn', 16, 'harder');

INSERT INTO workhours (date, task, hours , comments)
VALUES (now(), 'sleep', 4, 'hard');

INSERT INTO workhours (date, task, hours , comments)
VALUES (now(), 'Write several SQL statements', 4, '33 times');

/*34. Write a SQL query to find all the average work hours
per week for each country.

*/
UPDATE employees
SET task_id = 3
WHERE employee_id %2 = 1;

UPDATE employees
SET task_id = 1
WHERE employee_id %2 = 0;

SELECT AVG(W.hours) AS avg_workhours, C.country_name

FROM employees E
JOIN workhours W 
ON E.task_id = W.task_id
JOIN departments D
ON E.department_id = D.department_id
JOIN  locations L 
ON D.location_id = L.location_id
JOIN countries C 
ON C.country_id = L.country_id
GROUP BY c.country_id;

/*35. Write a SQL query to find all the departments where
some employee worked overtime (over 8 hours/day)
during the last week.
*/
SELECT D.department_name, count(E.employee_id)AS count_overtimers

FROM employees E
JOIN workhours W 
ON E.task_id = W.task_id
JOIN departments D
ON E.department_id = D.department_id
WHERE w.date > date_sub(now(), interval 1 week) and w.hours > 8
GROUP BY D.department_id;

/*36. Write a SQL query to find all employees that have
worked 3 or more days overtime in the last week.
Display their name, location department and country.
!Assume 40 hours per week -> more than 43 may be is 3 days overtime, 
but I know that is not the solution 
1. need more tasks for 1 employee
2. group by employees HAVING 3 tasks over 8h
//TODO find better solution, better structure
*/

SELECT E.first_name, E.last_name, W.hours, L.state_province, D.department_name, C.country_name
FROM employees E
JOIN workhours W 
ON E.task_id = W.task_id
JOIN departments D
ON E.department_id = D.department_id
JOIN  locations L 
ON D.location_id = L.location_id
JOIN countries C 
ON C.country_id = L.country_id
WHERE W.hours > 43;