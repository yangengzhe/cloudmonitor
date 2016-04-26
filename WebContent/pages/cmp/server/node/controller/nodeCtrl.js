/**
 * 
 */
var path = "node/";// 资源请求路径

Ext.define('NODE.controller.NodeCtrl', {
	extend : 'Ext.app.Controller',
	views : ['NodeIndex','AddNode','AddRoute'],// 声明该控制层要用到的view
	stores : ['NodeStore','RouteStore'],// 声明该控制层要用到的store
	init : function() {
		this.control({

			'#addNode' : {
				click : this.doaddNode//新增
			},
			'#deleteNode' : {
				click : this.dodeleteNode//删除
			},
			'#updateNode' : {
				click : this.doUpdateNode//编辑
			},
			'#showAllNode' : {
				click : this.doQueryNode//显示所有节点
			},
			'#searchNode' : {
				click : this.doSearchNode//查询
			},
			'#commitNode' : {
				click : this.doCommitNode
			},
			'#resetNode' : {
				click : this.doReset
			},
			'#resetSearchNode' : {
				click : this.doResetSearchNode//清空查询
			},
			'#closeNode' : {
				click : this.doClose
			},
			
			
			//路由相关
			"#nodeGrid" : {
				itemclick : this.doNodeGridItemclick
			},
			'#addRoute' : {
				click : this.doaddRoute//新增
			},
			'#deleteRoute' : {
				click : this.dodeleteRoute//删除
			},
			'#updateRoute' : {
				click : this.doUpdateRoute//编辑
			}
		});
	},
	doUpdateNode : function() {
		var nodeIndex = Ext.getCmp('nodeIndex');
		var nodeGrid = Ext.getCmp('nodeGrid');

		var sIds = nodeGrid.getSelectionModel().getSelection().length;

		if (sIds == 0) {
			Ext.Msg.alert('提示', '请选中节点。');
			return;
		}
		if (sIds > 1) {
			Ext.Msg.alert('提示', '请只选择一个节点进行编辑！');
			return;
		}
		var updateData = nodeGrid.getSelectionModel().getSelection();

		nodeWin = Ext.widget('addnode');
		nodeWin.setTitle('编辑节点');
		Ext.getCmp('nodeForm').loadRecord(updateData[0]);
	},
	doaddNode : function() {
		nodeWin = Ext.widget('addnode');
	},

	doQueryNode : function() {
		var nodeIndex = Ext.getCmp('nodeIndex');
		// 开启表格遮罩层
		nodeIndex.setLoading(true);
		// 获取查询条件
		var nodeGrid = nodeIndex.nodeGrid;
		var pagingBean = nodeGrid.getPageBar().getPagingData();
		var url = restPath + path + "findAllNode"+ "?paging="+ Ext.JSON.encode(pagingBean);
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "GET",
			url : url,
			timeout : 30000,// 30秒钟的查询超时
			success : function(data) {
				if (data) {
					nodeGrid.getStore().loadData(data.nodeList);// 加载表格数据
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
	dodeleteNode : function() {
		var nodeIndex = Ext.getCmp('nodeIndex');
		var nodeGrid = Ext.getCmp('nodeGrid');
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
					path : {
						'grid' : {// 校验用的表格配置
							'com.ices.csp.node.dto.NodeDto' : 'nodeGrid'
						}
					},
					timeout : 30000,
					success : function(data) {
						wake.showMessage("删除成功");
						nodeIndex.setLoading(false);// 关闭表格遮罩层
						control.doQueryNode();
					},
					error : function() {// 发生异常时
						wake.showMessage("删除失败");
						nodeIndex.setLoading(false);// 关闭表格遮罩层
					}
				});
			}
		});
	},

	doSearchNode : function() {
		var nodeIndex = Ext.getCmp('nodeIndex');
		var nodeName = Ext.getCmp('nodeName').getValue();
		if (nodeName == "")
			return;
		var param = nodeIndex.queryField.getForm().getFieldValues();
		nodeIndex.setLoading(true);
		var nodeGrid = nodeIndex.nodeGrid;
		var url = restPath + path + "searchNode";

		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "POST",
			url : url,
			data : param,// 将js对象转化为json数据
			path : {
				'grid' : {// 校验用的表格配置
					'com.ices.csp.node.dto.NodeDto' : 'nodeGrid'
				}
			},
			timeout : 30000,
			success : function(data) {
				if (data) {
					if (data.nodeList.length == 0) {
						Ext.Msg.alert('提示', '没有符合的结果！');
						control.doQueryNode();
					} else {
						nodeGrid.getStore().loadData(data.nodeList);// 加载表格数据
					}
				}
				nodeIndex.setLoading(false);// 关闭表格遮罩层

			},
			error : function() {// 发生异常时
				wake.showMessage("查询失败");
				nodeIndex.setLoading(false);// 关闭表格遮罩层
			}
		});
	},
	doCommitNode : function(showMask) {
		var proWin = Ext.ComponentQuery.query('addnode')[0];
		var formValid = Ext.getCmp('nodeForm').getForm().isValid();
		if (!formValid)
			return;
		var nodeId = Ext.getCmp('id').getValue();
		var node = Ext.getCmp('nodeForm').getForm().getFieldValues();
		if (showMask !== false)
			proWin.setLoading(true);// 开启表格遮罩层
		if (nodeId == "") {
			wake.ajax({
				contentType : 'application/json',// 声明提交的数据类型
				dataType : 'json',// 声明请求的数据类型
				type : "PUT",
				url : restPath + path + "addNode",
				data : node,// 将js对象转化为json数据
				timeout : 30000,// 30秒钟的查询超时
				success : function(data) {
					if (data.status == "error") {
						Ext.Msg.alert('提示', '已经存在相同的节点编码！');
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
					} else {
						wake.showMessage("添加成功");
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
						control.doQueryNode();
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
				url : restPath + path + "updateNode",
				data : node,// 将js对象转化为json数据
				timeout : 30000,// 30秒钟的查询超时
				success : function(data) {
					if (data.status == "error") {
						Ext.Msg.alert('提示', '已经存在相同的节点编码！');
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
					} else {
						wake.showMessage("修改成功");
						if (showMask !== false)
							proWin.setLoading(false);// 关闭表格遮罩层
						control.doQueryNode();
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
		var nodeId = Ext.getCmp('id').getValue();
		Ext.getCmp('nodeForm').getForm().reset();
		Ext.getCmp('id').setValue(nodeId);
	},
	doClose : function() {
		Ext.getCmp('nodeForm').getForm().reset();
		nodeWin.close();
	},
	doResetSearchNode : function() {
		Ext.getCmp('searchNode_form').getForm().reset();
	},
	
	
	/**路由部分操作
	 * 
	 */
	
	doQueryRoute:function(nodeId){
		var nodeIndex = Ext.getCmp('nodeIndex');
		// 开启表格遮罩层
		nodeIndex.setLoading(true);
		// 获取查询条件
		var nodeGrid = nodeIndex.routeGrid;
		var pagingBean = nodeGrid.getPageBar().getPagingData();
//		var url = restPath + path + "findAllNode"+ "?paging="+ Ext.JSON.encode(pagingBean);
		var url = "http://localhost:8080/cloudplatform//pages/cmp/server/node/getRoute_routes"+nodeId+".json"
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
			contentType : 'application/json',// 声明提交的数据类型
			dataType : 'json',// 声明请求的数据类型
			type : "GET",
			url : url,
			timeout : 30000,// 30秒钟的查询超时
			success : function(data) {
				if (data) {
					nodeGrid.getStore().loadData(data.routeList);// 加载表格数据
//					nodeGrid.getPageBar().setPagingData(data.pagingBean);// 加载分页数据
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
	doNodeGridItemclick:function(dataview, record, item, index, e){
		   control.doQueryRoute(dataview.getStore().getAt(index).data.id);
	  },
	
	  doUpdateRoute : function() {
			var nodeIndex = Ext.getCmp('nodeIndex');
			var nodeGrid = Ext.getCmp('routeGrid');

			var sIds = nodeGrid.getSelectionModel().getSelection().length;

			if (sIds == 0) {
				Ext.Msg.alert('提示', '请选中节点。');
				return;
			}
			if (sIds > 1) {
				Ext.Msg.alert('提示', '请只选择一个节点进行编辑！');
				return;
			}
			var updateData = nodeGrid.getSelectionModel().getSelection();

			nodeWin = Ext.widget('addroute');
			nodeWin.setTitle('编辑路由');
			Ext.getCmp('routeForm').loadRecord(updateData[0]);
		},
		doaddRoute : function() {
			nodeWin = Ext.widget('addroute');
		},
		dodeleteRoute : function() {
			var nodeIndex = Ext.getCmp('nodeIndex');
			var nodeGrid = Ext.getCmp('routeGrid');
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
							control.doQueryRoute();
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