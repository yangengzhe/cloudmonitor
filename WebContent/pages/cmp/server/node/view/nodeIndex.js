Ext.define('NODE.view.NodeIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.nodeindex',
	layout : 'border',
	initComponent : function() {
		var me = this;
		me.items = [me.createQueryField(), me.createShow(),me.createShowRoute()];
		me.callParent();
	},
	createQueryField : function() {
		var me = this;

		me.queryField = Ext.create("Ext.form.Panel", {
			id : 'searchNode_form',
			name : 'queryFieldForm',
			region : 'north',
			height : 80,
			bodyStyle : 'padding :5 5 5 5 ',

			defaults : {
				labelWidth : 60,
				padding : '15 100 10',
				columnWidth : 0.4// 以25%的宽度赋给每个组件
				,
				xtype : 'textfield',
				labelAlign : 'center',
				width : 200
			},
			tbar : [{
				xtype : 'button',
				text : wake.TEXT.query,
				iconCls : wake.CSS.queryImage,
				id : "searchNode"
			}, "-", {
				xtype : 'button',
				text : wake.TEXT.clear,
				iconCls : wake.CSS.resetImage,
				id : "resetSearchNode"
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{
				fieldLabel : '节点名称',
				name : 'nodeName',
				id : 'nodeName',
				xtype : 'textfield',
				text : null,
				align : "center"
			}]
		});
		return me.queryField;
	},
	createShow : function() {
		var me = this;

		me.show = Ext.create("Ext.form.Panel", {
			id : "defineNode_show",
			name : 'NodeShow',
			region : 'center',
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '增加',
				id : "addNode",
				iconCls : wake.CSS.addImage
			}, "-", {
				xtype : 'button',
				text : '编辑',
				id : "updateNode",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '删除',
				id : "deleteNode",
				iconCls : wake.CSS.deleteImage
			}, "-", {
				xtype : 'button',
				text : '显示全部节点',
				id : "showAllNode",
				iconCls : wake.CSS.queryImage
			}]

			,
			defaults : {
				labelWidth : 60,
				padding : '3 5',
				columnWidth : 0.5// 以25%的宽度赋给每个组件
				,
				xtype : 'textfield',
				labelAlign : 'right'
			},
			layout : "fit",
			items : [me.createShowNodeGrid()]
		});
		return me.show;
	},
	createShowNodeGrid : function() {
		var me = this;
		var paging = Ext.create('Wake.toolbar.Paging', {
			name : 'gridPagingBar',
			displayInfo : true,
			everySize : 15
		});
		var sm = Ext.create('Ext.selection.CheckboxModel', {
			mode : "MULTI"
		});
		me.nodeGrid = Ext.create('Wake.grid.Panel', {
			id : 'nodeGrid',
			name : 'nodeGrid',
			region : 'south',
			store : Ext.create("NODE.store.NodeStore"),
			selModel : sm,
			frame : false,

			columns : [{
				xtype : 'rownumberer',
				width : 35,
				sortable : false,
				header : '序号',
				hidden : true
			}, {
				dataIndex : 'id',
				header : 'id',
				width : 150,
				sortable : true,
				hidden : true
			}, {
				dataIndex : 'code',
				header : '节点编码' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'name',
				header : '节点名称' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'principal',
				header : '负责人',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'telephone',
				header : '电话',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'mobile',
				header : '手机',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'email',
				header : '邮箱',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'address',
				header : '地址',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}
			,{
				dataIndex : 'desc',
				header : '说明',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}
			],
			bbar : paging
		});
		
		
		return me.nodeGrid;
	},
	createShowRoute : function() {
		var me = this;

		me.show = Ext.create("Ext.form.Panel", {
			id : "defineNode_showRoute",
			name : 'routeShow',
			region : 'south',
			height : 200,
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '增加',
				id : "addRoute",
				iconCls : wake.CSS.addImage
			}, "-", {
				xtype : 'button',
				text : '编辑',
				id : "updateRoute",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '删除',
				id : "deleteRoute",
				iconCls : wake.CSS.deleteImage
			}]
			,
			defaults : {
				labelWidth : 60,
				padding : '3 5',
				columnWidth : 0.5// 以25%的宽度赋给每个组件
				,
				xtype : 'textfield',
				labelAlign : 'right'
			},
			layout : "fit",
			items : [me.createShowRouteGrid()]
		});
		return me.show;
	},
	createShowRouteGrid : function() {
		var me = this;
		var paging = Ext.create('Wake.toolbar.Paging', {
			name : 'gridPagingBar',
			displayInfo : true,
			everySize : 15
		});
		var sm = Ext.create('Ext.selection.CheckboxModel', {
			mode : "MULTI"
		});
		me.routeGrid = Ext.create('Wake.grid.Panel', {
			id : 'routeGrid',
			name : 'routeGrid',
			region : 'north',
			store : Ext.create("NODE.store.RouteStore"),
			selModel : sm,
			frame : false,

			columns : [{
				xtype : 'rownumberer',
				width : 35,
				sortable : false,
				header : '序号',
				hidden : true
			}, {
				dataIndex : 'id',
				header : 'id',
				width : 150,
				sortable : true,
				hidden : true
			}, {
				dataIndex : 'code',
				header : '节点编码' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'nodeid',
				header : '节点ID' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'name',
				header : '路由名字',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'superroute',
				header : '上一级',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'desc',
				header : '说明',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}
			],
			bbar : paging
		});
		return me.routeGrid;
	}
});