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
 * 使用spring提供的单元测试模块，测试CRUD请求的正确性
 * @author Administrator
 *Spring 4 单元测试需要servlet 3.0 的支持（java.lang.NoClassDefFoundError: javax/servlet/SessionCookieConfig）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	//传入springmvc的IOC
	@Autowired
	WebApplicationContext context;
	//虚拟的MVC请求，获取处理结果
	MockMvc mockMvc;
	@Before
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到结果
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		//请求成功后，请求域中会有pageInfo，取出pageInfo进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码：" + pi.getPageNum());
		System.out.println("总页码：" + pi.getPages());
		System.out.println("总记录数："+ pi.getTotal());
		System.out.println("在页面需要连续显示的页数");
		int[] nums = pi.getNavigatepageNums();
		for(int i:nums){
			System.out.print(" " + i + " ");
		}
		System.out.println();
		//获取员工数据
		List<Employee> list = pi.getList();
		for (Employee emp : list) {
			System.out.println("ID: " + emp.getdId() + "==>Name: " + emp.getEmpName());
		}
	}
}
