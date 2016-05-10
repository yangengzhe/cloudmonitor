/**
 * 
 */

Ext.define('REALSTATUS.model.statusChartModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'responsetime',type:'int'},
		{name : 'memory',type:'int'},
		{name : 'concurrent',type:'int'},
//		{name : 'date',type: 'date', dateFormat: 'Y-m-d H:i'}
		{name : 'date',type: 'string'}
	]
})