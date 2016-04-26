//首页 index





Ext.define('USAGE.view.usageIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.usageindex',//不能有大写
	layout : 'border',
	initComponent : function() {
		var me = this;
		me.items = [me.createTree(),me.createContent()];
		me.callParent();
	},
	//创建左侧导航树
	createTree : function(){
		var me = this;
		me.menuTree = new Ext.tree.Panel({
   		 width:240,
		 id:'usage_menu',
		 region:'west',
		 store: 'MenuStore4Tree',
		 rootVisible: true,
		 displayField:'name',//显示的名字变量
		 margin:'0 2 0 0',
	     useArrows: true,
	     tbar:[{
             icon:webRoot+'frame/common/themes/images/default/tree/expand-all.gif',
             tooltip:'展开全部',
             handler: function(){
            	 tree.expandAll();
             }
         },'-', {
             icon:webRoot+'frame/common/themes/images/default/tree/collapse-all.gif',
             tooltip:'收缩全部',
             handler: function(){
            	 tree.collapseAll();
             }
         }] 
		});
		return me.menuTree;
	},
	//创建右侧内容面板	
	createContent:function(){
		var me = this;
		var container = Ext.create("Ext.container.Container",{
			id:"usage_content",
			region:"center",
			layout:'border',
			items:[me.createQueryField(), me.createChartsContent()]
		});
		return container;
	},
	//上半部分详细信息内容	
	createQueryField : function() {
		var me = this;
		//组件缓存detailField供生成实例时访问
    	me.detailField = Ext.create("Ext.form.Panel",{//FORM不用Store
    		id:"usage_detailForm"
    		,name:'detailFieldForm'
    		,region:'north'
    		,height:100
	    	,defaults: { 
				labelWidth:80
				,padding:'3 5'
				,columnWidth:0.40//以25%的宽度赋给每个组件
				,xtype:'textfield'
				,labelAlign:'right'
	        }
    		,layout: {type:'table',columns:2}
    		,items:[{fieldLabel:'名称',name:'name',minWidth:100,maxWidth:250,id:'realstatus_detailForm_name'}
    		       ,{fieldLabel: '状态',name:'status',minWidth:100,maxWidth:250,id:'realstatus_detailForm_status'} 
    		       ,{fieldLabel: '所在地区',name:'area',minWidth:100,maxWidth:250,id:'realstatus_detailForm_area'}
    		       ,{fieldLabel: '创建时间',name:'gmt_create',minWidth:100,maxWidth:250,id:'realstatus_detailForm_gmtCreate'}
    		       ,{fieldLabel:'配置信息',name:'desc',width:480,colspan:2,id:'realstatus_detailForm_desc'}
					
			]
    	});
    	return me.detailField;
	},
	//下半部分 图表显示
	createChartsContent : function() {
		var me = this;
		me.show = Ext.create("Ext.form.Panel", {
			id : "usage_chartsContent",
			name : 'usageChartsContent',
			region : 'center',
			labelAlign : 'right',
			hidden:true,
			defaults : {
				labelWidth : 60,
				padding : '3 5',
				columnWidth : 0.5,
				xtype : 'textfield',
				labelAlign : 'right'
			},
			plain: true,
			layout: "anchor",
			items : [me.createChart_cpu(),me.createChart_mem(),me.createChart_net()]
		});
		return me.show;
	},
	//柱状图
	createChart_cpu : function() {
		var me = this;
		me.LineChart = Ext.create('Ext.chart.Chart', {
			id:'usage_chart_cpu',
			xtype: 'chart',
			animate: true,
            title: 'CPU使用情况',
			anchor: '95%, 30%',
			insetPadding: 20,
			store : 'usageChartStore',
			axes: [
			          {
			              type: 'Category',
			              position: 'left',
			              fields: ['ip']
			          },
			          {
			              type: 'Numeric',
			              title: 'CPU核数使用情况',
			              position: 'bottom',
			              fields: ['cpu','cpu_enable']
			          }
			      ],
			   series: [
			               {
			                   type: 'bar',
			                   tips: {
                                   trackMouse: true,
                                   width: '60px',
                                   renderer: function(storeItem, item) {
                                       this.setTitle(item.value[1]+ '个');
                                   }
                               },
			                   xField: 'ip',
			                   yField: ['cpu','cpu_enable'],
			                   title:['已使用CPU核数','剩余CPU核数'],
			                   stacked: true
			               }
			           ],
			           legend: {
                           position: 'right'
                       }
			});
		return me.LineChart;
	},
	createChart_mem : function() {
		var me = this;
		me.LineChart = Ext.create('Ext.chart.Chart', {
			id:'usage_chart_mem',
			xtype: 'chart',
			animate: true,
            title: '内存使用情况',
			anchor: '95%, 30%',
			insetPadding: 20,
			store : 'usageChartStore',
			axes: [
			          {
			              type: 'Category',
			              position: 'left',
			              fields: ['ip']
			          },
			          {
			              type: 'Numeric',
			              title: '内存使用情况',
			              position: 'bottom',
			              fields: ['mem','mem_enable']
			          }
			      ],
			   series: [
			               {
			                   type: 'bar',
			                   tips: {
                                   trackMouse: true,
                                   width: '60px',
                                   renderer: function(storeItem, item) {
                                       this.setTitle(item.value[1]+ 'G');
                                   }
                               },
			                   xField: 'ip',
			                   yField: ['mem','mem_enable'],
			                   title:['已使用内存容量','剩余内存容量'],
			                   stacked: true
			               }
			           ],
			           legend: {
                           position: 'right'
                       }
			});
		return me.LineChart;
	},
	createChart_net : function() {
		var me = this;
		me.LineChart = Ext.create('Ext.chart.Chart', {
			id:'usage_chart_net',
			xtype: 'chart',
			animate: true,
            title: '网络使用情况',
			anchor: '95%, 30%',
			insetPadding: 20,
			store : 'usageChartStore',
			axes: [
			          {
			              type: 'Category',
			              position: 'left',
			              fields: ['ip']
			          },
			          {
			              type: 'Numeric',
			              title: '网络带宽使用情况',
			              position: 'bottom',
			              fields: ['net','net_enable']
			          }
			      ],
			   series: [
			               {
			                   type: 'bar',
			                   tips: {
                                   trackMouse: true,
                                   width: '60px',
                                   renderer: function(storeItem, item) {
                                       this.setTitle(item.value[1]+ 'M');
                                   }
                               },
			                   xField: 'ip',
			                   yField: ['net','net_enable'],
			                   title:['已使用网络带宽','剩余网络带宽'],
			                   stacked: true
			               }
			           ],
			           legend: {
                           position: 'right'
                       }
			});
		return me.LineChart;
	}
});