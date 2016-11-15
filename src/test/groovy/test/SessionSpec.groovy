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

    def "Should be visible all field in form and correct titles"() {
        when:
        to SessionPage

        then:
        assertAllFieldsVisibleInForm()
        assertAllTitlesAreCorrect()
    }

    def "Create new valid session"() {
        when:
        to SessionPage

        then:
        sleep(1000)
    }

}