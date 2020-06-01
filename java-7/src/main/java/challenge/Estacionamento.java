package challenge;

import challenge.EstacionamentoException;
import java.util.List;
import java.util.ArrayList;

public class Estacionamento {

    private static Integer numeroVagas = 10;
    private static Integer idadeMinima = 18;
    private static Integer pontosMaximos = 20;
    private List<Carro> carrosEstacionados = new ArrayList<>();
    private static Integer idadeIdoso = 55;

    public void estacionar(Carro carro) {
        if(carro.getMotorista() == null) throw new EstacionamentoException("Sem motorista");
        if(carro.getMotorista().getIdade() < idadeMinima) throw new EstacionamentoException("Menor de idade");
        if(carro.getMotorista().getPontos() > pontosMaximos) throw new EstacionamentoException("Pontos");

        int i = -1;
        while(carrosEstacionados.size() >= numeroVagas) {
            i++;
            Motorista motorista = carrosEstacionados.get(i).getMotorista();
            if(motorista != null && motorista.getIdade() < idadeIdoso) {
                carrosEstacionados.remove(i);
            } else if(i == numeroVagas - 1) throw new EstacionamentoException("Nao tem quem tirar");

        }
        carrosEstacionados.add(carro);
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.contains(carro);
    }
}
