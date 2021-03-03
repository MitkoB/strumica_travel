var stars = 0;

function rate(number){
    stars = number;
    i=1;
    while(i<=number)
    {
        n=document.getElementById("star"+i);
        n.innerHTML="&starf;";
        i++;
    }
    while(i<6)
    {
        n=document.getElementById("star"+i);
        n.innerHTML="&star;";
        i++;
    }

}
var hiddenGrade=document.getElementsByClassName("hiddenGrade");
var insertGrade=document.getElementsByClassName("insertGrade");
var j=0;
while(j<hiddenGrade.length){
    n=hiddenGrade.item(j);
    grade=parseInt(n.innerHTML);
    k=0;
    while(k<grade){
        insertGrade.item(j).innerHTML+="&starf;";
        k++;
    }
    while(k<5){
        insertGrade.item(j).innerHTML+="&star;";
        k++;
    }
    j++;
}