package challenge;

public class CriptografiaCesariana implements Criptografia {

    private final String NON_LETTERS = "[^a-z]";
    private final int POSICAO = 3;

    private boolean isCaracterValido(final Character character) {
        return character.toString().matches(NON_LETTERS);
    }

    @Override
    public String criptografar(String texto) {
        return executarCriptografia(texto, false);
    }

    private boolean isTextoVazio(String texto) {
        return texto.trim().isEmpty();
    }

    @Override
    public String descriptografar(String texto) {
        return executarCriptografia(texto, true);
    }

    private String executarCriptografia(String texto, boolean isDescriptografar) {
        if (isTextoVazio(texto)) {
            throw new IllegalArgumentException();
        }
        texto = texto.toLowerCase();
        StringBuilder resultado = new StringBuilder();
        for (Character character : texto.toCharArray()) {
            if (!isCaracterValido(character)) {
                int posicaoOriginal = character - 'a';
                int novaPosicao = (isDescriptografar ? (posicaoOriginal - POSICAO) : (posicaoOriginal + POSICAO)) % 26;
                char novoCaracter = (char) ('a' + novaPosicao);
                resultado.append(novoCaracter);
            } else {
                resultado.append(character);
            }
        }
        return resultado.toString();
    }
}
