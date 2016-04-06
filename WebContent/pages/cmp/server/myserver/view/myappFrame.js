
Ext.define('MYSERVER.view.MyappFrame', {
	extend : 'Ext.window.Window',
	alias : 'widget.myappframe',// 别名
	layout : 'fit',
	width : 800,
	height : 500,
	modal : true,// 是否开启遮罩层
	autoShow : true,// 该组件被创建时立即展示
	title : '云应用',// 弹窗标题
	constrain : true,// 让该弹出窗口在父窗口中显示且不越界
	parentPage : null,
	initComponent : function() {// 初始化组件
		var me = this;
		me.items = [ me.createTabs() ];
		me.callParent();// 调用父类构造方法
	},
	createTabs : function() {
		var me = this;
		me.tabs = Ext.create('Ext.tab.Panel', {
			activeTab : 0,
			id:'myappFrameTab',
			defaults : {
				scroll : true
			},
			items : [ {
				title : '基本信息',
				autoScroll:true,
				//width:50,		
				layout: 'fit',
				items : [ {
					id : 'myappinfoTab',
					xtype : 'myappinfo'
				} ],
				listeners:{
					activate:function(){
						// myappGrid = Ext.getCmp('myappGrid');
						// myappData = myappGrid.getSelectionModel().getSelection();
						//control.initCombo();
					/*	var cargoTBar = Ext.getCmp('cargoTBar');
						cargoTBar.show();*/
					}
				}
			}, {
				title : '应用服务器',
				autoScroll:true,
				layout: 'fit',
				items : [{
					id : 'webserverTab',
					xtype : 'myappweb'
				} ],
				listeners:{
					activate:function(){
				   // Ext.getCmp('cabinetParamGridSelect').getPageBar().setQueryFunction(control.dolook);//将查询条件赋给分页条
					//control.dolook();

						Ext.getCmp('myappWebForm').loadRecord(myappData[0]);
					}
				}
			},{
				title : '数据库',
				autoScroll:true,
				layout: 'fit',
				items : [{
					id : 'dbserverTab',
					xtype : 'myappdb'
				} ],
				listeners:{
					activate:function(){
				   // Ext.getCmp('cabinetParamGridSelect').getPageBar().setQueryFunction(control.dolook);//将查询条件赋给分页条
					//control.dolook();

						Ext.getCmp('myappDBForm').loadRecord(myappData[0]);
					}
				}
			} ]

		});
		console.log(me.tabs);

		return me.tabs;
	},
	
	
//	createManage:function(){
//		var me = this;
//		me.viewPort = Ext.create('Ext.container.Viewport', {
//        	border:0,
//            layout: 'fit',
//            items: [{
//            	id:'customerIndex',
//            	xtype: 'customerIndex'
//            }]
//        })
//        return me.viewPort;
//	}
	
});