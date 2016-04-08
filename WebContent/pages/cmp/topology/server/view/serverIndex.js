//首页 index





Ext.define('SERVER.view.serverIndex', {
	extend : 'Ext.container.Container',
	alias : 'widget.serverindex',//不能有大写
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
    		id:"server_detailForm"
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
		
		//绘图
		var createGraph= function ()
		{
			function Topology(ele){
			    typeof(ele)=='string' && (ele=document.getElementById(ele));
			    var w=ele.clientWidth,
			        h=ele.clientHeight,
			        self=this;
			    this.force = d3.layout.force().gravity(.05).distance(200).charge(-800).size([w, h]);
			    this.nodes=this.force.nodes();
			    this.links=this.force.links();
			    this.clickFn=function(){};
			    this.vis = d3.select(ele).append("svg:svg")
			                 .attr("width", w).attr("height", h).attr("pointer-events", "all");
			    this.force.on("tick", function(x) {
			      self.vis.selectAll("g.node")
			          .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
			      self.vis.selectAll("line.link")
			          .attr("x1", function(d) { return d.source.x; })
			          .attr("y1", function(d) { return d.source.y; })
			          .attr("x2", function(d) { return d.target.x; })
			          .attr("y2", function(d) { return d.target.y; });
			    });
			}
			Topology.prototype.doZoom=function(){
			    d3.select(this).select('g').attr("transform","translate(" + d3.event.translate + ")"+ " scale(" + d3.event.scale + ")");
			 
			}			 
			//增加节点
			Topology.prototype.addNode=function(node){
			    this.nodes.push(node);
			}
			Topology.prototype.addNodes=function(nodes){
			    if (Object.prototype.toString.call(nodes)=='[object Array]' ){
			        var self=this;
			        nodes.forEach(function(node){
			            self.addNode(node);
			        });}}
			//增加连线
			Topology.prototype.addLink=function(source,target){
			    this.links.push({source:this.findNode(source),target:this.findNode(target)});
			}			 
			//增加多个连线
			Topology.prototype.addLinks=function(links){
			    if (Object.prototype.toString.call(links)=='[object Array]' ){
			        var self=this;
			        links.forEach(function(link){
			            self.addLink(link['source'],link['target']);
			        });}}			 
			//删除节点
			Topology.prototype.removeNode=function(id){
			    var i=0,
			        n=this.findNode(id),
			        links=this.links;
			    while ( i < links.length){
			        links[i]['source']==n || links[i]['target'] ==n ? links.splice(i,1) : ++i;
			    }
			    this.nodes.splice(this.findNodeIndex(id),1);
			}			 
			//删除节点下的子节点，同时清除link信息
			Topology.prototype.removeChildNodes=function(id){
			    var node=this.findNode(id),
			        nodes=this.nodes;
			        links=this.links,
			        self=this;			 
			    var linksToDelete=[],
			        childNodes=[];
			     
			    links.forEach(function(link,index){
			        link['source']==node 
			            && linksToDelete.push(index) 
			            && childNodes.push(link['target']);
			    });
			 
			    linksToDelete.reverse().forEach(function(index){
			        links.splice(index,1);
			    });
			 
			    var remove=function(node){
			        var length=links.length;
			        for(var i=length-1;i>=0;i--){
			            if (links[i]['source'] == node ){
			               var target=links[i]['target'];
			               links.splice(i,1);
			               nodes.splice(self.findNodeIndex(node.id),1);
			               remove(target);
			                
			            }
			        }
			    }			 
			    childNodes.forEach(function(node){
			        remove(node);
			    });
			 
			    //清除没有连线的节点
			    for(var i=nodes.length-1;i>=0;i--){
			        var haveFoundNode=false;
			        for(var j=0,l=links.length;j<l;j++){
			            ( links[j]['source']==nodes[i] || links[j]['target']==nodes[i] ) && (haveFoundNode=true) 
			        }
			        !haveFoundNode && nodes.splice(i,1);
			    }
			}
			//查找节点
			Topology.prototype.findNode=function(id){
			    var nodes=this.nodes;
			    for (var i in nodes){
			        if (nodes[i]['id']==id ) return nodes[i];
			    }
			    return null;
			}
			//查找节点所在索引号
			Topology.prototype.findNodeIndex=function(id){
			    var nodes=this.nodes;
			    for (var i in nodes){
			        if (nodes[i]['id']==id ) return i;
			    }
			    return -1;
			}
			//节点点击事件
			Topology.prototype.setNodeClickFn=function(callback){
			    this.clickFn=callback;
			}
			//更新拓扑图状态信息
			Topology.prototype.update=function(){
			  var link = this.vis.selectAll("line.link")
			      .data(this.links, function(d) { return d.source.id + "-" + d.target.id; })
			      .attr("class", function(d){
			            return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
			      });
			  link.enter().insert("svg:line", "g.node")
			      .attr("class", function(d){
			         return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
			      });
			  link.exit().remove();
			  var node = this.vis.selectAll("g.node")
			      .data(this.nodes, function(d) { return d.id;});
			  var nodeEnter = node.enter().append("svg:g")
			      .attr("class", "node")
			      .call(this.force.drag);
			  //增加图片，可以根据需要来修改
			  var self=this;
			  nodeEnter.append("svg:image")
			      .attr("class", "circle")
			      .attr("xlink:href", function(d){
			         //根据类型来使用图片
			    	  switch(d.type){
			    	  case "route":
			    		  return "../js/route.png";
			    	  case "server":
			    		  return "../js/server.png";
			    	  }
			      })
			      .attr("x", "-32px")
			      .attr("y", "-32px")
			      .attr("width", "64px")
			      .attr("height", "64px")
			      .on('click',function(d){ d.expand && self.clickFn(d);})
			 
			  nodeEnter.append("svg:text")
			      .attr("class", "nodetext")
			      .attr("dx", 15)
			      .attr("dy", -35)
			      .text(function(d) { return d.id });
			  node.exit().remove();
			  this.force.start();
			}
			
			var topology=new Topology('container');
			var nodes = new Array();
			var links = new Array();
			//加载数据
			wake.ajax({
				contentType : 'application/json',// 声明提交的数据类型
				dataType : 'json',// 声明请求的数据类型
				type : "GET",
				async:false,
//				url : webRoot + 'rest/webserver/testfunction?menuId='	+ node.get("id"),
				url : "http://localhost:8080/cloudplatform//pages/cmp/topology/server/topo.json",
				timeout : 30000,// 30秒钟的查询超时
				success : function(data) {
					if (!Ext.isEmpty(data)) {
						for(var i=0;i<data.total;i++){
							nodes[data.detal[i].id]={id:data.detal[i].name,type:data.detal[i].type,status:data.detal[i].status};//status为1时连通，否则红色
							if(data.detal[i].father>=0)
								links.push({source:data.detal[i].name,target:nodes[data.detal[i].father].id});
						}
					}
				},
				error : function() {// 发生异常时
				}
			});
//			var nodes=[
//			    {id:'10.4.42.1',type:'route'},
//			    {id:'10.4.43.1',type:'server'},
//			    {id:'10.4.44.1',type:'server'},
//			    {id:'10.4.45.1',type:'server'}
//			 
//			];
//			var links=[
//			    {source:'10.4.42.1',target:'10.4.43.1'},
//			    {source:'10.4.43.1',target:'10.4.44.1'},
//			    {source:'10.4.44.1',target:'10.4.45.1'}
//			];
//			var childNodes=[
//		    {id:'10.4.43.2',type:'switch',status:1},
//		    {id:'10.4.43.3',type:'switch',status:1}
//		 
//			];
//			var childLinks=[
//			    {source:'10.4.43.1',target:'10.4.43.2'},
//			    {source:'10.4.43.1',target:'10.4.43.3'},
//			    {source:'10.4.43.2',target:'10.4.43.3'}
//			]
			topology.addNodes(nodes);
			topology.addLinks(links);
			//可展开节点的点击事件
			topology.setNodeClickFn(function(node){
//			    if(!node['_expanded']){
//			        expandNode(node.id);
//			        node['_expanded']=true;
//			    }else{
//			        collapseNode(node.id);
//			        node['_expanded']=false;
//			    }
			});
			topology.update();
			function expandNode(id){
			    topology.addNodes(childNodes);
			    topology.addLinks(childLinks);
			    topology.update();
			}
			 
			function collapseNode(id){
			    topology.removeChildNodes(id);
			    topology.update();
			}
		};
		//结束
		
		
		
		me.show = Ext.create("Ext.form.Panel", {
			id : "server_chartsContent",
			region : 'center',
			labelAlign : 'right',
			hidden:false,
			listeners : {
				afterrender : function(Panel, eOpts) {
					createGraph();
					
				}
			},
			defaults : {
				labelWidth : 60,
				padding : '3 5',
				columnWidth : 0.5,
				xtype : 'textfield',
				labelAlign : 'right'
			},
			plain: true,
			layout: "anchor",
			html:'<div id="container"></div>'
		});
		return me.show;
	}
});