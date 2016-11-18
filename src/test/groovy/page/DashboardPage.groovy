package page

import geb.Page
import page.module.EventModule
import page.module.NavbarMenuModule
import page.module.NavbarModule

class DashboardPage extends Page {
    static url = "Dashboard/Index"

    static at = {
        title == "- ExamPlanner"
        $("#productList").displayed
        $("#calendar").displayed
    }

    static content = {
        addSessionButton { $("a.btn", href: "/gftpl/Session/AddSession") }
        registerButton { $("a.btn", href: "/gftpl") }
        todayButton { $("button", text: "Dziś") }
        registeredTable { $("#productList") }
        calendar { $("#calendar") }
        events { calendar.find(".fc-event-container .fc-event") }
        openedEvent { $(".popover-bottom").module(EventModule) }
        navbar { $(".Navigation-list").module(NavbarModule) }
        navbarMenu {$(".Navbar-menu").module(NavbarMenuModule)}
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

    void assertElementsDisplayed(){
        assert addSessionButton.displayed
        assert registerButton.displayed
        assert todayButton.displayed
        assert registeredTable.displayed
        assert calendar.displayed
    }
}

