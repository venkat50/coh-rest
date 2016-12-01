/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.util.InvocableMap;
import com.tangosol.util.processor.AbstractProcessor;

import model.Person;

/**
 *
 * @author venkat
 */

@Portable
public class NameUpdater extends AbstractProcessor {

    @PortableProperty(0)
    private String name;    

  
    public NameUpdater() {
        
    }

    public NameUpdater(String m_name) {
        
        this.name = m_name;
       
    }

    //@Override
    public Object process(InvocableMap.Entry entry) {
        Person p = (Person) entry.getValue();              
                    
        p.setName(this.getName());
        entry.setValue(p);
     
        return p;
    }

    public String getName() {
        
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    } 
   

}
