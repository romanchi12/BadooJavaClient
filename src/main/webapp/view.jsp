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
    <div class="overlay"></div>
    <div class="alert alert-success" role="alert">
        Помагаємо лайкати дівчат, Слава Україні
    </div>
    <div class="row">
        <div class="buttons">
            <button id="yesBtn" class="btn btn-success" >Файна</button>
            <button id="noBtn" class="btn btn-danger">Така собі</button>
        </div>
    </div>
    <div class="row">
        <h1><span id="currentcredentials"></span><span class="badge badge-secondary"><span id="currentage"></span></span></h1>
    </div>
    <div class="row">
        <img id="currentimage" style="max-width: 250px;" src="" class="img-fluid" alt="">
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#yesBtn").click(function () {
            $.ajax({
                url: '/liker/like',
                beforeSend: function(){
                    $(".overlay").fadeIn(1000);
                },
                success: function(data) {
                    var credentials = data['credentials'];
                    var age = data['age'];
                    var photourl = data['photoUrl'];
                    $("#currentimage").attr("src", photourl);
                    $("#currentcredentials").html(credentials);
                    $("#currentage").html(age);
                },
                complete: function(){
                    $(".overlay").fadeOut(1000);
                }
            });
        });
        $("#noBtn").click(function () {
            $.ajax({
                url: '/liker/next',
                success: function(data){
                    var credentials = data['credentials'];
                    var age = data['age'];
                    var photourl = data['photoUrl'];
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