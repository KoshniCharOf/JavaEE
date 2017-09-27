/*18. Write a SQL query to find all departments and the town of their location. Use inner join with ON clause.
19. Modify the last SQL query to include also the name of the manager for each department.*/


SELECT D.department_name, L.city, E.last_name 
FROM departments D
NATURAL JOIN employees E
NATURAL JOIN locations L





