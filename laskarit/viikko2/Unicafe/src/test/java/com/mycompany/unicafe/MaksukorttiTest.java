package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void lataaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void otaOikeinTarpeeksi() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
       
    @Test
    public void otaLiikaa() {
        kortti.otaRahaa(5000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void otaTrue() {
        assertEquals(true, kortti.otaRahaa(500));
    }
    
    @Test
    public void otaFalse() {
        assertEquals(false, kortti.otaRahaa(5000));
    }
    
    @Test
    public void saldoTest() {
        assertEquals(1000, kortti.saldo());
    }
    

}
