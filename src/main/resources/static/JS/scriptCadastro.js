/**
 * Aplica uma máscara de formatação de CPF (XXX.XXX.XXX-XX) a um valor de input.
 * @param {string} value - O valor atual do campo de CPF.
 * @returns {string} O valor com a máscara aplicada.
 */
function maskCPF(value) {
    // Remove todos os caracteres que não são dígitos
    value = value.replace(/\D/g, '');

    // Aplica a máscara de CPF passo a passo
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

    // Validações de frontend para feedback rápido ao usuário
    if (!name || !rawCPF || !email || !telephone || !password) {
        alert('Todos os campos são obrigatórios!');
        return;
    }
    const cleanCPF = rawCPF.replace(/\D/g, ''); // Remove a máscara
    if (cleanCPF.length !== 11) {
        alert("CPF inválido! O CPF deve conter 11 dígitos.");
        return;
    }
    if (password !== confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }

    // --- 2. Preparação dos Dados ---
    const userData = { name, CPF: cleanCPF, email, telephone, password };

    try {
        // --- 3. Envio para a API (sem cabeçalho CSRF) ---
        const response = await fetch('/clients/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                // O cabeçalho CSRF foi removido daqui
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

    // Adiciona o listener para a máscara de CPF
    const cpfInput = document.getElementById('CPF');
    if(cpfInput){
        cpfInput.addEventListener('input', (e) => {
            e.target.value = maskCPF(e.target.value);
        });
    }
});