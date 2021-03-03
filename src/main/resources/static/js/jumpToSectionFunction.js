var jumpToValue= document.getElementById("jumpSection").value;
if(jumpToValue!=null){
    document.getElementById(jumpToValue).scrollIntoView({behavior: 'smooth'});
}