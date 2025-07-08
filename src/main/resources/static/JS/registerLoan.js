document.addEventListener('DOMContentLoaded', function() {
    const dateOptions = document.querySelector('.date-options');
    const expectedReturnDateInput = document.getElementById('expectedReturnDate');
    const form = document.getElementById('registerLoanForm');

    // Lógica para seleção da data de devolução
    dateOptions.addEventListener('click', function(event) {
        if (event.target.tagName === 'BUTTON') {
            const days = parseInt(event.target.dataset.days);
            const today = new Date();
            today.setDate(today.getDate() + days);

            // Formata para YYYY-MM-DDTHH:mm para compatibilidade com datetime-local (embora hidden)
            // Definimos um horário padrão para que seja uma string datetime válida
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0'); // Mês é 0-indexado
            const day = String(today.getDate()).padStart(2, '0');
            const hours = '23'; // Padrão para final do dia
            const minutes = '59'; // Padrão para final do dia

            const formattedDate = `${year}-${month}-${day}T${hours}:${minutes}`;
            expectedReturnDateInput.value = formattedDate;

            // Opcional: Indica visualmente o botão selecionado
            document.querySelectorAll('.date-options .btn').forEach(btn => {
                btn.classList.remove('active');
            });
            event.target.classList.add('active');
        }
    });

    // Adiciona validação client-side antes do envio do formulário
    form.addEventListener('submit', function(event) {
        if (!expectedReturnDateInput.value) {
            alert('Por favor, selecione uma data de devolução.');
            event.preventDefault(); // Previne o envio do formulário
            return; // Sai da função
        }

        // Você pode adicionar mais validações aqui para clientId e bookId, se necessário.
        // Exemplo:
        const clientId = document.getElementById('clientId').value;
        const bookId = document.getElementById('bookId').value;

        if (!clientId) {
            alert('Por favor, selecione um cliente.');
            event.preventDefault();
            return;
        }

        if (!bookId) {
            alert('Por favor, selecione um livro.');
            event.preventDefault();
            return;
        }

        // Se você estiver enviando o formulário via AJAX, a lógica de AJAX viria aqui.
        // Se for um submit tradicional, ele continuará normalmente após as validações.

        // Exemplo de como você obteria os dados para um AJAX:
        // const loanData = {
        //     clientId: clientId,
        //     bookId: bookId,
        //     expectedReturnDate: expectedReturnDateInput.value
        // };
        // console.log("Dados a serem enviados:", loanData);

        // Se for AJAX, você faria event.preventDefault() aqui e faria a chamada fetch/axios.
        // fetch('/api/loans', { /* ... */ });
    });
});