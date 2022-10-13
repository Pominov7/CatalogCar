<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pominov7
  Date: 10.10.2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Каталог автомобилей</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <style>
        html {
            background-image: url(image/auto-1.jpg);
            overflow:  hidden;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: gray">
        <div>
            <a href="<%=request.getContextPath()%>/list" class="navbar-brand">На главную</a>
        </div>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">Каталог автомобилей</h3>
        <hr>
        <div class="container text-left" style="text-align: center">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-info">
                <svg
                        xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-car-front-fill" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                          d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM3 10a1 1 0 1 0 0-2 1 1 0 0 0 0 2Zm10 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM2.906 5.189l.956-1.913A.5.5 0 0 1 4.309 3h7.382a.5.5 0 0 1 .447.276l.956 1.913a.51.51 0 0 1-.497.731c-.91-.073-3.35-.17-4.597-.17-1.247 0-3.688.097-4.597.17a.51.51 0 0 1-.497-.731Z"></path>
                </svg>
                Добавить авто в каталог</a>
        </div>
        <br>
        <table class="table table-success table-striped">
            <thead>
            <tr class="align-middle text-center">
                <th>ID</th>
                <th>Производитель</th>
                <th>Модель</th>
                <th>Год выпуска</th>
                <th>Цена</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="car" items="${listCars}">

                <tr class="align-middle text-center">
                    <td><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.nameF}"/></td>
                    <td><c:out value="${car.modelF}"/></td>
                    <td><c:out value="${car.yearF}"/></td>
                    <td><c:out value="${car.priceF}"/></td>
                    <td><a href="edit?id=<c:out value='${car.id}' />" class="btn btn-outline-primary">
                        <small style="font-size: x-small">Изменить</small></a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${car.id}' />" class="btn btn-outline-danger">
                            <small style="font-size: x-small">Удалить</small></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="./footer.jsp"/>
</body>
</html>