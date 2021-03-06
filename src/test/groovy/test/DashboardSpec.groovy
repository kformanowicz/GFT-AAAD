package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.DashboardPage
import page.sessionManagement.AddSessionPage
import page.MyProfilePage
import spock.lang.Ignore


class DashboardSpec extends GebReportingSpec {

    def setupSpec() {
        CommonHelper.logInAsUser1()
    }

    def setup() {
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

    def "It's possible to open 'Mój profil' page"(){
        when:
        navbarMenu.goToMyProfile()

        then:
        at MyProfilePage
    }

    @Ignore
    def "It's possible to delete an exam session scheduled for next month"(){
        when:
        sleep(1)

        then:
        sleep(1)
    }

}
