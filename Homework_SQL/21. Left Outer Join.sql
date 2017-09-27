/*20. Write a SQL query to find all the locations and the departments for each location along with the locations that do not have department. User right outer join.
21. Rewrite the last SQL query to use left outer join.*/

SELECT L.city, D.department_name 
FROM locations L
LEFT OUTER JOIN departments D
ON (L.location_id = D.location_id);