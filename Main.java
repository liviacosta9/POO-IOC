public class Main {
    public static void main(String[] args) {
        SensorAmazonia sensor = new SensorAmazonia("Sensor Rio Negro");

        Cidade manaus = new Cidade("Manaus");
        Cidade belem = new Cidade("Belém");

        /**
         * EXPLICAÇÃO DA INVERSÃO DE CONTROLE (IoC)
         * no codigo antigo, a Cidade teria que ficar constantemente perguntando
         * ao Sensor se os dados mudaram (Polling). 
         * aqui nós invertemos o controle, a cidade apenas entrega a referência do seu 
         * método (manaus::atualizar) para o Sensor. O Sensor assume o controle do fluxo 
         * e avisa a cidade apenas quando for a hora certa Push.
         */
        sensor.registrarCallback(manaus::atualizar);
        sensor.registrarCallback(belem::atualizar);

        System.out.println("Recebendo novos dados do sensor...\n");
        //qunado altera os dados, o sensor usa as callbacks para mandar a informação para as cidades
        sensor.setDados(30.5, 6.8, 85.0);
    }
}