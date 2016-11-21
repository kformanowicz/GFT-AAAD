package page.registration

import geb.module.TextInput

class GroupRegistrationPageUsers extends RegistrationPage {
    static at = {
//        $(".Register-header.clearfix h3").text() == "Rejestracja grupowa na termin"
//        $(".Registration-products .radio input", 0).displayed
        sleep(200)
        return true
    }

    static content = {
        nameInput { $("input", name: "name").module(TextInput) }
        lastNameInput { $("input", name: "surname").module(TextInput) }
        emailInput { $("input", name: "email").module(TextInput) }
        phoneInput { $("input", name: "phone").module(TextInput) }

        availableExams { $(".Registration-products .radio input") }

        polishLanguageButton { $("#RegistrationLanguageID21") }
        englishLanguageButton { $("#RegistrationLanguageID22") }

        paperFormButton { $("#ProductFormIdpapierowa") }
        electronicFormButton { $("#ProductFormIdelektroniczna") }

        certificateNumberInput { $(".registration__product-options__certificate-number").module(TextInput) }
        certificateDateInput { $(".registration__product-options__certificate-picker").module(TextInput) }
        certificateProviderInput { $(".registration__product-options__certificate-provider").module(TextInput) }

        forwardButton { $(".button--accept") }
        addAttendeeButton {$(".btn-registrationAddAttendee")}
    }

    void selectExam(name) {
        availableExams.find { it.attr("data-name") == name }.click()
    }

    void setLanguage(boolean polish) {
        polish ? polishLanguageButton.click() : englishLanguageButton.click()
    }

    void setForm(boolean paper) {
        paper ? paperFormButton.click() : electronicFormButton.click()
    }

    void accept() {
        forwardButton.click()
    }

    void addAttendee(){
        addAttendeeButton.click()
    }

    def fillAndSubmitFormSingleUser(String name, String surname, String email, String phone, String examName,
                                    boolean setPolish = false, boolean setPaper = true, String certificateNumber = "",
                                    String certificateDate = "", String certificateProvider = "", accept = true) {
        nameInput.text = name
        lastNameInput.text = surname
        emailInput.text = email
        phoneInput.text = phone

        selectExam(examName)

        sleep(500) // ugly but works

        setLanguage(setPolish)
        setForm(setPaper)

        if (!(examName =~ /Foundation|Agile/)) { //fill certificate info only for advanced & expert exams
            certificateNumberInput.text = certificateNumber
            certificateDateInput.text = certificateDate
            certificateProviderInput.text = certificateProvider
        }

        if (accept) {
            accept()
        }
    }

    boolean fillAndSubmitFormMultiUsers(usersData) {
        usersData.each {
            fillAndSubmitFormSingleUser(it.name, it.surname, it.email, it.phone, it.examName, it.setPolish, it.setPaper,
                    it.certificateNumber, it.certificateDate, it.certificateProvider, false)

            addAttendee()
        }
        accept()

        return true // everything went fine
    }
}
