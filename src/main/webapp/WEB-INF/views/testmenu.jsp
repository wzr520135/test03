<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EasyUI-3-菜单按钮</title>
    <script type="text/javascript"
            src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css"
          href="/js/jquery-easyui-1.4.1/themes/icon.css" />
    <link rel="stylesheet" type="text/css"
          href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
    <script type="text/javascript">
        /*通过js创建树形结构 */
        $(function(){
            $("#tree").tree({
                url:"test01",		//加载远程JSON数据
                method:"get",			//请求方式  POST
                animate:true,			//表示显示折叠端口动画效果
                checkbox:true,			//表述复选框
                lines:true,				//表示显示连接线
                dnd:true,				//是否拖拽
                onClick:function(node){	//添加点击事件

                    alert(node.text);
                    //控制台
                    console.info(node);
                }
            });
        })
    </script>
</head>
<body>
<h1>EasyUI-权限管理界面</h1>
<ul id="tree"></ul>
</body>
</html>