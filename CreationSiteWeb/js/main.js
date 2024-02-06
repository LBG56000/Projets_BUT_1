window.onload = function() { 
    const imagesaison = document.getElementsByClassName("imgsaison");
    const imagefilm = document.getElementsByClassName("imgfilm");

    for (const image of imagesaison) {
        image.addEventListener("mouseover", function() {
            image.style.transform = "scale(1.1) translateY(-20px)";
            image.style.transitionDuration = "0.5s";
            image.parentElement.previousElementSibling.style.opacity ="0"
            image.parentElement.previousElementSibling.style.transitionDuration = "0.5s";
        });
        image.addEventListener("mouseleave", function() {
            image.style.transform = "scale(1) translateY(0px)";
            image.style.transition = "transform 0.1s";
            image.parentElement.previousElementSibling.style.opacity ="100"
            image.parentElement.previousElementSibling.style.transitionDuration = "0.1s";
        });
    }
    
    for (const image of imagefilm) {
        image.addEventListener("mouseover", function() {
            image.style.filter = "brightness(0.3)";
            image.style.transition = "filter 0.3s";
            image.nextElementSibling.style.opacity ="100"
            image.nextElementSibling.style.transitionDuration = "0.2s";
        });
        image.addEventListener("mouseout", function() {
            image.style.filter = "brightness(1)";
            image.style.transition = "filter 0.1s";
            image.nextElementSibling.style.opacity ="0"
            image.nextElementSibling.style.transitionDuration = "0.1s";
        });
    }
    const overlayTexts = document.getElementsByClassName("overlay-text");
    for (const text of overlayTexts) {
        text.style.opacity ="0";
        text.addEventListener("mouseover", function() {
            text.style.opacity ="100";
            text.previousElementSibling.style.filter = "brightness(0.3)";
            image.style.filter = "brightness(0.3)";

        });
        text.addEventListener("mouseout", function() {
           if(!text.previousElementSibling.matches(":hover")) {
                text.style.opacity ="0";
                style.transitionDuration = "0.1s";
                text.previousElementSibling.style.filter = "brightness(1)";
                text.previousElementSibling.style.transition = "filter 0.1s";
            }
        })
    }

    const imgPersos = document.getElementsByClassName("imgperso");
    for (const imgPerso of imgPersos) {
        imgPerso.style.transform = "translateY(-40px)";
        imgPerso.previousElementSibling.style.opacity ="0";
        imgPerso.addEventListener("mouseover", function() {
            imgPerso.style.transform = "translateY(-10px)";
            imgPerso.style.transitionDuration = "0.5s";
            imgPerso.previousElementSibling.style.opacity ="100"
            imgPerso.previousElementSibling.style.transitionDuration = "0.1s";
        });
        imgPerso.addEventListener("mouseout", function() {
            imgPerso.style.transform = "translateY(-40px)";
            imgPerso.style.transitionDuration = "0.1s";
            imgPerso.previousElementSibling.style.opacity ="0"
            imgPerso.previousElementSibling.style.transitionDuration = "0.1s";
        })
    }
    
    var sidenav = document.getElementById("mySidenav");
    var openBtn = document.getElementById("openBtn");
    var closeBtn = document.getElementById("closeBtn");

    openBtn.onclick = openNav;
    closeBtn.onclick = closeNav;

    function openNav() {
    sidenav.classList.add("active");
    }

    function closeNav() {
    sidenav.classList.remove("active");
    }
}

