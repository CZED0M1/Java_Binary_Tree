/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVS;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author D0M1
 * @param <K>
 */
public class AbstrTable<K extends Comparable<K>, V> implements ITable<K, V>, Serializable {

    private int velikost;
    private Uzel start;

    public AbstrTable() {
        this.velikost = 0;
        this.start = null;
    }

    public class Uzel {

        private K key;
        private V data;
        private Uzel left;
        private Uzel right;

        public Uzel(K key, V data, Uzel left, Uzel right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Uzel(K key, V data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public void zrus() {
        velikost = 0;
        start = null;
    }

    @Override
    public boolean jePrazdny() {
        return velikost == 0;
    }

    @Override
    public V najdi(K key) {
        if (start == null) {
            System.out.println("E");
            return null;
        } else {
            Uzel aktual = start;
            while (aktual != null) {
                if (key.compareTo(aktual.key) == 0) {
                    return aktual.data;
                } else if (key.compareTo(aktual.key) < 0) {
                    aktual = aktual.left;
                } else {
                    aktual = aktual.right;
                }
            }
            System.out.println("Není");
            return null;
        }
    }

    @Override
    public void vloz(K key, V value) {
        Uzel novy = new Uzel(key, value);
        if (start == null) {
            start = novy;
            velikost++;
            return;
        }

        Uzel aktualni = start;
        Uzel rodic;
        boolean vlozeno = false;
        while (!vlozeno) {
            rodic = aktualni;
            if (key.compareTo(aktualni.key) < 0) {
                System.out.println(aktualni.key);
                System.out.println(key.compareTo(aktualni.key));
                aktualni = aktualni.left;
                if (aktualni == null) {
                    System.out.println("left");
                    rodic.left = novy;
                    velikost++;
                    vlozeno = true;
                }
            } else if (key.compareTo(aktualni.key) > 0) {
                System.out.println(key.compareTo(aktualni.key));
                aktualni = aktualni.right;
                if (aktualni == null) {
                    System.out.println("right");
                    rodic.right = novy;
                    velikost++;
                    vlozeno = true;
                }
            } else {
                System.out.println("already In");
                vlozeno = true;
            }
        }
    }

    @Override
    public V odeber(K key) {
        Uzel rodic = start;
        Uzel aktual = start;
        //true = je to levá větev| false = je to pravá větev
        boolean isLeft = false;

        //nastaví aktuální na key
        while (aktual.key.compareTo(key) != 0) {
            rodic = aktual;
            if (key.compareTo(aktual.key) < 0) {
                isLeft = true;
                aktual = aktual.left;
            } else {
                isLeft = false;
                aktual = aktual.right;
            }
            if (aktual == null) {
                return null;
            }
        }

        //když nemá left ani right
        if (aktual.left == null && aktual.right == null) {
            if (aktual == start) {
                start = null;
            }
            if (isLeft) {
                rodic.left = null;
            } else {
                rodic.right = null;
            }
            //nemá right
        } else if (aktual.right == null) {
            if (aktual == start) {
                start = aktual.left;
            } else if (isLeft) {
                rodic.left = aktual.left;
            } else {
                rodic.right = aktual.left;
            }
            //nemá left
        } else if (aktual.left == null) {
            if (aktual == start) {
                start = aktual.right;
            } else if (isLeft) {
                rodic.left = aktual.right;
            } else {
                rodic.right = aktual.right;
            }
            //má oboje
        } else if (aktual.left != null && aktual.right != null) {
            Uzel dalsi = getNext(aktual);
            if (aktual == start) {
                start = dalsi;
            } else if (isLeft) {
                rodic.left = dalsi;
            } else {
                rodic.right = dalsi;
            }
            dalsi.left = aktual.left;
        }
        return aktual.data;

    }
        public Uzel getNext(Uzel uzel) {
        Uzel next = null;
        Uzel nextPar = null;
        Uzel aktualni = uzel.right;
        while (aktualni != null) {
            nextPar = next;
            next = aktualni;
            aktualni = aktualni.left;
        }

        if (next != uzel.right) {
            nextPar.left = next.right;
            next.right = uzel.right;
        }
        return next;
    }

    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        if (start == null) {
            return null;
        }

        switch (typ) {
            case HLOUBKA:
                return new Iterator<V>() {

                    AbstrLIFO<Uzel> zasobnik = new AbstrLIFO<>();

                    private void vlozVlevo(Uzel uzel) {
                        while (uzel != null) {
                            zasobnik.vloz(uzel);
                            uzel = uzel.left;
                        }
                    }

                    {
                        vlozVlevo(start);
                    }

                    @Override
                    public boolean hasNext() {
                        return !zasobnik.jePrazdny();
                    }

                    @Override
                    public V next() {

                        Uzel uzel = zasobnik.odeber();
                        vlozVlevo(uzel.right);
                        return uzel.data;

                    }
                };

            case SIRKA:
                return new Iterator<V>() {

                    AbstrFIFO<Uzel> fronta = new AbstrFIFO<>();

                    {
                        fronta.vloz(start);
                    }

                    @Override
                    public boolean hasNext() {
                        return !fronta.jePrazdny();
                    }

                    @Override
                    public V next() {
                        Uzel uzel = fronta.odeber();
                        if (uzel.left != null) {
                            System.out.println("L");
                            System.out.println(uzel.left);
                            fronta.vloz(uzel.left);
                        }
                        if (uzel.right != null) {
                            System.out.println("R");
                            System.out.println(uzel.right);
                            fronta.vloz(uzel.right);
                        }

                        return uzel.data;
                    }
                };
            default:
                return null;
        }
    }



}
