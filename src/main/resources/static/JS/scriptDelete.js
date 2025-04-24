
document.addEventListener('DOMContentLoaded', function() {
    // Configura todos os botões de exclusão
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const cpf = this.getAttribute('data-cpf');

            if (confirm('Tem certeza que quer excluir?')) {
                document.getElementById(`form-delete-${cpf}`).submit();
            }
        });
    });
});