/**
 * 
 */
var path = "topo/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('SERVER.controller.serverCtrl', {
	extend : 'Ext.app.Controller',
	views : ['serverIndex'],// 声明该控制层要用到的view
	stores : [ 'MenuStore4Tree','statusChartStore'],// 声明该控制层要用到的store
	models:['statusChartModel'],
	init : function() {
		this.control({
					'#showAllMyapp' : {
						click : this.doQuery
						// 显示所有
					},
					'#searchMyapp' : {
						click : this.dosearchMyapp
						// 查询
					},
					'#resetSearchMyapp' : {
						click : this.doresetSearchMyapp
						// 清空查询
					},
					'#closeConfig' : {
						click : this.doClose
					},

					'#myappup' : {
						click : this.domyappup
						// 上传功能
					},
					'#myappdown' : {
						click : this.domyappdown
						// 部署功能有待开发
					},
					'#myappshut' : {
						click : this.domyappshut
						// 卸载功能有待开发
					},
					'#updatedb' : {
						click : this.doupdatedb
						// 修改数据库密码
					},
					'#codeUpload' : {
						change : this.docodeUpload
					},
					'#pictureUpload' : {
						change : this.dopictureUpload
					},
					'#docUpload' : {
						change : this.dodocUpload
					},
					'#dbfileUpload' : {
						change : this.dodbfileUpload
					},
					"#topo_server_menu" : {
						beforeitemexpand : this.doMenuTreeLoadChildNodes,
						itemclick : this.doMenuTreeItemclick
					}
				});
	},
	dodbfileUpload : function() {
		var form = Ext.getCmp('dbfileForm').getForm();
		webappid = Ext.getCmp('id4').getValue();
		filePath = Ext.getCmp('dbfileUpload').getValue();// 保存的路径
		var regxImage = /.+(.SQL|.sql)$/i;
		if (filePath != null) {
			if (!regxImage.test(filePath)) {
				Ext.Msg.alert('提示', '仅允许的格式：sql');
				return;
			}
		}
		if (form.isValid()) {
			form.submit({// zzy,2015.09.29
				url : restPath + 'register/uploaddbfile' + "?webappid="
						+ webappid,
				waitMsg : '正在上传',
				timeout : 10000,
				success : function(fp, o) {
					if (o.result.success) {
						// Ext.getCmp('imagePath').setValue(o.result.result);
						wake.showMessage('上传成功');
						Ext.getCmp('dbfileUpload').setRawValue(filePath);
					} else {
						alert('上传失败' + o.result.result);
					}
				},
				failure : function(fp, o) {
					alert('错误' + o.result.result);
				}
			});
		}
	},
	dodocUpload : function() {
		var form = Ext.getCmp('docForm').getForm();
		webappid = Ext.getCmp('id2').getValue();
		filePath = Ext.getCmp('docUpload').getValue();// 保存的路径
		var regxImage = /.+(.DOC|.doc|.DOCX|.docx|.TXT|.txt|.WPS|.wps|.PDF|.pdf)$/i;
		if (filePath != null) {
			if (!regxImage.test(filePath)) {
				Ext.Msg.alert('提示', '仅允许的文档格式：doc、docx、txt、wps、pdf');
				return;
			}
		}
		if (form.isValid()) {
			form.submit({// zzy,2015.09.29
				url : restPath + 'register/uploaddocument' + "?webappid="
						+ webappid,
				waitMsg : '正在上传',
				timeout : 10000,
				success : function(fp, o) {
					if (o.result.success) {
						// Ext.getCmp('imagePath').setValue(o.result.result);
						wake.showMessage('上传成功');
						Ext.getCmp('docUpload').setRawValue(filePath);
					} else {
						alert('上传失败' + o.result.result);
					}
				},
				failure : function(fp, o) {
					alert('错误' + o.result.result);
				}
			});
		}
	},
	dopictureUpload : function() {
		var form = Ext.getCmp('pictureForm').getForm();
		webappid = Ext.getCmp('id2').getValue();
		filePath = Ext.getCmp('pictureUpload').getValue();// 保存的路径
		var regxImage = /.+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$/i;
		if (filePath != null) {
			if (!regxImage.test(filePath)) {
				Ext.Msg.alert('提示', '仅允许的图片格式：jpg、gif、bmp、jpeg、png');
				return;
			}
		}
		if (form.isValid()) {
			form.submit({// zzy,2015.09.29
				url : restPath + 'register/uploadpicture' + "?webappid="
						+ webappid,
				waitMsg : '正在上传',
				timeout : 10000,
				success : function(fp, o) {
					if (o.result.success) {
						// Ext.getCmp('imagePath').setValue(o.result.result);
						wake.showMessage('上传成功');
						Ext.getCmp('pictureUpload').setRawValue(filePath);
					} else {
						alert('上传失败' + o.result.result);
					}
				},
				failure : function(fp, o) {
					alert('错误' + o.result.result);
				}
			});
		}
	},
	docodeUpload : function() {
		var form = Ext.getCmp('codeForm').getForm();
		webappid = Ext.getCmp('id3').getValue();
		filePath = Ext.getCmp('codeUpload').getValue();// 保存的路径
		var regxImage = /.+(.war|.WAR|.txt|.TXT)$/i; // 设置允许的格式
		if (filePath != null) {
			if (!regxImage.test(filePath)) {
				Ext.Msg.alert('提示', '仅允许的格式：war');
				return;
			}
		}
		if (form.isValid()) {
			form.submit({// zzy,2015.09.28
				url : restPath + 'register/uploadwebcode' + "?webappid="
						+ webappid,
				waitMsg : '正在上传',
				timeout : 10000,
				success : function(fp, o) {
					if (o.result.success) {
						// Ext.getCmp('imagePath').setValue(o.result.result);
						wake.showMessage('上传成功');
						Ext.getCmp('codeUpload').setRawValue(filePath);
					} else {
						alert('上传失败' + o.result.result);
					}
				},
				failure : function(fp, o) {
					alert('错误' + o.result.result);
				}
			});
		}

	},
	doupdatedb : function() {
		var password = Ext.getCmp('password4').getValue();
		if (password == "")
			return;
		var savedata = Ext.getCmp('myappDBForm').getForm().getFieldValues();
		// console.log(savedata);
		wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "PUT",
					url : restPath + "server/" + "changepw",
					data : savedata,// 将js对象转化为json数据
					timeout : 30000,// 30秒钟的查询超时
					success : function(data) {
						if (data.status == "error") {
							Ext.Msg.alert('提示', '密码修改失败。');
							wake.showMessage("密码修改失败");
						} else {
							Ext.Msg.alert('提示', '密码修改成功。');
							wake.showMessage("密码修改成功");
							var myappIndex = Ext.getCmp('myappIndex');
							var myappGrid = myappIndex.myappGrid;
							// console.log(myappData);
							if (count == 0) {
								myappData = myappGrid.getSelectionModel()
										.getSelection();
								++count;
							}
							// console.log(myappData);
							myappData[0].data.password = Ext
									.getCmp('password4').getValue();
							// console.log(myappData[0].data);
							// var id = myappData[0].data.id;

							// console.log(id);
							control.doQuery();
							// control.doSclect(id);

							// myappGrid.getSelectionModel().select(0,true);//选择某一行
						}
					},
					error : function() {// 发生异常时
						Ext.Msg.alert('提示', '密码修改失败。');
						wake.showMessage("密码修改失败");
					}
				});
	},
	domyappshut : function() {
		Ext.Msg.alert('提示', '卸载功能有待开发。');
		// myappGrid.getSelectionModel().select(0,true);//选择某一行
	},
	domyappdown : function() {
		// Ext.Msg.alert('提示', '部署功能有待开发。');
		// wake.downloadFile("WEB-INFO","C:\upload\document","20151002154826-paper.docx");
// form.submit({//zzy,2015.09.28
		// url: restPath + 'wakedownload/file'+ "?rootPath=" +
		// Ext.String.escape("WEB-INFO")+ "&filePath=" +
		// Ext.String.escape("C:\upload\document") + "&fileName="+
		// Ext.String.escape("20151002154826-paper.docx"),
// waitMsg: '正在....',
		// timeout: 10000,
		// success: function(fp, o) {
		// if(o.result.success){
		// //Ext.getCmp('imagePath').setValue(o.result.result);
		// wake.showMessage('上传成功');
		// Ext.getCmp('codeUpload').setRawValue(filePath);
		// }else{
		// alert('上传失败'+o.result.result);
		// }
		// },
		// failure: function(fp, o) {
		// alert('错误'+ o.result.result);
		// }
		// });
		// var aaa="C:\\upload\\document";
// var url = restPath + "wakedownload/file"+ "?rootPath="+
// Ext.JSON.encode(pagingBean);
		var url = restPath + 'download/file' + "?rootPath=" + "WEB-INFO"
				+ "&filePath=" + "C:\\upload\\document" + "&fileName="
				+ "222.txt";
		// var url = restPath + 'download/file'+ "?fileName="+ "222.txt";
		// window.open('myapp.jsp?fileName=aaaa')
		Ext.Ajax.request({
					url : url,
					success : function(res) {
						console.log(res);
						var obj = res.responseText;
						console.log(obj);// 可以到火狐的firebug下面看看obj里面的结构
						// 加入getPath返回的json为{'path':'upload/abc.jpg'}
						window.location.href = url;
						// window.location.href = obj.path;//这样就可以弹出下载对话框了
					}
				});
		// wake.ajax({
		// contentType : 'application/json',// 声明提交的数据类型
		// dataType : 'json',// 声明请求的数据类型
		// type : "POST",
		// url : url,
		// timeout : 30000,// 30秒钟的查询超时
		// success:function(res){
		// console.log(res);
		// // downloadFile.do;
		// //var obj =Ext.decode(res.responseText);
		// // window.location.href =obj.path;
		//		    	  
		// },
		// error : function() {// 发生异常时
		// }
		// });
	},
	domyappup : function() {

		var myappGrid = Ext.getCmp('myappGrid');

		var sIds = myappGrid.getSelectionModel().getSelection().length;

		if (sIds == 0) {
			Ext.Msg.alert('提示', '请选中云应用。');
			return;
		}
		if (sIds > 1) {
			Ext.Msg.alert('提示', '请只选择一个云应用进行上传！');
			return;
		}
		myappData = myappGrid.getSelectionModel().getSelection();
		myappWin = Ext.widget('myappframe');
		Ext.getCmp('myappInfoForm').loadRecord(myappData[0]);
		// Ext.getCmp('number3').setValue(Ext.getCmp('number2').getValue());
		// control.doserverName();

	},

	doQuery : function() {
		var myappIndex = Ext.getCmp('myappIndex');
		// 开启表格遮罩层
		myappIndex.setLoading(true);
		// 获取查询条件
		var myappGrid = myappIndex.myappGrid;
		var url = restPath + "server/findAllServer" + "?paging=1";
		/** 组装请求对象，并调用框架的请求方法发送请求 */
		wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "GET",
					url : url,
					timeout : 30000,// 30秒钟的查询超时
					success : function(data) {
						if (data) {

						}
						// 关闭遮罩 并提示
						wake.showMessage("查询完毕");
						myappIndex.setLoading(false);
					},
					error : function() {// 发生异常时
						// 关闭表格遮罩层
						myappIndex.setLoading(false);
					}
				});

	},
	dosearchMyapp : function() {
		var myappIndex = Ext.getCmp('myappIndex');
		var cloudappname = Ext.getCmp('cloudappname').getValue();
		var cloudappstate = Ext.getCmp('cloudappstate').getValue();
		if (cloudappname == "" && cloudappstate == "")
			return;
		var param = myappIndex.queryField.getForm().getFieldValues();
		console.log(param);
		myappIndex.setLoading(true);
		var myappGrid = myappIndex.myappGrid;
		var url = restPath + path + "searchmyapp";

		wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "POST",
					url : url,
					data : param,// 将js对象转化为json数据
					path : {
						'grid' : {// 校验用的表格配置
							'com.ices.csp.cloudapp.dto.CloudAppDto2' : 'myappGrid'
						}
					},
					timeout : 30000,
					success : function(data) {
						if (data) {
							if (data.appList.length == 0) {
								Ext.Msg.alert('提示', '没有符合的结果！');
								control.doQuery();
							} else {
								myappGrid.getStore().loadData(data.appList);// 加载表格数据
							}
						}
						myappIndex.setLoading(false);// 关闭表格遮罩层

					},
					error : function() {// 发生异常时
						wake.showMessage("查询失败");
						myappIndex.setLoading(false);// 关闭表格遮罩层
					}
				});
	},
	doClose : function() {
		Ext.getCmp('configForm').getForm().reset();
		configWin.close();
	},
	doresetSearchMyapp : function() {
		Ext.getCmp('searchMyapp_form').getForm().reset();
	},
	/**
	 * 加载菜单数据
	 */
	doMenuTreeLoadChildNodes : function(node) {

		// 加载树的数据
		wake.ajax({
					contentType : 'application/json',// 声明提交的数据类型
					dataType : 'json',// 声明请求的数据类型
					type : "GET",
//					url : webRoot + 'rest/webserver/testfunction?menuId='	+ node.get("id"),
//					url : "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_menu_getChildrenMenu.json",
					url : restPath + "cmp_menu/" + "getMenuChildren?id="+node.get("id"),
					timeout : 30000,// 30秒钟的查询超时
					success : function(data) {
						if (!Ext.isEmpty(data)) {
							node.removeAll(true);
							node.appendChild(data);
						}
					},
					error : function() {// 发生异常时
					}
				});
	},
/**
		 * 点击查询菜单
		 */
	    doMenuTreeItemclick:function(view,record,item,index,e){
	    	/**
	    	 * 画图函数
	    	 */
	    var createGraph= function (server_id)
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
//				url : "http://localhost:8080/cloudplatform//pages/cmp/topology/server/topo.json",
				url : restPath + "topo/" + "getTopoServer?id="+server_id,
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
		
	    	if(record.get("leaf")){
	    		Ext.getCmp('server_detailForm').getForm().load({
//	    			url:restPath+'menu/edit/'+record.get("id"),
//		    		url:"http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getServerDetail.json",
	    			url : restPath + "status/" + "serverDetail?id="+record.get("id"),
	    			method:'GET',
	    	        success : function() {
	    	        	//绘图
	    	        	Ext.getCmp('server_chartsContent').body.update('<div id="container"></div>');
	    	        	createGraph(record.get("id"));
	    	        },
	    	        failure : function() {
	    	        	alert('failure');
	    	        }
	    		});
	    		//显示折线图
	    		Ext.getCmp('server_chartsContent').setVisible(true);
//	    		Ext.getCmp('server_chartsContent').createGraph();
//	    		var chartCPU = Ext.getCmp('realstatus_chart_cpu');
//	    		var chartMEM = Ext.getCmp('realstatus_chart_mem');
//	    		var chartNET = Ext.getCmp('realstatus_chart_net');
//		    		var url = "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getRealstatusChart.json";
//		    		wake.ajax({
//			    		contentType:'application/json',//声明提交的数据类型
//			    		dataType:'json',//声明请求的数据类型
//			    	    type: "GET",
//			    	    url:url,
//			    	    timeout:30000,//30秒钟的查询超时
//			    	    success:function(data){
//			    			if(data){
//			    				chartCPU.getStore().loadData(data.dataList);//加载表格数据
//			    				chartMEM.getStore().loadData(data.dataList);//加载表格数据
//			    				chartNET.getStore().loadData(data.dataList);//加载表格数据
//			    			}
//			    			wake.showMessage(wake.TEXT.querySuccess);
//			    		},
//			    		error:function(){//发生异常时
//			    		}
//			    	});
//	    	}
//	    	else{
//	    		Ext.getCmp('server_detailForm').getForm().reset();
//	    		Ext.getCmp('server_chartsContent').setVisible(false);
//	    	}
//	    }
};
}
});