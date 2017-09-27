/*23. Write a SQL query to find the manager name of each department.*/

select `departments`.`department_name`, `departments`.`manager_id`, `employees`.`last_name`, `employees`.`employee_id`
from `hr`.`departments` 
inner join `hr`.`employees` 
on `departments`.`manager_id` = `employees`.`employee_id`;