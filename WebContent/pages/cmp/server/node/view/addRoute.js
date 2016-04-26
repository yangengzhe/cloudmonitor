/**
 * 
 */

Ext.define('NODE.view.AddRoute', {
	extend : 'Ext.window.Window',
	alias : 'widget.addroute',// 添加省视图别名
	width : 500,
	height : 190,
	modal : true,// 是否开启遮罩层
	title : '增加路由',// 弹窗标题
	autoShow : true,// 该组件被创建时立即展示
	layout : 'border',// 采用border布局
	constrain : true,// 让该弹出窗口在父窗口中显示且不越界
	parentPage : null,

	initComponent : function() {
		var me = this;
		me.items = [me.createField()];
		me.callParent(arguments);// 执行该组件父类的同名方法
	},

	createField : function() {
		var me = this;

		me.form = Ext.create("Ext.form.Panel", {
			id : 'routeForm',
			name : 'routeForm',
			region : 'center',// 该组件放置在上方
			margin : 1,// 边距为1
			defaults : { // 所有子元素默认属性
				labelWidth : 60,
				padding : '3 5',
				columnWidth : 0.5,// 以25%的宽度赋给每个组件
				labelAlign : 'center'
			},
			tbar : [{
				xtype : 'button',
				text : '保存',
				id : "commitRoute",
				iconCls : wake.CSS.commitImage
			}, "-", {
				xtype : 'button',
				text : '重置',
				id : "resetRoute",
				iconCls : wake.CSS.resetImage
			}, "-", {
				xtype : 'button',
				text : '关闭',
				id : "closeRoute",
				iconCls : wake.CSS.closeImage
			}],
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{
				fieldLabel : '',
				name : 'id',
				id : 'id',
				xtype : 'textfield',
				text : null,
				hidden : true
			}, {
				fieldLabel : '路由编码',
				name : 'code',
				id : 'code',
				xtype : 'textfield',
				text : null,
				align : "center",
				allowBlank : false
			}, {
				fieldLabel : '路由名称',
				name : 'name',
				id : 'name',
				xtype : 'textfield',
				text : null,
				allowBlank : false
			},{
				fieldLabel : '节点ID',
				name : 'nodeid',
				id : 'nodeid',
				xtype : 'textfield',
				text : null
			},{
				fieldLabel : '上级路由',
				name : 'superroute',
				id : 'superroute',
				xtype : 'textfield',
				text : null
			},{
				fieldLabel : '说明',
				name : 'desc',
				id : 'desc',
				xtype : 'textarea',
				text : null,
				width:440,
				colspan:2,
				height:40
			}]
		});
		return me.form;
	}
});