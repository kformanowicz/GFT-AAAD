package page

import geb.Page
import page.module.dashboardPage.EventModule
import page.module.common.NavbarMenuModule
import page.module.common.NavbarModule

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
        navbarMenu { $(".Navbar-menu").module(NavbarMenuModule) }
        nextMonthButton { $(".fc-next-button") }
        previousMonthButton { $(".fc-prev-button") }
        currentMonth {$("h2").text()}
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

    boolean isSessionEntryPresent(date, city) {
        //TODO: placeholder, to be implemented
        return true;
    }

    void assertElementsDisplayed() {
        assert addSessionButton.displayed
        assert registerButton.displayed
        assert todayButton.displayed
        assert registeredTable.displayed
        assert calendar.displayed
    }

    void goToNextMonth() {
        nextMonthButton.click()
    }

    void goToPreviousMonth() {
        previousMonthButton.click()
    }

    void goToToday() {
        todayButton.click()
    }

    String getCurrentMonth(){
        return currentMonth
    }
}