/**
 * 
 */
var path = "register/";// 资源请求路径
var myappData;
var count = 1;
Ext.define('USAGE.controller.usageCtrl', {
	extend : 'Ext.app.Controller',
	views : ['usageIndex'],// 声明该控制层要用到的view
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
					"#usage_menu" : {
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
					url : "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_menu_getChildrenMenu.json",
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
	    	
	    	if(record.get("leaf")){
	    		Ext.getCmp('usage_detailForm').getForm().load({
//	    			url:restPath+'menu/edit/'+record.get("id"),
		    		url:"http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getServerDetail.json",
	    			method:'GET',
	    	        success : function() {
	    	        	
	    	        },
	    	        failure : function() {
	    	        	alert('failure');
	    	        }
	    		});
	    		//显示折线图
	    		Ext.getCmp('usage_chartsContent').setVisible(true);
	    		var chartCPU = Ext.getCmp('realstatus_chart_cpu');
	    		var chartMEM = Ext.getCmp('realstatus_chart_mem');
	    		var chartNET = Ext.getCmp('realstatus_chart_net');
//	    		var serviceId = record.get("serviceId");
//	        	if(serviceId!=null && serviceId!=""){
		    		var url = "http://localhost:8080/cloudplatform//pages/cmp/server/realstatus/realstatus_getRealstatusChart.json";
		    		wake.ajax({
			    		contentType:'application/json',//声明提交的数据类型
			    		dataType:'json',//声明请求的数据类型
			    	    type: "GET",
			    	    url:url,
			    	    timeout:30000,//30秒钟的查询超时
			    	    success:function(data){
			    			if(data){
			    				chartCPU.getStore().loadData(data.dataList);//加载表格数据
			    				chartMEM.getStore().loadData(data.dataList);//加载表格数据
			    				chartNET.getStore().loadData(data.dataList);//加载表格数据
			    			}
			    			wake.showMessage(wake.TEXT.querySuccess);
			    		},
			    		error:function(){//发生异常时
			    		}
			    	});
//	        	}
//	        	else{
//	        		Ext.getCmp('mainLayout_menuList_serviceInfoForm').getForm().reset();
//	        	}
	    	}
	    	else{
	    		Ext.getCmp('usage_detailForm').getForm().reset();
	    		Ext.getCmp('usage_chartsContent').setVisible(false);
	    	}
	    }
});