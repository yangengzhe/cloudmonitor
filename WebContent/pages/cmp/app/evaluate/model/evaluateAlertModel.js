/**
 * 
 */

Ext.define('EVALUATE.model.evaluateAlertModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'appid',type:'int'},
		{name : 'code',type: 'string'},
		{name : 'state',type: 'string'},
		{name : 'msg',type: 'string'},
		{name : 'date',type: 'string'},
		{name : 'desc',type: 'string'}
	]
})