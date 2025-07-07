document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("RegisterLivro_Form");
    const tituloInput = document.getElementById("titulo");
    const quantidadeInput = document.getElementById("quantExemplares");
    const btnMenos = document.getElementById("btnMenos");
    const btnMais = document.getElementById("btnMais");
    const disponibilidadeSelect = document.getElementById("disponibilidade");
    const indicadorDisp = document.getElementById("indicadorDisp");

    // --- LÓGICA PARA OS BOTÕES DE QUANTIDADE ---
    if (btnMenos && btnMais && quantidadeInput) {
        btnMenos.addEventListener('click', () => {
            let currentValue = parseInt(quantidadeInput.value) || 0;
            if (currentValue > 0) {
                quantidadeInput.value = currentValue - 1;
            }
        });

        btnMais.addEventListener('click', () => {
            let currentValue = parseInt(quantidadeInput.value) || 0;
            quantidadeInput.value = currentValue + 1;
        });
    }

    // --- LÓGICA PARA O INDICADOR DE DISPONIBILIDADE ---
    if(disponibilidadeSelect && indicadorDisp) {
        disponibilidadeSelect.addEventListener('change', function() {
            if (this.value === 'false') {
                indicadorDisp.classList.add('nao');
            } else {
                indicadorDisp.classList.remove('nao');
            }
        });
    }

    // --- LÓGICA PARA BUSCAR DADOS DO LIVRO (GOOGLE BOOKS API) ---
    tituloInput.addEventListener("blur", async () => {
        const titulo = tituloInput.value.trim();
        if (!titulo) return;

        try {
            const response = await fetch(`/google-books/titulo/${encodeURIComponent(titulo)}`);
            if (!response.ok) throw new Error("Livro não encontrado na API do Google.");
            const data = await response.json();

            // Preenche os campos do formulário
            document.getElementById("autor").value = data.authors ? data.authors.join(', ') : "";
            document.getElementById("isbn").value = data.industryIdentifiers ? data.industryIdentifiers.find(i => i.type === "ISBN_13")?.identifier || '' : '';
            document.getElementById("descricao").value = data.description || "";
            document.getElementById("editora").value = data.publisher || "";
            document.getElementById("dataPublicacao").value = formatarData(data.publishedDate);
            document.getElementById("categoria").value = data.categories ? data.categories.join(', ') : "";
            document.getElementById("thumbnailUrl").value = data.imageLinks?.thumbnail || "";

            if (data.imageLinks?.thumbnail) {
                mostrarImagem(data.imageLinks.thumbnail);
            } else {
                removerImagem();
            }
        } catch (err) {
            console.warn(err.message);
        }
    });

    // --- LÓGICA PARA O SUBMIT DO FORMULÁRIO ---
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const bookData = {
            title: document.getElementById('titulo').value,
            author: document.getElementById('autor').value,
            ISBN: document.getElementById('isbn').value,
            category: document.getElementById('categoria').value,
            year_publication: document.getElementById('dataPublicacao').value,
            publisher: document.getElementById('editora').value,
            description: document.getElementById('descricao').value,
            thumbnailUrl: document.getElementById('thumbnailUrl').value,
            total_quantity: parseInt(document.getElementById('quantExemplares').value, 10),
            disponibility_quantity: parseInt(document.getElementById('quantExemplares').value, 10),
            disponibility: document.getElementById('disponibilidade').value === 'true',
            collectionId: parseInt(document.getElementById('collectionId').value, 10),
            employeeId: parseInt(document.getElementById('employeeId').value, 10)
        };

        try {
            const response = await fetch(form.action, {
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(bookData)
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({ message: 'Erro desconhecido. Verifique os dados e tente novamente.' }));
                throw new Error(errorData.message);
            }

            alert('Livro cadastrado com sucesso!');
            window.location.href = '/books/reports';
        } catch (error) {
            mostrarAlerta('Falha no cadastro: ' + error.message);
        }
    });
});

// Funções auxiliares
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
    img.className = "image-preview";
    img.src = url;
    img.alt = "Capa do livro";
    imglivro.appendChild(img);
}

function removerImagem() {
    const imgPreview = document.querySelector(".image-preview");
    if (imgPreview) imgPreview.remove();
}

function mostrarAlerta(mensagem) {
    const alerta = document.getElementById("alertaErro");
    const msg = document.getElementById("mensagemErro");
    msg.textContent = mensagem;
    alerta.style.display = 'block';
}

function fecharAlerta() {
    document.getElementById('alertaErro').style.display = 'none';
}