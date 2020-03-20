
$(document).ready(function(){
    cost = $('#cost').html();
    
   itemCount =  $("input[type='number']").change(function (e) { 
        e.preventDefault();
        total = (itemCount.val()) * cost;
        $('.total').text(total);
    });

    //checkBox 全選的功能
    $('#allCheck').change(function(){
        $('.items').find(':checkbox').prop('checked', $(this).is(':checked')?true:false);
    })
    
});