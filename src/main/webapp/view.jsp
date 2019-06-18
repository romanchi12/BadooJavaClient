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

<div class="page">
    <div class="alert alert-success" role="alert">
        Помагаємо лайкати дівчат, Слава Україні
    </div>
    <div class="row">
        <div class="col">
            <div id="currentcredentials"></div>
        </div>
        <div class="col">
            <div id="currentage"></div>
        </div>
    </div>
    <div class="row">
        <img id="currentimage" height="250" src="" class="img-fluid" alt="">
    </div>
</div>
<div class="buttons">
    <button id="yesBtn">Файна</button>
    <button id="noBtn">Така собі</button>
</div>
<script>
    $(document).ready(function () {
        $("#yesBtn").click(function () {
            $.ajax({
                url: '/liker/like',
                success: function(data) {
                    var credentials = data['credentials'];
                    var age = data['age'];
                    var photourl = data['photourl'];
                    $("#currentimage").attr("src", photourl);
                    $("#currentcredentials").html(credentials);
                    $("#currentage").html(age);
                }
            });
        });
        $("#noBtn").click(function () {
            $.ajax({
                url: '/liker/next',
                success: function(data){
                    var credentials = data['credentials'];
                    var age = data['age'];
                    var photourl = data['photourl'];
                    $("#currentimage").attr("src", photourl);
                    $("#currentcredentials").html(credentials);
                    $("#currentage").html(age);
                }
            });
        });

    });
</script>
</body>
</html>