function maskCPF(value) {
    // Remove todos os caracteres que não são dígitos
    value = value.replace(/\D/g, '');

    // Aplica a máscara de CPF passo a passo
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

    return value;
}

document.getElementById('registerEmployeeForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    // --- 1. Captura e Limpa os Dados ---
    const rawCPF = document.getElementById('CPF').value;
    const cleanCPF = rawCPF.replace(/\D/g, ''); // Remove a máscara

    const userData = {
        name: document.getElementById('name').value,
        CPF: cleanCPF,
        email: document.getElementById('email').value,
        telephone: document.getElementById('telephone').value,
        password: document.getElementById('password').value
    };

    const confirmPassword = document.getElementById('confirmPassword').value;

    // --- 2. Validação no Frontend (ANTES de enviar) ---
    // Verifica se todos os campos estão preenchidos
    for (const key in userData) {
        if (!userData[key]) {
            alert(`O campo ${key} é obrigatório!`);
            return;
        }
    }
    if (cleanCPF.length !== 11) {
        alert("CPF inválido! O CPF deve conter 11 dígitos.");
        return;
    }
    if (userData.password !== confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }


    // --- 3. Envio para a API ---
    try {
        const response = await fetch('/clients/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Falha no cadastro. Verifique os dados.');
        }

        alert('Cadastro realizado com sucesso!');
        window.location.href = '/v1/login';

    } catch (error) {
        console.error('Erro no cadastro:', error);
        alert(error.message);
    }
});