<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<title>主页</title>
<style type="text/css">
	#head{/*设置头部属性*/
		height: 70px;
	}
	#content{/*设置主体的属性*/
		width: 100%;/*主体宽度*/
		height: auto;/*高度*/
		min-height: 700px;/*最小高度*/
	}
	#left{/*设置左侧浮动栏属性*/
		float: left;/*目录栏左浮动*/
		position: absolute;/*设置位置*/		
		height: 700px;/*高度*/
		width: 15%;/*宽度*/
		overflow: auto;
		background-color:#E7E7E7;/*背景色*/
		border:1px solid #E7E7F8;/*背景边框色*/
		text-align: left;/*文字左对齐*/
	}
	#right{/*设置右侧浮动栏属性*/
		float: right;/*右浮动*/
		position: absolute;/*设置针对根元素的绝对位置*/
		left:16%;/*距离右边的距离*/
		
		height:auto;/**/
		min-height:700px;/**/
		width: 75%;/**/
		min-width:500px;
		overflow: auto;	/**/	
	}
	#iframe2{/*设置框架属性*/
		width: 100%;
		height: 700px;
		
	}
	#foot{/*设置底部格式*/
		
	}
	ul,li{/*取消ul和li列表前面的点*/
		list-style-type: none;
	}
	#li1,#li2,#li3,#li4{/*设置子目录为隐藏*/
		display: none;
	}
	
/* 纯CSS 画图标 */
    .InvertedTriangle {
        text-align:center;
        width:0px;
        height:0px;
        float:left;
        border-top:10px solid #000000;
        border-left:5px solid transparent;
        border-right:5px solid transparent;
 
        position: relative;
        top: 5px;
        right: 8px;
    }
    .Triangle {
        text-align:center;
        padding-right:5px;
        width: 0;
        height:0;
        float: left;
        border-left: 10px solid #000000;
        border-top: 5px solid transparent;
        border-bottom: 5px solid transparent;
 
        position: relative;
        top: 5px;
        right: 5px;
    }
</style>
</head>
<body>
	<div>
		<!-- 头部 -->
		<div id="head">
			<iframe src="commonhead.jsp" width="100%" frameborder="0"></iframe>
		</div>
		<!-- 内容 -->
		<div id="content">
			<div id="left">
				<ul>
					<li><span class="Triangle" onclick="catalog(li1)"></span><span id="System1" onclick="openlist(this)">环境管理</span>
						<ul id="li1">
							<li id="addSystem" onclick="openlist(this)">新建环境</li>
							<li id="modifySystem" onclick="openlist(this)">修改环境</li>
							
						</ul>
					</li>
					<li><span class="Triangle" onclick="catalog(li2)"></span><span id="Interface1" onclick="openlist(this)">接口管理</span>
						<ul id="li2">
							<li id="addInterface" onclick="openlist(this)">新增接口</li>
							<li id="modifyInterface" onclick="openlist(this)">修改接口</li>
						</ul>
					</li>
					<li><span class="Triangle" onclick="catalog(li3)"></span><span id="plug1" onclick="openlist(this)">组件管理</span>
						<ul id="li3">
							<li id="addPlug" onclick="openlist(this)">新增组件</li>
							<li id="modifyPlug" onclick="openlist(this)">修改组件</li>
						</ul>
					</li>
					<li><span class="Triangle" onclick="catalog(li4)"></span><span id="plan1" onclick="openlist(this)">计划管理</span>
						<ul id="li4">
							<li id="addPlan" onclick="openlist(this)">新建计划</li>
							<li id="modifyPlan" onclick="openlist(this)">修改计划</li>
						</ul>
					</li>
				</ul>
				
				
			</div>
			<div id="right">
				<iframe id="iframe2" width="100%" ></iframe>
				
			</div>
		</div>
		<!-- 底部 -->
		<div id="foot"></div>
	</div>
<script type="text/javascript">
	//展开目录操作,还要变更展开图标样式
	function catalog(obj){
		var s=$(obj);
		var v=s[0].style.display;
		var bro=s.prev().prev();//传入id定位为后第二个兄弟元素，所以往前两次
		if(v=="block"){		// 第一次css中设置的none但是这里的v为空，所以没法用等于none来判断
			bro.attr("class","Triangle");
			s.hide();			
		}else{
			bro.attr("class","InvertedTriangle");
			s.show();	
		}
	}
	function openlist(obj){
		var  v=$(obj).attr("id");
		if(v=="System1"){// 环境页
			$("#iframe2").attr("src","systemList.action");
		}else if(v=="Interface1"){// 接口页
			$("#iframe2").attr("src","interfacelist.action");
		}else if(v=="plug1"){// 组件页
			$("#iframe2").attr("src","plugList.action");
		}else if(v=="plan1"){ // j计划页
			$("#iframe2").attr("src","planList.action");
		}else if(v=="addSystem"){
			$("#iframe2").attr("src","addSystem.jsp");
		}else if(v=="modifySystem"){
			$("#iframe2").attr("src","modifySystem.action");
		}else if(v=="addInterface"){
			$("#iframe2").attr("src","addInterface.jsp");
		}else if(v=="modifyInterface"){		
			$("#iframe2").attr("src","modifyInterface.action");
		}else if(v=="addPlug"){
			$("#iframe2").attr("src","addPlug.jsp");
		}else if(v=="modifyPlug"){
			$("#iframe2").attr("src","modifyPlug.action");
		}else if(v=="addPlan"){
			$("#iframe2").attr("src","addPlan.jsp");
		}else if(v=="modifyPlan"){
			$("#iframe2").attr("src","modifyPlan.action");
		}else{		
		
	}
}	

</script>
</body>
</html>