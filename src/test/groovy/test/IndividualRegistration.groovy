package test

import geb.spock.GebReportingSpec
import helper.CommonHelper
import org.openqa.selenium.By
import page.ExamPlannerHomePage
import page.IndividualRegistrationPage


class IndividualRegistration extends GebReportingSpec {

    Random random = new Random()
    private final String randomNumber = random.nextInt(100000000).toString()
    def appData = CommonHelper.jsonToObject("src/test/resources/applicationData.json")

    def setup(){
    }

    def cleanup(){
    }

    def "As a user I want to register to exam in order to participate in it (Foundation certificate)"() {
        given: "i am on the home page"
        to ExamPlannerHomePage

        when: "i chose individual registration"
        //TODO
        $(By.xpath("/html/body/div[2]/div/div/div[3]/div[8]/div[3]/div[2]/div[3]/table/tbody/tr[1]/td[3]")).click()

        then: "i see individual registration form"
        at IndividualRegistrationPage

        and: "i see all common elements"
        sleep(1000)
        getRegistrationImageVisibility == true
        getRegistrationTitle == appData.individualRegistration.formTitle
        getCalendarIconVisibility == true
        getClockIconVisibility == true

        and: "i fill registration form"
        baseDataForm.fillBaseData("Polski", "elektroniczna")
        contactDataForm.fillContactData("name", "surname", "email" + randomNumber + "@mail.pl", "123123123")
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
        //TODO
        $(By.xpath("/html/body/div[2]/div/div/div[3]/div[8]/div[3]/div[2]/div[4]/table/tbody/tr[1]/td[3]")).click()

        then: "i see individual registration form"
        at IndividualRegistrationPage

        and: "i see all common elemetns"
        sleep(1000)
        getRegistrationImageVisibility == true
        getRegistrationTitle == appData.individualRegistration.formTitle
        getCalendarIconVisibility == true
        getClockIconVisibility == true
//        getCertificateDataMessage == appData.individualRegistration.baseForm.certificateDataMessage

        and: "i fill registration form"
        baseDataForm.fillBaseData("Angielski", "papierowa", "0123456789", "17.11.2016", "SJSI")
        contactDataForm.fillContactData("name", "surname", "emailemai" + randomNumber + "@mail.pl", "123123123")
        certificateDataForm.fillCertificateData("name", "surname", "11-111", "Krakow", "address", "add info", "none")

        expect: "i see confirmation message"
        completeRegistrationPage.getThanksMessage == "Dziękujemy za zapisanie się na egzamin"
        completeRegistrationPage.getImageVisibility == true
        completeRegistrationPage.getNextRegistrationButtonText() == "Zarejestruj się na kolejny egzamin"
    }

    def "As a user I want to register group of user to exam in order they can participate in it"(){

    }

    def "As a user I want to register group of user to different exams in order they can participate in them"(){

    }
}
