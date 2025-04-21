let dark_light_theme_btn = document.getElementById("dark_light_theme_btn");
let html = document.querySelector("html");
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme(currentTheme); // method call when dom content loaded
})

function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme != null) {
        return theme;
    }
    return "light";
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function changeTheme(theme) {
    html.classList.add(theme);
    dark_light_theme_btn.querySelector("span").textContent = (theme === "light") ? "Dark" : "Light";
    setTheme(theme);
}

dark_light_theme_btn.addEventListener("click", () => {
    // document.querySelector('html').classList.toggle("dark");
    if (currentTheme === "light") {
        html.classList.remove(currentTheme);
        currentTheme = "dark";
        changeTheme(currentTheme);
    } else {
        html.classList.remove(currentTheme);
        currentTheme = "light";
        changeTheme(currentTheme);
    }
})
