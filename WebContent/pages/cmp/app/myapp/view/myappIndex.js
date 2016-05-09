//首页 index
Ext.define('MYAPP.view.MyappIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.myappindex',
	layout : 'border',
	initComponent : function() {
		var me = this;
		me.items = [me.createQueryField(), me.createShow()];
		me.callParent();
	},

	createQueryField : function() {
		var me = this;

		me.queryField = Ext.create("Ext.form.Panel", {
			id : 'searchapp_form',
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
				id : "searchapp"
			}, "-", {
				xtype : 'button',
				text : wake.TEXT.clear,
				iconCls : wake.CSS.resetImage,
				id : "resetSearchapp"
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{
				fieldLabel : '应用名称',
				name : 'appName',
				id : 'appName',
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
			id : "defineapp_show",
			name : 'appShow',
			region : 'center',
			height : 200,
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '增加',
				id : "addapp",
				iconCls : wake.CSS.addImage
			}, "-", {
				xtype : 'button',
				text : '编辑',
				id : "updateapp",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '删除',
				id : "deleteapp",
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
		me.myappGrid = Ext.create('Wake.grid.Panel', {
			id : 'myappGrid',
			name : 'myappGrid',
			region : 'south',
			height : 200,
			store : Ext.create("MYAPP.store.MyappStore"),
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
				dataIndex : 'vmCode',
				header : '虚拟机Code' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'code',
				header : '应用编码' ,
				width : 150,
				sortable : true,
				editor : {
					xtype : 'textfield'
				}
			}, {
				dataIndex : 'name',
				header : '应用名称' ,
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
				dataIndex : 'desc',
				header : '备注',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			}],
			bbar : paging
		});
		return me.myappGrid;
	}
});