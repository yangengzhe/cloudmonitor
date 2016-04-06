package com.ices.csp.vmorder.createvms;

//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SetIP {
	public  int setIpByWake(String IPAddress,int port,String finalIP){
        System.out.println("客户端启动...");     
            Socket socket = null;  
            try {  
                //创建一个流套接字并将其连接到指定主机上的指定端口号  
                socket = new Socket(IPAddress, port);    
                    
//                //读取服务器端数据    
//                DataInputStream input = new DataInputStream(socket.getInputStream());    
                //向服务器端发送数据    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
               // System.out.print("请输入要设置的ip地址: \t");    
               // String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
                out.writeUTF(finalIP);
                System.out.println("客户端将关闭连接");    
                Thread.sleep(500);     
                out.close();  
               // input.close(); 
                return 1;
            } catch (Exception e) {  
                System.out.println("客户端异常:" + e.getMessage());   
                return 0;
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) {  
                        socket = null;   
                        System.out.println("客户端 finally 异常:" + e.getMessage());   
                    }  
                }  
            }  
//        }    
    }
}
