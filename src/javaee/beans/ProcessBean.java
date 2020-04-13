package javaee.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;

import jee.ejb.stateless.DataProcessNoInterface;

@ManagedBean
@SessionScoped
public class ProcessBean implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@EJB
	private DataProcessNoInterface DBBean;
	
	private ArrayList rs;
	private String id="";
	private String name="";
	
	private Object[] data;
	
	public ArrayList getRs() {
		return rs;
	}
	public void setRs(ArrayList rs) {
		this.rs = rs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataProcessNoInterface getDBBean() {
		return DBBean;
	}
	public void setDBBean(DataProcessNoInterface dBBean) {
		DBBean = dBBean;
	}
	
	public ProcessBean(){
		
	}
	/*
	 * 
	 	
	public void getDBbeanFromClient(){
		DataprocessClient client=new DataprocessClient();
		setDBBean(client.getEJB());
		data=new Object[4];
		System.out.println("DataprocessClient had been gotten");
	}
	 */
	@PostConstruct
	public void init(){
		//this.DBBean=new DataProcessNoInterface();
		this.setId("");
		this.setName("");
		try {
			Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context context = new InitialContext(jndiProperties);
			final String appName = "app";
			final String moduleName = "55170330Zhouli";
			System.out.println("java:" + appName + "/" + moduleName + "/DataProcessNoInterface!jee.ejb.stateless.DataProcessNoInterface");
			this.DBBean = (DataProcessNoInterface) context.lookup("java:" + appName + "/" + moduleName + "/DataProcessNoInterface!jee.ejb.stateless.DataProcessNoInterface");
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("ProcessBean had been new");
	}
	public String readData(){
		rs=DBBean.ReadFromDB(id, name);
		if(rs!=null)
			return "success";
		else 
			return "error";
	}
	public void addData(){
		
	}
	public static void main(String args[]){
		ProcessBean bean=new ProcessBean();
		Object[] data=new Object[4];
		data[0]="zq001";
		data[1]="englis";
		data[2]="female";
		data[3]="16";
		//bean.WriteToDB(data);
		System.out.println("Data:");
		bean.readData();
	}
}
