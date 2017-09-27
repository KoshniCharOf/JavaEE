/*9. Write a SQL query to find the names of all employees whose last name contains the character sequence "ei". Use LIKE. */

SELECT * 
FROM hr.employees WHERE last_name LIKE '%ie%';
