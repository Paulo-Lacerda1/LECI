let button = document.getElementById("backToTop");

window.onscroll = function() {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop >= 1 || document.documentElement.scrollTop >= 1) {
        button.style.display = "block";
        button.style.animation = "fadeIn 0.5s";
    } else {
        button.style.animation = "fadeOut 0.5s";
        setTimeout(function() {
            button.style.display = "none";
        }, 500); 
    }
}