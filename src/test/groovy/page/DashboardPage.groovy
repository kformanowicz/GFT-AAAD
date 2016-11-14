package page

import geb.Page
import page.module.NavbarModule

class DashboardPage extends Page {
    static url = "Dashboard/Index"

    static at = {
        title == "- ExamPlanner"
        registeredTable.displayed
        calendar.displayed
    }

    static content = {
        addSessionButton { $("a", text: "Dodaj sesję") }
        registerButton { $("a", text: "Dodaj zgłoszenie") }
        todayButton { $("button", text: "Dziś") }
        registeredTable { $("table") }
        calendar { $("#calendar") }
        navbar {$(".Navigation-list").module(NavbarModule)}
    }

    void addSession() {
        addSessionButton.click()
    }

    void register() {
        registerButton.click()
    }
}

