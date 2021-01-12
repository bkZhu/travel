<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>旅游网</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/m_include.js"></script>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <style>
        .content{
            height:600px;
            border:1px solid;
        }
    </style>
    <script>
        function active_order(order_id){
            if(confirm("您确定要受理该订单吗")){
                location.href= "${pageContext.request.contextPath}/order/active?order_id="+order_id;
            }
        }
        window.onload = function(){
            //受理选中
            document.getElementById("activeSelected").onclick = function(){
                var flag = false;
                var cbs = document.getElementsByName("order_id");
                for(var i=0;i<cbs.length;i++){
                    if(cbs[i].checked){
                        flag = true;
                        break;
                    }
                }
                if(flag==false){
                    alert("请先选择条目");
                }
                else{
                    if(confirm("您确定要受理选中条目吗")){
                        document.getElementById("form").submit();
                    }
                }
            }
            //全选checkbox
            document.getElementById("firstCb").onclick = function(){
                    var cbs = document.getElementsByName("order_id");
                    for(var i=0;i<cbs.length;i++){
                        cbs[i].checked = this.checked;
                    }
            }
        }
    </script>
</head>
<body>
<!--引入头部-->
<div id="m_header"></div>
<!-- banner start-->
<section id="banner">
</section>
<!-- banner end-->
<!-- 旅游 start-->



<div class="container">
    <div>
        <a
            href="${pageContext.request.contextPath}/FindOrderByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
        </a>
    </div>
    <!-- 筛选搜索部分-->
    <form class="form-inline" style="float:left" action="${pageContext.request.contextPath}/FindOrderByPageServlet" method="post">
      <div class="form-group">
        <label for="username">姓名</label>
        <input type="text" class="form-control" name="username" value="${condition.username[0]}">
      </div>
      <div class="form-group">
        <label for="email">email</label>
        <input type="text" class="form-control" name="email" value="${condition.email[0]}">
      </div>
        <div class="form-group">
          <label for="status">是否受理(Y/N)</label>
          <input type="text" class="form-control" name="status" value="${condition.status[0]}">
        </div>
      <button type="submit" class="btn btn-default">筛选</button>
    </form>

    <div style="float:right; margin:10px">
        <a class="btn btn-primary" href="javascript:void(0);" id="activeSelected">受理选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/ActiveSelected" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb" style="width:15px;height:15px;"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>内容</th>
            <th>email</th>
            <th>价格</th>
            <th>是否受理</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="order" varStatus="s">
            <tr>
                <td><input type="checkbox" style="width:15px;height:15px;" name="order_id" value="${order.order_id}"></td>
                <td>${order.order_id}</td>
                <td>${order.username}</td>
                <td>${order.content}</td>
                <td>${order.email}</td>
                <td>${order.price}</td>
                <td>${order.status}</td>
                <td>
                <a class="btn btn-default btn-sm" href="javascript:active_order(${order.order_id})">受理</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li>
              <c:if test="${pb.currentPage==1}">
                <li class="disabled">
                  <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
              </c:if>
                <c:if test="${pb.currentPage!=1}">
                    <a href="${pageContext.request.contextPath}/FindOrderByPageServlet?currentPage=${pb.currentPage-1}&rows=10&username=${condition.username[0]}&email=${condition.email[0]}&status=${condition.status[0]}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>

            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage==i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/FindOrderByPageServlet?currentPage=${i}&rows=10&username=${condition.username[0]}&email=${condition.email[0]}&status=${condition.status[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/FindOrderByPageServlet?currentPage=${i}&rows=10&username=${condition.username[0]}&email=${condition.email[0]}&status=${condition.status[0]}">${i}</a></li>
                </c:if>
            </c:forEach>

            <li>
              <c:if test="${pb.totalPage == pb.currentPage}">
                  <li class = "disabled">
                  <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
              </c:if>
                <c:if test="${pb.totalPage!= pb.currentPage}">
                    <a href="${pageContext.request.contextPath}/FindOrderByPageServlet?currentPage=${pb.currentPage+1}&rows=10&username=${condition.username[0]}&email=${condition.email[0]}&status=${condition.status[0]}" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                </c:if>
            </li>
            <span style="font-size: 25px;margin-left:10px;">
                共${pb.totalCount}条记录，共${pb.totalPage}页。
            </span>
          </ul>
        </nav>
</div>

<!-- 旅游 end-->
<!--导入底部-->
<div id="footer"></div>

</body>
</html>