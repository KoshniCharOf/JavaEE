/*8. Write a SQL query to find the names of all employees whose first name starts with "Sa". Use LIKE.*/
 
SELECT * 
FROM hr.employees 
WHERE first_name LIKE 'sa%';


