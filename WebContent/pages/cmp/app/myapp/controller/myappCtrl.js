/**
 * 
 */
var path = "app/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('MYAPP.controller.MyappCtrl', {
	extend : 'Ext.app.Controller',
	views : ['MyappIndex','Addapp'],// 声明该控制层要用到的view
	stores : ['MyappStore'],// 声明该控制层要用到的store
	init : function() {
		this.control({
			'#searchapp' : {
				click : this.doSearchapp//检索
			},
			'#resetSearchapp' : {
				click : this.doResetSearchapp//清空查询
			},
//			'#showAllMyapp' : {
//				click : this.doQueryapp//显示所有
//			},
			'#addapp' : {
				click : this.doaddapp//新增
			},
			'#updateapp' : {
				click : this.doUpdateapp//编辑
			},
			'#deleteapp' : {
				click : this.dodeleteapp//删除
			},
			//添加应用窗口的按钮
			'#commitapp' : {
				click : this.doCommitapp//提交
			},
			'#resetapp' : {
				click : this.doReset//重置
			},
			'#closeapp' : {
				click : this.doClose//关闭
			}
		});
	},
	doSearchapp : function() {
		var serverIndex = Ext.getCmp('myappIndex');
		var serverName = Ext.getCmp('appName').getValue();
		if (serverName == ""){
			control.doQueryapp();
			return;}
		var param = serverIndex.queryField.getForm().getFieldValues();
		serverIndex.setLoading(true);
		var myserverGrid = serverIndex.myappGrid;
//		var url = restPath + path + "searchServer";//URL
		var url = "http://localhost:8080/cloudplatform//pages/cmp/app/myapp/myapp_getApp.json";
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "POST",
			url : url,
			data : param,// 将js对象转化为json数据
			timeout : 30000,
			success : function(data) {
				if (data) {
					if (data.appList.length == 0) {
						Ext.Msg.alert('提示', '没有符合的结果！');
						control.doQueryServer();
					} else {
						myserverGrid.getStore().loadData(data.appList);// 加载表格数据
					}
				}
				serverIndex.setLoading(false);// 关闭表格遮罩层

			},
			error : function() {// 发生异常时
				wake.showMessage("查询失败");
				serverIndex.setLoading(false);// 关闭表格遮罩层
			}
		});
	},
	doResetSearchapp : function() {
		Ext.getCmp('searchapp_form').getForm().reset();
	},
	doQueryapp : function() {
		var serverIndex = Ext.getCmp('myappIndex');
		// 开启表格遮罩层
		serverIndex.setLoading(true);
		// 获取查询条件
		var myserverGrid = serverIndex.myappGrid;
		var pagingBean = myserverGrid.getPageBar().getPagingData();
//		var url = restPath + path + "findAllServer"+ "?paging="+ Ext.JSON.encode(pagingBean);
		var url = "http://localhost:8080/cloudplatform//pages/cmp/app/myapp/myapp_getApp.json";
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "GET",
			url : url,
			timeout : 30000,// 30秒钟的查询超时
			success : function(data) {
				if (data) {
					myserverGrid.getStore().loadData(data.appList);// 加载表格数据
					myserverGrid.getPageBar().setPagingData(data.pagingBean);// 加载分页数据
				}
				// 关闭遮罩 并提示
				wake.showMessage("查询完毕");
				serverIndex.setLoading(false);
			},
			error : function() {// 发生异常时
				// 关闭表格遮罩层
				serverIndex.setLoading(false);
			}
		});

	},
	doaddapp : function() {
		appWin = Ext.widget('addapp');
	},
	doUpdateapp : function() {
		var serverIndex = Ext.getCmp('myappIndex');
		var serverGrid = Ext.getCmp('myappGrid');

		var sIds = serverGrid.getSelectionModel().getSelection().length;

		if (sIds == 0) {
			Ext.Msg.alert('提示', '请选中服务器。');
			return;
		}
		if (sIds > 1) {
			Ext.Msg.alert('提示', '请只选择一个服务器进行编辑！');
			return;
		}
		var updateData = serverGrid.getSelectionModel().getSelection();

		appWin = Ext.widget('addapp');
		appWin.setTitle('编辑虚拟机');
		Ext.getCmp('appForm').loadRecord(updateData[0]);
	},
	dodeleteapp : function() {
		var serverIndex = Ext.getCmp('myappIndex');
		var serverGrid = Ext.getCmp('myappGrid');
		// 获取选中行IDs
		var sIds = serverGrid.getSelectionModel().getSelection().length;

		if (sIds == 0) {
			Ext.Msg.alert('提示', '请选中服务器。');
			return;
		}
		var deleteData = serverGrid.getSelectionModel().getSelection();
		var deleteserverIds = "";
		for (var i = 0; ln = deleteData.length, i < ln; i++) {
			// content.push(deleteData[i].data.logiCorpName);
			if (i == (deleteData.length - 1)) {
				deleteserverIds = deleteserverIds + deleteData[i].data.id;
			} else {
				deleteserverIds = deleteserverIds + deleteData[i].data.id + ",";
			}
		}
		Ext.Msg.confirm("提示", "确定删除所选择的服务器？", function(con) {
			if (con == "yes") {
				wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "POST",
					url : restPath + path + "deleteServer",
					data : deleteserverIds,// 将js对象转化为json数据
					timeout : 30000,
					success : function(data) {
						wake.showMessage("删除成功");
						serverIndex.setLoading(false);// 关闭表格遮罩层
						control.doQueryapp();
					},
					error : function() {// 发生异常时
						wake.showMessage("删除失败");
						serverIndex.setLoading(false);// 关闭表格遮罩层
					}
				});
			}
		});
	},
	//添加窗口的功能
	doCommitapp : function(showMask) {
		var proWin = Ext.ComponentQuery.query('addapp')[0];
		var formValid = Ext.getCmp('appForm').getForm().isValid();
		if (!formValid)
			return;
		var serverId = Ext.getCmp('id').getValue();
		var server = Ext.getCmp('appForm').getForm().getFieldValues();
		if (showMask !== false)
			proWin.setLoading(true);// 开启表格遮罩层
		if (serverId == "") {
			wake.ajax({
				contentType : 'application/json',// 声明提交的数据类型
				dataType : 'json',// 声明请求的数据类型
				type : "PUT",
				url : restPath + path + "addServer",
				data : server,// 将js对象转化为json数据
				timeout : 30000,// 30秒钟的查询超时
				success : function(data) {
					if (data.status == "error") {
						Ext.Msg.alert('提示', '已经存在相同的服务器编码！');
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
					} else {
						wake.showMessage("添加成功");
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
						control.doQueryapp();
						control.doClose();
					}
				},
				error : function() {// 发生异常时
					wake.showMessage("添加失败");
					if (showMask !== false)
						proWin.setLoading(false);// 关闭表格遮罩层
				}
			});
		} else {
			wake.ajax({
				contentType : 'application/json',// 声明提交的数据类型
				dataType : 'json',// 声明请求的数据类型
				type : "POST",
				url : restPath + path + "updateServer",
				data : server,// 将js对象转化为json数据
				timeout : 30000,// 30秒钟的查询超时
				success : function(data) {
					if (data.status == "error") {
						Ext.Msg.alert('提示', '已经存在相同的服务器编码！');
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
					} else {
						wake.showMessage("修改成功");
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
						control.doQueryapp();
						control.doClose();
					}
				},
				error : function() {// 发生异常时
					wake.showMessage("修改失败");
					if (showMask !== false)
						proWin.setLoading(false);// 关闭表格遮罩层
				}
			});
		}
	},
	doReset : function() {
		var serverId = Ext.getCmp('id').getValue();
		Ext.getCmp('appForm').getForm().reset();
		Ext.getCmp('id').setValue(serverId);
	},
	doClose : function() {
		Ext.getCmp('appForm').getForm().reset();
		appWin.close();
	}
});