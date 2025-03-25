package br.com.vkl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        System.out.println("---- Lendo anotações com Reflections ----");
        Class tabelaClass = Tabela.class;
        try {
            Constructor tabelaCons = tabelaClass.getConstructor();
            Tabela tabela = (Tabela) tabelaCons.newInstance();
            Annotation[] annotations = tabelaClass.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation.annotationType().equals(TabelaAnnotation.class)){
                    TabelaAnnotation anno = (TabelaAnnotation) annotation;
                    System.out.println("---- Nome ----");
                    System.out.println(anno.value());
                    System.out.println("---- Valor ----");
                    System.out.println(anno.value2());
                }
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
