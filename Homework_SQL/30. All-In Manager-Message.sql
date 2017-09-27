/* 29. Write a SQL query to display all employees, along with their job title, department, location, country and region. Use multiple joins 
30. Modify the last SQL query to display also the manager name for each employee or “No manager” in case there is no manager */

select e.employee_id, ifnull(m.last_name, 'No manager') as manager,
e.first_name, e.last_name, department_name, city,country_name, region_name

from employees e
left outer join `hr`.`jobs` j on (e.job_id = j.job_id)
left outer join `hr`.`departments` d on (e.department_id = d.department_id)
left outer join `hr`.`locations` l on (d.location_id = l.location_id)
left outer join `hr`.`countries` c on (l.country_id = c.country_id)
left outer join `hr`.`regions` r on (c.region_id = r.region_id)
left outer join employees m on (e.manager_id = m.employee_id);