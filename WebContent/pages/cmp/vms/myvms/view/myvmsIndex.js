//首页 index
Ext.define('MYVMS.view.MyvmsIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.myvmsindex',
	layout : 'border',
	initComponent : function() {
		var me = this;
		me.items = [me.createQueryField(), me.createShow()];
		me.callParent();
	},

	createQueryField : function() {
		var me = this;

		me.queryField = Ext.create("Ext.form.Panel", {
			id : 'searchVms_form',
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
				id : "searchVms"
			}, "-", {
				xtype : 'button',
				text : wake.TEXT.clear,
				iconCls : wake.CSS.resetImage,
				id : "resetSearchVms"
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{
				fieldLabel : '虚拟机名称',
				name : 'vmsName',
				id : 'vmsName',
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
			id : "defineVms_show",
			name : 'VmsShow',
			region : 'center',
			height : 200,
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '增加',
				id : "addVms",
				iconCls : wake.CSS.addImage
			}, "-", {
				xtype : 'button',
				text : '编辑',
				id : "updateVms",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '删除',
				id : "deleteVms",
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
		me.myvmsGrid = Ext.create('Wake.grid.Panel', {
			id : 'myvmsGrid',
			name : 'myvmsGrid',
			region : 'south',
			height : 200,
			store : Ext.create("MYVMS.store.MyvmsStore"),
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
				dataIndex : 'serverCode',
				header : '服务器Code' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'code',
				header : '虚拟机编码' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'name',
				header : '虚拟机名称' ,
				width : 150,
				sortable : true,
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
				dataIndex : 'ip',
				header : 'ip',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'cpu',
				header : 'CPU',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'memory',
				header : '内存',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'disk',
				header : '硬盘',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'bandwidth',
				header : '网络带宽',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'desc',
				header : '备注',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}],
			bbar : paging
		});
		return me.myvmsGrid;
	}
});