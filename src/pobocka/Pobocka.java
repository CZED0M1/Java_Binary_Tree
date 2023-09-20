package pobocka;

import BVS.AbstrTable;
import BVS.eTypProhl;
import java.util.Iterator;
import kolekce.AbstrDoubleList;
import kolekce.EnumPozice;
import prostredky.Auto;

public class Pobocka implements IPobocka {

    private final String jmeno;
    private int pocetAuto = 0;
    private AbstrTable<String, Auto> seznamAut;

    public Pobocka(String jmeno) {
        this.jmeno = jmeno;
        this.seznamAut = new AbstrTable<>();
    }

    @Override
    public void vlozAuto(Auto auto) {
        System.out.println(auto.getSpz());
        this.seznamAut.vloz(auto.getSpz(), auto);
        
        pocetAuto++;
    }

    @Override
    public Auto zpristupnAuto(String spz) {
        return seznamAut.najdi(spz);
    }

    @Override
    public Auto odeberAuto(String key) {
        return seznamAut.odeber(key);
    }

    @Override
    public Iterator<Auto> iterator(eTypProhl typ) {
        return seznamAut.vytvorIterator(typ);
    }

    @Override
    public void zrus() {
        seznamAut.zrus();
        pocetAuto = 0;
    }

    public int getPocetAuto() {
        return pocetAuto;
    }

    public void setPocetAuto(int pocetAuto) {
        this.pocetAuto = pocetAuto;
    }

    @Override
    public String toString() {
        return jmeno;
    }

}
