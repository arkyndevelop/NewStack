document.addEventListener('DOMContentLoaded', () => {
    // Elementos importantes para a ordenação
    const container = document.querySelector('.row');
    const sortOptions = document.querySelectorAll('.sort-option');
    const searchInput = document.getElementById('searchInput');
    let currentSort = 'default';

    // Armazena a ordem original dos cards
    let originalOrder = [];
    if (container) {
        originalOrder = Array.from(container.children);
    }

    // Função principal de ordenação
    function sortCards(sortType) {
        if (!container) return;

        // Seleciona todos os cards (incluindo os ocultos)
        const cards = Array.from(container.children);

        switch(sortType) {
            case 'title-asc':
                cards.sort((a, b) => {
                    const titleA = a.querySelector('.card-title').textContent.trim().toLowerCase();
                    const titleB = b.querySelector('.card-title').textContent.trim().toLowerCase();
                    return titleA.localeCompare(titleB);
                });
                break;

            case 'title-desc':
                cards.sort((a, b) => {
                    const titleA = a.querySelector('.card-title').textContent.trim().toLowerCase();
                    const titleB = b.querySelector('.card-title').textContent.trim().toLowerCase();
                    return titleB.localeCompare(titleA);
                });
                break;

            default: // 'default'
                // Restaura a ordem original usando a referência armazenada
                cards.sort((a, b) => {
                    return originalOrder.indexOf(a) - originalOrder.indexOf(b);
                });
        }

        // Reorganiza os cards no container (mantendo todos)
        container.innerHTML = '';
        cards.forEach(card => container.appendChild(card));
    }

    // Event listeners para as opções de ordenação
    sortOptions.forEach(option => {
        option.addEventListener('click', function(e) {
            e.preventDefault();
            currentSort = this.getAttribute('data-sort');
            sortCards(currentSort);
            updateSortButton(this.textContent);
        });
    });

    // Atualiza o texto do botão de ordenação
    function updateSortButton(text) {
        const sortButton = document.querySelector('.sort-dropdown .dropdown-toggle');
        if (sortButton) {
            sortButton.innerHTML = text;
        }
    }

    // Filtro de pesquisa corrigido
    if (searchInput) {
        searchInput.addEventListener('keyup', function() {
            const filter = searchInput.value.toLowerCase();
            const cards = container.querySelectorAll('.col-lg-3.col-md-4.col-sm-6.mb-4');

            cards.forEach(card => {
                const title = card.querySelector('.card-title').textContent.toLowerCase();
                card.style.display = title.includes(filter) ? '' : 'none';
            });
        });
    }

    // Comportamento dos cards e dropdowns
    document.querySelectorAll('.btn-group, .dropdown-toggle, .dropdown-menu, .dropdown-item').forEach(element => {
        element.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    });

    document.querySelectorAll('.card').forEach(card => {
        card.addEventListener('click', function(e) {
            if (e.target.closest('.btn-group')) return;
            const link = card.querySelector('a.stretched-link');
            if (link) window.location.href = link.href;
        });
    });
});