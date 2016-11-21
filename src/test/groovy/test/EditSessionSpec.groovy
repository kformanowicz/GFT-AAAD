package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.DashboardPage


class EditSessionSpec extends GebReportingSpec{

    def setupSpec(){
        CommonHelper.logInAsUser1()
    }

    def "I can go to edit session page from dashboard"() {
        when:
        to DashboardPage

        and:


        then:
        sleep(1)
    }
}
