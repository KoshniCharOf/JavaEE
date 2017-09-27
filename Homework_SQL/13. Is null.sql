/*13. Write a SQL query to find all locations that have no state or post code defined. Use IS NULL.*/

SELECT * 
FROM `hr`.`locations` 
WHERE `postal_code` is null 
OR `state_province` is null;
