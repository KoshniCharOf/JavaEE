/*11. Write a SQL query to find the names of all employees whose salary is in the range [2000...15000]
 but is not in range [5000 … 10000]. Use MINUS.*/

SELECT e1.first_name, e1.last_name, e1.salary
FROM employees e1 
JOIN employees e2 on (e1.employee_id = e2.employee_id)
WHERE e1.salary BETWEEN 2000 AND 15000
  AND e2.salary NOT BETWEEN 5000 AND 10000;


