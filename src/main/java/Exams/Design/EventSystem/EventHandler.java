package Exams.Design.EventSystem;


import java.util.HashMap;
import java.util.HashSet;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a dispatcher for functions that handle different
 * event kinds. Event kinds are uniquely identified by
 * their name. A function that handles an event kind implements
 * the Consumer<Event> interface from the Java standard
 * library, where Event is a record defined above.
 *
 * You MUST NOT change the signatures of the existing methods in the class.
 * You MUST implement the class methods below.
 */
public class EventHandler {
    //FIXME add members
    HashMap<String,Consumer> hm =new HashMap<>();
    /**
     * Associates an event kind to an event handler function. If the
     * event kind is already associated with an event handler function,
     * the existing event handler function is replaced by the
     * one provided in this method.
     *
     * @param eventKind The kind of the event for which we want
     *                  to register an event handler function
     * @param handlerFunction The function than handles events of this kind
     */
    public void registerEventHandlerFunction(String eventKind, Consumer<Event> handlerFunction) {
        // FIXME complete method
        if(hm.containsKey(eventKind)){
            hm.replace(eventKind,handlerFunction);
        }else{
            hm.put(eventKind,handlerFunction);
        }

    }

    /**
     * Removes the association among an event kind and an event handler function.
     * If the event kind is already associated with an event handler
     * function, then the existing event handler function is returned by the
     * method. Otherwise, the method returns null.
     *
     * @param eventKind The kind of the event for which we want
     *                  to unregister an event handler function
     * @return The event handler function that was in charge of handling
     *         the event kind, or null if there was no event handler function
     *         associated to the event kind.
     */
    public Consumer<Event> unregisterEventHandlerFunction(String eventKind) {
         // FIXME complete method
        return hm.remove(eventKind);
    }

    /**
     * Given an event, tries to dispatch the event to the event handler function associated with
     * the event's kind.
     * If the event kind is associated to an event handler function, that function is called with
     * the event as an argument, and handleEvent returns true. Otherwise, nothing happens, and
     * handleEvent returns false.
     *
     * @param event The event for which we want to trigger an event handler function.
     * @return true if there is an event handler function currently associated to the event, and
     *         false otherwise
     */
    public boolean handleEvent(Event event) {
         // FIXME complete method
            if(hm.containsKey(event.kind())){
                Consumer<Event> c = hm.get(event.kind());
                c.accept(event);
                return true;
            }
            return false;
    }
}

