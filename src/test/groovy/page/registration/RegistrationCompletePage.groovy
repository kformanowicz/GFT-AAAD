package page.registration

import geb.Page

//stub
class RegistrationCompletePage extends Page{
    static at = {
        $(".space3").text().contains("Dziękujemy za zapisanie się na egzamin")
    }
}
