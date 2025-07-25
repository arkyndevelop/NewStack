document.addEventListener('DOMContentLoaded', () => {
    // Impede que cliques no dropdown propaguem para o card
    document.querySelectorAll('.btn-group, .dropdown-toggle, .dropdown-menu, .dropdown-item').forEach(element => {
        element.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    });

    // Clique no card leva para detalhes, exceto se for no dropdown
    document.querySelectorAll('.card').forEach(card => {
        card.addEventListener('click', function(e) {
            if (e.target.closest('.btn-group')) {
                return;
            }
            const link = card.querySelector('a.stretched-link');
            if (link) {
                window.location.href = link.href;
            }
        });
    });

    // Filtro por título (livros ou qualquer outra lista)
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keyup', function() {
            const filter = searchInput.value.toLowerCase();
            const cards = document.querySelectorAll('.card');
            cards.forEach(card => {
                const title = card.querySelector('.card-title').textContent.toLowerCase();
                if (title.includes(filter)) {
                    card.parentElement.style.display = ""; // mostra o card
                } else {
                    card.parentElement.style.display = "none"; // esconde o card
                }
            });
        });
    }
});
