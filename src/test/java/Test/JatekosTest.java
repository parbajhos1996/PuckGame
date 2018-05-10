package Test;

import Game.Model.Jatekos;
import Game.Model.Korong;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class JatekosTest
{
    @Test
    public void jatekosTest()
    {
        Jatekos jatekos = new Jatekos("Valami", "blue", "red");

        assertEquals("A játékos neve Valami.", jatekos.getName(), "Valami");
        assertEquals("A játékos első korongjának a színe kék.", jatekos.getKorong1().getSzin(), "blue");
        assertEquals("A játékos második korongjának a színe piros", jatekos.getKorong2().getSzin(), "red");
    }

    @Test
    public void setterTest()
    {
        Jatekos jatekos = new Jatekos("valami", "blue", "red");

        jatekos.setName("Player 1");
        assertEquals("A játékos neve Player 1.", jatekos.getName(), "Player 1");

        Korong korong1 = new Korong();
        jatekos.setKorong1(korong1);
        assertEquals("A korong1 a játékos első korongja.", jatekos.getKorong1(), korong1);

        Korong korong2 = new Korong();
        jatekos.setKorong2(korong2);
        assertEquals("A korong2 a játékos második korongja.", jatekos.getKorong2(), korong2);
    }
}
