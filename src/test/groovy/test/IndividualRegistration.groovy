package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import org.openqa.selenium.By
import page.ExamPlannerHomePage
import page.IndividualRegistrationPage
import page.registration.GroupRegistrationCertificateData
import page.registration.GroupRegistrationContactData
import page.registration.GroupRegistrationPageUsers
import page.registration.RegistrationCompletePage
import spock.lang.Ignore


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

    @Ignore
    def "As a user I want to register group of users to exam in order they can participate in it"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose group registration"
        //TODO
        getAgendaByDateAndPlace("30 listopada 2016, Sopot")
//        $(By.xpath("/html/body/div[2]/div/div/div[3]/div[6]/div[3]/div[3]/div[3]")).click()

        then: "i see group registration form"
        at GroupRegistrationPageUsers

        and: "i fill the group form"
//        TODO resolve examName error
        fillAndSubmitFormMultiUsers({
            [name : "name", surname: "surname", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "ISTQB Agile Tester Extension"];
            [name : "name2", surname: "surname2", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "ISTQB Agile Tester Extension"];
        })

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

    @Ignore
    def "As a user I want to register group of users to different exams in order they can participate in them"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose group registration"
        //TODO
        getAgendaByDateAndPlace("30 listopada 2016, Sopot")
//        $(By.xpath("/html/body/div[2]/div/div/div[3]/div[6]/div[3]/div[3]/div[3]")).click()

        then: "i see group registration form"
        at GroupRegistrationPageUsers

        and: "i fill the group form"
//        TODO resolve examName error
        fillAndSubmitFormMultiUsers({
            [name : "name", surname: "surname", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "ISTQB Agile Tester Extension"];
            [name : "name2", surname: "surname2", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "ISTQB Advanced Level Test Analyst"];
            [name : "name3", surname: "surname3", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "ISTQB Improving the Testing Process"];
            [name : "name4", surname: "surname4", email: CommonHelper.getRandomEmail(),
             phone: "123123123", examName: "REQB Foundation Level"];
        })

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
