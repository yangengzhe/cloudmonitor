/**
 * 
 */

Ext.define('EVALUATE.model.statusChartModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'responsetime',type:'int'},
		{name : 'memory',type:'int'},
		{name : 'net',type:'float'},
		{name : 'error',type:'float'},
		{name : 'thoughput',type:'float'},
		{name : 'concurrent',type: 'int'}
	]
})