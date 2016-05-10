Ext.define("VMS.store.MenuStore4Tree",{
	extend:'Ext.data.TreeStore',
	nodeParam : "menuId",
	fields:['id','name','leaf','serviceId'],
	rootVisible : true,	//显示根节点
		root: {
			id: '0',
			name: '虚拟机',
			expanded: false,
			leaf: false
		}
});