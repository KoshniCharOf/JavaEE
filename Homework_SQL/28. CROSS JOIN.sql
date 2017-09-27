/* 28. Combine all first names with all last names of the employees with a CROSS JOIN*/

select E.`first_name`, M.`last_name`
from  `employees`  E
cross  join `employees`  M;