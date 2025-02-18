package br.com.vkl;

public class Aviao {

    private String modelo;

    private int altitude;

    private int velocidade;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * Pega informações sobre o voo do avião.
     * @version 1.0
     */
    public void informacoesDeVoo(){
        System.out.println("Modelo: " + modelo + ", " + "Altitude: " + altitude + " Metros, "  + "Velocidade: " + velocidade + " KM/H.");
    }
}
