document.addEventListener('DOMContentLoaded', () => {
    const categoriasContainer = document.getElementById('categorias-container');
    const navbarLinks = document.querySelector('.navbar-links');

    const categoriasMap = {
        "todos": "Todas as Categorias",
        "lancamentos": "Lançamentos",
        "acao": "Ação",
        "aventura": "Aventura",
        "comedia": "Comédia",
        "drama": "Drama",
        "fantasia": "Fantasia",
        "romance": "Romance",
        "suspense": "Suspense",
        "terror": "Terror",
    };

    // Define a ordem e quais categorias (incluindo 'lancamentos') terão suas próprias seções
    const categoriasParaExibirEmTodos = [
        "lancamentos",
        "acao",
        "aventura",
        "comedia",
        "drama",
        "fantasia",
        "romance",
        "suspense",
        "terror"
    ];

    async function getLivrosFromAPI() {
        try {
            const response = await fetch('http://localhost:8080/livros');
            if (!response.ok) {
                throw new Error(`Erro ao carregar livros: ${response.statusText}`);
            }
            const livros = await response.json();
            return livros;
        } catch (error) {
            console.error('Falha ao buscar livros da API:', error);
            return [];
        }
    }

    function criarLivroCard(livro) {
        const livroCard = document.createElement('div');
        livroCard.classList.add('livro-card');
        livroCard.innerHTML = `
            <img src="${livro.imgLivro}" alt="Capa de ${livro.titulo}">
            <h3>${livro.titulo}</h3>
            <p>${livro.autor}</p>
        `;
        return livroCard;
    }

    async function renderizarCategoria(categoryKey) {
        categoriasContainer.innerHTML = ''; // Limpa o conteúdo
        const allBooks = await getLivrosFromAPI();
        const categoryDisplayName = categoriasMap[categoryKey] || "Livros";

        const sectionCategoria = document.createElement('section');
        sectionCategoria.classList.add('categoria');
        sectionCategoria.innerHTML = `<h2>${categoryDisplayName}</h2><div class="livros-container"></div>`;
        const livrosContainer = sectionCategoria.querySelector('.livros-container');

        let filteredBooks = [];
        if (categoryKey === "todos") {
            filteredBooks = allBooks;
        } else if (categoryKey === "lancamentos") {
            // Adapte esta lógica conforme a sua API marca os lançamentos
            filteredBooks = allBooks.filter(livro => livro.lancamento === true);
        } else {
            filteredBooks = allBooks.filter(livro =>
                livro.categoria && livro.categoria.toLowerCase() === categoryKey
            );
        }

        if (filteredBooks.length > 0) {
            filteredBooks.forEach(livro => {
                livrosContainer.appendChild(criarLivroCard(livro));
            });
        } else {
            const p = document.createElement('p');
            p.textContent = `Nenhum livro de ${categoryDisplayName} encontrado.`;
            livrosContainer.appendChild(p);
        }
        categoriasContainer.appendChild(sectionCategoria);
    }

    async function renderizarTodasCategorias() {
        categoriasContainer.innerHTML = '';
        const allBooks = await getLivrosFromAPI();

        for (const categoryKey of categoriasParaExibirEmTodos) {
            const categoriaNomeOriginal = categoriasMap[categoryKey];

            const sectionCategoria = document.createElement('section');
            sectionCategoria.classList.add('categoria');
            sectionCategoria.innerHTML = `<h2>${categoriaNomeOriginal}</h2><div class="livros-container"></div>`;
            const livrosContainer = sectionCategoria.querySelector('.livros-container');

            let livrosDaCategoria = [];
            if (categoryKey === "lancamentos") {
                livrosDaCategoria = allBooks.filter(livro => livro.lancamento === true);
            } else {
                livrosDaCategoria = allBooks.filter(livro =>
                    livro.categoria && livro.categoria.toLowerCase() === categoryKey
                );
            }

            if (livrosDaCategoria.length > 0) {
                livrosDaCategoria.forEach(livro => {
                    livrosContainer.appendChild(criarLivroCard(livro));
                });
            } else {
                const p = document.createElement('p');
                p.textContent = `Nenhum livro de ${categoriaNomeOriginal} adicionado ainda.`;
                livrosContainer.appendChild(p);
            }
            categoriasContainer.appendChild(sectionCategoria);
        }
    }

    navbarLinks.addEventListener('click', (event) => {
        const target = event.target;

        if (target.tagName === 'A') { // Garante que é um link clicado
            event.preventDefault(); // Impede o comportamento padrão do link

            if (target.textContent === 'Início' || target.textContent === 'Todas as Categorias') {
                renderizarTodasCategorias();
            } else if (target.textContent === 'Lançamentos') {
                renderizarCategoria('lancamentos');
            } else {
                // Lida com as categorias individuais (Ação, Aventura, etc.)
                for (const key in categoriasMap) {
                    if (categoriasMap[key] === target.textContent && key !== 'todos' && key !== 'lancamentos') {
                        renderizarCategoria(key);
                        break;
                    }
                }
            }
        }
    });

    // Carregamento inicial: Renderiza todas as categorias ao carregar a página
    renderizarTodasCategorias();
});