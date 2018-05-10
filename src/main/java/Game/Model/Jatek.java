package Game.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály végzi a játék irányítását.
 */
public class Jatek
{
    /**
     * A logger.
     */
    private static Logger logger = LoggerFactory.getLogger(Jatek.class);
    /**
     * A játékban történt lépések száma.
     */
    private int lepesSzam;
    /**
     * A játék első játékosa.
     */
    private Jatekos jatekos1;
    /**
     * A játék második játékosa.
     */
    private Jatekos jatekos2;
    /**
     * A játéktábla.
     */
    private Tabla tabla;
    /**
     * A játék végét jelző sztring.
     */
    private String gyoztesStr;

    /**
     * Létrehoz két játékost, akik különböz színű
     * korongokat kapnak.Majd létrehoz egy játéktáblát,
     * és a játék irányításához szükséges változókat beállítja.
     *
     * @param player1 Az első játékos neve.
     * @param player2 A második játékos neve.
     */
    public Jatek(String player1, String player2)
    {
        jatekos1 = new Jatekos(player1, "blue", "red");
        jatekos2 = new Jatekos(player2, "green", "yellow");
        tabla = new Tabla();
        lepesSzam = 0;
        logger.info("Lépésszám nullára beállítva.");
        gyoztesStr = new String();
        logger.info("Új játék létrehozva.");
    }

    /**
     * Megvizsgálja van-e még helyes lépése a játékosnak.
     * Ha nem tud lépni és még nem fogyott el az összes
     * korong, akkor a másik játékos nyert.
     * @param mezoSzam A lépés mezőszáma a táblán.
     * @param jatekos  A következő lépést végrehajtó játékos.
     */
    public void nincsLepes(int mezoSzam, Jatekos jatekos)
    {
        int counter = 0;
        for (int i = 0; i < 16; i++)
        {
            try
            {
                if (helyesLepes(i / 4, i % 4, jatekos.getKorong1().getSzin()) && jatekos.getKorong1().getMennyiseg() > 0)
                    counter++;
                if (helyesLepes(i / 4, i % 4, jatekos.getKorong2().getSzin()) && jatekos.getKorong2().getMennyiseg() > 0)
                    counter++;
            } catch (NullPointerException n)
            {
                logger.error(n.toString());
            }
        }

        if (counter == 0 && lepesSzam % 2 == 0)
        {
            gyoztesStr = "No more steps." + jatekos2.getName() + " won.";
            logger.info("Nincs több lehetséges lépés." + jatekos2.getName() + " nyert.");
        } else if (counter == 0 && lepesSzam % 2 == 1)
        {
            gyoztesStr = "No more steps." + jatekos1.getName() + " won.";
            logger.info("Nincs több lehetséges lépés." + jatekos1.getName() + " nyert.");
        }
    }

    /**
     * Megvizsgálja a helyes-e a játékos lépése
     * a korong színe és a darabszáma alapján.
     * @param mezoSzam A lépés mezőszáma a táblán.
     * @param szin     A korong színe.
     * @param jatekos  A következő lépést végrehajtó játékos.
     * @return Logikai érték, amely tartalmazza a lépés helyességét.
     */
    public boolean jatekosLepes(int mezoSzam, String szin, Jatekos jatekos)
    {
        if (helyesLepes((mezoSzam - 1) / 4, (mezoSzam - 1) % 4, szin))
        {
            if (szin.equals(jatekos.getKorong1().getSzin()) && jatekos.getKorong1().getMennyiseg() >= 1)
            {
                jatekos.getKorong1().setMennyiseg(jatekos.getKorong1().getMennyiseg() - 1);
                logger.info("Helyes lépés történt.");
                return true;
            } else if (szin.equals(jatekos.getKorong2().getSzin()) && jatekos.getKorong2().getMennyiseg() >= 1)
            {
                jatekos.getKorong2().setMennyiseg(jatekos.getKorong2().getMennyiseg() - 1);
                logger.info("Helyes lépés történt.");
                return true;
            } else
            {
                logger.info("Helytelen lépés történt.");
                return false;
            }
        }
        return false;
    }

    /**
     * A játékos által megadott következő lépést hajtja végre.
     * Eldönti, hogy a lépés után véget ér-e a játék, tehát van-e győztes.
     * @param mezoSzam A lépés mezőszáma a táblán.
     * @param szin     A korong színe.
     * @return Logikai érték, amely tartalmazza a lépés helyességét.
     */
    public boolean kovLepes(int mezoSzam, String szin)
    {
        boolean helyes;

        if (lepesSzam % 2 == 0)
        {
            nincsLepes(mezoSzam, jatekos1);
            helyes = jatekosLepes(mezoSzam, szin, jatekos1);
        } else
        {
            nincsLepes(mezoSzam, jatekos2);
            helyes = jatekosLepes(mezoSzam, szin, jatekos2);
        }

        if (helyes)
        {
            tabla.setOnePos((mezoSzam - 1) / 4, (mezoSzam - 1) % 4, szin);
            lepesSzam++;
            logger.info("Lépésszám növelve.");
            if (lepesSzam == 12)
            {
                gyoztesStr = "Draw.";
                logger.info("A játék vége döntetlen.");
            } else if (tabla.osszesSzabaly() && lepesSzam % 2 == 1)
            {
                gyoztesStr = jatekos1.getName() + " won.";
                logger.info(jatekos1.getName() + " nyert.");
            } else if (tabla.osszesSzabaly() && lepesSzam % 2 == 0)
            {
                gyoztesStr = jatekos2.getName() + " won.";
                logger.info(jatekos2.getName() + " nyert.");
            }
        }

        return helyes;
    }


    /**
     * Eldönti a megadott paraméterek alapján a lépés helyességét.
     * @param x    A lépés sorának a száma a táblán.
     * @param y    A lépés oszlopának a száma a táblán.
     * @param szin A lépésben használt korong színe.
     * @return Logikai érték, ami eldönti helyes volt-e a lépés.
     */
    public boolean helyesLepes(int x, int y, String szin)
    {
        if (x > 0 && tabla.getOnePos(x - 1, y) != null && tabla.getOnePos(x - 1, y).equals(szin))
            return false;
        if (y > 0 && tabla.getOnePos(x, y - 1) != null && tabla.getOnePos(x, y - 1).equals(szin))
            return false;
        if (x < 3 && tabla.getOnePos(x + 1, y) != null && tabla.getOnePos(x + 1, y).equals(szin))
            return false;
        if (y < 3 && tabla.getOnePos(x, y + 1) != null && tabla.getOnePos(x, y + 1).equals(szin))
            return false;
        if (y < 3 && x < 3 && tabla.getOnePos(x + 1, y + 1) != null && tabla.getOnePos(x + 1, y + 1).equals(szin))
            return false;
        if (y < 3 && x > 0 && tabla.getOnePos(x - 1, y + 1) != null && tabla.getOnePos(x - 1, y + 1).equals(szin))
            return false;
        if (x > 0 && y > 0 && tabla.getOnePos(x - 1, y - 1) != null && tabla.getOnePos(x - 1, y - 1).equals(szin))
            return false;
        if (x < 3 && y > 0 && tabla.getOnePos(x + 1, y - 1) != null && tabla.getOnePos(x + 1, y - 1).equals(szin))
            return false;
        if (tabla.getOnePos(x, y) != null)
            return false;
        if (szin.equals(""))
            return false;

        return true;
    }

    /**
     * Visszaadja a játékban történt lépések számát.
     * @return Egész szám, amiben a lépések száma van tárolva.
     */
    public int getLepesSzam()
    {
        logger.info("Lépésszám lekérve.");
        return lepesSzam;
    }

    /**
     * Beállítja a játékban történt lépések számát.
     * @param lepesSzam A lépések száma.
     */
    public void setLepesSzam(int lepesSzam)
    {
        logger.info("Lépésszám beállítva.");
        this.lepesSzam = lepesSzam;
    }

    /**
     * Visszaadja a játék első játékosát.
     * @return {@link Game.Model.Jatekos Jatekos} objektum, amiben az első játékos adatai vannak tárolva.
     */
    public Jatekos getJatekos1()
    {
        logger.info("Első játékos lekérve.");
        return jatekos1;
    }

    /**
     * Beállítja a játék első játékosát.
     * @param jatekos1 Az első játékos.
     */
    public void setJatekos1(Jatekos jatekos1)
    {
        logger.info("Első játékos beállítva.");
        this.jatekos1 = jatekos1;
    }

    /**
     * Visszaadja a játék második játékosát.
     * @return {@link Game.Model.Jatekos Jatekos} objektum, amiben a második játékos adatai vannak tárolva.
     */
    public Jatekos getJatekos2()
    {
        logger.info("Második játékos lekérve.");
        return jatekos2;
    }

    /**
     * Beállítja a játék második játékosát.
     * @param jatekos2 A második játékos.
     */
    public void setJatekos2(Jatekos jatekos2)
    {
        logger.info("Második játékos beállítva");
        this.jatekos2 = jatekos2;
    }

    /**
     * Visszaadja a játéktáblát.
     * @return {@link Game.Model.Tabla Tabla} objektum, amiben a játéktábla adatai vannak tárolva.
     */
    public Tabla getTabla()
    {
        logger.info("Tábla lekérve.");
        return tabla;
    }

    /**
     * Beállítja a játéktáblát.
     * @param tabla A játéktábla.
     */
    public void setTabla(Tabla tabla)
    {
        logger.info("Játéktábla beállítva.");
        this.tabla = tabla;
    }

    /**
     * Visszaadja a játék végéhez szükséges információt
     * tartalmazó sztringet.
     * @return Sztring, amiben a játék végéhez szükséges információk vannak tárolva.
     */
    public String getGyoztesStr()
    {
        logger.info("GyőztesStr sztring lekérve.");
        return gyoztesStr;
    }

    /**
     * Beállítja a játék végéhez szükséges információt
     * tartalmazó sztringet.
     * @param gyoztesStr A játék végéhez szükséges információt
     *                   tartalmazó sztring.
     */
    public void setGyoztesStr(String gyoztesStr)
    {
        logger.info("GyőztesStr sztring beállítva.");
        this.gyoztesStr = gyoztesStr;
    }
}
