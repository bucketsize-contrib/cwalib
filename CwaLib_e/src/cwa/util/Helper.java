/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cwa.util;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author JA34916
 */
public class Helper {
   public static int max(Collection<Integer> c){
       Iterator<Integer> ci = c.iterator();
       int max = 0;
       while(ci.hasNext()){
           int e = (int) ci.next();
           if (e > max)
               max = e;
       }
       return max;
   }
}
