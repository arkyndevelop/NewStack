document.addEventListener('DOMContentLoaded', function() {
    console.log("Página de relatório de empréstimos carregada.");

    // Botões de DEVOLUÇÃO
    const returnButtons = document.querySelectorAll('.return-loan-btn');
    returnButtons.forEach(button => {
        button.addEventListener('click', function() {
            const loanId = this.dataset.loanId;
            if (confirm('Tem certeza que deseja confirmar a devolução deste livro?')) {
                returnLoan(loanId);
            }
        });
    });

    // Botões de EXCLUSÃO
    const deleteButtons = document.querySelectorAll('.delete-loan-btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const loanId = this.dataset.loanId;
            if (confirm('Tem certeza que deseja excluir este empréstimo? Esta ação não pode ser desfeita.')) {
                deleteLoan(loanId, this);
            }
        });
    });
});

function returnLoan(loanId) {
    const url = `/v1/loans/return/${loanId}`;

    fetch(url, {
        method: 'POST',
        headers: {
            // Adicione headers de CSRF aqui se necessário no futuro
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Devolução confirmada com sucesso!');
                window.location.reload(); // Recarrega a página para atualizar o status
            } else {
                // Tenta ler uma mensagem de erro do backend
                response.text().then(text => {
                    alert('Falha ao confirmar a devolução: ' + text);
                });
            }
        })
        .catch(error => {
            console.error('Erro ao tentar confirmar devolução:', error);
            alert('Ocorreu um erro de comunicação. Verifique sua conexão e tente novamente.');
        });
}

function deleteLoan(loanId, buttonElement) {
    const url = `/loans/delete/${loanId}`;

    fetch(url, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                const row = buttonElement.closest('tr');
                row.remove();
                alert('Empréstimo excluído com sucesso!');
            } else {
                alert('Falha ao excluir o empréstimo. Tente novamente.');
            }
        })
        .catch(error => {
            console.error('Erro ao tentar excluir empréstimo:', error);
            alert('Ocorreu um erro de comunicação.');
        });
}