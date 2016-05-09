/**
 * 
 */

Ext.define('MYAPP.model.MyappModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'vmCode',type:'string'},
		{name : 'code',type:'string'},
		{name : 'name',type:'string'},
		{name : 'state',type:'string'},
		{name : 'desc',type:'string'}
	]
})