package page

import geb.Page
import page.module.individualRegistrationPage.BaseDataFormModule
import page.module.individualRegistrationPage.CertificateDataFormModule
import page.module.individualRegistrationPage.CompleteRegistrationModule
import page.module.individualRegistrationPage.ContactDataFormModule


class IndividualRegistrationPage extends Page {

    static at = {
        title == "- ExamPlanner"
    }

    static content = {
        getRegistrationImageVisibility { $(".Register-image").displayed }
        getRegistrationTitle { $(".Register-header h3").text() }

        getCalendarIconVisibility { $(".Register-headerItem .icomoon-calendar").displayed }
        getDate { $(".Register-headerItem .Register-info.u-hasRightPadding").text() }
        getClockIconVisibility { $(".Register-headerItem .icomoon-clock").displayed }
        getTime { $(".Register-headerItem", 2).children("span").text() }
        getRegisteredLevel { $(".Register-RightColumn .Register-info.u-isLight").text() }
        getRegisteredCourse { $(".Register-RightColumn h6 span > .Register-info", 2).text() }

        baseDataForm { $(".Register-content.clearfix").module(BaseDataFormModule) }
        contactDataForm { $(".Register-content.clearfix").module(ContactDataFormModule) }
        certificateDataForm { $(".Register-content.clearfix").module(CertificateDataFormModule) }
        completeRegistrationPage {$(".col-sm-offset-1 > .text-center").module(CompleteRegistrationModule)}
    }

}
