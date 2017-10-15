package cn.hsu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hsu.crud.bean.Employee;
import cn.hsu.crud.bean.Msg;
import cn.hsu.crud.service.EmployeeService;

/**
 * 处理员工CRUD请求
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	/**
	 * 单个员工删除:1
	 * 员工批量删除：1-2-3
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg empDelete(@PathVariable("ids")String ids){
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			//批量删除
			String[] strs_ids = ids.split("-");
			//组装集合
			for (String str_id : strs_ids) {
				del_ids.add(Integer.parseInt(str_id));
			}
			employeeService.deleteBatch(del_ids);
			
		}else{
			Integer id = Integer.parseInt(ids);
			employeeService.empDelete(id);
		}
		return Msg.success();
	}
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据除了ID之外全部为空
	 * 
	 * 
	 * 问题：
	 * 请求体中有数据；但是Employee对象封装不上
	 * 
	 * 原因：
	 * Tomcat：
	 * 		将请求体中数据封装一个map
	 * 		request.getParameter("empName")就会从这个map中取出
	 * 		SpringMVC封装POJO对象时，会把POJO 中每个属性值request.getParameter("empName")
	 * ajax发送PUT请求引发的血案
	 * $.ajax({
				url:"${APP_PATH}/emp/" + $(this).attr("edit_id"),
				type:"POST",
				data:$("#empUpdateModal form").serialize() + "&_method=PUT",
				success:function(result){
					alert(result.msg);
				}
			});
	 * 		PUT请求体中的数据request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT请求，就不会封装请求体中的数据为map，只有POST才会封装请求体中的数据为map
	 * 
	 * org.apache.catalina.connector.Request
	 * 
	 * 解决方案：
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据到map中
	 * web.xml配置上HiddenHttpMethodFilter
	 * 他的作用就是讲请求体中的数据解析封装到map
	 * reqeust被重新包装，request.getParameter（）被重写，就会从自己封装的map中提取数据
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee){
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * 根据ID查询员工
	 * @param id
	 * @return
	 */
	//REST风格请求，得到不同的处理
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	//@PathVariable("id")从请求路径中获取
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	/**
	 * 非空验证
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUser")
	public Msg checkUser(@RequestParam("empName")String empName){
		String regx = "(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,6})";
		if(!empName.matches(regx)){
			return Msg.fail().add("valid_msg", "用户名中文长度为2-6位，字母、数字及非特殊字符长度为4-16位");
		}
		boolean flag = employeeService.checkUser(empName);
		if(flag){
			return Msg.success();
		}else{
			return Msg.fail().add("valid_msg", "用户名不可用！");
		}
	}
	
	/**
	 * 员工保存
	 * 1、支持JSR303校验（重要数据需要后端验证，防止前端校验被绕过）
	 * 2、导入hibernate-validator-5.4.1.Final.jar
	 * 
	 * @param emp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee emp,BindingResult result){
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示错误信息
			Map<String,Object> map = new HashMap<String	, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
//				System.out.println("错误的字段名：" + fieldError.getField());
//				System.out.println("错误信息： " + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			employeeService.saveEmp(emp);
//			System.out.println(Msg.success().getMsg());//@ResponseBody忘记添加，服务器无json对象返回
			return Msg.success();			
		}

	}
	/**
	 * 将查询到的数据通过json字符串的形式返回
	 * 需要引入Jackson包
	 * @param pn
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsByJson(@RequestParam(value="pn",defaultValue="1")Integer pn)throws Exception{
		//引入pageHelper分页插件
		//在查询之前只需调用pagehelper，传入页码，每一页的记录条数
		PageHelper.startPage(pn, 10);
		//startPage后面紧跟的查询就是分页查询
		List<Employee> emps = employeeService.getAll();
		//使用PageInfo包装查询后的结果，只需要将PageInfo交个页面就行了
		//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
		PageInfo page = new PageInfo(emps,5);		
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询所有员工信息(分页查询)
	 * @return
	 */
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model)throws Exception{
//		//引入pageHelper分页插件
//		//在查询之前只需调用pagehelper，传入页码，每一页的记录条数
//		PageHelper.startPage(pn, 10);
//		//startPage后面紧跟的查询就是分页查询
//		List<Employee> emps = employeeService.getAll();
//		//使用PageInfo包装查询后的结果，只需要将PageInfo交个页面就行了
//		//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
//		PageInfo page = new PageInfo(emps,5);
//		model.addAttribute("pageInfo", page);
//		
//		return "list";
//	}
}
