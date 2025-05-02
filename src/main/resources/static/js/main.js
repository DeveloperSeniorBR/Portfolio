document.addEventListener('DOMContentLoaded', function() {
    console.log('Portfolio Manager iniciado!');
    
    // Exemplo de interatividade
    const container = document.querySelector('.container');
    if (container) {
        container.addEventListener('click', function() {
            console.log('Container clicado!');
        });
    }
}); 