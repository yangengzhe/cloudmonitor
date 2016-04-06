/**
 * 
 */

Ext.define('REALSTATUS.model.statusChartModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'cpu',type:'int'},
		{name : 'mem',type:'int'},
		{name : 'net',type:'int'},
		{name : 'date',type: 'date', dateFormat: 'Y-m-d H:i'}
	]
})