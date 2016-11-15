package page

import geb.Page
import page.module.EventModule
import page.module.NavbarModule

class DashboardPage extends Page {
    static url = "Dashboard/Index"

    static at = {
        title == "- ExamPlanner"
        registeredTable.displayed
        calendar.displayed
    }

    static content = {
        addSessionButton { $("a", href: "/gftpl/Session/AddSession") }
        registerButton { $("a", href: "/gftpl") }
        todayButton { $("button", text: "Dziś") }
        registeredTable { $("table") }
        calendar { $("#calendar") }
        events { calendar.find(".fc-event-container .fc-event") }
        openedEvent { $(".popover-bottom").module(EventModule) }
        navbar { $(".Navigation-list").module(NavbarModule) }
    }

    void addSession() {
        addSessionButton.click()
    }

    void register() {
        registerButton.click()
    }

    void openEvent(eventTitle) {
        def event = events.find { it.text() == eventTitle }
        event.click()
    }
}

