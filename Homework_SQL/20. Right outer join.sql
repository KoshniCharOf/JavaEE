/*Write a SQL query to find all the locations and the departments for each location along with the locations 
that do not have department. Use right outer join. */

SELECT concat( `locations`.`city`,' - ', `locations`.`state_province`)
as 'location_name', `departments`.`department_name`
FROM `departments` 
right outer join `locations` 
on `departments`.`location_id` = `locations`.`location_id`;


 