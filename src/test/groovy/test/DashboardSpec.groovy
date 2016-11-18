package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.DashboardPage
import page.AddSessionPage


class DashboardSpec extends GebReportingSpec {

    def setupSpec() {
        println("spec setup")
        CommonHelper.logInAsUser1()
    }

    def setup() {
        println("setup")
        to DashboardPage
    }

    def "clicking on 'Dodaj sesję' button redirects to 'Add Session' page"() {
        when:
        addSession()

        then:
        at AddSessionPage
    }

    def "clicking on 'Dodaj zgłoszenie' redirects to homepage"() {
        when:
        register()

        then:
        $('h3').text().contains('Lorem Ipsum')

    }

}
