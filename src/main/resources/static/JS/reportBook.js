document.addEventListener('DOMContentLoaded', function() {
    const editBookModal = new bootstrap.Modal(document.getElementById('editBookModal'));
    const editBookForm = document.getElementById('editBookForm');

    // Botões de DELETAR
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function(event) {
            event.stopPropagation(); // evita que clique no card abra a página
            const bookId = this.getAttribute('data-book-id');
            if (confirm(`Tem certeza que deseja excluir o livro com ID ${bookId}?`)) {
                fetch(`/books/delete/${bookId}`, {
                    method: 'DELETE',
                })
                    .then(response => {
                        if (response.ok) {
                            alert('Livro excluído com sucesso!');
                            window.location.reload();
                        } else {
                            alert('Falha ao excluir o livro.');
                        }
                    })
                    .catch(error => console.error('Erro:', error));
            }
        });
    });

    // Botões de EDITAR
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function(event) {
            event.stopPropagation(); // evita abrir detalhes do livro ao clicar no botão
            const bookId = this.getAttribute('data-book-id');

            // Exemplo: preenche o formulário do modal (ajuste conforme seu formulário)
            if (editBookForm) {
                editBookForm.querySelector('#editBookId').value = bookId;
                // Preencha outros campos conforme necessário
            }
            editBookModal.show();
        });
    });

    // Submit do formulário de edição
    if(editBookForm) {
        editBookForm.addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = new FormData(this);
            const bookData = Object.fromEntries(formData.entries());

            fetch(this.action, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(bookData)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Livro atualizado com sucesso!');
                        editBookModal.hide();
                        window.location.reload();
                    } else {
                        alert('Falha ao atualizar o livro.');
                    }
                })
                .catch(error => console.error('Erro:', error));
        });
    }
});