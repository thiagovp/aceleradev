package challenge;

import java.util.*;

public class Estacionamento {

    Queue<Carro> lotacaoEstacionamento = new PriorityQueue<>();

    public void estacionar(Carro carro) throws EstacionamentoException {
        if(carrosEstacionados() == 10){
            validarTodosMotoristasSenior(carro);
            Carro carroASair =
                    lotacaoEstacionamento
                            .stream()
                            .filter(carro1 -> carro1.getMotorista().getIdade() <= 55).findFirst().get();
            lotacaoEstacionamento.remove(carroASair);

            lotacaoEstacionamento.offer(carro);
        }else{
            validarSeCarroAutonomo(carro);
            validarMotoristaHabilitado(carro);
            validarHabilitacaoSuspensa(carro);
            lotacaoEstacionamento.offer(carro);
        }
    }

    private void validarTodosMotoristasSenior(Carro carro) {
        if(lotacaoEstacionamento.stream().allMatch(carro1 -> carro1.getMotorista().getIdade() > 55)){
            throw new EstacionamentoException("Todos os motoristas estacionados tem mais de 55 anos.");
        }
    }

    private void validarMotoristaHabilitado(Carro carro) {
        if(carro.getMotorista().getIdade() < 18
                || Objects.isNull(carro.getMotorista().getHabilitacao())
                || carro.getMotorista().getHabilitacao().trim().isEmpty()){
            throw new EstacionamentoException("Motorista menor ou inabilitado.");
        }
    }

    private void validarHabilitacaoSuspensa(Carro carro) {
        if(carro.getMotorista().getPontos() > 20){
            throw new EstacionamentoException("Motorista com habilitação suspensa por exceder o número de pontos.");
        }
    }

    private void validarSeCarroAutonomo(Carro carro) {
        if(Objects.isNull(carro.getMotorista())){
            throw new EstacionamentoException("Carro Autonomo não é permitido neste estacionamento");
        }
    }


    public int carrosEstacionados() {
        return lotacaoEstacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return lotacaoEstacionamento.contains(carro);
    }
}
