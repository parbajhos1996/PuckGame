package Game.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játékkorongot megvalósító osztály.
 */
public class Korong
{
    /**
     * A logger.
     */
    private static Logger logger = LoggerFactory.getLogger(Korong.class);
    /**
     * A korong színe.
     */
    private String szin;
    /**
     * A korong darabszáma.
     */
    private int mennyiseg;

    /**
     * Meghatározza a korong darabszámát.
     * Alapvetően a játékhoz minden
     * korongból 3 darab szükséges.
     */
    public Korong()
    {
        mennyiseg = 3;
        logger.info("Elkészült egy új korong.");
    }

    /**
     * Visszaadja a korong színét.
     * @return Sztring, amiben a korong színe van tárolva.
     */
    public String getSzin()
    {
        logger.info("Korong színe lekérve.");
        return szin;
    }

    /**
     * Beállítja a korong színét.
     * @param szin A korong színe.
     */
    public void setSzin(String szin)
    {
        logger.info("Új korong szín beállítva.");
        this.szin = szin;
    }

    /**
     * Visszaadja a korong darabszámát.
     * @return Egész szám, amiben a korong darabszáma van tárolva.
     */
    public int getMennyiseg()
    {
        logger.info("Korong darabszáma lekérve.");
        return mennyiseg;
    }

    /**
     * Beállítja a korong darabszámát.
     * @param mennyiseg A korong darabszáma.
     */
    public void setMennyiseg(int mennyiseg)
    {
        logger.info("Új korong darabszám beállítva.");
        this.mennyiseg = mennyiseg;
    }
}
