<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改订单</h3>
        <form action="${pageContext.request.contextPath}/OrderUpdateServlet" method="post">
            <!--  隐藏域 提交id-->
            <input type="hidden" name="order_id" value="${order.order_id}">
          <div class="form-group">
            <label for="username">姓名：</label>
            <input type="text" class="form-control" id="username" name="username"  value="${order.username}" readonly="readonly" />
          </div>
            <div class="form-group">
              <label for="content">内容：</label>
              <input type="text" class="form-control" id="content" name="content"  value="${order.content}" readonly="readonly" />
            </div>
              <div class="form-group">
                <label for="price">价格：</label>
                <input type="text" class="form-control" id="price" name="price"  value="${order.price}" readonly="readonly"/>
              </div>
                <div class="form-group">
                  <label for="status">受理情况：</label>
                  <input type="text" class="form-control" id="status" name="status"  value="${order.status}" readonly="readonly" />
                </div>
          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" value="${order.email}" name="email" placeholder="请输入邮箱地址"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回"/>
             </div>
        </form>
        </div>
    </body>
</html>