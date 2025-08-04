
async function fetchData(url) {
    const token = localStorage.getItem('jwt_token');

    // Se não houver token, o usuário não está logado. Redireciona para a página de login.
    if (!token) {
        alert("Sua sessão expirou ou você não está logado. Por favor, faça o login novamente.");
        window.location.href = '/login'; // Ajuste para a sua página de login
        return; // Interrompe a execução
    }

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}` // Adiciona o token JWT ao cabeçalho
            }
        });

        // Se o token for inválido ou expirado, o servidor retornará 403 (Forbidden)
        if (response.status === 403) {
            logout(); // Desloga o usuário se a permissão for negada
            return;
        }

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Ocorreu um erro ao buscar os dados.');
        }

        return await response.json(); // Retorna os dados como JSON

    } catch (error) {
        console.error('Falha na requisição:', error);
        // Você pode adicionar um tratamento de erro mais visível para o usuário aqui
    }
}

/**
 * Função para remover o token e redirecionar o usuário para a página de login.
 */
function logout() {
    localStorage.removeItem('jwt_token');
    window.location.href = '/login'; // Ajuste para a sua página de login
}
// Função para criar o card de livro
function criarCardLivro(livro) {
    const card = document.createElement('div');
    card.className = 'col-md-4 mb-4'; // Usa classes do Bootstrap para layout

    // Verifica se a disponibilidade é true para definir a cor e o texto do badge
    const disponibilidadeBadge = livro.disponibility
        ? '<span class="badge bg-success">Disponível</span>'
        : '<span class="badge bg-danger">Indisponível</span>';

    card.innerHTML = `
        <div class="card h-100 shadow-sm">
            <div class="card-body">
                <h5 class="card-title">${livro.title}</h5>
                <h6 class="card-subtitle mb-2 text-muted">ISBN: ${livro.ISBN}</h6>
                <p class="card-text"><strong>Categoria:</strong> ${livro.category}</p>
                <p class="card-text"><strong>Status:</strong> ${disponibilidadeBadge}</p>
                 <a href="#" class="btn btn-primary">Ver Detalhes</a>
            </div>
        </div>
    `;
    return card;
}

/**
 * Função principal para carregar os livros da API e renderizá-los na tela.
 */
async function carregarLivros() {
    const container = document.getElementById('livros-container');
    if (!container) {
        console.error('Elemento com id "livros-container" não encontrado na página.');
        return;
    }

    // Usa a função fetchData para fazer a chamada autenticada
    const livros = await fetchData('/books/reports/all');

    // Limpa o conteúdo atual
    container.innerHTML = '';

    if (livros && livros.length > 0) {
        livros.forEach(livro => {
            const card = criarCardLivro(livro);
            container.appendChild(card);
        });
    } else {
        // Mensagem caso nenhum livro seja encontrado
        container.innerHTML = '<p class="text-center">Nenhum livro encontrado no acervo.</p>';
    }
}

// Event listener para garantir que o DOM esteja totalmente carregado antes de executar o script.
document.addEventListener('DOMContentLoaded', () => {
    carregarLivros(); // Chama a função para carregar os livros assim que a página carregar
});