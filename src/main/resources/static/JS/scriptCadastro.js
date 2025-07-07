async function handleRegisterSubmit(event) {
    event.preventDefault();

    const form = event.target;
    // ... (captura dos dados do formulário permanece a mesma)
    const name = form.querySelector('input[name="name"]').value;
    const rawCPF = form.querySelector('input[name="CPF"]').value;
    const email = form.querySelector('input[name="email"]').value;
    const telephone = form.querySelector('input[name="telephone"]').value;
    const password = form.querySelector('input[name="password"]').value;
    const confirmPassword = form.querySelector('input[name="confirmPassword"]').value;

    // ... (validações permanecem as mesmas)
    if (!name || !rawCPF || !email || !telephone || !password) {
        alert('Todos os campos são obrigatórios!');
        return;
    }
    const cleanCPF = rawCPF.replace(/\D/g, '');
    if (cleanCPF.length !== 11) {
        alert("CPF inválido! O CPF deve conter 11 dígitos.");
        return;
    }
    if (password !== confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }


    const userData = {
        name: name,
        CPF: cleanCPF,
        email: email,
        telephone: telephone,
        password: password
    };

    try {
        const response = await fetch('/clients/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({ message: 'Ocorreu um erro desconhecido.' }));
            throw new Error(errorData.message || 'Falha no cadastro. Verifique os dados.');
        }

        // --- LÓGICA DE REDIRECIONAMENTO CORRIGIDA ---
        const responseData = await response.json(); // Pega o JSON da resposta
        alert('Cadastro realizado com sucesso!');
        window.location.href = responseData.redirectUrl; // Usa a URL enviada pelo backend

    } catch (error) {
        console.error('Erro no cadastro:', error);
        alert(error.message);
    }
}

// Adiciona o listener de evento ao formulário quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', () => {
    // Aplica a lógica de submit a todos os formulários com a classe .login-form
    document.querySelectorAll('.login-form').forEach(form => {
        form.addEventListener('submit', handleRegisterSubmit);
    });

    // Aplica a máscara de CPF a todos os inputs de CPF
    document.querySelectorAll('input[name="CPF"]').forEach(cpfInput => {
        cpfInput.addEventListener('input', (e) => {
            e.target.value = maskCPF(e.target.value);
        });
    });
});