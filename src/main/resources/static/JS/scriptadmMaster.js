document.getElementById('RegisterLivro_Form').addEventListener('submit', async function(e) {
    e.preventDefault(); // Impede o envio padrão do formulário.

    // Coleta os dados do formulário a partir dos campos de entrada.
    const disponibilidadeValue = document.getElementById('disponibilidade').value;
    const bookData = {
        titulo: document.getElementById('titulo').value,
        autor: document.getElementById('autor').value,
        categoria: document.getElementById('categoria').value,
        dataPublicacao: document.getElementById('dataPublicacao').value,
        quantExemplares: document.getElementById('quantExemplares').value,
        dataCadastro: document.getElementById('dataCadastro').value,
        /*imgLivro: document.getElementById('imgLivro').value,*/
        disponibilidade: disponibilidadeValue === 'Sim', // Converte 'sim' para true e qualquer outra coisa para false
    };


    try {
        // Envia os dados do livro para a API usando o método POST.
        const response = await fetch('http://localhost:8080/livros', { // add a url da api
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Indica que os dados estão em formato JSON.
            },
            body: JSON.stringify(bookData) // Converte os dados do objeto em uma string JSON.
        });

        // Verifica se a resposta da API foi bem-sucedida.
        if (!response.ok) {
            const error = await response.text(); // Obtém a mensagem de erro da resposta.
            throw new Error(error); // Lança um erro com a mensagem da API.
        }

        // Exibe uma mensagem de sucesso e redireciona o usuário.
        alert('Livro cadastrado com sucesso!');
        limpaFormular();
        window.location.href = 'admMaster.html'; // Redireciona para a página admMaster.html.
    } catch (error) {
        // Exibe o alerta de erro
        document.getElementById('mensagemErro').textContent = error.message;
        document.getElementById('alertaErro').style.display = 'block';
    }
});

function limpaFormular() {
    document.getElementById('titulo').value = '';
    document.getElementById('autor').value = '';
    document.getElementById('categoria').value = '';
    document.getElementById('dataPublicacao').value = '';
    document.getElementById('quantExemplares').value = '';
    document.getElementById('dataCadastro').value = '';
    document.getElementById('disponibilidade').value = 'Sim';
}

// Função para fechar o alerta de erro
function fecharAlerta() {
    document.getElementById('alertaErro').style.display = 'none';
}