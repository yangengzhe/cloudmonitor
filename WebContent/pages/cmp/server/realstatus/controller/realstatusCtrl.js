/**
 * 
 */
var path = "status/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('REALSTATUS.controller.realstatusCtrl', {
	extend : 'Ext.app.Controller',
	views : ['realstatusIndex'],// 声明该控制层要用到的view
	stores : ['MenuStore4Tree', 'statusChartStore'],// 声明该控制层要用到的store
	models : ['statusChartModel'],
	init : function() {
		this.control({
					"#realstatus_menu" : {
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
			// url : webRoot + 'rest/webserver/testfunction?menuId=' +
			// node.get("id"),
			url : restPath + "cmp_menu/" + "getMenuChildren?id="+node.get("id"),
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
	doMenuTreeItemclick : function(view, record, item, index, e) {
		
		if (record.get("leaf")) {
			Ext.getCmp('realstatus_detailForm').getForm().load({
				// url:restPath+'menu/edit/'+record.get("id"),
				url : restPath + path + "serverDetail?id="+record.get("id"),
				method : 'GET',
				success : function() {

				},
				failure : function() {
					alert('failure');
				}
			});
			// 显示折线图
			Ext.getCmp('realstatus_chartsContent').setVisible(true);
			var chartCPU = Ext.getCmp('realstatus_chart_cpu');
			var chartMEM = Ext.getCmp('realstatus_chart_mem');
			var chartNET = Ext.getCmp('realstatus_chart_net');
			// var serviceId = record.get("serviceId");
			// if(serviceId!=null && serviceId!=""){
			var url = restPath + path + "findAllStatus?id="+record.get("id");
//			var url = "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getRealstatusChart.json";
			wake.ajax({
						contentType : 'application/json',// 声明提交的数据类型
						dataType : 'json',// 声明请求的数据类型
						type : "GET",
						url : url,
						timeout : 30000,// 30秒钟的查询超时
						success : function(data) {
							if (data) {
								chartCPU.getStore().loadData(data.statusList);// 加载表格数据
								chartMEM.getStore().loadData(data.statusList);// 加载表格数据
								chartNET.getStore().loadData(data.statusList);// 加载表格数据
							}
							wake.showMessage(wake.TEXT.querySuccess);
						},
						error : function() {// 发生异常时
						}
					});
			// }
			// else{
			// Ext.getCmp('mainLayout_menuList_serviceInfoForm').getForm().reset();
			// }
		} else {
			Ext.getCmp('realstatus_detailForm').getForm().reset();
			Ext.getCmp('realstatus_chartsContent').setVisible(false);
		}
	}
});