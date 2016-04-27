/**
 * 
 */

Ext.define('EVALUATE.model.statusChartModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'ResponseTime',type:'int'},
		{name : 'Memory',type:'int'},
		{name : 'Net',type:'float'},
		{name : 'Error',type:'float'},
		{name : 'Thoughput',type:'float'},
		{name : 'Concurrent',type: 'int'}
	]
})