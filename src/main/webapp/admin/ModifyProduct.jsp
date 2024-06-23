<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/modifyProduct.css">
    <script src="../script/modifyProduct.js"></script>
</head>
<body>
<%@ include file="../Header.jsp" %>
<%
    ProductBean product = (ProductBean) request.getSession().getAttribute("product");
    CategoryBean category = (CategoryBean) request.getSession().getAttribute("category");
%>



<div></div>

</body>
</html>
