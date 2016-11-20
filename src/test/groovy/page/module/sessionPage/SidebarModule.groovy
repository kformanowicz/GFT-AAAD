package page.module.sessionPage

import geb.Module


class SidebarModule extends Module{
    static content = {
        sessionStatus {$(".js-session-status").text()}
        sessionDetailsLink {$("#sidebarItem-SessionDetails")}
        sessionExamsLink {$("#sidebarItem-SessionExams")}
        sessionNotesLink {$("#sidebarItem-SessionNotes")}
        sessionAttachmentsLink {$("#sidebarIte-SessionAttachments")}
    }
}
