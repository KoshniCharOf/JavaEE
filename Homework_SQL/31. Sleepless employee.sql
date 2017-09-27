/*31.Write a SQL query to find all employees that have worked in the past at job position “AC_ACCOUNT” 
and at some time later at job position “AC_MGR”. Display the employees names and current job title.
 Hint: first self-join JOB_HISTORY table, then apply filtering and finally join the result with EMPLOYEES and JOBS tables.*/


select j1.employee_id, e.first_name, e.last_name, j.job_title
from job_history j1
join job_history j2 on (j1.job_id = 'AC_ACCOUNT')
join employees e on (e.employee_id = j1.employee_id)
join jobs j on (e.job_id = j.job_id)
where j2.job_id = 'AC_MGR' and j2.start_date > j1.end_date;
