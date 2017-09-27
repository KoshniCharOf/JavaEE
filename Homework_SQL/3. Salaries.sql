/*3. Write a SQL query to find the salary of each employee by month, by day and hour. 
Consider that one month has 20 workdays and each workday has 8 work hours. */

SELECT salary, salary/20 as daily, salary/20/8 as wage
FROM `hr`.`employees`;
