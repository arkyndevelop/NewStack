document.addEventListener('DOMContentLoaded', function() {
    const editBookModal = new bootstrap.Modal(document.getElementById('editBookModal'));
    const editBookForm = document.getElementById('editBookForm');

    // Lógica para os botões de DELETAR
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function() {
            const bookId = this.getAttribute('data-book-id');
            if (confirm(`Tem certeza que deseja excluir o livro com ID ${bookId}?`)) {
                fetch(`/books/delete/${bookId}`, {
                    method: 'DELETE',
                })
                    .then(response => {
                        if (response.ok) {
                            alert('Livro excluído com sucesso!');
                            window.location.reload(); // Recarrega a página
                        } else {
                            alert('Falha ao excluir o livro.');
                        }
                    })
                    .catch(error => console.error('Erro:', error));
            }
        });
    });

    // Lógica para os botões de EDITAR
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function() {
            // Pega os dados do livro da linha da tabela ou de atributos data-*
            const bookId = this.getAttribute('data-book-id');
            const bookISBN = this.getAttribute('data-book-isbn');
            const row = this.closest('tr');
            const title = row.cells[1].innerText;
            const totalQuantity = row.cells[5].innerText;


            // Preenche o formulário do modal
            editBookForm.querySelector('#editBookId').value = bookId;
            editBookForm.querySelector('#editTitle').value = title;
            editBookForm.querySelector('#editISBN').value = bookISBN;
            editBookForm.querySelector('#editTotalQuantity').value = totalQuantity;

            // Define a ação do formulário dinamicamente
            editBookForm.action = `/books/update/${bookISBN}`;
        });
    });

    // Lógica para o SUBMIT do formulário de edição
    editBookForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(this);
        const bookData = Object.fromEntries(formData.entries());

        fetch(this.action, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
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
});