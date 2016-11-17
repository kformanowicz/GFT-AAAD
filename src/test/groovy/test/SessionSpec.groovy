package test

import geb.spock.GebReportingSpec
import helper.CommonHelper

class SessionSpec extends GebReportingSpec {

    def setup() {
        CommonHelper.logInAsUser1()
    }

    def cleanup() {}

}