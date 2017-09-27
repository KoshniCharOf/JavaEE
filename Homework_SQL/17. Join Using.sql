/*17. Write a SQL query to find all departments and the town of their location. Use join with USING clause. */


select `departments`.*, `locations`.`city`  
from  hr.locations 
join  hr.departments  
using (location_id);




