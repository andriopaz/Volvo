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

import com.volvo.test.domain.Permission;
import com.volvo.test.service.PermissionService;

@Component
@ManagedBean(name = "permissionModel")
@RequestScoped
public class PermissionModel {

    @ManagedProperty(value = "#{permission}")
    private Permission permission = new Permission();
    
    @Autowired
    private PermissionService permissionService;

    public void doFindPermissionById() {
        Permission found = permissionService.findOne(this.permission.getId()).get();
        this.permission.setId(found.getId());
        this.permission.setName(found.getName());
        this.permission.setDescription(found.getDescription());
        this.permission.setUsers(found.getUsers());
    }

    public List<Permission> findAllPermissions() {
        List<Permission> permissions = new ArrayList<Permission>();
        for(Permission entity : this.permissionService.findAll()) {
        	Permission view = new Permission();
            view.setId(entity.getId());
            view.setName(entity.getName());
            view.setDescription(entity.getDescription());
            view.setUsers(entity.getUsers());
            permissions.add(view);
        }
        return permissions;
    }
    
    public void deletePermission(long departmentId) {
    	permissionService.delete(departmentId);
    }
    
    public void save() {
    	Permission p = new Permission(permission.getId(), permission.getName(), permission.getDescription(), permission.getUsers());
    	permissionService.save(p);
    	
    	RequestContext.getCurrentInstance().update("permissionForm");
    	
    	FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Permission created",
                    "The permission " + p.getName() + " has been created with id=" + p.getId()));
    }
    
    public void edit() {
    	Long editId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editPermissionId");
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("editPermissionId");
    	Permission p = new Permission(editId, permission.getName(), permission.getDescription(), permission.getUsers());
    	permissionService.save(p);
    	
    	RequestContext.getCurrentInstance().update("permissionForm");	
    	FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Permission updated",
                    "The permission " + p.getName() + " has been updated with id=" + p.getId()));
    }
    
	public void openEditModal(Permission permission) {
		setPermission(permission);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editPermissionId", permission.getId());
		
	    RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgEditPermission').show()");
	}
	
	public void openCreateModal() {
		setPermission(new Permission());
		
		RequestContext context = RequestContext.getCurrentInstance();	
		context.execute("PF('dlgCreatePermission').show()");
	}
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }
    
}
