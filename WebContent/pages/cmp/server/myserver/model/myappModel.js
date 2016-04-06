Ext.define('MYSERVER.model.MyappModel',{
	extend: 'Ext.data.Model',
	fields:[
		{name : 'id',type:'int'},
		{name : 'name',type:'string'},
		{name : 'state',type:'string'},
		{name : 'version',type:'string'},
		{name : 'type',type:'string'},
		{name : 'registerTime',type:'string'},
		{name : 'beginTime',type:'string'},
		{name : 'endTime',type:'string'},
		{name : 'wsType',type:'string'},
		{name : 'wsVersion',type:'string'},
		{name : 'appSize',type:'float'},
		{name : 'concurrentUsers',type:'int'},
		{name : 'wsdeploy',type:'string'},
		{name : 'wsName',type:'string'},
		{name : 'dbType',type:'string'},
		{name : 'dbVersion',type:'string'},
		{name : 'dbSize',type:'float'},
		{name : 'maxTransactions',type:'int'},
		{name : 'dbStore',type:'string'},
		{name : 'dbName',type:'string'},
		
		{name : 'webServer',type:'string'},
		{name : 'url',type:'string'},
		{name : 'appIp',type:'string'},
		{name : 'appPort',type:'int'},
		{name : 'dns',type:'string'},
		
		{name : 'database',type:'string'},
		{name : 'dbIp',type:'string'},
		{name : 'dbPort',type:'int'},
		{name : 'username',type:'string'},
		{name : 'password',type:'string'}
		
		
	]
});