/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tangosol.net.events.Event;
import com.tangosol.net.events.EventInterceptor;
import com.tangosol.net.events.annotation.Interceptor;
import com.tangosol.net.events.partition.TransferEvent;
import com.tangosol.net.events.partition.cache.EntryEvent;


/**
 *
 * @author venkat
 */

@Interceptor(
    identifier  = "MyInterceptor",
    entryEvents =
    {
        EntryEvent.Type.INSERTING, EntryEvent.Type.INSERTED, EntryEvent.Type.REMOVING, EntryEvent.Type.REMOVED,
        EntryEvent.Type.UPDATING, EntryEvent.Type.UPDATED
    },
    transferEvents = {TransferEvent.Type.ARRIVED, TransferEvent.Type.DEPARTING}
)

public class MyInterceptor  implements EventInterceptor
{

    public void onEvent(Event event)
    {
        if (event instanceof EntryEvent)
        {
            System.out.println("EntryEvent "+ event.toString());
        }
        else if (event instanceof TransferEvent)
        {
            System.out.println("TransferEvent "+ event.toString());
            
        }
    }
}
