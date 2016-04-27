/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'REALSTATUS',
	controllers : ['realstatusCtrl'],
	launch : function() {
		app = this;
		control = this.getController("realstatusCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'realstatusIndex',
				xtype : 'realstatusindex'
			}]
		});
//		control.doQuery();
		//control.initcreat();
	}
});