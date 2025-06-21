const formulario = document.querySelector('form');
const Icpf = document.querySelector('#cpf');
const Ipassword = document.querySelector('.password');

formulario.addEventListener('submit',function(event){
    event.preventDefault();
    login(Icpf.value, Ipassword.value);
});

function login(CPF, password){
    fetch('/home',{
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            CPF: CPF,
            password: password
        })
    }).then(function(res){console.log(res)})
        .catch(function (res){console.log(res)});
}
