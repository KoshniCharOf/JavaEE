/* 27. Write a SQL query to display all employees (first and last name) along with their corresponding manager
 (first and last name). Use self-join.*/
 
select concat(`E`.`last_name`, '  ', `E`.`first_name`) as employee,
       concat(`M`.`last_name`, ' ' ,`M`.`first_name`) as manager
from  `employees` as E
join  `employees` as M
on (E.manager_id = M.employee_id);