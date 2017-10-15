package cn.hsu.crud.bean;

import java.util.HashMap;
import java.util.Map;
/**
 * 定义一个方便扩展的实体类
 * @author Administrator
 *
 */
public class Msg {
	//状态码——100：成功  200：失败
	private int code;
	//返回给客户端的信息
	private String msg;
	//用户自定义的信息
	private Map<String,Object> extend = new HashMap<String, Object>();
	
	public Msg() {
	}

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("操作成功！");
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("操作失败！");
		return result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
}
