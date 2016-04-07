/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'USAGE',
	controllers : ['usageCtrl'],
	launch : function() {
		app = this;
		control = this.getController("usageCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'usageIndex',
				xtype : 'usageindex'
			}]
		});
//		control.doQuery();
		//control.initcreat();
	}
});