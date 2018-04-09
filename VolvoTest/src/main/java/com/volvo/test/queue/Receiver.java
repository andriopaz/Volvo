package com.volvo.test.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.volvo.test.domain.Department;
import com.volvo.test.domain.Permission;
import com.volvo.test.domain.User;

@Component
public class Receiver {
	
	@Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "permission", containerFactory = "myFactory")
    public void receiveMessage(Permission permission) {
        System.out.println("Received <" + permission.getName() + ">");
        this.jmsTemplate.convertAndSend("permissionBack", permission.getName());
    }
    
    @JmsListener(destination = "department", containerFactory = "myFactory")
    public void receiveMessage(Department department) {
        System.out.println("Received <" + department.getName() + ">");
        this.jmsTemplate.convertAndSend("departmentBack", department.getName());
    }
    
    @JmsListener(destination = "user", containerFactory = "myFactory")
    public void receiveMessage(User user) {
        System.out.println("Received <" + user.getName() + ">");
        this.jmsTemplate.convertAndSend("userBack", user.getName());
    }

}