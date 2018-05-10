package Test;

import Game.Model.Jatek;
import Game.Model.Jatekos;
import Game.Model.Tabla;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


public class JatekTest
{
    @Test
    public void nincsLepesTest()
    {
        Jatek jatek = new Jatek("valami", "valami2");

        Tabla tabla = new Tabla();
        tabla.setOnePos(0, 0, "blue");
        tabla.setOnePos(0, 1, "red");
        tabla.setOnePos(0, 3, "green");
        tabla.setOnePos(1, 0, "yellow");
        tabla.setOnePos(1, 2, "blue");
        tabla.setOnePos(2, 0, "green");
        tabla.setOnePos(2, 2, "red");
        tabla.setOnePos(3, 0, "yellow");
        tabla.setOnePos(3, 1, "blue");
        tabla.setOnePos(3, 3, "green");
        jatek.setTabla(tabla);
        jatek.nincsLepes(15, jatek.getJatekos1());
        assertTrue("Nincs több lépés.", !jatek.getGyoztesStr().equals(""));
    }

    @Test
    public void jatekosLepesTest()
    {
        Jatek jatek = new Jatek("valami", "valami2");

        jatek.getJatekos1().getKorong1().setMennyiseg(0);
        assertEquals("Nincs már kék korong.", jatek.jatekosLepes(1, jatek.getJatekos1().getKorong1().getSzin(),
                jatek.getJatekos1()), false);
        jatek.getJatekos1().getKorong2().setMennyiseg(0);
        assertEquals("Nincs már piros korong.", jatek.jatekosLepes(1, jatek.getJatekos1().getKorong2().getSzin(),
                jatek.getJatekos1()), false);
        assertEquals("Van még zöld korong.", jatek.jatekosLepes(1, jatek.getJatekos2().getKorong1().getSzin(),
                jatek.getJatekos2()), true);
        assertEquals("Van még sárga korong.", jatek.jatekosLepes(1, jatek.getJatekos2().getKorong2().getSzin(),
                jatek.getJatekos2()), true);
    }

    @Test
    public void kovLepesTest()
    {
        Jatek jatek = new Jatek("valami", "valami2");

        assertEquals("Első lépés piros koronggal.", jatek.kovLepes(1, "red"), true);
        assertEquals("Piros korongból már csak 2 db van.", jatek.getJatekos1().getKorong2().getMennyiseg(), 2);
        assertEquals("Második lépés zöld koronggal.", jatek.kovLepes(2, "green"), true);
        assertEquals("Egymás mellé két piros korong.", jatek.kovLepes(2, "red"), false);
        assertEquals("Két oszloppal mellé piros korong.", jatek.kovLepes(3, "red"), true);
        assertEquals("Egymás alá két zöld korong.", jatek.kovLepes(6, "green"), false);
        assertEquals("Két sorral alá zöld korong.", jatek.kovLepes(11, "green"), true);
        assertEquals("Átlósan két piros korong.", jatek.kovLepes(6, "red"), false);
        assertEquals("Átlósan két piros korong.", jatek.kovLepes(8, "red"), false);
        assertEquals("Foglalt mezőre piros korong.", jatek.kovLepes(1, "red"), false);
        assertEquals("Piros alá kék korong.", jatek.kovLepes(5, "blue"), true);
        assertEquals("Kék alá két sorral zöld korong.", jatek.kovLepes(13, "green"), true);
        assertEquals("Zöld mellé kék korong.", jatek.kovLepes(12, "blue"), true);
        assertEquals("Zöld alá sárga korong, ami győzelmet jelent.", jatek.kovLepes(9, "yellow"), true);
        assertTrue("Van-e győztes", !jatek.getGyoztesStr().equals(""));
    }

    @Test
    public void helyesLepesTest()
    {
        Jatek jatek = new Jatek("valami", "valami2");

        jatek.kovLepes(7, "red");
        assertEquals("Piros mellé zöld korong.", jatek.helyesLepes(1, 3, "zöld"), true);
        jatek.kovLepes(8, "green");
        assertEquals("Piros mellé piros korong.", jatek.helyesLepes(1, 1, "red"), false);
        jatek.kovLepes(16, "red");
        assertEquals("Zöld fölé zöld korong.", jatek.helyesLepes(0, 3, "green"), false);
    }

    @Test
    public void setterTest()
    {
        Jatek jatek = new Jatek("valami", "valami2");

        Jatekos jatekos1 = new Jatekos("player1", "blue", "red");
        Jatekos jatekos2 = new Jatekos("player2", "green", "yellow");
        jatek.setJatekos1(jatekos1);
        jatek.setJatekos2(jatekos2);
        assertEquals("Az első játékos első korongja kék színű.", jatek.getJatekos1().getKorong1().getSzin(), "blue");
        assertEquals("A második játékos második korongja sárga színű.", jatek.getJatekos2().getKorong2().getSzin(), "yellow");

        jatek.setLepesSzam(10);
        assertEquals("A lépések száma 10.", jatek.getLepesSzam(), 10);

        jatek.setGyoztesStr("Valaki nyert.");
        assertEquals("A gyoztesStr tartalma Valaki nyert.", jatek.getGyoztesStr(), "Valaki nyert.");
    }
}
