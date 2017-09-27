/*4. Write a SQL query to find the email addresses of each employee. Consider that the mail domain is mail.somecompany.com.
 Emails should look like "bernst@mail.somecompany.com". The produced column should be named "Full Email Address". 

 */
select concat(email,'@mail.somecompany.com') as 'Full Email Address'
from hr.employees;

