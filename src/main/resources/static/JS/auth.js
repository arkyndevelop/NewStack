document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const cpf = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            try {
                const response = await fetch('/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ username: cpf, password: password }),
                });

                // CORREÇÃO: Verificamos apenas se a resposta foi bem-sucedida (status 200-299)
                if (response.ok) {
                    // Se o login foi bem-sucedido, o cookie foi definido pelo servidor.
                    // A única tarefa do JavaScript agora é redirecionar.
                    window.location.href = '/v1/home';
                } else {
                    // Se a resposta não foi 'ok', lançamos um erro.
                    throw new Error('CPF ou senha inválidos.');
                }

                // As linhas abaixo foram removidas, pois não são mais necessárias
                // const data = await response.json();
                // localStorage.setItem('jwt_token', data.token);

            } catch (error) {
                alert(error.message);
            }
        });
    }
});

// A função de logout agora deve redirecionar para o endpoint do servidor
function logout() {
    window.location.href = '/auth/logout';
}

// A função fetchData não é mais necessária para as requisições normais do navegador,
// mas pode ser mantida caso você tenha alguma chamada de API dinâmica via JavaScript.
// Com a abordagem de cookies, as requisições do navegador já enviarão o token automaticamente.
async function fetchData(url) {
    // Não é mais necessário buscar o token do localStorage e adicionar no header
    const response = await fetch(url, {
        method: 'GET'
    });

    if (response.status === 403) {
        logout();
    }

    return response.json();
}