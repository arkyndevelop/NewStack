document.addEventListener('DOMContentLoaded', function() {
    // Configura o formulário de edição quando o modal é aberto
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', function() {
            const cpf = this.getAttribute('data-cpf');
            const form = document.getElementById('editForm');

            // Define a ação do formulário com o CPF correto
            form.action = `/v1/clients/update/${cpf}`;


            const name = row.children[1].textContent;
            const email = row.children[3].textContent;
            const telephone = row.children[4].textContent;

            // Preenche os campos do modal
            document.getElementById("editCpf").value = cpf;
            document.getElementById("editName").value = name;
            document.getElementById("editEmail").value = email;
            document.getElementById("editTelephone").value = telephone;

            // Atualiza o action do form dinamicamente
            document.getElementById("editForm").action = `/clientes/update/${cpf}`;
        });
    });
});
