package page.registration

import geb.module.TextInput

class GroupRegistrationPageUsers extends RegistrationPage {
    static at = {
        getHeader() == "Rejestracja grupowa na termin"
//        $(".Registration-products .radio input", 0).displayed
    }

    static content = {
        nameInput { $("input", name: "name").module(TextInput) }
        lastNameInput { $("input", name: "surname").module(TextInput) }
        emailInput { $("input", name: "email").module(TextInput) }
        phoneInput { $("input", name: "phone").module(TextInput) }

        availableExams { $(".Registration-products .radio input") }

        polishLanguageButton { $("input#RegistrationLanguageID21") }
        englishLanguageButton { $("input#RegistrationLanguageID21") }

        paperFormButton { $("#ProductFormIdpapierowa") }
        electronicFormButton { $("#ProductFormIdelektroniczna") }

        certificateNumberInput { $(".registration__product-options__certificate-number").module(TextInput) }
        certificateDateInput { $(".registration__product-options__certificate-picker").module(TextInput) }
        certificateProviderInput { $(".registration__product-options__certificate-provider").module(TextInput) }

        forwardButton { $(".button--accept") }
    }

    void selectExam(name) {
        availableExams.find { it.attr("data-name") == name }.click()
    }

    void setLanguage(boolean polish) {
        if (polish) {
            polishLanguageButton.click()
        } else {
            englishLanguageButton.click()
        }
    }

    void setForm(boolean paper) {
        if (paper) {
            paperFormButton.click()
        } else {
            electronicFormButton.click()
        }
    }

    def accept() {
        forwardButton.click()
    }

    def fillAndSubmitFormSingleUser(String name, String surname, String email, String phone, String examName,
                                    boolean setPolish = true, boolean setPaper = true, String certificateNumber = null,
                                    String certificateDate = null, String certificateProvider = null, accept = true) {
        nameInput.text = name
        lastNameInput.text = surname
        emailInput.text = email
        phoneInput.text = phone

        selectExam(examName)

        waitFor { polishLanguageButton.displayed }

        setLanguage(setPolish)
        setForm(setPaper)

        if (!examName =~ /Foundation|Agile/) { //fill certificate info only for advanced & expert exams
            certificateNumberInput.text = certificateNumber
            certificateDateInput.text = certificateDate
            certificateProviderInput.text = certificateProvider
        }

        if (accept) {
            accept()
        }
    }

    def fillAndSubmitFormMultiUsers(Object usersData) {
        usersData.each {
            fillAndSubmitFormSingleUser(it.name, it.surname, it.email, it.phone, it.examName, it.setPolish, it.setPaper,
                    it.certificateNumber, it.certificateDate, it.certificateProvider, false)
        }

        accept()
    }
}
