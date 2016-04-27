//首页 index

Ext
		.define(
				'EVALUATE.view.evaluateIndex',
				{
					extend : 'Ext.container.Container',
					alias : 'widget.evaluateindex',// 不能有大写
					layout : 'border',
					initComponent : function() {
						var me = this;
						me.items = [ me.createTree(), me.createContent() ];
						me.callParent();
					},
					// 创建左侧导航树
					createTree : function() {
						var me = this;
						me.menuTree = new Ext.tree.Panel(
								{
									width : 240,
									id : 'evaluate_menu',
									region : 'west',
									store : 'MenuStore4Tree',
									rootVisible : true,
									displayField : 'name',// 显示的名字变量
									margin : '0 2 0 0',
									useArrows : true,
									tbar : [
											{
												icon : webRoot
														+ 'frame/common/themes/images/default/tree/expand-all.gif',
												tooltip : '展开全部',
												handler : function() {
													tree.expandAll();
												}
											},
											'-',
											{
												icon : webRoot
														+ 'frame/common/themes/images/default/tree/collapse-all.gif',
												tooltip : '收缩全部',
												handler : function() {
													tree.collapseAll();
												}
											} ]
								});
						return me.menuTree;
					},
					// 创建右侧内容面板
					createContent : function() {
						var me = this;
						var container = Ext.create("Ext.container.Container", {
							id : "evaluate_content",
							region : "center",
							layout : 'border',
							items : [ me.createQueryField(),
									me.createChartsContent() ]
						});
						return container;
					},
					// 上半部分详细信息内容
					createQueryField : function() {
						var me = this;
						// 组件缓存detailField供生成实例时访问
						me.detailField = Ext.create("Ext.form.Panel", {
							id : "evaluate_detailForm",
							name : 'detailFieldForm',
							region : 'north',
							height : 100,
							defaults : {
								labelWidth : 80,
								padding : '3 5',
								columnWidth : 0.40// 以25%的宽度赋给每个组件
								,
								xtype : 'textfield',
								labelAlign : 'right'
							},
							layout : {
								type : 'table',
								columns : 2
							},
							items : [ {
								fieldLabel : '应用名称',
								name : 'name',
								minWidth : 100,
								maxWidth : 250,
								id : 'evaluate_detailForm_name'
							}, {
								fieldLabel : '状态',
								name : 'status',
								minWidth : 100,
								maxWidth : 250,
								id : 'evaluate_detailForm_status'
							}, {
								fieldLabel : '虚拟机ID',
								name : 'area',
								minWidth : 100,
								maxWidth : 250,
								id : 'evaluate_detailForm_area'
							}, {
								fieldLabel : '更新时间',
								name : 'gmt_create',
								minWidth : 100,
								maxWidth : 250,
								id : 'evaluate_detailForm_gmtCreate'
							}, {
								fieldLabel : '备注',
								name : 'desc',
								width : 480,
								colspan : 2,
								id : 'evaluate_detailForm_desc'
							}

							]
						});
						return me.detailField;
					},
					// 下半部分 图表显示
					createChartsContent : function() {
						var me = this;
						me.show = Ext.create("Ext.form.Panel", {
							id : "evaluate_chartsContent",
							name : 'evaluateChartsContent',
							region : 'center',
							labelAlign : 'right',
							hidden : true,
							defaults : {
								labelWidth : 60,
								padding : '3 5',
								columnWidth : 0.5,
								xtype : 'textfield',
								labelAlign : 'right'
							},
							plain : true,
							layout : "anchor",
							items : [me.createLineChart_net(),me.createTable() ]
						});
						return me.show;
					},
					// 折线图
					createLineChart_net : function() {
						var me = this;
						me.LineChart = Ext.create('Ext.chart.Chart', {
							id : 'evaluate_chart_net',
							xtype : 'chart',
							anchor : '100%, 50%',
							style : 'margin:10px 0px 0px 30px;',
							// theme: 'Green',
							store : 'statusChartStore',
							axes : [ {
								type : 'Numeric',
								position : 'left',
								fields : [ 'ResponseTime','Memory','Net','Error','Thoughput' ],
								minimum : 0
							}, {
								title : '资源评估模型',
								type : 'Category',
								position : 'bottom',
								fields : [ 'Concurrent' ]
							} ],
							series : [ {
								type : 'line',
								xField : 'Concurrent',
								yField :  'ResponseTime',
								title : [ '响应时间(s)'],
								tips: {
									trackMouse: true,
									width: 100, 
									height: 18, 
									renderer: function(storeItem, item) {
										this.setTitle(storeItem.get('ResponseTime') + 's' );  
									}
								}   
							},
							{
								type : 'line',
								xField : 'Concurrent',
								yField :  'Memory',
								title : [ '内存(m)'],
								tips: {
									trackMouse: true,
									width: 100, 
									height: 18, 
									renderer: function(storeItem, item) {
										this.setTitle(storeItem.get('Memory') + 'm' );  
									}
								}   
							},
							{
								type : 'line',
								xField : 'Concurrent',
								yField :  'Net',
								title : [ '网络速率(k/s)'],
								tips: {
									trackMouse: true,
									width: 100, 
									height: 18, 
									renderer: function(storeItem, item) {
										this.setTitle(storeItem.get('Net') + 'k/s' );  
									}
								}   
							},
							{
								type : 'line',
								xField : 'Concurrent',
								yField :  'Error',
								title : [ '错误率(%)'],
								tips: {
									trackMouse: true,
									width: 100, 
									height: 18, 
									renderer: function(storeItem, item) {
										this.setTitle(storeItem.get('Error') + '%' );  
									}
								}   
							},
							{
								type : 'line',
								xField : 'Concurrent',
								yField :  'Thoughput',
								title : [ '吞吐率(k/s)'],
								tips: {
									trackMouse: true,
									width: 100, 
									height: 18, 
									renderer: function(storeItem, item) {
										this.setTitle(storeItem.get('Thoughput') + 'k/s' );  
									}
								}   
							}],
							legend : {
								position : 'right'
							}
						});
						return me.LineChart;
					},
					createTable : function() {
						var me = this;
						
						me.ShowAlerts = Ext.create("Ext.form.Panel", {
							id : "showTable",
							anchor : '100%, 50%',
							name : 'showAlerts',
							labelAlign : 'right',
							tbar : [{
								xtype : 'button',
								text : '标记为已读',
								id : "updateAlert",
								iconCls : wake.CSS.editImage
							}, "-", {
								xtype : 'button',
								text : '删除',
								id : "deleteAlert",
								iconCls : wake.CSS.deleteImage
							}]
							,
							defaults : {
								labelWidth : 60,
								padding : '3 5',
								columnWidth : 0.5// 以25%的宽度赋给每个组件
								,
								xtype : 'textfield',
								labelAlign : 'right'
							},
							layout : "fit",
							items : [me.createShowAlertGrid()]
						});
						return me.ShowAlerts;
						
						
					},
					createShowAlertGrid : function() {
						var me = this;
						var paging = Ext.create('Wake.toolbar.Paging', {
							name : 'gridPagingBar',
							displayInfo : true,
							everySize : 15
						});
						var sm = Ext.create('Ext.selection.CheckboxModel', {
							mode : "MULTI"
						});
						me.alertGrid = Ext.create('Wake.grid.Panel', {
							id : 'alertGrid',
							name : 'alertGrid',
							region : 'north',
							store : Ext.create("EVALUATE.store.evaluateAlertStore"),
							selModel : sm,
							frame : false,

							columns : [{
								xtype : 'rownumberer',
								width : 35,
								sortable : false,
								header : '序号',
								hidden : true
							}, {
								dataIndex : 'id',
								header : 'id',
								width : 150,
								sortable : true,
								hidden : true
							}, {
								dataIndex : 'code',
								header : '编码' ,
								width : 150,
								sortable : true,
								editor : {
									xtype : 'textfield'
								}
							}, {
								dataIndex : 'appid',
								header : '应用ID' ,
								width : 150,
								sortable : true,
								editor : {
									xtype : 'textfield'
								}
							}, {
								dataIndex : 'type',
								header : '类别',
								width : 150,
								editor : {
									xtype : 'textfield'
								}
							}, {
								dataIndex : 'msg',
								header : '消息',
								width : 150,
								editor : {
									xtype : 'textfield'
								}
							},{
								dataIndex : 'desc',
								header : '说明',
								width : 150,
								editor : {
									xtype : 'textfield'
								}
							}
							],
							bbar : paging
						});
						return me.alertGrid;
					}
				});