document.addEventListener('DOMContentLoaded', function() {
    console.log("Página de relatório de empréstimos carregada.");

    // Seleciona TODOS os botões de exclusão que têm a classe 'delete-loan-btn'
    const deleteButtons = document.querySelectorAll('.delete-loan-btn');

    // Adiciona um "ouvinte" de clique para cada botão encontrado
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Pega o ID do empréstimo guardado no atributo 'data-loan-id' do botão
            const loanId = this.dataset.loanId;

            // Pede confirmação ao usuário para evitar exclusões acidentais
            if (confirm('Tem certeza que deseja excluir este empréstimo? Esta ação não pode ser desfeita.')) {
                // Se o usuário confirmar, chama a função para deletar
                deleteLoan(loanId, this);
            }
        });
    });
});

function deleteLoan(loanId, buttonElement) {
    // Monta a URL da API para o endpoint de exclusão
    const url = `/loans/delete/${loanId}`; // Certifique-se que o caminho está correto!

    // Usa a API Fetch para enviar uma requisição do tipo DELETE
    fetch(url, {
        method: 'DELETE',
        headers: {
            // Se você usa Spring Security, pode precisar enviar um token CSRF
            // 'X-CSRF-TOKEN': 'seu-token-aqui'
        }
    })
        .then(response => {
            // Verifica se a resposta da API foi bem-sucedida (status 2xx)
            if (response.ok) {
                // Se deu certo, remove a linha (<tr>) da tabela que contém o botão clicado
                // Isso dá um feedback visual imediato ao usuário.
                const row = buttonElement.closest('tr');
                row.remove();
                alert('Empréstimo excluído com sucesso!');
            } else {
                // Se a API retornou um erro, exibe uma mensagem de falha
                alert('Falha ao excluir o empréstimo. Tente novamente.');
                console.error('Erro na resposta da API:', response.statusText);
            }
        })
        .catch(error => {
            // Se houve um erro de rede ou na execução do fetch
            console.error('Erro ao tentar excluir empréstimo:', error);
            alert('Ocorreu um erro de comunicação. Verifique sua conexão e tente novamente.');
        });
}