package pobocka;

import BVS.eTypProhl;
import java.util.Iterator;
import kolekce.EnumPozice;
import prostredky.Auto;

public interface IPobocka {

    void vlozAuto(Auto auto);
//vloží nové auto do
//seznamu na příslušnou pozici (první, poslední, předchůdce, následník)

    Auto zpristupnAuto(String spz);
//zpřístupní auto z požadované
//pozice (první, poslední, předchůdce, následník, aktuální)

    Auto odeberAuto(String spz);
//odebere auto z požadované
//pozice (první, poslední, předchůdce, následník, aktuální),

    Iterator iterator(eTypProhl typ);
//vytvoří iterátor

    void zrus();
//zruší všechny auta.
}
