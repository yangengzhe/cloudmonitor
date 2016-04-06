/**
 * 
 */

Ext.define('MYSERVER.view.MyappWeb', {
	extend : 'Ext.form.Panel',
	alias : 'widget.myappweb',// 添加视图别名
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
			id : 'myappWebForm',
			name : 'myappWebForm',
			margin : 1,// 边距为1
			defaults : { // 所有子元素默认属性
				labelWidth : 60,
				padding : '10 40 10 40',
				//columnWidth : 0.5,// 以25%的宽度赋给每个组件
				labelAlign : 'center'
			},
			layout : {
				type : 'table',
				columns : 3
			},
			items : [{
				fieldLabel : '',
				name : 'id',
				id : 'id3',
				xtype : 'textfield',
				text : null,
				align : "center",
				width:300,
				 readOnly : true,
				 hidden:true
			},{
				fieldLabel : '应用服务器类型',
				name : 'wsType',
				id : 'wsType3',
				xtype : 'textfield',
				text : null,
				align : "center",
				 readOnly : true,
			}, {
				fieldLabel : '版本',
				name : 'wsVersion',
				id : 'wsVersion3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '应用大小上限',
				name : 'appSize',
				id : 'appSize3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{fieldLabel: '最大并发用户数',
				xtype : 'textfield',
				name:'concurrentUsers',
				id:'concurrentUsers3',
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '部署方式',
				name : 'wsdeploy',
				id : 'wsdeploy3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{
				fieldLabel : '应用实例名',
				name : 'wsName',
				id : 'wsName3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '应用服务器',
				name : 'webServer',
				id : 'webServer3',
				xtype : 'textfield',
				text : null,
				colspan:3,
				readOnly : true,
				width: 400,
			},{
				fieldLabel : 'IP地址',
				name : 'appIp',
				id : 'appIp3',
				xtype : 'textfield',
				 readOnly : true,
			},{
				fieldLabel : '端口号',
				name : 'appPort',
				id : 'appPort3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2,
			},{
				fieldLabel : 'URL',
				name : 'url',
				id : 'url3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:3,
				width: 400,
			},{
				fieldLabel : 'DNS',
				name : 'dns',
				id : 'dns3',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:3,
				width: 400,
			},{
            	xtype:'form',
            	border:false,  
            	fileUpload: true, 
            	padding : '10 40 10 40',
            	id: 'codeForm',
            	items:[  {
            		xtype: 'filefield',
	                fieldLabel: '代码文件',
	                id: 'codeUpload',
	                width:300,
	                name: 'filePath',
	                labelWidth: 60,
	                labelAlign : 'center',
	                buttonText: '上传' 
            	  }]
            }
//			,{
//    			xtype : 'fieldcontainer',
//    			layout : 'column',
//    			combineErrors : true,
//    			defaultType : 'textfield',
//    			colspan:3,
//    			defaults : {
//    				//columnWidth: 0.5,
//    				labelAlign : 'right',
//    				labelWidth : 80
//    			},
//    			items : [{
//    						name : 'auditDesc3',
//    						fieldLabel : '代码文件',
//    						id: 'auditDesc3333333',
//    						//columnWidth : 0.47
//    						width : 360,
//    						allowBlank : false,
//    						padding : '10 10 10 20',
//    					},{
//    		            	xtype: 'button',
//    		            	id: 'chosecodefile',
//    		            	text:'选择',
//    		            	width : 70,
//    		            	margin : '10 10 10 0',
//    		            },{
//    		            	xtype: 'button',
//    		            	id: 'commitcodefile',
//    		            	text:'上传',
//    		            	width : 70,
//    		            	margin : '10 10 10 0',
//    		            }]
//    		}
			]
		});
		return me.form;
	}
});