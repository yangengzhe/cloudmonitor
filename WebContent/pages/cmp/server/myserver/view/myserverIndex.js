//首页 index
Ext.define('MYSERVER.view.MyserverIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.myserverindex',
	layout : 'border',
	initComponent : function() {
		var me = this;
		me.items = [me.createQueryField(), me.createShow()];
		me.callParent();
	},

	createQueryField : function() {
		var me = this;

		me.queryField = Ext.create("Ext.form.Panel", {
			id : 'searchServer_form',
			name : 'queryFieldForm',
			region : 'north',
			height : 80,
			bodyStyle : 'padding :5 5 5 5 ',

			defaults : {
				labelWidth : 60,
				padding : '15 100 10',
				columnWidth : 0.4,// 以25%的宽度赋给每个组件
				xtype : 'textfield',
				labelAlign : 'center',
				width : 200
			},
			tbar : [{
				xtype : 'button',
				text : wake.TEXT.query,
				iconCls : wake.CSS.queryImage,
				id : "searchServer"
			}, "-", {
				xtype : 'button',
				text : wake.TEXT.clear,
				iconCls : wake.CSS.resetImage,
				id : "resetSearchServer"
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{fieldLabel: '节点',
				name:'nodeName',
				xtype:'wakecombobox',
				dictUrl:'com.ices.csp.node.domain.Node.findAll',
				id:'searchList_node',
				text:""},{
				fieldLabel : '服务器名称',
				name : 'serverName',
				id : 'serverName',
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
			id : "defineServer_show",
			name : 'ServerShow',
			region : 'center',
			height : 200,
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '增加',
				id : "addServer",
				iconCls : wake.CSS.addImage
			}, "-", {
				xtype : 'button',
				text : '编辑',
				id : "updateServer",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '删除',
				id : "deleteServer",
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
			items : [me.createShowServerGrid()]
		});
		return me.show;
	},
	createShowServerGrid : function() {
		var me = this;
		var paging = Ext.create('Wake.toolbar.Paging', {
			name : 'gridPagingBar',
			displayInfo : true,
			everySize : 15
		});
		var sm = Ext.create('Ext.selection.CheckboxModel', {
			mode : "MULTI"
		});
		me.myserverGrid = Ext.create('Wake.grid.Panel', {
			id : 'myserverGrid',
			name : 'myserverGrid',
			region : 'south',
			height : 200,
			store : Ext.create("MYSERVER.store.MyserverStore"),
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
				header : '服务器编码' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'name',
				header : '服务器名称' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'nodeName',
				header : '节点',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'state',
				header : '状态',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'vpaltform',
				header : '虚拟化平台',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'ip',
				header : 'IP地址',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'user',
				header : 'CPU',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'user',
				header : '内存',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'user',
				header : '网络带宽',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'user',
				header : '备注',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}],
			bbar : paging
		});
		return me.myserverGrid;
	}
});