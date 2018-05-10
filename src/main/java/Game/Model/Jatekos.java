package Game.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Játékost megvalósító osztály.
 */
public class Jatekos
{
    /**
     * A logger.
     */
    private static Logger logger = LoggerFactory.getLogger(Jatekos.class);
    /**
     * A játékos neve.
     */
    private String name;
    /**
     * A játékos első típusú korongja.
     */
    private Korong korong1;
    /**
     * A játékos második típusú korongja.
     */
    private Korong korong2;

    /**
     * Létrehoz egy játékost, akinek készít két különböző
     * színű korongot.
     * @param name  A játékos neve.
     * @param szin1 A játékos első korongjának a színe.
     * @param szin2 A játékos második korongjának a színe.
     */
    public Jatekos(String name, String szin1, String szin2)
    {
        this.name = name;
        korong1 = new Korong();
        korong2 = new Korong();
        korong1.setSzin(szin1);
        korong2.setSzin(szin2);
        logger.info("Új játékos létrehozva " + name + " névvel.");
    }

    /**
     * Visszaadja a játékos nevét.
     * @return Sztring, amiben a játékos neve van tárolva.
     */
    public String getName()
    {
        logger.info("Játékos neve lekérve.");
        return name;
    }

    /**
     * Beállítja a játékos nevét.
     * @param name A játékos neve.
     */
    public void setName(String name)
    {
        logger.info("Új játékos név beállítva.");
        this.name = name;
    }

    /**
     * Visszaadja a játékoshoz tartozó első korongot.
     * @return {@link Game.Model.Korong Korong} objektum, ami a játékos első korongja.
     */
    public Korong getKorong1()
    {
        logger.info(this.name + " nevű játékos első korongja lekérve.");
        return korong1;
    }

    /**
     * Beállítja a játékos első korongját.
     * @param korong1 A játékos első korongja.
     */
    public void setKorong1(Korong korong1)
    {
        logger.info(this.name + " nevű játékos első korongja beállítva.");
        this.korong1 = korong1;
    }

    /**
     * Visszaadja a játékoshoz tartozó második korongot.
     * @return {@link Game.Model.Korong Korong} objektum, ami a játékos második korongja.
     */
    public Korong getKorong2()
    {
        logger.info(this.name + " nevű játékos második korongja lekérve.");
        return korong2;
    }

    /**
     * Beállítja a játékos második korongját.
     * @param korong2 A játékos második korongja
     */
    public void setKorong2(Korong korong2)
    {
        logger.info(this.name + " nevű játékos második korongja beállítva.");
        this.korong2 = korong2;
    }
}
