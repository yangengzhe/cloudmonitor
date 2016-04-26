/**
 * 
 */

Ext.define('NODE.model.NodeModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'code',type:'string'},
		{name : 'name',type:'string'},
		{name : 'principal',type:'string'},
		{name : 'telephone',type:'string'},
		{name : 'mobile',type:'string'},
		{name : 'email',type:'string'},
		{name : 'address',type:'string'},
		{name : 'desc',type:'string'}
	]
})