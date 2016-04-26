/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'MYSERVER',
	controllers : ['MyserverCtrl'],
	launch : function() {
		app = this;
		control = this.getController("MyserverCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'myserverIndex',
				xtype : 'myserverindex'
			}]
		});
		Ext.getCmp('myserverGrid').getPageBar().setQueryFunction(control.doQuery);//将查询条件赋给分页条
		control.doQueryServer();
		//control.initcreat();
	}
});