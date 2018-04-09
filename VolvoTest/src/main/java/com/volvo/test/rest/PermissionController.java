package com.volvo.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.volvo.test.domain.Permission;
import com.volvo.test.service.PermissionService;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/service/permissions")
    public @ResponseBody
    Iterable<Permission> getAllPermissions() {
        return this.permissionService.findAll();
    }
    
    @RequestMapping(value="/service/permission/{id}", method=RequestMethod.GET)
    public @ResponseBody Permission getPermissionById(@PathVariable Long id) {
        return this.permissionService.findOne(id).get();
    }
    
    @RequestMapping(value="/service/permission/delete/{id}", method=RequestMethod.GET)
    public @ResponseBody void deletePermissionById(@PathVariable Long id) {
    	this.permissionService.delete(id);
    }
    
    @RequestMapping(value="/savePermission", method = RequestMethod.POST)
    public ResponseEntity<Permission> savePermission(@RequestBody Permission permission) {
    	
    	if (permission == null || permission.getId() == null || StringUtils.isEmpty(permission.getName())) {
    		return new ResponseEntity<Permission>(permission, HttpStatus.BAD_REQUEST);
    	}
    	
    	this.permissionService.save(permission);
    	
    	return new ResponseEntity<Permission>(permission, HttpStatus.OK);
    }
}
