package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.EmpDaoImpl;
import com.app.entity.Employee;

@RestController
public class EmpController 
{
	@Autowired
	private EmpDaoImpl esi;
	@PostMapping(value="/save")
	public String saveEmployee(@RequestBody Employee e)
	{
		
        boolean b=esi.saveEmployee(e);
        if(b)
        {
        	return "Save SucessFully";
        }
        else
        	return "Unsucessfully";
	}
	@GetMapping(value="/employees")
	public List<Employee> ListEmployees(ModelMap  map)
	{
		
		List<Employee> list=esi.getAllEmployees();
        return list;	
		
	}

	@DeleteMapping(value="/delete/{eid}")
	public String deleteEmployee(@RequestParam int eid)
	{
		Boolean b=esi.deleteEmployee(eid);
		if(b)
		{
			return "Delete  Emp Successfully";
		}
		else
		{
			return "Not Delete  Successfully";	
		}
		
	}
	@PutMapping(value="/edit")
	public String UpdateEmployee(@RequestBody Employee e)
	{
		boolean b=esi.updateEmployee(e);
		if(b)
		{
			return "Updated";
		}
		else
		{
			return "Not Updated";
		}
	}
}

