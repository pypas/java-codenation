package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        texto = texto.toLowerCase();
        if(texto.length() <= 0) {
            throw new IllegalArgumentException();
        }
        String res = "";
        for(int i = 0; i < texto.length(); i++) {
            char newChar = texto.charAt(i);
            if((int) newChar > 96 && (int) newChar < 123) {
                newChar = (char) (((int) newChar + 3 - 97) % 26 + 97);
            }
            res += newChar;
        }
        return res;
        //throw new UnsupportedOperationException("esse method nao esta implementado aainda");
    }

    @Override
    public String descriptografar(String texto) {
        if(texto.length() <= 0) {
            throw new IllegalArgumentException();
        }
        texto = texto.toLowerCase();

        String res = "";

        for(int i = 0; i < texto.length(); i++) {
            char newChar = texto.charAt(i);
            if((int) newChar > 96 && (int) newChar < 123) {
                newChar = (char) (((int) newChar - 3 - 97) % 26 + 97);
            }
            res += newChar;
        }
        return res;
        //throw new UnsupportedOperationException("esse method nao esta implementado aainda");
    }
}
