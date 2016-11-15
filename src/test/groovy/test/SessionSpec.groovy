package test

import geb.spock.GebReportingSpec
import page.LoginPage
import page.sessionPage.SessionPage

class SessionSpec extends GebReportingSpec {

    def setupSpec() {
        to LoginPage
        makeLogin("gft.poland1@pgs-soft.com", "9dbiUZbDif")
    }

    def cleanupSpec() {

    }

    def "Correct form title is displayed"() {
        when:
        to SessionPage

        then:
        assert sessionFormTitle == "Dodaj Sesję"
    }

    def "Create new valid session"() {
        when:
        to SessionPage

        then:
        sleep(1000)
    }

}