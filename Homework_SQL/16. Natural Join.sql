/*16. Write a SQL query to find all departments and the town of their location.*/

select `departments`.* , `locations`.`city`
from hr.departments 
natural join hr.locations;



