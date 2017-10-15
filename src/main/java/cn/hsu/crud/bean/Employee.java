package cn.hsu.crud.bean;

import javax.validation.constraints.Pattern;


public class Employee {
    private Integer empId;
    
    //注意\的转义（"\u2E80"各种语言都能识别）
    @Pattern(regexp="(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,6})"
    		,message="用户名必须符合中文长度为2-6位，字母、数字及非特殊字符长度为4-16位")
    private String empName;

    private String gender;

    //@Email
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$"
    		,message="邮箱格式不正确!")
    private String email;

    private Integer dId;
    
    private Department department;

	public Employee() {
	}

    
    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}


	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    
    public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}
}