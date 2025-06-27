function maskCPF(value) {
    value = value.replace(/\D/g, '');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    return value;
}

document.getElementById('registerEmployeeForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const rawCPF = document.getElementById('CPF').value;
    const cleanCPF = rawCPF.replace(/\D/g, '');

    const employeeData = {
        name: document.getElementById('name').value,
        CPF: cleanCPF,
        email: document.getElementById('email').value,
        telephone: document.getElementById('telephone').value.replace(/\D/g, ''),
        password: document.getElementById('password').value,
        confirmPassword: document.getElementById('confirmPassword').value,
        typeEmployee: document.getElementById('typeEmployee').value // Coleta o cargo
    };

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
        const token = document.querySelector("meta[name='_csrf']").getAttribute("content");
        const header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

        const response = await fetch('/employee/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [header]: token
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