package com.volvo.test.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.volvo.test.domain.User;

@ManagedBean(name = "user", eager = true)
@RequestScoped
public class UserView extends User {

    public UserView() { }
}
