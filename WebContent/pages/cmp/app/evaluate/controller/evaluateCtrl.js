/**
 * 
 */
var path = "evaluate/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('EVALUATE.controller.evaluateCtrl', {
	extend : 'Ext.app.Controller',
	views : ['evaluateIndex'],// 声明该控制层要用到的view
	stores : ['MenuStore4Tree', 'statusChartStore','evaluateAlertStore'],// 声明该控制层要用到的store
	models : ['statusChartModel','evaluateAlertModel'],
	init : function() {
		this.control({
					"#evaluate_menu" : {
						beforeitemexpand : this.doMenuTreeLoadChildNodes,
						itemclick : this.doMenuTreeItemclick
					},
					'#deleteAlert' : {
						click : this.dodeleteAlert//删除
					},
					'#updateAlert' : {
						click : this.updateAlert//编辑
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
			url :  restPath + "cmp_menu/" + "getMenuAppChildren?id="+node.get("id"),
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
			Ext.getCmp('evaluate_detailForm').getForm().load({
				// url:restPath+'menu/edit/'+record.get("id"),
				url : restPath + path + "appDetail?id="+record.get("id"),
				method : 'GET',
				success : function() {

				},
				failure : function() {
					alert('failure');
				}
			});
			// 显示折线图
			Ext.getCmp('evaluate_chartsContent').setVisible(true);
			var chartNET = Ext.getCmp('evaluate_chart_net');
			var url = restPath + path + "findAllStatus?id="+record.get("id");
			wake.ajax({
						contentType : 'application/json',// 声明提交的数据类型
						dataType : 'json',// 声明请求的数据类型
						type : "GET",
						url : url,
						timeout : 30000,// 30秒钟的查询超时
						success : function(data) {
							if (data) {
								chartNET.getStore().loadData(data.dataList);// 加载表格数据
							}
							wake.showMessage(wake.TEXT.querySuccess);
							control.doQueryAlert();
						},
						error : function() {// 发生异常时
						}
					});
		} else {
			Ext.getCmp('evaluate_detailForm').getForm().reset();
			Ext.getCmp('evaluate_chartsContent').setVisible(false);
		}
	},
	doQueryAlert:function(){
		var nodeIndex = Ext.getCmp('evaluateIndex');
		// 开启表格遮罩层
		nodeIndex.setLoading(true);
		// 获取查询条件
		var nodeGrid = nodeIndex.alertGrid;
		var pagingBean = nodeGrid.getPageBar().getPagingData();
//		var url = restPath + path + "findAllNode"+ "?paging="+ Ext.JSON.encode(pagingBean);
		var url = "http://localhost:8080/cloudplatform//pages/cmp/app/evaluate/evaluate_getAlert.json";
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "GET",
			url : url,
			timeout : 30000,// 30秒钟的查询超时
			success : function(data) {
				if (data) {
					nodeGrid.getStore().loadData(data.alertList);// 加载表格数据
					nodeGrid.getPageBar().setPagingData(data.pagingBean);// 加载分页数据
				}
				// 关闭遮罩 并提示
				wake.showMessage("查询完毕");
				nodeIndex.setLoading(false);
			},
			error : function() {// 发生异常时
				// 关闭表格遮罩层
				nodeIndex.setLoading(false);
			}
		});
	},
	updateAlert : function() {
			var nodeIndex = Ext.getCmp('evaluateIndex');
			var nodeGrid = Ext.getCmp('alertGrid');
			// 获取选中行IDs
			var sIds = nodeGrid.getSelectionModel().getSelection().length;

			if (sIds == 0) {
				Ext.Msg.alert('提示', '请选中节点。');
				return;
			}
			var deleteData = nodeGrid.getSelectionModel().getSelection();
			var deletenodeIds = "";
			for (var i = 0; ln = deleteData.length, i < ln; i++) {
				// content.push(deleteData[i].data.logiCorpName);
				if (i == (deleteData.length - 1)) {
					deletenodeIds = deletenodeIds + deleteData[i].data.id;
				} else {
					deletenodeIds = deletenodeIds + deleteData[i].data.id + ",";
				}
			}
			Ext.Msg.confirm("提示", "确定标记所选择的节点？", function(con) {
				if (con == "yes") {
					wake.ajax({
						contentType : 'application/json',// 声明提交的数据类型
						dataType : 'json',// 声明请求的数据类型
						type : "POST",
						url : restPath + path + "signNode",
						data : deletenodeIds,// 将js对象转化为json数据
						timeout : 30000,
						success : function(data) {
							wake.showMessage("标记成功");
							nodeIndex.setLoading(false);// 关闭表格遮罩层
							control.doQueryAlert();
						},
						error : function() {// 发生异常时
							wake.showMessage("标记失败");
							nodeIndex.setLoading(false);// 关闭表格遮罩层
						}
					});
				}
			});
		},
		dodeleteAlert : function() {
			var nodeIndex = Ext.getCmp('evaluateIndex');
			var nodeGrid = Ext.getCmp('alertGrid');
			// 获取选中行IDs
			var sIds = nodeGrid.getSelectionModel().getSelection().length;

			if (sIds == 0) {
				Ext.Msg.alert('提示', '请选中节点。');
				return;
			}
			var deleteData = nodeGrid.getSelectionModel().getSelection();
			var deletenodeIds = "";
			for (var i = 0; ln = deleteData.length, i < ln; i++) {
				// content.push(deleteData[i].data.logiCorpName);
				if (i == (deleteData.length - 1)) {
					deletenodeIds = deletenodeIds + deleteData[i].data.id;
				} else {
					deletenodeIds = deletenodeIds + deleteData[i].data.id + ",";
				}
			}
			Ext.Msg.confirm("提示", "确定删除所选择的节点？", function(con) {
				if (con == "yes") {
					wake.ajax({
						contentType : 'application/json',// 声明提交的数据类型
						dataType : 'json',// 声明请求的数据类型
						type : "POST",
						url : restPath + path + "deleteNode",
						data : deletenodeIds,// 将js对象转化为json数据
						timeout : 30000,
						success : function(data) {
							wake.showMessage("删除成功");
							nodeIndex.setLoading(false);// 关闭表格遮罩层
							control.doQueryAlert();
						},
						error : function() {// 发生异常时
							wake.showMessage("删除失败");
							nodeIndex.setLoading(false);// 关闭表格遮罩层
						}
					});
				}
			});
		}
});