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

import com.volvo.test.domain.User;
import com.volvo.test.service.PermissionService;
import com.volvo.test.service.UserService;

@Component
@ManagedBean(name = "userModel", eager = true)
@RequestScoped
public class UserModel {

    @ManagedProperty(value = "#{user}")
    private UserView user = new UserView();

    @Autowired
    private UserService userService;
    
    @Autowired
    private PermissionService permissionService;

    public void doFindUserById() {
        User found = userService.findOne(this.user.getId()).get();
        this.user.setId(found.getId());
        this.user.setName(found.getName());
        this.user.setDescription(found.getDescription());
        this.user.setDepartment(found.getDepartment());
        this.user.setPermissions(this.user.getPermissions());
    }

    public List<UserView> findAllUsers() {
        List<UserView> users = new ArrayList<UserView>();
        for(User entity : this.userService.findAll()) {
            UserView view = new UserView();
            view.setId(entity.getId());
            view.setName(entity.getName());
            view.setDescription(entity.getDescription());
            view.setDepartment(entity.getDepartment());
            users.add(view);
        }
        return users;
    }
    
    public void deleteUser(long userId) {
    	userService.delete(userId);
    }
    
	public void openEditModal(UserView user) {
		setUser(user);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editUserId", user.getId());
		
		RequestContext context = RequestContext.getCurrentInstance();	
		context.execute("PF('dlgEditUser').show()");
	}
	
	public void openCreateModal() {
		setUser(new UserView());
		
		RequestContext context = RequestContext.getCurrentInstance();	
		context.execute("PF('dlgCreateUser').show()");
	}
	
    public void save() {
    	User u = new User(user.getId(), user.getName(), user.getDescription(), user.getDepartment(), user.getPermissions());
    	
    	permissionService.save(user.getPermissions());    	
    	userService.save(u);
    	
    	RequestContext.getCurrentInstance().update("userForm");
    	
    	FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "user created",
                    "The user " + u.getName() + " has been created with id=" + u.getId()));
    }
    
    public void edit() {
    	Long editId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editUserId");
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("editUserId");
    	User u = new User(editId, user.getName(), user.getDescription(), user.getDepartment(), user.getPermissions());
    	
    	permissionService.save(user.getPermissions());
    	userService.save(u);
    	
    	RequestContext.getCurrentInstance().update("userForm");
    	
    	FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "user updated",
                    "The user " + u.getName() + " has been updated with id=" + u.getId()));
    }
    
    public void setUser(UserView user) {
        this.user = user;
    }

    public UserView getUser() {
        return user;
    }

}
