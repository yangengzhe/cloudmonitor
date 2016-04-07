/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'SERVER',
	controllers : ['serverCtrl'],
	launch : function() {
		app = this;
		control = this.getController("serverCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'serverIndex',
				xtype : 'serverindex'
			}]
		});
//		control.dorefresh();
		//control.initcreat();
	}
});