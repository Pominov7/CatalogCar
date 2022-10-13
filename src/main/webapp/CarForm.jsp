<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pominov7
  Date: 10.10.2022
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
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
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: gray">
        <div>
            <a href="<%=request.getContextPath()%>/list" class="navbar-brand">Главная</a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${car != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${car == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${car != null}">
                                Изменить данные
                            </c:if>
                            <c:if test="${car == null}">
                                Добавить автомобиль
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${car != null}">
                        <input type="hidden" name="id" value="<c:out value='${car.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Производитель</label> <input type="text"
                                                            value="<c:out value='${car.nameF}' />"
                                                            class="form-control"
                                                            name="nameCar" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Модель</label> <input type="text"
                                                     value="<c:out value='${car.modelF}' />" class="form-control"
                                                     name="model">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Год выпуска</label> <input type="text"
                                                          value="<c:out value='${car.yearF}' />" class="form-control"
                                                          name="year">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Цена</label> <input type="text"
                                                   value="<c:out value='${car.priceF}' />" class="form-control"
                                                   name="price">
                    </fieldset>
                    <br>
                    <button type="submit" class="btn btn-success">Сохранить</button>
                </form>
        </div>
    </div>
</div>
<jsp:include page="./footer.jsp"/>
</body>
</html>