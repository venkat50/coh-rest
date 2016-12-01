/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Set;


import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

/**
 *
 * @author venkat
 */
@Portable
public class MyAgent extends AbstractInvocable {

    public void run() {
    	
    		InvocationService myservice = (InvocationService) CacheFactory.getService("MyService");
    		Set<Member> workerMembers = myservice.getInfo().getServiceMembers();
            System.out.println(CacheFactory.getCluster().getClusterName());
            workerMembers.forEach(m -> System.out.println(m.getRoleName()));
    }
    
    
}
