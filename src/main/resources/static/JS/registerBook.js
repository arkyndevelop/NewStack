document.getElementById('RegisterLivro_Form').addEventListener('submit', async function(e) {
    e.preventDefault();

    // Coleta os dados do formulário como objeto JSON
    const bookData = {
        title: document.getElementById('title').value,
        category: document.getElementById('category').value,
        year_publication: document.getElementById('year_publication').value,
        disponibility: document.getElementById('disponibility').value,
        total_quantitys: document.getElementById('total_quantitys').value
    };

    try {
        const response = await fetch('/registerBook/new', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookData)
        });

        if (!response.ok) {
            const error = await response.text();
            throw new Error(error);
        }

        alert('Livro cadastrado com sucesso!');
        // window.location.href = '/book'; // Redireciona se necessário
    } catch (error) {
        document.getElementById('mensagemErro').textContent = error.message;
        document.getElementById('alertaErro').style.display = 'block';
    }
});