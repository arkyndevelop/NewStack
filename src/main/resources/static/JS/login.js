document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', async (event) => {
            // Previne o envio padrão do formulário, que recarrega a página.
            event.preventDefault();

            // Pega os valores dos campos de input.
            const cpf = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            // Validação simples no frontend.
            if (!cpf || !password) {
                alert('Por favor, preencha o CPF e a senha.');
                return;
            }

            // Cria o corpo da requisição em formato JSON.
            const loginData = {
                username: cpf,
                password: password
            };

            try {
                // Envia a requisição POST para o endpoint de autenticação da API.
                const response = await fetch('/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(loginData)
                });

                // Se a resposta não for 'ok' (ex: 401 Unauthorized), lança um erro.
                if (!response.ok) {
                    const error = await response.json();
                    throw new Error(error.message || 'CPF ou senha inválidos.');
                }

                // Se o login for bem-sucedido, extrai o token da resposta.
                const data = await response.json();

                // Armazena o token no localStorage do navegador para uso futuro.
                localStorage.setItem('jwt_token', data.token);

                // Redireciona o usuário para a página principal do sistema.
                window.location.href = '/v1/home';

            } catch (error) {
                console.error('Erro no login:', error);
                alert(error.message);
            }
        });
    }
});