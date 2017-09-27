/*18. Write a SQL query to find all departments and the town of their location. Use inner join with ON clause.*/

select d.*, loc.`city`
from hr.departments d
join hr.locations loc on (d.`location_id` = loc.`location_id`);





