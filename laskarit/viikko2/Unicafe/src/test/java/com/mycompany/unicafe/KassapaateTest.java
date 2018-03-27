package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author roxeh
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(0);
    }
    
    @Test
    public void luotuOikeinSaldo() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void luotuOikeinMyytyEdullisesti() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luotuOikeinMyytyKalliilla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    //SyöEdullisesti käteinen
    
    @Test
    public void syoEdullisestiMaksaOikein() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiMaksaVahan() {
        paate.syoEdullisesti(230);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiMaksaVahanPalautus() {
        assertEquals(230, paate.syoEdullisesti(230));
    }
    
    @Test
    public void syoEdullisestiMaksaLiikaa() {
        paate.syoEdullisesti(480);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    //SyöMaukkaasti käteinen
    
    @Test
    public void syoMaukkaastiMaksaOikein() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiMaksaVahan() {
        paate.syoMaukkaasti(230);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiMaksaVahanPalautus() {
        assertEquals(230, paate.syoMaukkaasti(230));
    }
    
    @Test
    public void syoMaukkaastiMaksaLiikaa() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }    
    
    //SyöEdullinen kortti
    
    @Test
    public void syoEdullisestiMaksaOikeinKortti() {
        kortti.lataaRahaa(1000);
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiMaksaVahanKortti() {
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }    

    //SyöEMaukkaasti kortti
    
    @Test
    public void syoMaukkaastiMaksaOikeinKortti() {
        kortti.lataaRahaa(1000);
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiMaksaVahanKortti() {
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    //LataaRahaa
    
    @Test
    public void lataaRahaaOikein() {
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(100010, paate.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaNegatiivinen() {
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, kortti.saldo());
    }
    
}
