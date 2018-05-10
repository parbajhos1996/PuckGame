package Test;

import Game.Model.Tabla;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class TablaTest
{
    @Test
    public void onePosTest()
    {
        Tabla tabla = new Tabla();

        tabla.setOnePos(0, 0, "red");
        assertEquals("1.sor 1.oszlopának eleme piros.", tabla.getOnePos(0, 0), "red");

        tabla.setOnePos(1, 3, "blue");
        assertEquals("2.sor 4.oszlopának eleme kék.", tabla.getOnePos(1, 3), "blue");
    }

    @Test
    public void osszesSzabalyTest()
    {
        Tabla tabla = new Tabla();

        tabla.setOnePos(0, 0, "red");
        assertEquals("A játék nem ért még véget.", tabla.osszesSzabaly(), false);

        tabla.setOnePos(1, 1, "blue");
        assertEquals("A játék még mindig nem ért véget.", tabla.osszesSzabaly(), false);

        tabla.setOnePos(2, 2, "green");
        tabla.setOnePos(3, 3, "yellow");
        assertEquals("A játék véget ért.", tabla.osszesSzabaly(), true);

    }

    @Test
    public void setterTest()
    {
        Tabla tabla = new Tabla();

        tabla.setTableSize(5);
        assertEquals("A tábla mérete 5x5.", tabla.getTableSize(), 5);

        String tablaa[][] = new String[tabla.getTableSize()][tabla.getTableSize()];
        tabla.setTablePos(tablaa);
        assertEquals("A tábla a tablaa tömb.", tabla.getTablePos(), tablaa);
    }
}
