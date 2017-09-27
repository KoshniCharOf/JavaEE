/*22. Rewrite the last query to use WHERE instead of JOIN.*/
/*TODO NULL VAL FROM LOCATIONS*/

SELECT concat( `locations`.`city`,' - ', `locations`.`state_province`)
as 'location_name', `departments`.`department_name`
FROM  `departments`, `locations`
where `locations`.`location_id` = `departments`.`location_id`;


 