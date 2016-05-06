/**
 * 
 */

Ext.define('MYSERVER.model.MyserverModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'code',type:'string'},
		{name : 'name',type:'string'},
		{name : 'nodeName',type:'string'},
		{name : 'vplatform',type:'string'},
		{name : 'ip',type:'string'},
		{name : 'state',type:'string'},
		{name : 'desc',type:'string'},
		{name : 'cpu',type:'int'},
		{name : 'memory',type:'int'},
		{name : 'bandwidth',type:'int'},
		{name : 'superroute',type:'string'}
	]
})