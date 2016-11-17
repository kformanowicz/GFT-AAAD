package page

import geb.Page
import page.module.SidebarModule

class SessionPage extends Page{

    static content = {
        backToDashboardButton { $(".Backoffice-backButton", href: "/gftpl/Dashboard/Index") }
        deleteButton { $("button.js-session-delete") }
        sidebar { $(".Sidebar-left").module(SidebarModule) }
    }

    void goBackToDashboard() {
        backToDashboardButton.click()
    }

    void deleteSession() {
        deleteButton.click()
    }

    void goToExams() {
        sidebar.sessionExamsLink.click()
    }

    void goToDetails() {
        sidebar.sessionDetailsLink.click()
    }

    void goToNotes() {
        sidebar.sessionNotesLink.click()
    }

    void goToAttachments() {
        sidebar.sessionAttachmentsLink.click()
    }

    String getSessionStatus() {
        return sidebar.sessionStatus
    }

}
