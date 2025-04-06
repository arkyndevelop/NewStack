document.getElementById('registerForm').addEventListener('submit', async function(e) {
    e.preventDefault(); // Impede o envio tradicional do formulário

    const userData = {
        name: document.querySelector('.name').value,
        cpf: document.querySelector('.cpf').value,
        email: document.querySelector('.email').value,
        telephone: document.querySelector('.telephone').value,
        password: document.querySelector('.password').value,
        confirmPassword: document.querySelector('.confirmPassword').value
    };

    try {
        const response = await fetch('/crud/register/new', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const error = await response.text();
            throw new Error(error);
        }

        if (!userData.cpf) {
            alert("CPF inválido!");
            return;
        }

        alert('Cadastro realizado com sucesso!');
        window.location.href = '/home/login'; // Redireciona após o sucesso
    } catch (error) {
        alert(error.message);
    }
});