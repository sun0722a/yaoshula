<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<style>
    
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap');
    body{
        background: aliceblue;
        color: #0e2640;
        font-family: 'Noto Sans TC', sans-serif;
    }
    .resetBox{
        width: 300px;
        height: 200px;
        margin: 10px auto;
    }
    
</style>

<script>
    $(document).ready(function () {
        $('#button-addon').click(function(){
           let changeType = $('.checked').attr('type')
           if(changeType == 'password'){
               $('.checked').attr('type','text')
           }else{
            $('.checked').attr('type','password')
           }
        })
    });
</script>
<body>
    <div class="resetBox">
        <div>請重設密碼</div>
        <form>
            <div class="form-group">
              <input type="password" class="form-control mt-2" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Password">
            </div>
            <div class="checkPswd input-group mb-3">
              <input type="password" class="form-control checked" id="exampleInputPassword1" placeholder="Check Password"  >
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon"><i class="fas fa-unlock"></i></button>
              </div>
            </div>
            <button type="submit" class="btn-sm btn-primary">Submit</button>
            
          </form>
    </div>
</body>
</html>