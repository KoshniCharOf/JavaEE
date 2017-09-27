/*6. Write a SQL query to find all departments and all region names, country names and city names as a single list. Use UNION.*/

select `department_name` as single_list from hr.departments
union
select `region_name`  from hr.regions
union 
select `country_name`  from hr.countries
union 
select `locations`.`city` from hr.locations;

