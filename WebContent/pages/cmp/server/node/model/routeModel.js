/**
 * 
 */

Ext.define('NODE.model.RouteModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'code',type:'string'},
		{name : 'nodeid',type:'int'},
		{name : 'name',type:'string'},
		{name : 'superroute',type:'int'},
		{name : 'desc',type:'string'}
	]
})