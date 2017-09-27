/*10. Write a SQL query to find the names of all employees whose salary is in the range [3000...5000]. Use BETWEEN.  */

SELECT concat(first_name,' ', last_name) as 'name', salary
FROM `hr`.`employees` where `employees`.`salary` BETWEEN 3000 and 5000 ;

