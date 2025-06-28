/**
 * Aplica uma máscara de formatação de CPF (XXX.XXX.XXX-XX) a um valor de input.
 * @param {string} value - O valor atual do campo de CPF.
 * @returns {string} O valor com a máscara aplicada.
 */
function maskCPF(value) {
    // Remove todos os caracteres que não são dígitos
    value = value.replace(/\D/g, '');

    // Aplica a máscara de CPF passo a passo para uma melhor experiência de digitação
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

    return value;
}

/**
 * Lida com o envio do formulário de cadastro de cliente.
 * @param {Event} event - O evento de submit do formulário.
 */
async function handleRegisterSubmit(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    // --- 1. Captura e Validação dos Dados ---
    const form = event.target;
    const name = form.querySelector('#name').value;
    const rawCPF = form.querySelector('#CPF').value;
    const email = form.querySelector('#email').value;
    const telephone = form.querySelector('#telephone').value;
    const password = form.querySelector('#password').value;
    const confirmPassword = form.querySelector('#confirmPassword').value;
    const cleanCPF = rawCPF.replace(/\D/g, ''); // Remove a máscara

    // Validações de frontend para feedback rápido ao usuário
    if (!name || !rawCPF || !email || !telephone || !password) {
        alert('Todos os campos são obrigatórios!');
        return;
    }
    if (cleanCPF.length !== 11) {
        alert("CPF inválido! O CPF deve conter 11 dígitos.");
        return;
    }
    if (password !== confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }

    // --- 2. Preparação dos Dados e do Token CSRF ---
    const userData = { name, CPF: cleanCPF, email, telephone, password };

    try {
        // Lê o token e o nome do cabeçalho das meta tags no HTML
        const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
        const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

        // --- 3. Envio para a API com o Token CSRF ---
        const response = await fetch('/clients/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // Inclui o token CSRF no cabeçalho da requisição
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            // Tenta extrair uma mensagem de erro mais clara do backend
            const errorData = await response.json().catch(() => ({ message: 'Ocorreu um erro desconhecido.' }));
            throw new Error(errorData.message || 'Falha no cadastro. Verifique os dados.');
        }

        alert('Cadastro realizado com sucesso!');
        window.location.href = '/login'; // Redireciona para a página de login

    } catch (error) {
        console.error('Erro no cadastro:', error);
        alert(error.message);
    }
}

// Adiciona o listener de evento ao formulário quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerClientForm');
    if (registerForm) {
        registerForm.addEventListener('submit', handleRegisterSubmit);
    }
});