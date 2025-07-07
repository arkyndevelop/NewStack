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

                // CORREÇÃO: Apenas verificamos se a resposta foi bem-sucedida (status 200-299)
                if (response.ok) {
                    // Se o login foi bem-sucedido, o cookie foi definido pelo servidor.
                    // A única tarefa do JavaScript agora é redirecionar.
                    window.location.href = '/v1/home';
                } else {
                    // Se a resposta não foi 'ok', lançamos um erro.
                    throw new Error('CPF ou senha inválidos.');
                }

            } catch (error) {
                alert(error.message);
            }
        });
    }
});