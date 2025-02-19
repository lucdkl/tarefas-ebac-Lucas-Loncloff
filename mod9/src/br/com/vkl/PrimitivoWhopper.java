package br.com.vkl;

public class PrimitivoWhopper {
    public static void main (String[] args){
        long longo = 100;
        System.out.println("Longo: " + longo);
        int medio = (int) longo;
        System.out.println("Medio: " + medio);
        short pequeno = (short) medio;
        System.out.println("Pequeno: " + pequeno);
    }
}
