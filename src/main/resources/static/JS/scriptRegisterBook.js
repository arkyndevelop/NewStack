document.addEventListener('DOMContentLoaded', function () {
    const titleInput = document.getElementById('title');
    if (titleInput) {
        // Evento 'blur' é acionado quando o usuário sai do campo
        titleInput.addEventListener('blur', preencherFormularioComDadosDoGoogle);
    }

    // Lógica para os botões de quantidade
    const quantInput = document.getElementById('total_quantity');
    const btnMais = document.getElementById('btnMais');
    const btnMenos = document.getElementById('btnMenos');

    if (btnMais) {
        btnMais.addEventListener('click', () => {
            quantInput.value = parseInt(quantInput.value, 10) + 1;
        });
    }

    if (btnMenos) {
        btnMenos.addEventListener('click', () => {
            const currentValue = parseInt(quantInput.value, 10);
            if (currentValue > 1) { // Evita quantidade menor que 1
                quantInput.value = currentValue - 1;
            }
        });
    }
});

async function preencherFormularioComDadosDoGoogle() {
    const titulo = document.getElementById('title').value;
    if (titulo.length < 3) {
        // Não busca se o título for muito curto
        return;
    }

    try {
        const response = await fetch(`/google-books/titulo/${encodeURIComponent(titulo)}`);
        if (!response.ok) {
            const errorText = await response.text();
            mostrarAlerta(`Erro ao buscar livro: ${errorText || response.statusText}`);
            return;
        }

        const data = await response.json();
        if (data) {
            // Preenche os campos do formulário com os dados da API
            document.getElementById('author').value = data.authors ? data.authors.join(', ') : '';
            document.getElementById('publisher').value = data.publisher || '';
            document.getElementById('description').value = data.description || '';
            document.getElementById('category').value = data.categories ? data.categories[0] : '';

            // Lógica robusta para encontrar ISBN
            let isbn = '';
            if (data.industryIdentifiers && Array.isArray(data.industryIdentifiers)) {
                const isbn13 = data.industryIdentifiers.find(i => i.type === "ISBN_13");
                const isbn10 = data.industryIdentifiers.find(i => i.type === "ISBN_10");
                if (isbn13) {
                    isbn = isbn13.identifier;
                } else if (isbn10) {
                    isbn = isbn10.identifier;
                }
            }
            document.getElementById('isbn').value = isbn;

            // Formata e preenche o ano de publicação
            if (data.publishedDate) {
                // Extrai apenas o ano (os 4 primeiros caracteres)
                document.getElementById('year_publication').value = data.publishedDate.substring(0, 4);
            }

            // Preenche a imagem e o campo oculto com a URL da thumbnail
            const thumbnailUrl = data.imageLinks ? data.imageLinks.thumbnail : '';
            document.getElementById('thumbnailUrl').value = thumbnailUrl;
            const imgContainer = document.getElementById('imglivro');
            imgContainer.innerHTML = thumbnailUrl ? `<img src="${thumbnailUrl}" alt="Capa do livro" style="max-width: 150px; border-radius: 5px;">` : '<span>Nenhuma imagem disponível</span>';

        } else {
            mostrarAlerta('Nenhum livro encontrado com este título.');
        }

    } catch (error) {
        console.error('Falha na requisição:', error);
        mostrarAlerta('Não foi possível conectar à API do Google Books.');
    }
}

function mostrarAlerta(mensagem) {
    const alertaDiv = document.getElementById('alertaErro');
    alertaDiv.textContent = mensagem;
    alertaDiv.style.display = 'block';

    // Esconde o alerta após 5 segundos
    setTimeout(() => {
        alertaDiv.style.display = 'none';
    }, 5000);
}