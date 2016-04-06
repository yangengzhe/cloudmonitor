//首页 index
Ext.define('MYSERVER.view.MyappIndex', {
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
			id : 'searchMyapp_form',
			name : 'queryFieldForm',
			region : 'north',
			height : 100,
			bodyStyle : 'padding :5 5 5 5 ',

			defaults : {
				labelWidth : 60,
				//padding : '15 100 10',
				 margin: '20 5 20 50',
				columnWidth : 0.4,// 以25%的宽度赋给每个组件
				xtype : 'textfield',
				labelAlign : 'center',
				width : 200
			},
			tbar : [{
				xtype : 'button',
				text : wake.TEXT.query,
				iconCls : wake.CSS.queryImage,
				id : "searchMyapp"
			}, "-", {
				xtype : 'button',
				text : wake.TEXT.clear,
				iconCls : wake.CSS.resetImage,
				id : "resetSearchMyapp"
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{fieldLabel: '所在地区',
				name:'serverArea',
				xtype:'wakecombobox',
				dictUrl:'com.ices.csp.common.enumitem.cloudapp.CloudAppState',
				id:'serverArea',
				text:""}
			,{
				fieldLabel : '根据IP搜索',
				name : 'serverIP',
				id : 'serverIP',
				xtype : 'textfield',
				text : null,
				align : "center",
				//colspan:2,
				//width : 300
			}]
		});
		return me.queryField;
	},
	createShow : function() {
		var me = this;

		me.show = Ext.create("Ext.form.Panel", {
			id : "myapp_show",
			name : 'myappShow',
			region : 'center',
			height : 200,
			labelAlign : 'right',
			tbar : [{
				xtype : 'button',
				text : '上传',
				id : "myappup",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '部署',
				id : "myappdown",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '卸载',
				id : "myappshut",
				iconCls : wake.CSS.editImage
			}, "-", {
				xtype : 'button',
				text : '显示我的全部云应用',
				id : "showAllMyapp",
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
			items : [me.createShowServerGrid()]
		});
		return me.show;
	},
	createAppGrid : function() {
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
			store : Ext.create("MYSERVER.store.ServerStore"),
			selModel : sm,
			frame : false,

			columns:[
	    		    {xtype: 'rownumberer',width: 35,sortable: false ,header:'序号',hidden:true}
	    		    ,{dataIndex:'id',header:'id',width:50,sortable:true,hidden:true}
	    		    ,{dataIndex:'name',header:'云应用名称',width:100,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'version',header:'版本',width:75,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'state',header:'状态',width:50,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'type',header:'访问方式',width:75,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'url',header:'URL',width:200,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'registerTime',header:'注册时间',width:150,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'beginTime',header:'开始时间',width:150,sortable:true,editor:{xtype:'textfield'}}
	    		    ,{dataIndex:'endTime',header:'结束时间',width:150,sortable:true,editor:{xtype:'textfield'}}
	    		],
			bbar : paging
		});
		return me.myappGrid;
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
			store : Ext.create("MYSERVER.store.ServerStore"),
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
				dataIndex : 'type',
				header : '服务器类型',
				width : 150,
				editor : {
					xtype : 'textfield'
				}
			},{
				dataIndex : 'flag',
				header : '是否虚拟化',
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
				header : '登录名',
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