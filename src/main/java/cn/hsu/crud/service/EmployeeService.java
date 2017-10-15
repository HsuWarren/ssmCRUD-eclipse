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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		return mapper.selectByExampleWithDept(null);
	}
	/**
	 * ����Ա������
	 * @param emp
	 */
	public void saveEmp(Employee emp) {
		mapper.insertSelective(emp);
	}
	/**
	 * �����֤�û��Ƿ����
	 * @param empName
	 * @return true: �û�������  false: �û���������
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = mapper.countByExample(example);
		return count == 0;
	}
	/**
	 * ����Ա��id��ѯԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		Employee employee = mapper.selectByPrimaryKey(id);
		return employee;
	}
	
	/**
	 * ��ѡ���Ա������
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		mapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * ����Ա��ɾ��
	 * @param id
	 */
	public void empDelete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	/**
	 * ����ɾ��
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
