package jee.ejb.stateless;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.entity.Student;

@Stateless
@LocalBean
public class DataProcessNoInterface {
	EntityManager manager=null;
	public DataProcessNoInterface(){
	     EntityManagerFactory factory =
	    	       Persistence.createEntityManagerFactory("55170330Zhouli",null);
	    	        manager = factory.createEntityManager();
	}
	public ArrayList ReadFromDB(String id,String name){
	    EntityTransaction transaction = this.manager.getTransaction();
	    transaction.begin();
	    String sql="from Student c";
	    if(!id.contentEquals("")&&!name.contentEquals("")){
	    	sql+=" where c.id="+id+" and c.name=\'"+name+"\'";
	    }
	    else if(!id.contentEquals("")&&name.contentEquals("")){
	    	sql+=" where c.id="+id;
	    }
	    else if(id.contentEquals("")&&!name.contentEquals("")){
	    	sql+=" where c.name=\'"+name+"\'";
	    }
	    System.out.println(sql);
	    System.out.println("\'"+name+"\'");
	    Query q=manager.createQuery(sql);
	    List results=q.getResultList();
	    ArrayList aList=new ArrayList();
	    aList.addAll(results);
	    transaction.commit();
	    Iterator it=results.iterator();
	    while(it.hasNext())
	    {
	   	  Student p=(Student)it.next();
	   	  System.out.print(p.getId()+"\t");
	   	  System.out.print(p.getName()+"\t");
	   	  System.out.print(p.getMajor()+"\t");
	      System.out.print(p.getGender()+"\t");
	      System.out.println(p.getAddress_id()+"\t");
	    }
	    return aList;
}
	public void WriteToDB(Object[] data){
		if(data!=null){
			   Student p=new Student ();
			   p.setName(data[0].toString());
			   p.setMajor(data[1].toString());
			   p.setGender(data[2].toString());
			   p.setAddress_id(data[3].toString());
			   
		     EntityTransaction transaction = manager.getTransaction();
		     transaction.begin();
		      manager.persist(p);  
		      transaction.commit();
		}
	}
	public static void main(String args[]){
		DataProcessNoInterface bean=new DataProcessNoInterface();
		Object[] data=new Object[4];
		data[0]="zq001";
		data[1]="englis";
		data[2]="female";
		data[3]="16";
		//bean.WriteToDB(data);
		System.out.println("Data:");
		bean.ReadFromDB("","");
	}

}
