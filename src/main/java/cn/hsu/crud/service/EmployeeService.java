package cn.hsu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hsu.crud.bean.Employee;
import cn.hsu.crud.bean.EmployeeExample;
import cn.hsu.crud.bean.EmployeeExample.Criteria;
import cn.hsu.crud.dao.EmployeeMapper;



@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper mapper;
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return mapper.selectByExampleWithDept(null);
	}
	/**
	 * 保存员工数据
	 * @param emp
	 */
	public void saveEmp(Employee emp) {
		mapper.insertSelective(emp);
	}
	/**
	 * 后端验证用户是否存在
	 * @param empName
	 * @return true: 用户名可用  false: 用户名不可用
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = mapper.countByExample(example);
		return count == 0;
	}
	/**
	 * 根据员工id查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		Employee employee = mapper.selectByPrimaryKey(id);
		return employee;
	}
	
	/**
	 * 有选择地员工更新
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		mapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * 单个员工删除
	 * @param id
	 */
	public void empDelete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		//delete from xx where emp_id in(1,2,3)
		mapper.deleteByExample(example);
	}

}
