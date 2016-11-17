package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.DashboardPage
import page.sessionPage.SessionPage

class SessionSpec extends GebReportingSpec {

    def setup() {
        CommonHelper.logInAsUser1()
    }

    def cleanup() {}

    def "Should be able to create an exam session for one product" () {
        setup:
        def calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)
        def Date date = calendar.getTime()
        def city = "Szczecin"
        to SessionPage

        when:
        createSessionForOneProductDateCity(date, city)

        then:
        assert page instanceof DashboardPage
        assert isSessionEntryPresent(date, city)
    }
}