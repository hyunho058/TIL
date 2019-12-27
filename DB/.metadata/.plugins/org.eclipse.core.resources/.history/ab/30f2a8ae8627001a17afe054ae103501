
--각 사원(employees)에 대해서 사번(employee_id), 이름(first_name), 부서명(department_name), 매니저(manager)의 이름(first_name)을 조회하시오.
select e.employee_id, e.first_name,department_name, m.first_name
from employees e , employees m , departments
where e.employee_id = m.manager_id;


select *
from employees;

select last_name
from employees;

--문제2. 자신의 매니저보다 연봉(salary)을 많이 받는 직원들의 성(last_name)과 연봉(salary)을 출력하시오. 
select e.last_name, e.salary
from employees e, employees m
where e.employee_id = m.manager_id and e.salary >= m.salary;


--문제3. 업무명(job_title)이 ‘Sales Representative’인 직원 중에서
-- 연봉(salary)이 9,000 이상, 10,000 이하인 직원들의 이름(first_name), 성(last_name)과 연봉(salary)을 출력하시오.

select first_name, last_name, salary
from employees ,jobs
where job_title = 'Sales Representative' and salary >= 9000 and salary <= 10000;


--4
select e.employee_id, e.last_name, e.hire_date
from employees e, employees m
where e.employee_id = m.manager_id and e.hire_date < m.hire_date;


--5
select e.first_name, e.hire_date, e.employee_id,m.first_name
from employees e, employees m
where e.employee_id = m.manager_id and substr(e.hire_date,1,2) = '08';

--6

select first_name, salary, department_name
from employees, departments
where UPPER(department_name) = UPPER('Sales');

--7
select substr(hire_date,4,2) as "월", count(employee_id) as "입사자 수"
from employees 
group by substr(hire_date,4,2)
order by substr(hire_date,4,2);
