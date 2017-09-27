/*14. Write a SQL query to find all employees that are paid more than 10 000. Order them in decreasing order by salary. Use ORDER BY.*/


SELECT * 
FROM hr.employees
WHERE salary > 10000
ORDER BY salary DESC;

