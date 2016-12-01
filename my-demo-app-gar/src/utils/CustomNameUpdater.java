/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tangosol.coherence.rest.util.processor.ProcessorFactory;
import com.tangosol.util.InvocableMap;

/**
 *
 * @author venkat
 */
public class CustomNameUpdater extends NameUpdater implements ProcessorFactory {

          
    public CustomNameUpdater() {
        
    }
        
    public CustomNameUpdater(String sname){
        super(sname);   
             
    }
              
    public InvocableMap.EntryProcessor getProcessor(String... strings) {        
       
         return new CustomNameUpdater(strings[0]);
        
    }
    
}
