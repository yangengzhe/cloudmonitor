/**
 * 
 */

Ext.define('MYAPP.view.Addapp', {
	extend : 'Ext.window.Window',
	alias : 'widget.addapp',// 添加省视图别名
	width : 635,
	height : 200,
	modal : true,// 是否开启遮罩层
	title : '增加应用',// 弹窗标题
	autoShow : true,// 该组件被创建时立即展示
	// layout : 'border',// 采用border布局
	layout : 'column',
	constrain : true,// 让该弹出窗口在父窗口中显示且不越界
	parentPage : null,

	initComponent : function() {
		var me = this;
		me.items = [ me.createField() ];
		me.callParent(arguments);// 执行该组件父类的同名方法
	},
	createField : function() {
		var me = this;
		me.form = Ext.create('Ext.form.Panel', {
			id : 'appForm',
			name : 'editForm',
			// url : 'www',
			layout : 'column',
			width : 635,
			frame : true,
			tbar : [ {
				xtype : 'button',
				text : '保存',
				id : "commitapp",
				iconCls : wake.CSS.commitImage
			}, "-", {
				xtype : 'button',
				text : '重置',
				id : "resetapp",
				iconCls : wake.CSS.resetImage
			}, "-", {
				xtype : 'button',
				text : '关闭',
				id : "closeapp",
				iconCls : wake.CSS.closeImage
			} ],
			items : [ me.createField1() ]
		});
		return me.form;
	},

	createField1 : function() {
		var me = this;

		me.form = Ext.create("Ext.form.Panel", {
			id : 'appForm1',
			name : 'editForm1',
			// region : 'left',// 该组件放置在上方
			frame : true,
			margin : 1,// 边距为1
			width : 620,
			bodyPadding : 0,
			layout : 'anchor',
			defaults : { // 所有子元素默认属性
				labelWidth : 80,
				padding : '3 5',
				columnWidth : 0.5,// 以25%的宽度赋给每个组件
				labelAlign : 'center'
			},
			items : [ {
				fieldLabel : '',
				name : 'id',
				id : 'id',
				xtype : 'textfield',
				text : null,
				hidden : true
			}, {
				xtype : 'fieldset',
				title : '基本信息',
				collapsible : true,
				draggable : false,
				defaultType : 'textfield',
				layout : 'anchor',
				items : [ {
					xtype : 'fieldcontainer',
					layout : 'column',
					combineErrors : true,
					defaultType : 'textfield',
					defaults : {
						columnWidth : 0.5,
						labelAlign : 'right',
						labelWidth : 80
					},
					items : [ {
						name : 'code',
						fieldLabel : wake.TEXT.redStar + '应用编码',
						allowBlank : false,
						columnWidth : 0.5
					}, {
						name : 'vmid',
						fieldLabel : wake.TEXT.redStar + '虚拟机ID',
						allowBlank : false,
						columnWidth : 0.5
					} ]
				}, {
					xtype : 'fieldcontainer',
					layout : 'column',
					combineErrors : true,
					defaultType : 'textfield',
					defaults : {
						columnWidth : 0.5,
						labelAlign : 'right',
						labelWidth : 80
					},
					items : [ {
						name : 'name',
						fieldLabel : '应用名称'
					}, {
						name : 'state',
						fieldLabel : '状态'
					} ]
				}, {
					xtype : 'fieldcontainer',
					layout : 'column',
					combineErrors : true,
					defaultType : 'textfield',
					defaults : {
						columnWidth : 1,
						labelAlign : 'right',
						labelWidth : 80
					},
					items : [ {
						name : 'desc',
						fieldLabel : '说明'
					} ]
				} ]
			} ]
		});
		return me.form;
	}
});