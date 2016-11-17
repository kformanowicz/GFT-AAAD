package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.sessionPage.SessionPage

class SessionSpec extends GebReportingSpec {

    def setup() {
        CommonHelper.logInAsUser1()
    }

    def cleanup() {}

}