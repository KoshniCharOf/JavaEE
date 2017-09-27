/*23. Write a SQL query to find the manager name of each department.
24. Modify the last SQL query to find also the location of each department manager.*/

select `departments`.`department_name`, `departments`.`manager_id`, `employees`.`last_name`, `employees`.`employee_id`, `locations`.`city`
from `hr`.`departments` 
inner join `hr`.`employees` 
on `departments`.`manager_id` = `employees`.`employee_id`
natural join `hr`.`locations`;