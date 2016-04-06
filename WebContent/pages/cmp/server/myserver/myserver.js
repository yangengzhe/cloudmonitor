/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'MYSERVER',
	controllers : ['MyappCtrl'],
	launch : function() {
		app = this;
		control = this.getController("MyappCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'myappIndex',
				xtype : 'myappindex'
			}]
		});
		Ext.getCmp('myappGrid').getPageBar().setQueryFunction(control.doQuery);//将查询条件赋给分页条
		control.doQuery();
		//control.initcreat();
	}
});