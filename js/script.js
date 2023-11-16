const form = document.querySelector("form");
const nome = document.querySelector(".nome");
const email = document.querySelector(".email");
const senha = document.querySelector(".senha");
const rsenha = document.querySelector(".rsenha");

function cadastrar () {

    fetch("http://localhost:8080/usuarios",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({ 
                nome: nome.value,
                email: email.value,
                senha: senha.value
            })
        })
    .then(function (res) { console.log(res); })
    .catch(function (res) { console.log(res); })
};

function limpar() {
    nome.value = "";
    email.value = "";
    senha.value = "";
    rsenha.value = "";
}

form.addEventListener("submit", function(event) {
    event.preventDefault();

    cadastrar();
    limpar();
});

function abrirMenu() {
    var dropdown = document.getElementById("menuDropdown");
    dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";

    var mobileMenu = document.querySelector('.mobile-menu');
    var rect = mobileMenu.getBoundingClientRect();

    dropdown.style.top = (rect.bottom + window.scrollY) + 'px';
    dropdown.style.left = rect.left + 'px';
}


function login() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("senha").value;
}