package com.volvo.test.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.volvo.test.domain.Department;
import com.volvo.test.service.DepartmentService;

@Component
@ManagedBean(name = "departmentModel", eager = true)
@RequestScoped
public class DepartmentModel {

    @ManagedProperty(value = "#{department}")
    private DepartmentView department = new DepartmentView();

    @Autowired
    private DepartmentService departmentService;

    public void doFindDepartmentById() {
        Department found = departmentService.findOne(this.department.getId()).get();
        this.department.setId(found.getId());
        this.department.setName(found.getName());
        this.department.setDescription(found.getDescription());
    }

    public List<DepartmentView> findAllDepartments() {
        List<DepartmentView> departments = new ArrayList<DepartmentView>();
        for(Department entity : this.departmentService.findAll()) {
            DepartmentView view = new DepartmentView();
            view.setId(entity.getId());
            view.setName(entity.getName());
            view.setDescription(entity.getDescription());
            departments.add(view);
        }
        return departments;
    }
    
    public void deleteDepartment(long departmentId) {
    	departmentService.delete(departmentId);
    }
    
    public void save() {
    	Department d = new Department(department.getId(), department.getName(), department.getDescription(), department.getUsers());
    	departmentService.save(d);
    	
    	RequestContext.getCurrentInstance().update("departmentForm");
    	
        FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Department created",
                    "The department " + d.getName() + " has been created with id=" + d.getId()));
    }
    
    public void edit() {
    	Long editId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editDepartmentId");
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("editDepartmentId");
    	Department d = new Department(editId, department.getName(), department.getDescription(), department.getUsers());
    	departmentService.save(d);
    	
    	RequestContext.getCurrentInstance().update("departmentForm");
    	
        FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Department updated",
                    "The department " + d.getName() + " has been updated with id=" + d.getId()));
    }
    
	public void openEditModal(DepartmentView department) {
		setDepartment(department);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editDepartmentId", department.getId());
		
		RequestContext context = RequestContext.getCurrentInstance();	
		context.execute("PF('dlgEditDepartment').show()");
	}
	
	public void openCreateModal() {
		setDepartment(new DepartmentView());
		
		RequestContext context = RequestContext.getCurrentInstance();	
		context.execute("PF('dlgCreateDepartment').show()");
	}
    
    public void setDepartment(DepartmentView department) {
        this.department = department;
    }

    public DepartmentView getDepartment() {
        return department;
    }
}
