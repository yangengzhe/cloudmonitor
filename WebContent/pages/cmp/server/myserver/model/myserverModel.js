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
		{name : 'type',type:'string'},
		{name : 'flag',type:'string'},
		{name : 'vpaltform',type:'string'},
		{name : 'ip',type:'string'},
		{name : 'state',type:'string'},
		{name : 'user',type:'string'},
		{name : 'password',type:'string'},
		{name : 'desc',type:'string'},
		{name : 'producer',type:'string'},
		{name : 'model',type:'string'},
		{name : 'category',type:'string'},
		{name : 'structure',type:'string'},
		{name : 'cpuType',type:'string'},
		{name : 'cpuModel',type:'string'},
		{name : 'cpuFrequency',type:'float'},
		{name : 'cpuAcount',type:'int'},
		{name : 'treeBuffer',type:'float'},
		{name : 'busStand',type:'string'},
		{name : 'cpuCores',type:'int'},
		{name : 'cpuThreads',type:'int'},
		{name : 'memSlots',type:'int'},
		{name : 'allocatedMemSlots',type:'int'},
		{name : 'maxMemCapacity',type:'float'},
		{name : 'memCapacity',type:'float'},
		{name : 'stanDiskCapacity',type:'float'},
		{name : 'maxDiskCapacity',type:'float'},
		{name : 'diskRacks',type:'int'},
		{name : 'diskRacksNorm',type:'string'},
		{name : 'allocatedDiskRacks',type:'int'},
		{name : 'diskCapacity',type:'float'},
		{name : 'netCarAcount',type:'int'},
		{name : 'bandwidth',type:'float'}
	]
})