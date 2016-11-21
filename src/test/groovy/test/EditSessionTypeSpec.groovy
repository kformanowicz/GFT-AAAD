package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.session.SessionDetailsPage
import page.sessionManagement.AddSessionPage
import page.sessionManagement.EditSessionPage
import spock.lang.Ignore

class EditSessionTypeSpec extends GebReportingSpec {

    def setupSpec() {
        CommonHelper.logInAsUser1()
    }

    def setup() {
    }

    def "As an admin I want to edit exam session in order to change maximum number of participants definition from ‘per exam session’"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15, ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski"],
                "GFT Poland1 Test", true)

        when: 'i am on the session details page and click edit session button'
        at SessionDetailsPage
        editSession()

        then: 'i am on the edit session page and change type'
        at EditSessionPage
        setTypeOfSpace("Dla produktu")
        sleep(1000)
        getSpaceForSessionFieldVisibility() == false
        saveForm(false)

        and:
        at SessionDetailsPage
        editSession()
        at EditSessionPage
        getSpaceForSessionFieldVisibility() == false
    }

    @Ignore
    def "As an admin I want to edit exam session in order to change maximum number of participants definition from ‘per exam type’"() {
        setup:
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage
        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", "Dla produktu", null, ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski"],
                "GFT Poland1 Test", true)

        when: 'i am on the session details page and click edit session button'
        at SessionDetailsPage
        editSession()

        then: 'i am on the edit session page and change type'
        at EditSessionPage
        setTypeOfSpace("Dla sesji")
        sleep(1000)
        getSpaceForSessionFieldVisibility() == true
        withConfirm(true) { saveForm(false) }

        and:
        at SessionDetailsPage
        editSession()
        at EditSessionPage
        getSpaceForSessionFieldVisibility() == true
    }
}
