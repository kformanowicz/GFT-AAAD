package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import helper.GroupRegistrationExamForm
import page.ExamPlannerHomePage
import page.IndividualRegistrationPage
import page.registration.GroupRegistrationCertificateData
import page.registration.GroupRegistrationContactData
import page.registration.GroupRegistrationPageUsers
import page.registration.RegistrationCompletePage


class IndividualRegistration extends GebReportingSpec {

    def appData = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

    def setup() {
    }

    def cleanup() {
    }

    def "As a user I want to register to exam in order to participate in it (Foundation certificate)"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose individual registration"
        registerIndividual("30 listopada 2016, Sopot", "ISTQB Foundation Level")

        then: "i see individual registration form"
        at IndividualRegistrationPage

        and: "i see all common elements"
        waitFor { getRegistrationImageVisibility }
        getRegistrationImageVisibility == true
        getRegistrationTitle == appData.individualRegistration.formTitle
        getCalendarIconVisibility == true
        getClockIconVisibility == true

        and: "i fill registration form"
        baseDataForm.fillBaseData("Polski", "elektroniczna")
        contactDataForm.fillContactData("name", "surname", CommonHelper.getRandomEmail(), "123123123")
        certificateDataForm.fillCertificateData("name", "surname", "11-111", "Krakow", "address", "add info", "none")

        expect: "i see confirmation message"
        completeRegistrationPage.getThanksMessage == "Dziękujemy za zapisanie się na egzamin"
        completeRegistrationPage.getImageVisibility == true
        completeRegistrationPage.getNextRegistrationButtonText() == "Zarejestruj się na kolejny egzamin"
    }

    def "As a user I want to register to exam in order to participate in it (Advance certificate)"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose individual registration"
        registerIndividual("30 listopada 2016, Sopot", "ISTQB Advanced Level Technical Test Analyst")

        then: "i see individual registration form"
        at IndividualRegistrationPage

        and: "i see all common elements"
        waitFor { getRegistrationImageVisibility }
        getRegistrationImageVisibility == true
        getRegistrationTitle == appData.individualRegistration.formTitle
        getCalendarIconVisibility == true
        getClockIconVisibility == true
        baseDataForm.getCertificateDataMessage == appData.individualRegistration.baseForm.certificateDataMessage

        and: "i fill registration form"
        baseDataForm.fillBaseData("Angielski", "papierowa", "0123456789", "17.11.2016", "SJSI")
        contactDataForm.fillContactData("name", "surname", CommonHelper.getRandomEmail(), "123123123")
        certificateDataForm.fillCertificateData("name", "surname", "11-111", "Krakow", "address", "add info", "none")

        expect: "i see confirmation message"
        completeRegistrationPage.getThanksMessage == "Dziękujemy za zapisanie się na egzamin"
        completeRegistrationPage.getImageVisibility == true
        completeRegistrationPage.getNextRegistrationButtonText() == "Zarejestruj się na kolejny egzamin"
    }

    def "As a user I want to register group of users to exam in order they can participate in it"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose group registration"
        getAgendaByDateAndPlace("30 listopada 2016, Sopot").registerGroup()

        then: "i see group registration form"
        at GroupRegistrationPageUsers

        and: "i fill the group form"
        fillAndSubmitFormMultiUsers([
                new GroupRegistrationExamForm("name", "surname", "123123123",
                        "ISTQB Agile Tester Extension", false, true),
                new GroupRegistrationExamForm("name2", "surname2", "123123123",
                        "ISTQB Agile Tester Extension", false, true)
        ])

        and: "i see contact registration form"
        at GroupRegistrationContactData

        and: "i fill the contact form"
        fillAndSubmitForm("name", "surname", CommonHelper.getRandomEmail(), "123123123")

        and: "i see certificate form"
        at GroupRegistrationCertificateData

        and: "i feel the certificate form"
        fillAndSubmitForm("name", "surname", "11-111", "Krakow", "address", "add info")

        expect: "i see confirmation page"
        at RegistrationCompletePage
    }

    def "As a user I want to register group of users to different exams in order they can participate in them"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose group registration"
        getAgendaByDateAndPlace("30 listopada 2016, Sopot").registerGroup()

        then: "i see group registration form"
        waitFor { at GroupRegistrationPageUsers }

        and: "i fill the group form"
        fillAndSubmitFormMultiUsers([
                new GroupRegistrationExamForm("name", "surname", "123123123", "ISTQB Agile Tester Extension", false, true),
                new GroupRegistrationExamForm("name2", "surname2", "123123123", "ISTQB Advanced Level Test Analyst",
                        false, true, "11111", "15.06.2016", "ASDF"),
                new GroupRegistrationExamForm("name3", "surname3", "123123123", "ISTQB Improving the Testing Process",
                        false, true, "11111", "15.06.2016", "ASDF"),
                new GroupRegistrationExamForm("name4", "surname4", "123123123", "REQB Foundation Level", false, true)
        ])

        and: "i see contact registration form"
        at GroupRegistrationContactData

        and: "i fill the contact form"
        fillAndSubmitForm("name", "surname", CommonHelper.getRandomEmail(), "123123123")

        and: "i see certificate form"
        at GroupRegistrationCertificateData

        and: "i feel the certificate form"
        fillAndSubmitForm("name", "surname", "11-111", "Krakow", "address", "add info")

        expect: "i see confirmation page"
        at RegistrationCompletePage
    }
}
