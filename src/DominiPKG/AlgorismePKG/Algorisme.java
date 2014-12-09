package DominiPKG.AlgorismePKG;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DominiPKG.GrafPKG.Graf;

import java.util.HashMap;

/**
 *
 * @author Federico Buldin
 */
public interface Algorisme {
    public abstract int trobaCami(Graf G, HashMap Path);
}