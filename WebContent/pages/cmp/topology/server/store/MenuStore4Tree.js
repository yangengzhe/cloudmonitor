Ext.define("SERVER.store.MenuStore4Tree",{
	extend:'Ext.data.TreeStore',
	nodeParam : "menuId",
	fields:['id','name','leaf','serviceId'],
	rootVisible : true,	//显示根节点
		root: {
			id: '-1',
			name: '菜单管理',
			expanded: false,
			leaf: false
		}
});