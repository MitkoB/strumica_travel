function f() {
    document.getElementsByClassName('dropdown_mobile')[0].classList.toggle('down');
    if (document.getElementsByClassName('dropdown_mobile')[0].classList.contains('down')) {
        setTimeout(function() {
            document.getElementsByClassName('dropdown_mobile')[0].style.overflow = 'visible'
        }, 500)
    } else {
        document.getElementsByClassName('dropdown_mobile')[0].style.overflow = 'hidden'
    }
}

function myFunction(x) {
    if (x.matches) { // If media query matches
        document.getElementById("default_row").style.display="none";
        document.getElementById("smaller_row").style.display="initial";
        document.getElementById("wider_row").style.display="initial";

    } else {
        document.getElementById("default_row").style.display="";
        document.getElementById("smaller_row").style.display="none";
        document.getElementById("wider_row").style.display="none";
    }
}

var x = window.matchMedia("(max-width: 800px)")
myFunction(x) // Call listener function at run time
x.addListener(myFunction)