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
 * ����Ա��CRUD����
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	/**
	 * ����Ա��ɾ��:1
	 * Ա������ɾ����1-2-3
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg empDelete(@PathVariable("ids")String ids){
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			//����ɾ��
			String[] strs_ids = ids.split("-");
			//��װ����
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
	 * ���ֱ�ӷ���ajax=PUT��ʽ������
	 * ��װ�����ݳ���ID֮��ȫ��Ϊ��
	 * 
	 * 
	 * ���⣺
	 * �������������ݣ�����Employee�����װ����
	 * 
	 * ԭ��
	 * Tomcat��
	 * 		�������������ݷ�װһ��map
	 * 		request.getParameter("empName")�ͻ�����map��ȡ��
	 * 		SpringMVC��װPOJO����ʱ�����POJO ��ÿ������ֵrequest.getParameter("empName")
	 * ajax����PUT����������Ѫ��
	 * $.ajax({
				url:"${APP_PATH}/emp/" + $(this).attr("edit_id"),
				type:"POST",
				data:$("#empUpdateModal form").serialize() + "&_method=PUT",
				success:function(result){
					alert(result.msg);
				}
			});
	 * 		PUT�������е�����request.getParameter("empName")�ò���
	 * 		Tomcatһ����PUT���󣬾Ͳ����װ�������е�����Ϊmap��ֻ��POST�Ż��װ�������е�����Ϊmap
	 * 
	 * org.apache.catalina.connector.Request
	 * 
	 * ���������
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е����ݵ�map��
	 * web.xml������HiddenHttpMethodFilter
	 * �������þ��ǽ��������е����ݽ�����װ��map
	 * reqeust�����°�װ��request.getParameter��������д���ͻ���Լ���װ��map����ȡ����
	 * Ա�����·���
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
	 * ����ID��ѯԱ��
	 * @param id
	 * @return
	 */
	//REST������󣬵õ���ͬ�Ĵ���
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	//@PathVariable("id")������·���л�ȡ
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	/**
	 * �ǿ���֤
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUser")
	public Msg checkUser(@RequestParam("empName")String empName){
		String regx = "(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,6})";
		if(!empName.matches(regx)){
			return Msg.fail().add("valid_msg", "�û������ĳ���Ϊ2-6λ����ĸ�����ּ��������ַ�����Ϊ4-16λ");
		}
		boolean flag = employeeService.checkUser(empName);
		if(flag){
			return Msg.success();
		}else{
			return Msg.fail().add("valid_msg", "�û��������ã�");
		}
	}
	
	/**
	 * Ա������
	 * 1��֧��JSR303У�飨��Ҫ������Ҫ�����֤����ֹǰ��У�鱻�ƹ���
	 * 2������hibernate-validator-5.4.1.Final.jar
	 * 
	 * @param emp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee emp,BindingResult result){
		if(result.hasErrors()){
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾ������Ϣ
			Map<String,Object> map = new HashMap<String	, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
//				System.out.println("������ֶ�����" + fieldError.getField());
//				System.out.println("������Ϣ�� " + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			employeeService.saveEmp(emp);
//			System.out.println(Msg.success().getMsg());//@ResponseBody������ӣ���������json���󷵻�
			return Msg.success();			
		}

	}
	/**
	 * ����ѯ��������ͨ��json�ַ�������ʽ����
	 * ��Ҫ����Jackson��
	 * @param pn
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsByJson(@RequestParam(value="pn",defaultValue="1")Integer pn)throws Exception{
		//����pageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ�����pagehelper������ҳ�룬ÿһҳ�ļ�¼����
		PageHelper.startPage(pn, 10);
		//startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��PageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
		PageInfo page = new PageInfo(emps,5);		
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯ����Ա����Ϣ(��ҳ��ѯ)
	 * @return
	 */
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model)throws Exception{
//		//����pageHelper��ҳ���
//		//�ڲ�ѯ֮ǰֻ�����pagehelper������ҳ�룬ÿһҳ�ļ�¼����
//		PageHelper.startPage(pn, 10);
//		//startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
//		List<Employee> emps = employeeService.getAll();
//		//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��PageInfo����ҳ�������
//		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
//		PageInfo page = new PageInfo(emps,5);
//		model.addAttribute("pageInfo", page);
//		
//		return "list";
//	}
}
