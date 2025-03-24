package com.vkl;

public class UiInfo {
    private static UiInfo instance;

    private int montadoraIndex;
    private int modeloIndex;
    private int carroSelecionado;

    private UiInfo(){
    }

    public static UiInfo getInstance(){
        if (instance == null){
            instance = new UiInfo();
        }
        return instance;
    }

    public int getModeloIndex() {
        return modeloIndex;
    }

    public void setModeloIndex(int modeloIndex) {
        this.modeloIndex = modeloIndex;
    }

    public int getMontadoraIndex() {
        return montadoraIndex;
    }

    public void setMontadoraIndex(int montadoraIndex) {
        this.montadoraIndex = montadoraIndex;
    }

    public int getCarroSelecionado() {
        return carroSelecionado;
    }

    public void setCarroSelecionado(int carroSelecionado) {
        this.carroSelecionado = carroSelecionado;
    }
}
