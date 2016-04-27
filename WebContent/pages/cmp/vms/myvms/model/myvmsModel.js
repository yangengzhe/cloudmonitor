/**
 * 
 */

Ext.define('MYVMS.model.MyvmsModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'serverid',type:'int'},
		{name : 'code',type:'string'},
		{name : 'name',type:'string'},
		{name : 'state',type:'string'},
		{name : 'ip',type:'string'},
		{name : 'cpu',type:'string'},
		{name : 'mem',type:'int'},
		{name : 'disk',type:'int'},
		//{name : 'bandwidth',type:'float'},
		{name : 'bandwidth',type:'int'},
		{name : 'desc',type:'string'}
	]
})