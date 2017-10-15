package cn.hsu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.hsu.crud.bean.Employee;

import com.github.pagehelper.PageInfo;

/**
 * ʹ��spring�ṩ�ĵ�Ԫ����ģ�飬����CRUD�������ȷ��
 * @author Administrator
 *Spring 4 ��Ԫ������Ҫservlet 3.0 ��֧�֣�java.lang.NoClassDefFoundError: javax/servlet/SessionCookieConfig��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	//����springmvc��IOC
	@Autowired
	WebApplicationContext context;
	//�����MVC���󣬻�ȡ������
	MockMvc mockMvc;
	@Before
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//ģ�������õ����
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		//����ɹ����������л���pageInfo��ȡ��pageInfo������֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ�룺" + pi.getPageNum());
		System.out.println("��ҳ�룺" + pi.getPages());
		System.out.println("�ܼ�¼����"+ pi.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[] nums = pi.getNavigatepageNums();
		for(int i:nums){
			System.out.print(" " + i + " ");
		}
		System.out.println();
		//��ȡԱ������
		List<Employee> list = pi.getList();
		for (Employee emp : list) {
			System.out.println("ID: " + emp.getdId() + "==>Name: " + emp.getEmpName());
		}
	}
}
