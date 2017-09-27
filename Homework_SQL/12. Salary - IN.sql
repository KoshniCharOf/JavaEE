/*12. Write a SQL query to find the names of all employees whose salary is 2500, 4000 or 5000. Use IN. */
 
SELECT * 
FROM `hr`.`employees` 
WHERE `salary` IN(2500,4000,5000);



