<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Wcd Exam</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/public/images/logo-mini.svg"/>
    <!-- plugins:css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@mdi/font@6.9.96/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/paged.list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/toast.custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/custom.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.7/css/fileinput.css" media="all"
          rel="stylesheet" type="text/css"/>
    <!-- end plugins:css -->
    <script src="${pageContext.request.contextPath}/public/js/jquery/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/pages/const.js"></script>

</head>

<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
        <jsp:include page="_navbar.jsp"/>
        <div class="main-panel">
            <div id="contentBody" class="content-wrapper container-fluid position-relative py-3">
                <jsp:include page="${body}"/>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/public/js/jquery/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/jquery/jquery.validate.unobtrusive.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/toast.custom.js"></script>
<script src="${pageContext.request.contextPath}/public/js/ajax.helper.js"></script>
<script src="${pageContext.request.contextPath}/public/js/pages/misc.js"></script>
</body>
</html>