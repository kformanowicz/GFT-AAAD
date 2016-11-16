package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.LoginPage
import page.sessionPage.SessionPage

class SessionSpec extends GebReportingSpec {

    def setupSpec() {
        CommonHelper.logInAsUser1()
    }

    def cleanupSpec() {

    }

//    def "Should be visible all field in form and correct titles"() {
//        when:
//        to SessionPage
//
//        then:
////        assertAllFieldsVisibleInForm()
//        assertAllTitlesAreCorrect()
//    }

    def "Create new valid session"() {
        when:
        to SessionPage

        then:
        sleep(1000)
    }

}