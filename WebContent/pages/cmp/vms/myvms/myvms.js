/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'MYVMS',
	controllers : ['MyvmsCtrl'],
	launch : function() {
		app = this;
		control = this.getController("MyvmsCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'myvmsIndex',
				xtype : 'myvmsindex'
			}]
		});
		Ext.getCmp('myvmsGrid').getPageBar().setQueryFunction(control.doQuery);//将查询条件赋给分页条
		control.doQueryVms();
	}
});