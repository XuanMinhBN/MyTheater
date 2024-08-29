let btn = document.getElementById("btn");

// Check if there is a stored position and apply it
window.onload = function() {
    const savedPosition = localStorage.getItem("btnPosition");
    if (savedPosition) {
        btn.style.left = savedPosition;
    }
};

function leftClick() {
    btn.style.left = "0";
    localStorage.setItem("btnPosition", "0");
    window.location = 'http://localhost:8080/main/home/1';
}

function rightClick() {
    btn.style.left = "158px";
    localStorage.setItem("btnPosition", "158px");
    window.location = 'http://localhost:8080/main/home/2';
}



