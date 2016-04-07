<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/wake.tld" prefix="wake" %>
<!DOCTYPE html>
<html>
<head>		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<wake:import cmp="wake-debug.cmp"/>
<wake:import path="pages/cmp/topology/server" apps="server" ctrls="serverCtrl" views="serverIndex" stores="MenuStore4Tree,statusChartStore" models="statusChartModel"/>


<link rel="stylesheet" href="../jsPlumb/topo-all.css">
<link rel="stylesheet" href="../jsPlumb/topo.css">
<script src="../jsPlumb/jsBezier-0.6-min.js"></script> 
		<!-- jsplumb geom functions -->   
		<script src="../jsPlumb/jsplumb-geom-0.1.js"></script>
		<!-- jsplumb util -->
		<script src="../jsPlumb/util.js"></script>
        <!-- base DOM adapter -->
		<script src="../jsPlumb/dom-adapter.js"></script>        
		<!-- main jsplumb engine -->
		<script src="../jsPlumb/jsPlumb.js"></script>
        <!-- endpoint -->
		<script src="../jsPlumb/endpoint.js"></script>                
        <!-- connection -->
		<script src="../jsPlumb/connection.js"></script>
        <!-- anchors -->
		<script src="../jsPlumb/anchors.js"></script>        
		<!-- connectors, endpoint and overlays  -->
		<script src="../jsPlumb/defaults.js"></script>
		<!-- connector editors -->
		<script src="../jsPlumb/connector-editors.js"></script>
		<!-- bezier connectors -->
		<script src="../jsPlumb/connectors-bezier.js"></script>
		<!-- state machine connectors -->
		<script src="../jsPlumb/connectors-statemachine.js"></script>
        <!-- flowchart connectors -->
		<script src="../jsPlumb/connectors-flowchart.js"></script>        
		<!-- SVG renderer -->
		<script src="../jsPlumb/renderers-svg.js"></script>
		<!-- canvas renderer -->
		<script src="../jsPlumb/renderers-canvas.js"></script>
		<!-- vml renderer -->
		<script src="../jsPlumb/renderers-vml.js"></script>
        
        <!-- jquery jsPlumb adapter -->
		<script src="../jsPlumb/jquery.jsPlumb.js"></script>
		<script src="../jsPlumb/drawtopo.js"></script>
		
		<script type="text/javascript">
			
		
		
		</script>

</head>
<body>

</body>
</html>