/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'VMS',
	controllers : ['vmsCtrl'],
	launch : function() {
		app = this;
		control = this.getController("vmsCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'vmsIndex',
				xtype : 'vmsindex'
			}]
		});
//		control.dorefresh();
		//control.initcreat();
	}
});