/**
 * 
 */

Ext.define('USAGE.model.usageChartModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'cpu',type:'int'},
		{name : 'mem',type:'int'},
		{name : 'net',type:'int'},
		{name : 'cpu_enable',type:'int'},
		{name : 'mem_enable',type:'int'},
		{name : 'net_enable',type:'int'},
		{name : 'ip',type:'string'}
	]
})