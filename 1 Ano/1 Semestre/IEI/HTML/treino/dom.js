// Arquivo dom.js

function calcularResultado() {
    // Obtém os elementos de input com os IDs "op1", "op2" e "res"
    var op1Input = document.getElementById("op1");
    var op2Input = document.getElementById("op2");
    var resultadoInput = document.getElementById("res");

    // Obtém os valores dos campos "op1" e "op2" e converte para números
    var op1 = parseFloat(op1Input.value);
    var op2 = parseFloat(op2Input.value);

    // Verifica se os valores são números válidos
    if (!isNaN(op1) && !isNaN(op2)) {
        // Realiza a adição
        var resultado = op1 + op2;

        // Define o resultado no campo "res"
        resultadoInput.value = resultado;
    } else {
        // Exibe uma mensagem de erro se os valores não são válidos
        alert("Insira valores numéricos válidos para op1 e op2.");
    }
}
