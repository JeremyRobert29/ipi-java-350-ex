package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.exception.EmployeException;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void getNombreAnneeAncienneteNow(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNminus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(2, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0"
    })
    public void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertEquals(primeAnnuelle, prime);

    }
    @Test
    public void augmenterSalaire(){

        //Given
        Employe a = new Employe();
        a.setSalaire(1500.00);

        //When
        try {
            a.augmenterSalaire(0.5);
        }
        catch (EmployeException probleme) {
        }

        //Then
        Assertions.assertEquals(2250, a.getSalaire().doubleValue());
    }
    @Test
    public void augmenterSalairenull(){

        //Given
        Employe a = new Employe();
        a.setSalaire(1500.00);

        //When
        try {
            a.augmenterSalaire(0.0);
        }catch(EmployeException probleme) {

        }
        //Then
        Assertions.assertEquals(1500, a.getSalaire().doubleValue());

    }
    @Test
    public void augmenterSalaireE (){

        //Given
        Employe a = new Employe();
        a.setSalaire(1500.00);

        //When
        try {
            a.augmenterSalaire(0.6);
        }
        catch (EmployeException probleme){

        }

        //Then
        Assertions.assertEquals(1500, a.getSalaire().doubleValue());

    }
    @ParameterizedTest
    @CsvSource({
            //Source avec Date et nbRTT
            "2019-08-06, 8",
            "2021-08-06, 11",
            "2022-08-06, 10",
            "2032-08-06, 12"
    })
    void getNbRtt (LocalDate dadate, Integer nbRtt){
        //Given
        Employe employe = new Employe();
        //When
        Integer RTT =  employe.getNbRtt(dadate);
        //Then
        Assertions.assertEquals(nbRtt, RTT);
    }

}