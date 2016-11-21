package page.session

import geb.Page
import page.module.sessionPage.SidebarModule

class SessionPage extends Page{

    static content = {
        backToDashboardButton { $(".Backoffice-backButton", href: "/gftpl/Dashboard/Index") }
        deleteButton { $("button.js-session-delete") }
        sidebar { $(".Sidebar-left").module(SidebarModule) }
        activateSessionInput {$("input.js-popover", 0)}
    }

    def goBackToDashboard() {
        backToDashboardButton.click()
    }

    def deleteSession() {
        deleteButton.click()
    }

    def goToExams() {
        sidebar.sessionExamsLink.click()
    }

    def goToDetails() {
        sidebar.sessionDetailsLink.click()
    }

    def goToNotes() {
        sidebar.sessionNotesLink.click()
    }

    def goToAttachments() {
        sidebar.sessionAttachmentsLink.click()
    }

    String getSessionStatus() {
        return sidebar.sessionStatus
    }

    def activateSession(){
        withConfirm(true) {activateSessionInput.click()}
    }

}
