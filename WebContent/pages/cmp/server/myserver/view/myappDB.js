/**
 * 
 */

Ext.define('MYSERVER.view.MyappDB', {
	extend : 'Ext.form.Panel',
	alias : 'widget.myappdb',// 添加视图别名
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
			id : 'myappDBForm',
			name : 'myappDBForm',
			margin : 1,// 边距为1
			defaults : { // 所有子元素默认属性
				labelWidth : 60,
				padding : '10 40 10 40',
				columnWidth : 0.5,// 以25%的宽度赋给每个组件
				labelAlign : 'center'
			},
			layout : {
				type : 'table',
				columns : 3
			},
			items : [{
				fieldLabel : '',
				name : 'id',
				id : 'id4',
				xtype : 'textfield',
				text : null,
				align : "center",
				width:300,
				 readOnly : true,
				 hidden:true
			},{
				fieldLabel : '数据库类型',
				name : 'dbType',
				id : 'dbType4',
				xtype : 'textfield',
				text : null,
				align : "center",
				width:300,
				 readOnly : true,
			}, {
				fieldLabel : '版本',
				name : 'dbVersion',
				id : 'dbVersion4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '数据库大小上限',
				name : 'dbSize',
				id : 'dbSize4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{fieldLabel: '最大并发事务数',
				xtype : 'textfield',
				name:'maxTransactions',
				id:'maxTransactions4',
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '存储方式',
				name : 'dbStore',
				id : 'dbStore4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
			},{
				fieldLabel : '数据库名',
				name : 'dbName',
				id : 'dbName4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2
			},{
				fieldLabel : '数据库服务器',
				name : 'database',
				id : 'database4',
				xtype : 'textfield',
				text : null,
				colspan:3,
				readOnly : true,
				width: 400,
			},{
				fieldLabel : 'IP地址',
				name : 'dbIp',
				id : 'dbIp4',
				xtype : 'textfield',
				 readOnly : true,
			},{
				fieldLabel : '端口号',
				name : 'dbPort',
				id : 'dbPort4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				colspan:2,
			},{
				fieldLabel : '用户名',
				name : 'username',
				id : 'username4',
				xtype : 'textfield',
				text : null,
				readOnly : true,
				//colspan:3,
			},{
    			xtype : 'fieldcontainer',
    			layout : 'column',
    			combineErrors : true,
    			defaultType : 'textfield',
    			colspan:2,
    			defaults : {
    				//columnWidth: 0.5,
    				labelAlign : 'right',
    				labelWidth : 80
    			},
    			items : [{
    						name : 'password',
    						fieldLabel : '密码',
    						id: 'password4',
    						//columnWidth : 0.47
    						allowBlank : false,
    						padding : '10 10 10 20',
    					},{
    		            	xtype: 'button',
    		            	id: 'updatedb',
    		            	text:'修改',
    		            	width : 70,
    		            	margin : '10 10 10 0',
    		            }]
    		},{
            	xtype:'form',
            	colspan:3,
            	border:false,  
            	fileUpload: true, 
            	padding : '10 40 10 40',
            	id: 'dbfileForm',
            	items:[  {
            		xtype: 'filefield',
	                fieldLabel: '数据库文件',
	                id: 'dbfileUpload',
	                width:300,
	                name: 'filePath',
	                labelWidth: 60,
	                labelAlign : 'center',
	                buttonText: '上传' 
            	  }]
            }]
		});
		return me.form;
	}
});