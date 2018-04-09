package com.volvo.test.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.volvo.test.domain.Permission;

@ManagedBean(name = "permission", eager = true)
@RequestScoped
public class PermissionView extends Permission {

    public PermissionView() { }
}
