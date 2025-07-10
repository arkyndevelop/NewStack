document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerLivroForm');
    const titleInput = document.getElementById('title');
    const quantInput = document.getElementById('total_quantity');
    const btnMais = document.getElementById('btnMais');
    const btnMenos = document.getElementById('btnMenos');

    // Form submission handler
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else if (response.ok) {
                    window.location.href = '/v1/books/reports';
                }
                return response.text();
            })
            .catch(error => {
                console.error('Error:', error);
                const alertDiv = document.getElementById('alerta');
                alertDiv.textContent = 'Erro ao cadastrar livro';
                alertDiv.className = 'alert alert-danger';
                alertDiv.style.display = 'block';

                setTimeout(() => {
                    alertDiv.style.display = 'none';
                }, 5000);
            });
        });
    }

    // Google Books API integration
    if (titleInput) {
        titleInput.addEventListener('blur', preencherFormularioComDadosDoGoogle);
    }

    // Quantity buttons logic
    if (btnMais) {
        btnMais.addEventListener('click', () => {
            quantInput.value = parseInt(quantInput.value, 10) + 1;
        });
    }

    if (btnMenos) {
        btnMenos.addEventListener('click', () => {
            const currentValue = parseInt(quantInput.value, 10);
            if (currentValue > 1) {
                quantInput.value = currentValue - 1;
            }
        });
    }
});

// CATEGORY TRANSLATION DICTIONARY
const traducaoCategorias = {
    "Fiction": "Ficção",
    "Science": "Ciência",
    "Science Fiction": "Ficção Científica",
    "Biography": "Biografia",
    "History": "História",
    "Computers": "Computação",
    "Technology": "Tecnologia",
    "Art": "Arte",
    "Business": "Negócios",
    "Education": "Educação",
    "Health & Fitness": "Saúde e Boa Forma",
    "Self-Help": "Autoajuda",
    "Poetry": "Poesia",
    "Drama": "Drama",
    "Comics & Graphic Novels": "Quadrinhos e Novelas Gráficas",
    "Philosophy": "Filosofia",
    "Religion": "Religião",
    "Travel": "Viagem",
    "Sports & Recreation": "Esportes e Recreação",
    "Juvenile Fiction": "Ficção Juvenil",
    "Young Adult": "Jovem Adulto",
    "Music": "Música",
    "Cooking": "Culinária",
    "Law": "Direito",
    "Psychology": "Psicologia",
    "Nature": "Natureza",
    "Mathematics": "Matemática",
    "Medical": "Medicina",
    "Political Science": "Ciência Política",
    "Literary Criticism": "Crítica Literária",
    "Humor": "Humor",
    "House & Home": "Casa e Lar",
    "True Crime": "Crime Real",
    "Games": "Jogos",
    "Performing Arts": "Artes Cênicas",
    "Social Science": "Ciências Sociais",
    "Transportation": "Transporte",
    "Pets": "Animais de Estimação",
    "Parenting": "Parentalidade",
    "Antiques & Collectibles": "Antiguidades e Colecionáveis",
    "Foreign Language Study": "Estudo de Línguas Estrangeiras",
    "Language Arts & Disciplines": "Linguística e Comunicação",
    "Crafts & Hobbies": "Artesanato e Passatempos",
    "Architecture": "Arquitetura",
    "Design": "Design",
    "Photography": "Fotografia",
    "Reference": "Referência",
    "Body, Mind & Spirit": "Corpo, Mente e Espírito",
    "Economics": "Economia",
    "Fantasy": "Fantasia",
    "Thrillers": "Suspense",
    "Mystery": "Mistério",
    "Horror": "Terror",
    "Adventure": "Aventura",
    "Relationships": "Relacionamentos",
    "Erotica": "Erótica",
    "Western": "Faroeste"
};

async function preencherFormularioComDadosDoGoogle() {
    const titulo = document.getElementById('title').value;
    if (titulo.length < 3) {
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
            document.getElementById('author').value = data.authors ? data.authors.join(', ') : '';
            document.getElementById('publisher').value = data.publisher || '';
            document.getElementById('description').value = data.description || '';

            // Category translation
            if (data.categories && data.categories.length > 0) {
                const categoriaOriginal = data.categories[0];
                const categoriaTraduzida = traducaoCategorias[categoriaOriginal] || categoriaOriginal;
                document.getElementById('category').value = categoriaTraduzida;
            }

            // Fill ISBN
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

            // Fill publication year
            if (data.publishedDate) {
                document.getElementById('year_publication').value = data.publishedDate.substring(0, 4);
            }

            // Image
            const thumbnailUrl = data.imageLinks ? data.imageLinks.thumbnail : '';
            document.getElementById('thumbnailUrl').value = thumbnailUrl;
            const imgContainer = document.getElementById('imglivro');
            imgContainer.innerHTML = thumbnailUrl
                ? `<img src="${thumbnailUrl}" alt="Capa do livro" style="max-width: 150px; border-radius: 5px;">`
                : '<span>Nenhuma imagem disponível</span>';

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

    setTimeout(() => {
        alertaDiv.style.display = 'none';
    }, 5000);
}