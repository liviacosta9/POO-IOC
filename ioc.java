import java.util.ArrayList;
import java.util.List;

//1. interface funcional para o callback (substitui as interfaces Sujeito/Observador)
@FunctionalInterface
interface CallbackSensor {
    void executar(String nomeSensor, double temperatura, double ph, double umidade);
}

/*
 * A decisão arquitetural foi tornar o SensorAmazonia completamente "cego" em relação
 * a quem consome seus dados. Ele não sabe o que é uma Cidade. Ele só pega 
 * os dados na natureza e executa uma lista de funções, as callbacks.
 */

//2. Classe Sensor = Sujeito
class SensorAmazonia {
    private String nomeSensor;
    private double temperatura;
    private double ph;
    private double umidade;

    //Em vez de lista de Observadores, é uma lista de funções (Callbacks)
    private List<CallbackSensor> callbacks;


    public SensorAmazonia(String nomeSensor) {
        this.nomeSensor = nomeSensor;
        this.callbacks = new ArrayList<>();
    }

    public void registrarCallback(CallbackSensor c) {
        callbacks.add(c);
    }

    public void notificarCallbacks() {
        for (CallbackSensor c : callbacks) {
            //executa a função passando os dados
            c.executar(nomeSensor, temperatura, ph, umidade);
        }
    }

    public void setDados(double temperatura, double ph, double umidade) {
        this.temperatura = temperatura;
        this.ph = ph;
        this.umidade = umidade;
        notificarCallbacks();
    }
}

/**
 * A Cidade agora é uma classe totalmente independente. Ela não implementa mais
 * a interface Observador. O único elo entre a Cidade e a Amazonia será feito
 * no momento da execução no Main, mantendo as responsabilidades separadas.
 */

//3. Classe Cidade = Observador
class Cidade {
    private String nome;

    public Cidade(String nome) {
        this.nome = nome;
    }

    //sem override
    public void atualizar(String nomeSensor, double temperatura, double ph, double umidade) {
        System.out.println("Cidade: " + nome);
        System.out.println("Sensor: " + nomeSensor);
        System.out.println("Temperatura: " + temperatura + " °C");
        System.out.println("pH: " + ph);
        System.out.println("Umidade do ar: " + umidade + " %");
        System.out.println("----------------------------------");
    }
}