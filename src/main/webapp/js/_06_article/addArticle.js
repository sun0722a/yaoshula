function doFirst(){
    type = document.getElementById('type');
    typeTitle = document.getElementById('typeTitle');
    // select = false;
    type.addEventListener('change',removeType);
}
function removeType(){
    typeTitle.remove();
    // select=true;
}

window.addEventListener('load',doFirst);