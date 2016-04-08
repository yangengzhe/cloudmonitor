<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/wake.tld" prefix="wake" %>
<!DOCTYPE html>
<html>
<head>		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<wake:import cmp="wake-debug.cmp"/>
<wake:import path="pages/cmp/topology/server" apps="server" ctrls="serverCtrl" views="serverIndex" stores="MenuStore4Tree,statusChartStore" models="statusChartModel"/>


<style type="text/css">
.link { stroke: green; stroke-linejoin:bevel;}
 
.link_error{
    stroke:red;
    stroke-linejoin:bevel;
}
 
.nodetext {
 
    font: 12px sans-serif;
    -webkit-user-select:none;
    -moze-user-select:none;
    stroke-linejoin:bevel;
     
}
 
#container{
    width:1000px;
    height:500px;
    border-radius:5px;
    position:relative;
    margin:20px;
}
</style>
<script type="text/javascript" src="../js/d3.js"></script>

</head>
<body>
</body>
</html>