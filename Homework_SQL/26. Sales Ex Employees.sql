/*26. Find all employees that have worked in the past in the department “Sales”. Use complex join condition.*/

select `employees`.`last_name`,`employees`.`hire_date`,`employees`.`department_id`, `departments`.`department_name`
from  `hr`.`employees` 
inner join `hr`.`departments`
on (`departments`.`department_id` = `employees`.`department_id`
	and `departments`.`department_name` in('Sales') 
	and `employees`.`hire_date` between '1995-01-01' and now() - interval 1 day);