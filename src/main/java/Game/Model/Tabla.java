package Game.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A játéktáblát megvalósító osztály.
 */
public class Tabla
{
    /**
     * A logger.
     */
    private static Logger logger = LoggerFactory.getLogger(Tabla.class);
    /**
     * A játéktábla.
     */
    private String[][] tablePos;
    /**
     * A játéktábla mérete.
     */
    private int tableSize;

    /**
     * Létrehozza a játéktáblát a méretnek megfelelően.
     */
    public Tabla()
    {
        tableSize = 4;
        tablePos = new String[tableSize][tableSize];
        logger.info("Elkészült egy új tábla.");
    }

    /**
     * A tábla egy adott sorban és oszlopban lévő korongját határozza meg.
     * @param x    A meghatározandó korong sorának a száma a táblán.
     * @param y    A meghatározandó korong oszlopának a száma a táblán.
     * @param szin A meghatározandó korong színe.
     */
    public void setOnePos(int x, int y, String szin)
    {
        tablePos[x][y] = szin;
        logger.info("A tábla " + Integer.toString(x + 1) + ". sorának" + " " + Integer.toString(y + 1) + ".oszlopa beállítva.");
    }

    /**
     * Visszaadja az adott pozicíóban lévő korong színét.
     * @param x A korong sorának a száma a táblán.
     * @param y A korong oszlopának a száma a táblán.
     * @return Egy sztring, ami a korong színét tartalmazza.
     */
    public String getOnePos(int x, int y)
    {
        if (x > -1 && y > -1 && x < tableSize && y < tableSize)
            return tablePos[x][y];

        logger.info("A tábla " + Integer.toString(x + 1) + ". sorának" + " " + Integer.toString(y + 1) + ".oszlopa lekérdezve.");
        return "";
    }

    /**
     * A játék összes szabályát ellenőrzi az adott állás alapján.
     * @return Egy logikai érték, ami eldönti hogy a játék véget ért-e.
     */
    public boolean osszesSzabaly()
    {
        StringBuilder atloSzinek = new StringBuilder();
        int atloCounter = 0;
        StringBuilder atloSzinek2 = new StringBuilder();
        int atloCounter2 = 0;

        for (int i = 0; i < tableSize; i++)
        {
            StringBuilder sorSzinek = new StringBuilder();
            StringBuilder oszlopSzinek = new StringBuilder();
            int sorCounter = 0;
            int oszlopCounter = 0;
            for (int j = 0; j < tableSize; j++)
            {
                try
                {
                    if (!sorSzinek.toString().contains(tablePos[i][j].toLowerCase()))
                        sorCounter++;
                } catch (NullPointerException n)
                {
                    logger.error(n.toString());
                }
                try
                {
                    if (!oszlopSzinek.toString().contains(tablePos[j][i].toLowerCase()))
                        oszlopCounter++;
                } catch (NullPointerException n)
                {
                    logger.error(n.toString());
                }
                sorSzinek.append(tablePos[i][j]);
                oszlopSzinek.append(tablePos[j][i]);

                if (sorCounter == tableSize || oszlopCounter == tableSize)
                {
                    logger.info("Van győztes.");
                    return true;
                }
            }

            try
            {
                if (!atloSzinek.toString().contains(tablePos[i][i]))
                    atloCounter++;
            } catch (NullPointerException n)
            {
                logger.error(n.toString());
            }
            try
            {
                if (!atloSzinek2.toString().contains(tablePos[i][tableSize - 1 - i]))
                    atloCounter2++;
            } catch (NullPointerException n)
            {
                logger.error(n.toString());
            }
            atloSzinek.append(tablePos[i][i]);
            atloSzinek2.append(tablePos[i][tableSize - 1 - i]);
        }

        if (atloCounter == tableSize || atloCounter2 == tableSize)
        {
            logger.info("Van győztes.");
            return true;
        }

        logger.info("Összes szabály ellenőrizve , nincs győztes.");
        return false;
    }

    /**
     * Visszaadja a játéktáblát.
     * @return Kétdimenziós sztring tömb, amiben az  mezőkön lévő korongok színe van tárolva.
     */
    public String[][] getTablePos()
    {
        logger.info("Tábla lekérve.");
        return tablePos;
    }

    /**
     * Beállítja a játéktáblát.
     * @param tablePos A játéktábla.
     */
    public void setTablePos(String[][] tablePos)
    {
        logger.info("Új tábla beállítva.");
        this.tablePos = tablePos;
    }

    /**
     * Visszadja a tábla méretét.
     * @return Egész szám, amiben a tábla mérete van tárolva.
     */
    public int getTableSize()
    {
        logger.info("Tábla mérete lekérve.");
        return tableSize;
    }

    /**
     * Beállítja a játéktábla méretét.
     * @param tableSize A játéktába mérete.
     */
    public void setTableSize(int tableSize)
    {
        logger.info("Új tábla méret beállítva.");
        this.tableSize = tableSize;
    }
}
