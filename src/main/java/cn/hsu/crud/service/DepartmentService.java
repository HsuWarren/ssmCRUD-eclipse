package cn.hsu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hsu.crud.bean.Department;
import cn.hsu.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	@Autowired
	DepartmentMapper deptMapper;

	public List<Department> getAllDepts() {
		//查询所有部门信息
		return deptMapper.selectByExample(null);
	}

}
