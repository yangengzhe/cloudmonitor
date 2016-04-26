/**
 * 
 */
var path = "register/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('USAGE.controller.usageCtrl', {
	extend : 'Ext.app.Controller',
	views : ['usageIndex'],// 声明该控制层要用到的view
	stores : [ 'MenuStore4Tree','usageChartStore'],// 声明该控制层要用到的store
	models:['usageChartModel'],
	init : function() {
		this.control({
					"#usage_menu" : {
						beforeitemexpand : this.doMenuTreeLoadChildNodes,
						itemclick : this.doMenuTreeItemclick
					}
				});
	},
	
	/**
	 * 加载菜单数据
	 */
	doMenuTreeLoadChildNodes : function(node) {

		// 加载树的数据
		wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "GET",
//					url : webRoot + 'rest/webserver/testfunction?menuId='	+ node.get("id"),
					url : "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_menu_getChildrenMenu.json",
					timeout : 30000,// 30秒钟的查询超时
					success : function(data) {
						if (!Ext.isEmpty(data)) {
							node.removeAll(true);
							node.appendChild(data);
						}
					},
					error : function() {// 发生异常时
					}
				});
	},
/**
		 * 点击查询菜单
		 */
	    doMenuTreeItemclick:function(view,record,item,index,e){
	    	
	    	if(record.get("leaf")){
	    		Ext.getCmp('usage_detailForm').getForm().load({
//	    			url:restPath+'menu/edit/'+record.get("id"),
		    		url:"http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getServerDetail.json",
	    			method:'GET',
	    	        success : function() {
	    	        	
	    	        },
	    	        failure : function() {
	    	        	alert('failure');
	    	        }
	    		});
	    		//显示折线图
	    		Ext.getCmp('usage_chartsContent').setVisible(true);
	    		var chartCPU = Ext.getCmp('usage_chart_cpu');
	    		var chartMEM = Ext.getCmp('usage_chart_mem');
	    		var chartNET = Ext.getCmp('usage_chart_net');
		    		var url = "http://localhost:8080/cloudplatform//pages/cmp/server/usage/usage_getUsageChart.json";
		    		wake.ajax({
			    		contentType:'application/json',//声明提交的数据类型
			    		dataType:'json',//声明请求的数据类型
			    	    type: "GET",
			    	    url:url,
			    	    timeout:30000,//30秒钟的查询超时
			    	    success:function(data){
			    			if(data){
			    				chartCPU.getStore().loadData(data.dataList);//加载表格数据
			    				chartMEM.getStore().loadData(data.dataList);//加载表格数据
			    				chartNET.getStore().loadData(data.dataList);//加载表格数据
			    			}
			    			wake.showMessage(wake.TEXT.querySuccess);
			    		},
			    		error:function(){//发生异常时
			    		}
			    	});
//	        	}
//	        	else{
//	        		Ext.getCmp('mainLayout_menuList_serviceInfoForm').getForm().reset();
//	        	}
	    	}
	    	else{
	    		Ext.getCmp('usage_detailForm').getForm().reset();
	    		Ext.getCmp('usage_chartsContent').setVisible(false);
	    	}
	    }
});