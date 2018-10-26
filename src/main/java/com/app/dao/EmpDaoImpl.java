package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.entity.Employee;

@Repository
public class EmpDaoImpl implements EmpDao 
{
	@Autowired
	private SessionFactory sf;
	public List<Employee> getAllEmployees() 
	{
		return sf.getCurrentSession().createQuery("from	com.app.model.Employee").list();


	}
	public Employee getEmployeeById(int empId) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	public boolean saveEmployee(Employee emp) 
	{
		boolean b=false;
		try
		{
			sf.getCurrentSession().save(emp);
			b=true;
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		return b;
	}
	public boolean updateEmployee(Employee emp) 
	{
		
		boolean b=false;
		try
		{
			sf.getCurrentSession().update(emp);;
			b=true;
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		return b;

	}
public boolean deleteEmployee(int empId) 
{
	boolean b=false;
	try
	{
		Employee e=new Employee();
		e.setEid(empId);
		sf.getCurrentSession().delete(e);
		b=true;
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	return b;
}
}
