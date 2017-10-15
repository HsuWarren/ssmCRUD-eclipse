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
 * �־ò������
 * @author Administrator
 *�Ƽ�Spring��Ŀ�п���ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 *1������spring test��Ԫ����ģ��
 *2��@ContextConfigurationָ��spring�����ļ���λ��
 *3��ֱ��@AutowiredҪʹ�õ��������
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
	 * ����DepartmentMapper
	 */
	@Test
	public void testCRUD() {
		//1������IOC������
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		//2���������л�ȡmapper
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		
//		System.out.println(departmentMapper);
		//1�����벿����Ϣ
//		departmentMapper.insertSelective(new Department(null, "�г���"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		//2������Ա�����ݣ�����Ա������
//		employeeMapper.insertSelective(new Employee(null, "Jack", "M", "Jack@sohu.com", 3));
		//3������������Ա����ʹ�ÿ�������������sqlSession
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
//		System.out.println("������ɣ�");
	}
}
