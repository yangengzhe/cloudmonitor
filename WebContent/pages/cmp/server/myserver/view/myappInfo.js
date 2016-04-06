/**
 * 
 */

Ext.define('MYSERVER.view.MyappInfo', {
	extend : 'Ext.form.Panel',
	alias : 'widget.myappinfo',// 添加视图别名
    autoScroll:true,
    layout:"form",

	initComponent : function() {
		var me = this;
		me.items = [me.createField()];
		me.callParent(arguments);// 执行该组件父类的同名方法
	},

	createField : function() {
		var me = this;

		me.form = Ext.create("Ext.form.Panel", {
			id : 'myappInfoForm',
			name : 'myappInfoForm',
			margin : 1,// 边距为1
			defaults : { // 所有子元素默认属性
				labelWidth : 60,
				padding : '10 40 10 40',
				//columnWidth : 0.5,// 以25%的宽度赋给每个组件
				labelAlign : 'center'
			},
			tbar : [{
				xtype : 'button',
				text : '保存',
				id : "saveMyappInfo",
				iconCls : wake.CSS.commitImage
			}],
			layout : {
				type : 'table',
				columns : 3
			},
			items : [{
				fieldLabel : '',
				name : 'id',
				id : 'id2',
				xtype : 'textfield',
				text : null,
				align : "center",
				width:300,
				 readOnly : true,
				 hidden:true
			},{
				fieldLabel : '云应用名称',
				name : 'name',
				id : 'name2',
				xtype : 'textfield',
				text : null,
				align : "center",
				width:300,
				 readOnly : true,
			}, {
				fieldLabel : '云应用版本',
				name : 'version',
				id : 'version2',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '访问方式',
				name : 'type',
				id : 'type2',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{fieldLabel: '注册时间',
				xtype : 'textfield',
				name:'registerTime',
				id:'registerTime2',
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '开始时间',
				name : 'beginTime',
				id : 'beginTime2',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{
				fieldLabel : '结束时间',
				name : 'endTime',
				id : 'endTime2',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
            	xtype:'form',
            	colspan:3,
            	border:false,  
            	fileUpload: true, 
            	padding : '10 40 10 40',
            	id: 'pictureForm',
            	items:[  {
            		xtype: 'filefield',
	                fieldLabel: '图片',
	                id: 'pictureUpload',
	                width:300,
	                name: 'filePath',
	                labelWidth: 60,
	                labelAlign : 'center',
	                buttonText: '上传' 
            	  }]
            },{
            	xtype:'form',
            	colspan:3,
            	border:false,  
            	fileUpload: true, 
            	padding : '10 40 10 40',
            	id: 'docForm',
            	items:[  {
            		xtype: 'filefield',
	                fieldLabel: '文档',
	                id: 'docUpload',
	                width:300,
	                name: 'filePath',
	                labelWidth: 60,
	                labelAlign : 'center',
	                buttonText: '上传' 
            	  }]
            },{
				fieldLabel : '描述',
				name : 'auditDesc2',
				id : 'auditDesc22222',
				xtype : 'textarea',
				text : null,
				height:40,
				width:500,
				colspan:3,
			}]
		});
		return me.form;
	}
});