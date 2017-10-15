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
 * ����Ա��CRUD����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	/**
	 * ����ѯ��������ͨ��json�ַ�������ʽ����
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/depts")
	public Msg getDeptsByJson() throws Exception{
		//��ȡ���в�����Ϣ��������Ϊjson��ʽ
		List<Department> deptList = departmentService.getAllDepts();
		
		return Msg.success().add("deptsList", deptList);
	}
}
