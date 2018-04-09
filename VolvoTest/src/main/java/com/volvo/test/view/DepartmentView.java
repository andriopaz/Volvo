package com.volvo.test.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.volvo.test.domain.Department;

@ManagedBean(name = "department", eager = true)
@RequestScoped
public class DepartmentView extends Department {

    public DepartmentView() { }
}
