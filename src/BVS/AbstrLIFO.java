/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BVS;

import java.util.Iterator;
import kolekce.AbstrDoubleList;

/**
 *
 * @author D0M1
 */
public class AbstrLIFO<T> implements ILIFOFIFO<T>{

    AbstrDoubleList<T> list = new AbstrDoubleList<>();
    
    @Override
    public void zrus() {
        list.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        list.vlozPosledni(data);
    }

    @Override
    public T odeber() {
        return list.odeberPosledni();
    }

    @Override
    public Iterator vytvorIterator() {
        return list.iterator();
    }

    
}
