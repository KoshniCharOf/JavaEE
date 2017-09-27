/* 29. Write a SQL query to display all employees, along with their job title, department, location, country and region. Use multiple joins !*/

select employee_id, first_name, last_name, job_title, department_name, city,country_name, region_name
from `hr`.`employees` e
left outer join `hr`.`jobs` j on (e.job_id = j.job_id)
left outer join `hr`.`departments` d on (e.department_id = d.department_id)
left outer join `hr`.`locations` l on (d.location_id = l.location_id)
left outer join `hr`.`countries` c on (l.country_id = c.country_id)
left outer join `hr`.`regions` r on (c.region_id = r.region_id);