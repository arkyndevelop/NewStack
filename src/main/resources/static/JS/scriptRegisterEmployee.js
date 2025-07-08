// NewStack/src/main/resources/static/JS/scriptRegisterEmployee.js

document.addEventListener('DOMContentLoaded', function() {
    // Função para aplicar a máscara de CPF
    const applyCpfMask = (input) => {
        let value = input.value.replace(/\D/g, '');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
        input.value = value;
    };

    // Função para aplicar a máscara de telefone
    const applyTelephoneMask = (input) => {
        let value = input.value.replace(/\D/g, '');
        if (value.length <= 10) {
            value = value.replace(/(\d{2})(\d)/, '($1) $2');
            value = value.replace(/(\d{4})(\d)/, '$1-$2');
        } else {
            value = value.replace(/(\d{2})(\d)/, '($1) $2');
            value = value.replace(/(\d{5})(\d)/, '$1-$2');
        }
        input.value = value;
    };

    // Aplica as máscaras aos inputs
    const cpfInput = document.getElementById('CPF');
    if (cpfInput) {
        cpfInput.addEventListener('input', () => applyCpfMask(cpfInput));
    }

    const telephoneInput = document.getElementById('telephone');
    if (telephoneInput) {
        telephoneInput.addEventListener('input', () => applyTelephoneMask(telephoneInput));
    }
});

// Adiciona o listener para o envio do formulário
document.getElementById('registerEmployeeForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const employeeData = {
        name: document.getElementById('name').value,
        CPF: document.getElementById('CPF').value.replace(/\D/g, ''),
        email: document.getElementById('email').value,
        telephone: document.getElementById('telephone').value.replace(/\D/g, ''),
        password: document.getElementById('password').value,
        confirmPassword: document.getElementById('confirmPassword').value,
        typeEmployee: document.getElementById('typeEmployee').value
    };

    // Validação da senha
    if (employeeData.password !== employeeData.confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }

    if (!employeeData.typeEmployee) {
        alert("Por favor, selecione um cargo para o funcionário.");
        return;
    }

    // Envia para a API
    try {
        const response = await fetch('/employees/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employeeData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Falha no cadastro do funcionário.');
        }

        alert('Funcionário cadastrado com sucesso!');
        window.location.href = '/v1/home';

    } catch (error) {
        console.error('Erro no cadastro:', error);
        alert(error.message);
    }
});