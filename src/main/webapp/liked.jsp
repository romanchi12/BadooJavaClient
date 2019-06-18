<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Liker</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/styles.css"/>
</head>
<body>
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/liked?page=${page - 1}">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="/liked?page=${page + 1}">1</a></li>
                    <li class="page-item"><a class="page-link" href="/liked?page=${page + 2}">2</a></li>
                    <li class="page-item"><a class="page-link" href="/liked?page=${page + 3}">3</a></li>
                    <li class="page-item"><a class="page-link" href="/liked?page=${page + 1}">Next</a></li>
                </ul>
            </nav>
            <div class="content">
                <table id="example" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Credentials</th>
                        <th>Age</th>
                        <th>Photo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="girls" type="java.util.List<org.romanchi.instagram.model.entities.Girl>"--%>
                    <c:forEach var="girl" items="${girls}">
                        <tr>
                            <td>${girl.id}</td>
                            <td>${girl.credentials}</td>
                            <td>${girl.age}</td>
                            <td><img src="${girl.photoUrl}" alt=""/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col"></div>
    </div>
</body>
</html>