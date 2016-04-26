/**
 * 
 */

var app = null;
var control = null;
Ext.application({
	name : 'NODE',
	controllers : ['NodeCtrl'],
	launch : function() {
		app = this;
		control = this.getController("NodeCtrl");
		var viewPort = Ext.create('Ext.container.Viewport', {
			border : 0,
			layout : 'fit',
			items : [{
				id : 'nodeIndex',
				xtype : 'nodeindex'
			}]
		});
		Ext.getCmp('nodeGrid').getPageBar().setQueryFunction(control.doQueryNode);//将查询条件赋给分页条
		control.doQueryNode();
	}
});