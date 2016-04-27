/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'EVALUATE',
	controllers : ['evaluateCtrl'],
	launch : function() {
		app = this;
		control = this.getController("evaluateCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'evaluateIndex',
				xtype : 'evaluateindex'
			}]
		});
//		control.doQuery();
		//control.initcreat();
	}
});