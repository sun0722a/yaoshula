function doFirst(){
    letterContent = document.getElementById('letterContent');
    contentLength = document.getElementById('contentLength');
    submitbtn = document.getElementById('submitbtn');

    letterContent.addEventListener('keyup',textlength)
    
    

    
}

function textlength() {
    var lengthNo = letterContent.value.length
    contentLength.innerHTML = 250 - lengthNo;

    if(letterContent.value.length > 10){
        submitbtn.disabled = false;
    }else{
        submitbtn.disabled = true;
    }
}


window.addEventListener('load',doFirst);