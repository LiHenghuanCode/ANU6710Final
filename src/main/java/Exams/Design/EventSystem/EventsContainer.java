package Exams.Design.EventSystem;

import java.util.List;

/**
 * Represents an ordered container of Events stored for future handling.
 * Events are kept in the order in which they were added.
 * The container itself does not handle events; it relies on an EventHandler
 * to dispatch the events in the container to event handlers corresponding
 * to the right event kind.
 *
 * You MUST NOT change the signatures of the existing methods in the class.
 * You MUST implement the class methods below.
 */
public class EventsContainer {
    //FIXME add members

    /**
     * Adds a new Event at the tail of the container.
     *
     * @param event The event to be added to the container
     */
    public void addEvent(Event event) {
        // FIXME complete method
    }

    /**
     * Extracts (removes) an Event from the head of the container.
     * If the container is empty, it returns null.
     *
     * @return event The event at the head of the container, or null
     *               if the container is empty
     */
    public Event extractEvent() {
        return null; // FIXME complete method
    }

    /**
     * Tries to use the given event handler to handle as many events in the
     * container as possible, by asking it to dispatch each event in the container
     * (in the order in which they were added to the container) to a handler function
     * corresponding to the event's kind.
     * Each event that can be handled in this way is removed from the container,
     * while any events for which no handling function is registered, will remain,
     * preserving the existing order of the remaining events.
     * Returns a list with the events that were handled.
     *
     * @param eventHandler An event handler
     * @return A list with the events that were handled (and thus extracted
     *         from the container). If no events were handled, returns an empty
     *         list.
     */
    public List<Event> handleEvents(EventHandler eventHandler) {
        return null; // FIXME complete method
    }
}

