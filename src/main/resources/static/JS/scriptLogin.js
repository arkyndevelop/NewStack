const formulario = document.querySelector('form');
const Iusername = document.querySelector('.username');
const Ipassword = document.querySelector('.password');

formulario.addEventListener('submit',function(event){
    event.preventDefault();
    login(Iusername.value, Ipassword.value);
});

function login(username, password){
    fetch('/home/login',{
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            username: username,
            password: password
        })
    }).then(function(res){console.log(res)})
        .catch(function (res){console.log(res)});
}
