package cn.hsu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hsu.crud.bean.Department;
import cn.hsu.crud.bean.Employee;
import cn.hsu.crud.dao.DepartmentMapper;
import cn.hsu.crud.dao.EmployeeMapper;

/**
 * 持久层测试类
 * @author Administrator
 *推荐Spring项目中可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入spring test单元测试模块
 *2、@ContextConfiguration指定spring配置文件的位置
 *3、直接@Autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
//	@Autowired
//	DepartmentMapper departmentMapper;
//	@Autowired
//	EmployeeMapper employeeMapper;
//	@Autowired
//	ApplicationContext applicationContext;
//	@Autowired
//	SqlSession sqlSession;
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD() {
		//1、创建IOC容器、
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		//2、从容器中获取mapper
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		
//		System.out.println(departmentMapper);
		//1、插入部门信息
//		departmentMapper.insertSelective(new Department(null, "市场部"));
//		departmentMapper.insertSelective(new Department(null,"测试部"));
		//2、生成员工数据，测试员工插入
//		employeeMapper.insertSelective(new Employee(null, "Jack", "M", "Jack@sohu.com", 3));
		//3、批量插入多个员工；使用可以批量操作的sqlSession
//		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i=0;i<500;i++){
//			String uid = UUID.randomUUID().toString().substring(0, 4)+ i;
//			mapper.insertSelective(new Employee(null, uid, "F", uid+"@sina.com", 3));
//		}
//		for(int i=0;i<500;i++){
//			String uid = UUID.randomUUID().toString().substring(0, 4)+ i;
//			mapper.insertSelective(new Employee(null, uid, "F", uid+"@sohu.com", 2));
//		}
//		for(int i=0;i<500;i++){
//			String uid = UUID.randomUUID().toString().substring(0, 4)+ i;
//			mapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
//		}
//		for(int i=0;i<500;i++){
//			String uid = UUID.randomUUID().toString().substring(0, 4)+ i;
//			mapper.insertSelective(new Employee(null, uid, "M", uid+"@qq.com", 1));
//		}
//		System.out.println("批量完成！");
	}
}
