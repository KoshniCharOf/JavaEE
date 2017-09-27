/*25. Write a SQL query to find the names of all employees from the departments "Sales" and "Finance"
 whose hire year is between 1995 and 2000.*/
 
select `employees`.`last_name`,`employees`.`hire_date`,`employees`.`department_id`, `departments`.`department_name`
from  `hr`.`employees` 
inner join `hr`.`departments`
on (`departments`.`department_id` = `employees`.`department_id`
and `departments`.`department_name` in( 'Sales', 'Finance'))
where  `employees`.`hire_date` between '1995-01-01' and '2000-01-01';