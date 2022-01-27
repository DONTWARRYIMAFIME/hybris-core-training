<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Band</title>
    </head>
    <body>
        <c:if test="${not empty bands}">
        <table class="table table-bordered mt-3">
            <thead class="thead-dark">
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>History</th>
                    <th>AlbumSales</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="band" items="${bands}">
                <tr>
                    <td>${band.code}</td>
                    <td>${band.name}</td>
                    <td>${band.history}</td>
                    <td>${band.albumSales}</td>
                    <td>
                        <img src="${band.image}">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
        <c:if test="${empty bands}">
            No bands found
        </c:if>
    </body>
</html>