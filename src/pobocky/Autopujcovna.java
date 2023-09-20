package pobocky;

import BVS.eTypProhl;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kolekce.AbstrDoubleList;
import kolekce.EnumPozice;
import kolekce.IAbstrDoubleList;
import pobocka.IPobocka;
import prostredky.Auto;

public class Autopujcovna implements IAutopujcovna {

    public static final IAbstrDoubleList<IPobocka> SEZNAM_POBOCEK = new AbstrDoubleList<>();
    public static final IAbstrDoubleList<Auto> SEZNAM_VYPUJC = new AbstrDoubleList<>();

    private static final Random RANDOM = new Random();

    @Override
    public void vlozPobocku(IPobocka Pobocka, EnumPozice Pozice) {
        switch (Pozice) {
            case NASLEDNIK:
                SEZNAM_POBOCEK.vlozNaslednika(Pobocka);
                break;
            case POSLEDNI:
                SEZNAM_POBOCEK.vlozPosledni(Pobocka);
                break;
            case PREDCHUDCE:
                SEZNAM_POBOCEK.vlozPredchudce(Pobocka);
                break;
            case PRVNI:
                SEZNAM_POBOCEK.vlozPrvni(Pobocka);
                break;
            default:
                System.out.println("EEE");
        }
    }

    @Override
    public IPobocka zpristupniPobocku(EnumPozice Pozice) {
        IPobocka pob = null;
        switch (Pozice) {
            case AKTUALNI:
                pob = SEZNAM_POBOCEK.zpristupniAktualni();
                break;
            case NASLEDNIK:
                pob = SEZNAM_POBOCEK.zpristupniNaslednika();
                break;
            case POSLEDNI:
                pob = SEZNAM_POBOCEK.zpristupniPosledni();
                break;
            case PREDCHUDCE:
                pob = SEZNAM_POBOCEK.zpristupniPredchudce();
                break;
            case PRVNI:
                pob = SEZNAM_POBOCEK.zpristupniPrvni();
                break;
            default:
                System.out.println("EEE");
        }
        return pob;
    }

    @Override
    public IPobocka odeberPobocku(EnumPozice Pozice) {
        IPobocka pob = null;
        switch (Pozice) {
            case AKTUALNI:
                pob = SEZNAM_POBOCEK.odeberAktualni();

                break;
            case NASLEDNIK:
                pob = SEZNAM_POBOCEK.odeberNaslednika();

                break;
            case POSLEDNI:
                pob = SEZNAM_POBOCEK.odeberPosledni();

                break;
            case PREDCHUDCE:
                pob = SEZNAM_POBOCEK.odeberPredchudce();

                break;
            case PRVNI:
                pob = SEZNAM_POBOCEK.odeberPrvni();

                break;
            default:
                System.out.println("EEE");
        }
        return pob;
    }

    @Override
    public void vlozAuto(Auto auto, EnumPozice Pozice) {
        SEZNAM_POBOCEK.zpristupniAktualni().vlozAuto(auto);
    }

    @Override
    public Auto zpristupnAuto(String spz) {
        return SEZNAM_POBOCEK.zpristupniAktualni().zpristupnAuto(spz);
    }

    @Override
    public Auto odeberAuto(String spz) {
        return SEZNAM_POBOCEK.zpristupniAktualni().odeberAuto(spz);
    }

    @Override
    public Auto vypujcAuto(String spz) {
        Auto auto = SEZNAM_POBOCEK.zpristupniAktualni().odeberAuto(spz);
        SEZNAM_VYPUJC.vlozPosledni(auto);

        return auto;
    }

    @Override
    public Auto vratAuto(EnumPozice Pozice) {
        Auto auto = null;
        IPobocka pob = SEZNAM_POBOCEK.zpristupniAktualni();
        switch (Pozice) {
            case AKTUALNI:
                auto = SEZNAM_VYPUJC.odeberAktualni();
                auto.setPocetVypujceni(auto.getPocetVypujceni() + 1);
                auto.setStavKm(auto.getStavKm() + RANDOM.nextInt(2500));
                pob.vlozAuto(auto);
                break;
            case NASLEDNIK:
                auto = SEZNAM_VYPUJC.odeberNaslednika();
                auto.setPocetVypujceni(auto.getPocetVypujceni() + 1);
                auto.setStavKm(auto.getStavKm() + RANDOM.nextInt(2500));
                pob.vlozAuto(auto);
                break;
            case POSLEDNI:
                auto = SEZNAM_VYPUJC.odeberPosledni();
                auto.setPocetVypujceni(auto.getPocetVypujceni() + 1);
                auto.setStavKm(auto.getStavKm() + RANDOM.nextInt(2500));
                pob.vlozAuto(auto);
                break;
            case PREDCHUDCE:
                auto = SEZNAM_VYPUJC.odeberPredchudce();
                auto.setPocetVypujceni(auto.getPocetVypujceni() + 1);
                auto.setStavKm(auto.getStavKm() + RANDOM.nextInt(2500));
                pob.vlozAuto(auto);
                break;
            case PRVNI:
                auto = SEZNAM_VYPUJC.odeberPrvni();
                auto.setPocetVypujceni(auto.getPocetVypujceni() + 1);
                auto.setStavKm(auto.getStavKm() + RANDOM.nextInt(2500));
                pob.vlozAuto(auto);
                break;
            default:
                System.out.println("EEE");
        }
        return auto;
    }

    @Override
    public Auto zpristupniVypujceneAuto(EnumPozice Pozice) {
        Auto auto = null;
        switch (Pozice) {
            case AKTUALNI:
                auto = SEZNAM_VYPUJC.zpristupniAktualni();
                break;
            case NASLEDNIK:
                auto = SEZNAM_VYPUJC.zpristupniNaslednika();
                break;
            case POSLEDNI:
                auto = SEZNAM_VYPUJC.zpristupniPosledni();
                break;
            case PREDCHUDCE:
                auto = SEZNAM_VYPUJC.zpristupniPredchudce();
                break;
            case PRVNI:
                auto = SEZNAM_VYPUJC.zpristupniPrvni();
                break;
            default:
                System.out.println("EEE");
        }
        return auto;
    }

    @Override
    public Iterator iterator(eTyp typ) {
        switch (typ) {
            case AUTA:
                String vyber=JOptionPane.showInputDialog("H/S");
                switch (vyber) {
                    case "h":
                        return SEZNAM_POBOCEK.zpristupniAktualni().iterator(eTypProhl.HLOUBKA);
                    case "s":
                        return SEZNAM_POBOCEK.zpristupniAktualni().iterator(eTypProhl.SIRKA);
                }

                
            case POBOCKY:
                return SEZNAM_POBOCEK.iterator();
            case VYPUJCENA_AUTA:
                return SEZNAM_VYPUJC.iterator();
            default:
                return null;
        }
    }

    @Override
    public void zrusPobocku() {
        SEZNAM_POBOCEK.zpristupniAktualni().zrus();
        SEZNAM_POBOCEK.odeberAktualni();
    }

    @Override
    public void zrus() {
        SEZNAM_POBOCEK.zrus();
    }

}
