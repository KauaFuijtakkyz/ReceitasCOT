// URL base da sua API Spring Boot
const API_URL = 'http://localhost:8080';

// Executa assim que a página carrega para trazer os ingredientes do banco
document.addEventListener('DOMContentLoaded', () => {
    carregarIngredientes();
});

// Função para buscar os ingredientes salvos no banco de dados
async function carregarIngredientes() {
    try {
        // Altere '/api/ingredientes' para o endpoint real do seu FoodItemController
        const response = await fetch(`${API_URL}/api/ingredientes`);
        if (!response.ok) throw new Error('Erro ao buscar ingredientes.');
        
        const ingredientes = await response.json();
        const listaContainer = document.getElementById('listaIngredientes');
        listaContainer.innerHTML = '';

        ingredientes.forEach(ing => {
            const item = document.createElement('div');
            item.className = 'ingrediente-item';
            item.innerHTML = `
                <strong>${ing.name || ing.nome}</strong> - ${ing.quantity || ing.quantidade} 
                <small>(${ing.category || ing.categoria})</small>
            `;
            listaContainer.appendChild(item);
        });
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para adicionar um ingrediente via formulário
async function adicionarIngrediente() {
    const nome = document.getElementById('nomeIngrediente').value;
    const categoria = document.getElementById('categoriaIngrediente').value;
    const quantidade = document.getElementById('quantidadeIngrediente').value;
    const validade = document.getElementById('validadeIngrediente').value;

    if (!nome || !categoria || !quantidade || !validade) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const novoIngrediente = {
        nome: nome,          // Ajuste as chaves conforme os atributos da sua FoodItemModel
        categoria: categoria,
        quantidade: parseInt(quantidade),
        validade: validade
    };

    try {
        // Altere '/api/ingredientes' para o endpoint de cadastro do seu FoodItemController
        const response = await fetch(`${API_URL}/api/ingredientes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novoIngrediente)
        });

        if (response.ok) {
            // Limpa o formulário e atualiza a lista lateral
            document.getElementById('nomeIngrediente').value = '';
            document.getElementById('categoriaIngrediente').value = '';
            document.getElementById('quantidadeIngrediente').value = '';
            document.getElementById('validadeIngrediente').value = '';
            carregarIngredientes();
        } else {
            alert('Falha ao adicionar ingrediente.');
        }
    } catch (error) {
        console.error('Erro ao conectar com a API:', error);
    }
}

// FUNÇÃO CONECTADA DIRETAMENTE AO SEU RecipeController
async function gerarReceita() {
    const display = document.getElementById('receitaDisplay');
    display.innerHTML = '<p class="carregando">🍳 A IA está criando sua receita, aguarde...</p>';

    try {
        // Faz a chamada exata para o GetMapping da sua RecipeController
        const response = await fetch(`${API_URL}/generate`);

        if (response.status === 204) {
            display.innerHTML = '<p>Nenhum ingrediente encontrado no banco para gerar a receita.</p>';
            return;
        }

        if (!response.ok) throw new Error('Erro na comunicação com o GroqService.');

        // Como o seu controller retorna ResponseEntity<String>, pegamos como texto plano
        const receitaTexto = await response.text();
        
        // Formata quebras de linha básicas para exibição amigável no HTML
        display.innerHTML = `<div class="receita-texto">${receitaTexto.replace(/\n/g, '<br>')}</div>`;

    } catch (error) {
        console.error('Erro ao gerar receita:', error);
        display.innerHTML = '<p style="color: red;">Ocorreu um erro ao gerar a receita. Tente novamente.</p>';
    }
}