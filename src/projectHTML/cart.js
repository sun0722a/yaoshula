
$(document).ready(function(){
    cost = $('#cost').html();
    
   itemCount =  $("input[type='number']").change(function (e) { 
        e.preventDefault();
        total = (itemCount.val()) * cost;
        $('.total').text(total);
    });


    // $('submitBtn').click((){
        
    // })
});