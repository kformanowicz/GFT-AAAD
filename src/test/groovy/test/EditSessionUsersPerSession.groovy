package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import page.session.SessionDetailsPage
import page.sessionManagement.AddSessionPage
import page.sessionManagement.EditSessionPage
import spock.lang.Unroll


class EditSessionUsersPerSession extends GebReportingSpec{

    def setup(){
        GregorianCalendar calendar = new GregorianCalendar()
        calendar.set(2016, 10, 30)  //results in setting date to 2016-11-30
        Date date = calendar.getTime()
        String city = CommonHelper.getRandomCity()
        to AddSessionPage

        handleForm(date, "11-222", city, "ul. Degrengolady 4", "", null, 15, ["Zaawansowany"],
                ["ISTQB Advanced Level Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Technical Test Analyst / Polski, Angielski",
                 "ISTQB Advanced Level Test Manager / Polski, Angielski"],
                "GFT Poland1 Test", true)

        assert browser.at(SessionDetailsPage)
    }


    @Unroll
    def "I can edit number of free spaces per session"(){
        when:
        editSession()
        waitFor {browser.at(EditSessionPage)}

        and:
        setAmountOfSpace(newUsers)
        saveForm(true)

        then:
        getSpaces == newUsers.toString()

        where:
        newUsers << [0, 20, 30, 100, 500, 999]

    }

    def "I can edit number of free spaces per session to 0, and the edit the session again"(){
        given:
        editSession()
        waitFor {browser.at(EditSessionPage)}
        setAmountOfSpace(0)
        saveForm(true)

        when:
        editSession()
        waitFor {browser.at(EditSessionPage)}

        then:
        spaceForSession.enabled
    }

    def 'I cant edit number of free spaces per session to over 999'(){
        when:
        editSession()
        waitFor {browser.at(EditSessionPage)}

        and:
        setAmountOfSpace(1000)
        saveForm(true)

        then:
        $("label.text-danger").text() == "Wymagana jest liczba całkowita z zakresu 0-999"
    }
}
