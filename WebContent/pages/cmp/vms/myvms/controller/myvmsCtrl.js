/**
 * 
 */
var path = "my_vms/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('MYVMS.controller.MyvmsCtrl', {
	extend : 'Ext.app.Controller',
	views : ['MyvmsIndex','AddVms'],// 声明该控制层要用到的view
	stores : ['MyvmsStore'],// 声明该控制层要用到的store
	init : function() {
		this.control({
			'#searchVms' : {
				click : this.doSearchVms//检索
			},
			'#resetSearchVms' : {
				click : this.doResetSearchVms//清空查询
			},
//			'#showAllMyapp' : {
//				click : this.doQueryVms//显示所有
//			},
			'#addVms' : {
				click : this.doaddVms//新增
			},
			'#updateVms' : {
				click : this.doUpdateVms//编辑
			},
			'#deleteVms' : {
				click : this.dodeleteVms//删除
			},
			//添加应用窗口的按钮
			'#commitVms' : {
				click : this.doCommitVms//提交
			},
			'#resetVms' : {
				click : this.doReset//重置
			},
			'#closeVms' : {
				click : this.doClose//关闭
			}
		});
	},
	doSearchVms : function() {
		var serverIndex = Ext.getCmp('myvmsIndex');
		var serverName = Ext.getCmp('vmsName').getValue();
		if (serverName == ""){
			control.doQueryVms();
			return;}
		var param = serverIndex.queryField.getForm().getFieldValues();
		serverIndex.setLoading(true);
		var myserverGrid = serverIndex.myvmsGrid;
		var url = restPath + path + "searchVms";//URL
//		var url = "http://localhost:8080/cloudplatform//pages/cmp/vms/myvms/myvms_getVms.json";
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "POST",
			url : url,
			data : param,// 将js对象转化为json数据
			timeout : 30000,
			success : function(data) {
				if (data) {
					if (data.vmsList.length == 0) {
						Ext.Msg.alert('提示', '没有符合的结果！');
						serverIndex.setLoading(false);// 关闭表格遮罩层
					} else {
						myserverGrid.getStore().loadData(data.vmsList);// 加载表格数据
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
	doResetSearchVms : function() {
		Ext.getCmp('searchVms_form').getForm().reset();
	},
	doQueryVms : function() {
		var serverIndex = Ext.getCmp('myvmsIndex');
		// 开启表格遮罩层
		serverIndex.setLoading(true);
		// 获取查询条件
		var myserverGrid = serverIndex.myvmsGrid;
		var pagingBean = myserverGrid.getPageBar().getPagingData();
		var url = restPath + path + "findAllVms"+ "?paging="+ Ext.JSON.encode(pagingBean);
//		var url = "http://localhost:8080/cloudplatform//pages/cmp/vms/myvms/myvms_getVms.json";
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "GET",
			url : url,
			timeout : 30000,// 30秒钟的查询超时
			success : function(data) {
				if (data) {
					myserverGrid.getStore().loadData(data.vmsList);// 加载表格数据
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
	doaddVms : function() {
		vmsWin = Ext.widget('addvms');
	},
	doUpdateVms : function() {
		var serverIndex = Ext.getCmp('myvmsIndex');
		var serverGrid = Ext.getCmp('myvmsGrid');

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

		vmsWin = Ext.widget('addvms');
		vmsWin.setTitle('编辑虚拟机');
		Ext.getCmp('vmsForm').loadRecord(updateData[0]);
	},
	dodeleteVms : function() {
		var serverIndex = Ext.getCmp('myvmsIndex');
		var serverGrid = Ext.getCmp('myvmsGrid');
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
					url : restPath + path + "deleteVms",
					data : deleteserverIds,// 将js对象转化为json数据
					timeout : 30000,
					success : function(data) {
						wake.showMessage("删除成功");
						serverIndex.setLoading(false);// 关闭表格遮罩层
						control.doQueryVms();
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
	doCommitVms : function(showMask) {
		var proWin = Ext.ComponentQuery.query('addvms')[0];
		var formValid = Ext.getCmp('vmsForm').getForm().isValid();
		if (!formValid)
			return;
		var serverId = Ext.getCmp('id').getValue();
		var server = Ext.getCmp('vmsForm').getForm().getFieldValues();
		if (showMask !== false)
			proWin.setLoading(true);// 开启表格遮罩层
		if (serverId == "") {
			wake.ajax({
				contentType : 'application/json',// 声明提交的数据类型
				dataType : 'json',// 声明请求的数据类型
				type : "PUT",
				url : restPath + path + "addVms",
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
						control.doQueryVms();
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
				url : restPath + path + "updateVms",
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
						control.doQueryVms();
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
		Ext.getCmp('vmsForm').getForm().reset();
		Ext.getCmp('id').setValue(serverId);
	},
	doClose : function() {
		Ext.getCmp('vmsForm').getForm().reset();
		vmsWin.close();
	}
});