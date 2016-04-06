package com.ices.csp.vmorder.createvms;

import java.net.URL;
import java.util.Map;

import com.xensource.xenapi.APIVersion;
import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Session;
import com.xensource.xenapi.VM;

import com.ices.csp.vmorder.createvms.TargetServer;

/**
 * 2015.10.26 15:24pm
 * @author ZZY
 * 根据虚拟机的名称，cpu数量和内存大小来选择相应的虚拟机模板，来进行虚拟机的创建，返回虚拟机的uuid唯一标识
 */
public class CreateVMByWake {
	public  String crateVM(String VMName,Integer CpuCount,Integer MemSize){
		// TODO Auto-generated method stub
		//连接服务器信息，主机名，用户名，用户密码
		String[] args = new String[3];
		args[0] = "172.29.131.53";
		args[1] = "root";
		args[2] = "123456";
		//存储服务器信息
    	 TargetServer server = new TargetServer(args[0], args[1], args[2]);
         try {
        	 //连接并登陆
        	 Connection connection =new Connection(new URL("http://" + server.Hostname));
			Session.loginWithPassword(connection, server.Username, server.Password, APIVersion.latest().toString());
	        Map<VM, VM.Record> all_recs = VM.getAllRecords(connection);
	        //查找模板
	        VM myvm=null;
	        for (Map.Entry<VM, VM.Record> e : all_recs.entrySet())
	        {
	        	String templatename = "win server2008 64位 "+CpuCount+"CPU "+MemSize;
	            if (e.getValue().isATemplate == true && e.getValue().nameLabel.contains(templatename))
	            {
	            	myvm =  e.getKey();
	            }
	        }
	        //克隆模板
	        VM newVm = myvm.createClone(connection, VMName);
	        //将模板转换为虚拟机
	        newVm.provision(connection);
	       // String a = newVm.getGenerationId(connection);
	        System.out.println("虚拟机创建完毕！");
	        //输出虚拟机的唯一标识
	        String uuid = newVm.getUuid(connection);
	       // System.out.println(a);
	        System.out.println(uuid);
	        return uuid;
         } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	public static void main(String[] args) {
		CreateVMByWake test = new CreateVMByWake();
		String uuid = test.crateVM("testzhang",1,2);
		System.out.println(uuid);
	}
}

/*开机和关机
        //   Should have done the trick. Let's see if it starts. 
        logln("Starting new VM.....");
        newVm.start(connection, false, false);

        logln("Shutting it down (hard).....");
        newVm.hardShutdown(connection);
        
        */
