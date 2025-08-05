// Gráfico de Livros Mais Emprestados (%)
fetch("/loans/report-quantidade")
    .then(res => res.json())
    .then(data => {
        const labels = Object.keys(data);
        const valores = Object.values(data);
        const cores = labels.map((_, i) => `hsl(${(i * 45) % 360}, 65%, 55%)`);

        new Chart(document.getElementById("graficoLivros"), {
            type: "pie",
            data: {
                labels: labels,
                datasets: [{
                    label: 'Empréstimos',
                    data: valores,
                    backgroundColor: cores,
                    borderColor: '#343a40',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'right',
                        labels: {
                            color: '#f8f9fa',
                            boxWidth: 20,
                            padding: 20
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                let label = context.label || '';
                                if (label) label += ': ';
                                if (context.parsed !== null) label += context.parsed.toFixed(2) + '%';
                                return label;
                            }
                        }
                    }
                }
            }
        });
    })
    .catch(console.error);

// Gráfico de Empréstimos por Mês
fetch("/loans/report-by-month")
    .then(res => res.json())
    .then(data => {
        const labels = Object.keys(data);
        const valores = Object.values(data);

        new Chart(document.getElementById("graficoEmprestimosMes").getContext("2d"), {
            type: "line",
            data: {
                labels: labels,
                datasets: [{
                    label: "Empréstimos Mensais",
                    data: valores,
                    fill: false,
                    borderColor: "#0d6efd",
                    tension: 0.3
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        labels: { color: '#f8f9fa' }
                    }
                },
                scales: {
                    x: { ticks: { color: '#f8f9fa' } },
                    y: { beginAtZero: true, ticks: { color: '#f8f9fa' } }
                }
            }
        });
    })
    .catch(console.error);

// Gráfico de Clientes por Mês
fetch("/clients/reports-clients-per-month")
    .then(res => res.json())
    .then(data => {
        const labels = Object.keys(data);
        const valores = Object.values(data);

        new Chart(document.getElementById("graficoClientesMes").getContext("2d"), {
            type: "bar",
            data: {
                labels: labels,
                datasets: [{
                    label: "Clientes Cadastrados",
                    data: valores,
                    backgroundColor: 'rgba(13, 110, 253, 0.5)',
                    borderColor: 'rgba(13, 110, 253, 1)',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { labels: { color: '#f8f9fa' } },
                    title: {
                        display: true,
                        text: 'Novos Clientes por Mês',
                        color: '#f8f9fa',
                        font: { size: 18 }
                    }
                },
                scales: {
                    x: { ticks: { color: '#f8f9fa' } },
                    y: { beginAtZero: true, ticks: { color: '#f8f9fa' } }
                }
            }
        });
    })
    .catch(console.error);
