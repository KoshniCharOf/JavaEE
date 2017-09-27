/*15. Write a SQL query to find the first 10 employees joined the company (most senior people).*/

select *
from hr.employees 
ORDER BY `employee_id`
limit 10 ;


