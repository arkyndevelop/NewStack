document.addEventListener("DOMContentLoaded", () => {
    const tituloInput = document.getElementById("titulo");
    const form = document.getElementById("RegisterLivro_Form");

    tituloInput.addEventListener("blur", async () => {
        const titulo = tituloInput.value.trim();
        if (!titulo) return;

        try {
            const response = await fetch(`/google-books/titulo/${encodeURIComponent(titulo)}`);
            if (!response.ok) throw new Error("Livro não encontrado");

            const data = await response.json();

            document.getElementById("autor").value = data.authors?.[0] || "";
            document.getElementById("descricao").value = data.description || "";
            document.getElementById("editora").value = data.publisher || "";
            document.getElementById("dataPublicacao").value = formatarData(data.publishedDate);

            const categoriaOriginal = data.categories?.[0] || "";
            const categoriaTraduzida = categoriasTraduzidas[categoriaOriginal] || categoriaOriginal;
            document.getElementById("categoria").value = categoriaTraduzida;

            if (data.imageLinks?.thumbnail) {
                mostrarImagem(data.imageLinks.thumbnail);
                document.getElementById("imagemUrlGoogle").value = data.imageLinks.thumbnail;
            } else {
                removerImagem();
                document.getElementById("imagemUrlGoogle").value = "";
            }

        } catch (err) {
            mostrarErro("Livro não encontrado.");
        }
    });

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const formData = new FormData(form);

        try {
            const response = await fetch(form.action, {
                method: "POST",
                body: formData
            });

            if (!response.ok) throw new Error("Erro ao cadastrar livro");

            mostrarSucesso();
            form.reset();
            removerImagem();

        } catch (error) {
            mostrarErro(error.message);
        }
    });
});

function formatarData(dataStr) {
    if (!dataStr) return "";
    const partes = dataStr.split("-");
    if (partes.length === 3) return dataStr;
    if (partes.length === 2) return `${partes[0]}-${partes[1]}-01`;
    if (partes.length === 1) return `${partes[0]}-01-01`;
    return "";
}

function mostrarImagem(url) {
    removerImagem();
    const imglivro = document.getElementById("imglivro");
    const img = document.createElement("img");
    img.id = "previewImagem";
    img.src = url;
    img.alt = "Capa do livro";
    img.style.maxWidth = "200px";
    img.style.marginTop = "10px";
    imglivro.appendChild(img);
}

function removerImagem() {
    const imgPreview = document.getElementById("previewImagem");
    if (imgPreview) imgPreview.remove();
}

function mostrarErro(mensagem) {
    const alerta = document.getElementById("alertaErro");
    const msg = document.getElementById("mensagemErro");
    msg.textContent = mensagem;
    alerta.classList.remove("d-none");
}

function fecharAlerta() {
    const alerta = document.getElementById("alertaErro");
    alerta.classList.add("d-none");
}

function mostrarSucesso() {
    const alerta = document.getElementById("alertaSucesso");
    alerta.classList.remove("d-none");
}

function fecharAlertaSucesso() {
    const alerta = document.getElementById("alertaSucesso");
    alerta.classList.add("d-none");
}


const categoriasTraduzidas = {
    "Art": "Arte",
    "Biography & Autobiography": "Biografia e Autobiografia",
    "Body, Mind & Spirit": "Corpo, Mente e Espírito",
    "Business & Economics": "Negócios e Economia",
    "Computers": "Computação",
    "Cooking": "Culinária",
    "Crafts & Hobbies": "Artesanato e Passatempos",
    "Design": "Design",
    "Drama": "Drama",
    "Education": "Educação",
    "Family & Relationships": "Família e Relacionamentos",
    "Fiction": "Ficção",
    "Foreign Language Study": "Estudo de Línguas Estrangeiras",
    "Games & Activities": "Jogos e Atividades",
    "Gardening": "Jardinagem",
    "Health & Fitness": "Saúde e Boa Forma",
    "History": "História",
    "House & Home": "Casa e Lar",
    "Humor": "Humor",
    "Juvenile Fiction": "Ficção Juvenil",
    "Juvenile Nonfiction": "Não Ficção Juvenil",
    "Language Arts & Disciplines": "Linguística e Disciplinas Relacionadas",
    "Law": "Direito",
    "Literary Collections": "Coleções Literárias",
    "Literary Criticism": "Crítica Literária",
    "Mathematics": "Matemática",
    "Medical": "Medicina",
    "Music": "Música",
    "Nature": "Natureza",
    "Performing Arts": "Artes Cênicas",
    "Pets": "Animais de Estimação",
    "Philosophy": "Filosofia",
    "Photography": "Fotografia",
    "Poetry": "Poesia",
    "Political Science": "Ciência Política",
    "Psychology": "Psicologia",
    "Religion": "Religião",
    "Science": "Ciência",
    "Self-Help": "Autoajuda",
    "Social Science": "Ciências Sociais",
    "Sports & Recreation": "Esportes e Recreação",
    "Study Aids": "Guias de Estudo",
    "Technology & Engineering": "Tecnologia e Engenharia",
    "Transportation": "Transporte",
    "Travel": "Viagem",
    "True Crime": "Crimes Reais",
    "Young Adult Fiction": "Ficção para Jovens",
    "Young Adult Nonfiction": "Não Ficção para Jovens"
};