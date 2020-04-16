<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>寄信囉</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@700&display=swap');

    body{
        font-family: 'Noto Sans TC', sans-serif;
        background: #fad2e2;
    }
</style>

<script>
    $(document).ready(function () {
        $('.selectType').change(function(){
            
            if($('.darkType').is(':checked')){
             $('body').css('background','#0e2640').css('color','#fff')
             
            }else{
                $('body').css('background','#fad2e2').css('color','black')
            }
        })
       
    });
</script>
<body>
    <form action="<c:url value='/letter/sendLetter' />" method="POST">
        <div class="form-column">
          <div class="form-group col-md-4">
            <label for="inputEmail4">信件標題</label>
            <input type="text" class="form-control" id="inputEmail4" name="letterTitle">
          </div>
         
          <div class="form-group col-md-2">
            <label for="inputState">想要的回覆類型</label>
            <select id="inputState" class="form-control selectType" name="letterCategory">
              <option selected class="brightType" value="天使">天使</option>
              <option class="darkType" value="惡魔">惡魔</option>
            </select>
          </div>
        </div>
        <div class="form-group m-2">
            <textarea class="form-control col-6" name="content" rows="10"
                required="required" placeholder="內文..." style="resize: none;"></textarea>
        </div>
        <button type="submit" class="btn btn-primary ml-2">送往信箱
        </button>
      </form>
</body>
</html>