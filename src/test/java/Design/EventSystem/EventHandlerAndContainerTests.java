package Design.EventSystem;

import Exams.Design.EventSystem.Event;
import Exams.Design.EventSystem.EventHandler;
import Exams.Design.EventSystem.EventsContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class EventHandlerAndContainerTests {
    private EventHandler eventHandler;
    private EventsContainer eventsContainer;

    @BeforeEach
    void setUp() {
        eventHandler = new EventHandler();
        eventsContainer = new EventsContainer();
    }

    static private class EventHandlerFunctionMock implements Consumer<Event> {
        private int numTimesCalled =0;
        private Event lastEventHandled = null;
        @Override
        public void accept(Event event) {
            numTimesCalled++;
            lastEventHandled=event;
        }
        public int numTimesCalled() {
            return numTimesCalled;
        }
        public Event lastEventHandled() {
            return lastEventHandled;
        }
    }

    @Test
    public void singleRegisterLight() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        var event = new Event("MockEvent", "MockData");
        assertTrue(eventHandler.handleEvent(event));
    }

    public void checkEventHandlerFunctionIsRegisteredComprehensive(Event event, EventHandlerFunctionMock functionMock) {
        assertTrue(eventHandler.handleEvent(event));
        assertEquals(1, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
    }

    @Test
    public void singleRegisterComprehensive() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        assertEquals(0, functionMock.numTimesCalled());
        var event = new Event("MockEvent", "MockData");
        checkEventHandlerFunctionIsRegisteredComprehensive(event, functionMock);
    }

    @Test
    public void multipleRegisterNoReplacementLight() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var event1 = new Event("MockEvent1", "MockData1");
        assertTrue(eventHandler.handleEvent(event1));
        var event2 = new Event("MockEvent2", "MockData2");
        assertTrue(eventHandler.handleEvent(event2));
        var event3 = new Event("MockEvent3", "MockData3");
        assertTrue(eventHandler.handleEvent(event3));
    }

    @Test
    public void multipleRegisterNoReplacementComprehensive() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        checkEventHandlerFunctionIsRegisteredComprehensive(event1, functionMock1);
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        checkEventHandlerFunctionIsRegisteredComprehensive(event2, functionMock2);
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        checkEventHandlerFunctionIsRegisteredComprehensive(event3, functionMock3);
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(1, functionMock2.numTimesCalled());
    }

    @Test
    public void multipleRegisterNoReplacementCase() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("mOCKeVENT1", functionMock3);

        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("mOCKeVENT1", "mOCKdATA1");
        checkEventHandlerFunctionIsRegisteredComprehensive(event1, functionMock1);
        checkEventHandlerFunctionIsRegisteredComprehensive(event2, functionMock2);
        checkEventHandlerFunctionIsRegisteredComprehensive(event3, functionMock3);
    }

    @Test
    public void multipleRegisterReplacementLight() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);

        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock1);
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock2);

        var event1 = new Event("MockEvent1", "MockData1");
        assertTrue(eventHandler.handleEvent(event1));
        var event2 = new Event("MockEvent2", "MockData2");
        assertTrue(eventHandler.handleEvent(event2));
        var event3 = new Event("MockEvent3", "MockData3");
        assertTrue(eventHandler.handleEvent(event3));
    }

    @Test
    public void multipleRegisterReplacementComprehensive() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());

        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock1);
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock2);
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());

        var event1 = new Event("MockEvent1", "MockData1");
        checkEventHandlerFunctionIsRegisteredComprehensive(event1, functionMock1);
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());

        var event2 = new Event("MockEvent2", "MockData2");
        assertTrue(eventHandler.handleEvent(event2));
        assertEquals(2, functionMock1.numTimesCalled());
        assertEquals(event2, functionMock1.lastEventHandled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());

        var event3 = new Event("MockEvent3", "MockData3");
        assertTrue(eventHandler.handleEvent(event3));
        assertEquals(2, functionMock1.numTimesCalled());
        assertEquals(event2, functionMock1.lastEventHandled());
        assertEquals(1, functionMock2.numTimesCalled());
        assertEquals(event3, functionMock2.lastEventHandled());
        assertEquals(0, functionMock3.numTimesCalled());
    }

    @Test
    public void singleUnregisterEmptyHandler() {
        assertEquals(null, eventHandler.unregisterEventHandlerFunction("MockEvent"));
    }

    @Test
    public void singleUnregisterNonExistingAfterRegisterLight() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock);
        assertEquals(null, eventHandler.unregisterEventHandlerFunction("MockEvent2"));
        var event1 = new Event("MockEvent1", "MockData1");
        assertTrue(eventHandler.handleEvent(event1));
    }

    @Test
    public void singleUnregisterNonExistingAfterRegisterComprehensive() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        assertEquals(null, eventHandler.unregisterEventHandlerFunction("MockEventX"));
        var event = new Event("MockEvent", "MockData");
        checkEventHandlerFunctionIsRegisteredComprehensive(event, functionMock);
    }

    @Test
    public void singleUnregisterExistingAfterRegisterLight() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        assertEquals(functionMock, eventHandler.unregisterEventHandlerFunction("MockEvent"));
        var event = new Event("MockEvent", "MockData");
        assertFalse(eventHandler.handleEvent(event));
    }

    @Test
    public void singleUnregisterExistingAfterRegisterComprehensive() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        assertEquals(functionMock, eventHandler.unregisterEventHandlerFunction("MockEvent"));
        assertEquals(0, functionMock.numTimesCalled());
        assertEquals(null, functionMock.lastEventHandled());

        var event = new Event("MockEvent", "MockData");
        assertFalse(eventHandler.handleEvent(event));
        assertEquals(0, functionMock.numTimesCalled());
        assertEquals(null, functionMock.lastEventHandled());
    }

    @Test
    public void multipleUnregisterExistingLight() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var functionMock4 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent4", functionMock4);
        assertEquals(functionMock1, eventHandler.unregisterEventHandlerFunction("MockEvent1"));
        assertEquals(functionMock3, eventHandler.unregisterEventHandlerFunction("MockEvent3"));
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        var event4 = new Event("MockEvent4", "MockData4");
        assertTrue(eventHandler.handleEvent(event4));
        assertFalse(eventHandler.handleEvent(event3));
        assertTrue(eventHandler.handleEvent(event2));
        assertFalse(eventHandler.handleEvent(event1));
    }

    @Test
    public void multipleUnregisterExistingComprehensive() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var functionMock4 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent4", functionMock4);
        assertEquals(functionMock1, eventHandler.unregisterEventHandlerFunction("MockEvent1"));
        assertEquals(functionMock3, eventHandler.unregisterEventHandlerFunction("MockEvent3"));
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        var event4 = new Event("MockEvent4", "MockData4");

        checkEventHandlerFunctionIsRegisteredComprehensive(event4, functionMock4);
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());

        assertFalse(eventHandler.handleEvent(event3));
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        assertEquals(1, functionMock4.numTimesCalled());

        checkEventHandlerFunctionIsRegisteredComprehensive(event2, functionMock2);
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        assertEquals(1, functionMock4.numTimesCalled());

        assertFalse(eventHandler.handleEvent(event1));
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(1, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
        assertEquals(1, functionMock4.numTimesCalled());
    }


    @Test
    void handleEventEmptyHandler() {
        var event = new Event("MockEvent", "MockData");
        assertFalse(eventHandler.handleEvent(event));
    }

    @Test
    void handleSameEventRepeteadlyLight() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        var event = new Event("MockEvent", "MockData");
        assertTrue(eventHandler.handleEvent(event));
        assertTrue(eventHandler.handleEvent(event));
        assertTrue(eventHandler.handleEvent(event));
        assertTrue(eventHandler.handleEvent(event));
    }

    @Test
    void handleSameEventRepeteadlyComprehensive() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        var event = new Event("MockEvent", "MockData");
        assertTrue(eventHandler.handleEvent(event));
        assertEquals(1, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
        assertTrue(eventHandler.handleEvent(event));
        assertEquals(2, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
        assertTrue(eventHandler.handleEvent(event));
        assertEquals(3, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
        assertTrue(eventHandler.handleEvent(event));
        assertEquals(4, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
    }

    @Test
    void handleAlternatingEventsRepeteadlyLight() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var event1 = new Event("MockEvent1", "MockData");
        var event2 = new Event("MockEvent2", "MockData");
        var event3 = new Event("MockEvent3", "MockData");
        assertTrue(eventHandler.handleEvent(event1));
        assertTrue(eventHandler.handleEvent(event2));
        assertFalse(eventHandler.handleEvent(event3));
        assertTrue(eventHandler.handleEvent(event2));
        assertTrue(eventHandler.handleEvent(event1));
    }

    @Test
    void handleAlternatingEventsRepeteadlyComprehensive() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var event1 = new Event("MockEvent1", "MockData");
        var event2 = new Event("MockEvent2", "MockData");
        var event3 = new Event("MockEvent3", "MockData");
        assertTrue(eventHandler.handleEvent(event1));
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(event1, functionMock1.lastEventHandled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertTrue(eventHandler.handleEvent(event2));
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(1, functionMock2.numTimesCalled());
        assertFalse(eventHandler.handleEvent(event3));
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(1, functionMock2.numTimesCalled());
        assertTrue(eventHandler.handleEvent(event2));
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(2, functionMock2.numTimesCalled());
        assertTrue(eventHandler.handleEvent(event1));
        assertEquals(2, functionMock1.numTimesCalled());
        assertEquals(2, functionMock2.numTimesCalled());
    }

    @Test
    void extractEventEmptyContainer() {
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void addAndextractSingleEvent() {
        var event = new Event("MockEvent", "MockData");
        eventsContainer.addEvent(event);
        assertEquals(event, eventsContainer.extractEvent());
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void addAndextractSeveralEventsOrderOblivious() {
        var event = new Event("MockEvent", "MockData");
        eventsContainer.addEvent(event);
        eventsContainer.addEvent(event);
        eventsContainer.addEvent(event);
        assertEquals(event, eventsContainer.extractEvent());
        assertEquals(event, eventsContainer.extractEvent());
        assertEquals(event, eventsContainer.extractEvent());
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void addAndextractSeveralEventsOrderAware() {
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);
        eventsContainer.addEvent(event3);
        assertEquals(event1, eventsContainer.extractEvent());
        assertEquals(event2, eventsContainer.extractEvent());
        assertEquals(event3, eventsContainer.extractEvent());
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void handleEventsEmptyContainerAndHandler() {
        assertTrue(eventsContainer.handleEvents(eventHandler).isEmpty());
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void handleEventsEmptyHandler() {
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);
        eventsContainer.addEvent(event3);
        assertTrue(eventsContainer.handleEvents(eventHandler).isEmpty());
        eventsContainer.extractEvent();
        eventsContainer.extractEvent();
        eventsContainer.extractEvent();
        assertEquals(null, eventsContainer.extractEvent());
    }

    @Test
    void handleEventsEmptyContainer() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        assertTrue(eventsContainer.handleEvents(eventHandler).isEmpty());
    }

    @Test
    void SingleEventContainerThatCanBeHandled() {
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        var event = new Event("MockEvent", "MockData");
        eventsContainer.addEvent(event);
        var handledEvents = eventsContainer.handleEvents(eventHandler);
        assertEquals(1, handledEvents.size());
        assertEquals(event, handledEvents.get(0));
        assertEquals(1, functionMock.numTimesCalled());
        assertEquals(event, functionMock.lastEventHandled());
    }

    @Test
    void noEventsCanBeHandled() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock2 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent2", functionMock2);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var event1 = new Event("MockEventA", "MockData1");
        var event2 = new Event("MockEventB", "MockData2");
        var event3 = new Event("MockEventC", "MockData3");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);
        eventsContainer.addEvent(event3);
        assertTrue(eventsContainer.handleEvents(eventHandler).isEmpty());
        eventsContainer.extractEvent();
        eventsContainer.extractEvent();
        eventsContainer.extractEvent();
        assertEquals(null, eventsContainer.extractEvent());
        assertEquals(0, functionMock1.numTimesCalled());
        assertEquals(0, functionMock2.numTimesCalled());
        assertEquals(0, functionMock3.numTimesCalled());
    }

    @Test
    void AllEventsCanBeHandled() {
        var event = new Event("MockEvent", "MockData");
        var functionMock = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent", functionMock);
        eventsContainer.addEvent(event);
        eventsContainer.addEvent(event);
        eventsContainer.addEvent(event);
        var handledEvents = eventsContainer.handleEvents(eventHandler);
        assertEquals(null, eventsContainer.extractEvent());
        assertEquals(3, handledEvents.size());
        for (var e : handledEvents) {
            assertEquals(event, e);
        }
        assertEquals(3, functionMock.numTimesCalled());
    }

    @Test
    void PartialEventsCanBeHandledOrderOblivious1() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);

        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);

        var handledEvents = eventsContainer.handleEvents(eventHandler);
        assertEquals(2, handledEvents.size());

        for (var e : handledEvents) {
            assertEquals(event1, e);
        }
        assertEquals(event2, eventsContainer.extractEvent());
        assertEquals(event2, eventsContainer.extractEvent());
        assertEquals(null, eventsContainer.extractEvent());
        assertEquals(2, functionMock1.numTimesCalled());
        assertEquals(event1, functionMock1.lastEventHandled());
    }

    @Test
    void PartialEventsCanBeHandledOrderOblivious2() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        var event4 = new Event("MockEvent4", "MockData3");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);
        eventsContainer.addEvent(event3);
        eventsContainer.addEvent(event4);
        var handledEvents = eventsContainer.handleEvents(eventHandler);
        assertEquals(2, handledEvents.size());
        for (var e : handledEvents) {
            assertTrue(e.kind().equals("MockEvent1") || e.kind().equals("MockEvent3") );
        }
        var e1 = eventsContainer.extractEvent();
        assertTrue(e1.kind().equals("MockEvent2") || e1.kind().equals("MockEvent4") );
        var e2 = eventsContainer.extractEvent();
        assertTrue(e2.kind().equals("MockEvent2") || e2.kind().equals("MockEvent4") );
        assertEquals(null, eventsContainer.extractEvent());
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(event1, functionMock1.lastEventHandled());
        assertEquals(1, functionMock3.numTimesCalled());
        assertEquals(event3, functionMock3.lastEventHandled());
    }

    @Test
    void PartialEventsCanBeHandledOrderAware1() {
        var functionMock1 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent1", functionMock1);
        var functionMock3 = new EventHandlerFunctionMock();
        eventHandler.registerEventHandlerFunction("MockEvent3", functionMock3);
        var event1 = new Event("MockEvent1", "MockData1");
        var event2 = new Event("MockEvent2", "MockData2");
        var event3 = new Event("MockEvent3", "MockData3");
        var event4 = new Event("MockEvent4", "MockData3");
        eventsContainer.addEvent(event1);
        eventsContainer.addEvent(event2);
        eventsContainer.addEvent(event3);
        eventsContainer.addEvent(event4);
        var handledEvents = eventsContainer.handleEvents(eventHandler);
        assertEquals(2, handledEvents.size());
        for (var e : handledEvents) {
            assertTrue(e.kind().equals("MockEvent1") || e.kind().equals("MockEvent3") );
        }
        var e1 = eventsContainer.extractEvent();
        assertTrue(e1.kind().equals("MockEvent2") );
        var e2 = eventsContainer.extractEvent();
        assertTrue(e2.kind().equals("MockEvent4") );
        assertEquals(null, eventsContainer.extractEvent());
        assertEquals(1, functionMock1.numTimesCalled());
        assertEquals(event1, functionMock1.lastEventHandled());
        assertEquals(1, functionMock3.numTimesCalled());
        assertEquals(event3, functionMock3.lastEventHandled());
    }

    @Test
    void PartialEventsCanBeHandledOrderAware2() {
        int [] i = {0};

        eventHandler.registerEventHandlerFunction("ADD", (x)->{i[0]+=Integer.parseInt(x.data());} );
        eventHandler.registerEventHandlerFunction("MULTIPLY", (x)->{i[0]=i[0]*Integer.parseInt(x.data());} );

        eventsContainer.addEvent(new Event("ADD", "2"));
        eventsContainer.addEvent(new Event("MULTIPLY", "4"));
        eventsContainer.addEvent(new Event("NON_REGISTERED1", "DATA"));
        eventsContainer.addEvent(new Event("MULTIPLY", "4"));
        eventsContainer.addEvent(new Event("NON_REGISTERED2", "DATA"));

        eventsContainer.handleEvents(eventHandler);

        assertEquals(32, i[0]);
        var event1 = eventsContainer.extractEvent();
        assertEquals("NON_REGISTERED1", event1.kind());
        var event2 = eventsContainer.extractEvent();
        assertEquals("NON_REGISTERED2", event2.kind());
        assertEquals(null, eventsContainer.extractEvent());
    }
    @Test
    void EventHandlerTest() {
        int [] i = {0};
        var eventHandler = new EventHandler();
        eventHandler.registerEventHandlerFunction("ADD", (x)->{i[0]+=Integer.parseInt(x.data());} );

        assertTrue(eventHandler.handleEvent(new Event("ADD", "1")));
        Assertions.assertFalse(eventHandler.handleEvent(new Event("NON_REGISTERED", "X")));
        assertEquals(1, i[0]);

        var plusOneEventHandlerFunction = eventHandler.unregisterEventHandlerFunction("ADD");
        Assertions.assertFalse(eventHandler.handleEvent(new Event("ADD", "1")));

        eventHandler.registerEventHandlerFunction("ADD_AGAIN", plusOneEventHandlerFunction);
        assertTrue(eventHandler.handleEvent(new Event("ADD_AGAIN", "1")));
        assertEquals(2, i[0]);
    }

    @Test
    void EventsContainerTest() {
        int [] i = {0};

        var eventHandler = new EventHandler();
        eventHandler.registerEventHandlerFunction("ADD", (x)->{i[0]+=Integer.parseInt(x.data());} );
        eventHandler.registerEventHandlerFunction("MULTIPLY", (x)->{i[0]=i[0]*Integer.parseInt(x.data());} );

        var eventsContainer = new EventsContainer();
        eventsContainer.addEvent(new Event("ADD", "1"));
        eventsContainer.addEvent(new Event("MULTIPLY", "3"));
        eventsContainer.addEvent(new Event("NON_REGISTERED", "DATA"));
        eventsContainer.addEvent(new Event("MULTIPLY", "3"));

        eventsContainer.handleEvents(eventHandler);

        assertEquals(9, i[0]);
        var event1 = eventsContainer.extractEvent();
        assertEquals("NON_REGISTERED", event1.kind());
        var event2 = eventsContainer.extractEvent();
        assertNull(event2);
    }
}

