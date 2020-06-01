package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import javax.management.Attribute;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel
{

    @Override
    public BigDecimal somar(Object type) {
        Field[] fields = type.getClass().getDeclaredFields();
        BigDecimal soma = BigDecimal.ZERO;
        for(Field field: fields) {
            if(field.getType().isAssignableFrom(BigDecimal.class) && field.getDeclaredAnnotation(Somar.class) != null) {
                field.setAccessible(true);
                try {
                    soma = soma.add((BigDecimal) field.get(type));
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal Access");
                }

            }
        }
        return soma;
    }

    @Override
    public BigDecimal subtrair(Object type) {
        Field[] fields = type.getClass().getDeclaredFields();
        BigDecimal soma = BigDecimal.ZERO;
        for(Field field: fields) {
            if(field.getType().isAssignableFrom(BigDecimal.class) && field.getDeclaredAnnotation(Subtrair.class) != null) {
                field.setAccessible(true);
                try {
                    soma = soma.add((BigDecimal) field.get(type));
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal Access");
                }
            }
        }
        return soma;
    }

    @Override
    public BigDecimal totalizar(Object type) {
        return somar(type).subtract(subtrair(type));
    }
}
