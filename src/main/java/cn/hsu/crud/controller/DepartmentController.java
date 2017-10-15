package cn.hsu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hsu.crud.bean.Department;
import cn.hsu.crud.bean.Msg;
import cn.hsu.crud.service.DepartmentService;

/**
 * 处理员工CRUD请求
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	/**
	 * 将查询到的数据通过json字符串的形式返回
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/depts")
	public Msg getDeptsByJson() throws Exception{
		//获取所有部门信息，并保存为json格式
		List<Department> deptList = departmentService.getAllDepts();
		
		return Msg.success().add("deptsList", deptList);
	}
}
