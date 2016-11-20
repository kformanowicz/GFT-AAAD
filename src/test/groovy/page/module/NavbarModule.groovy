package page.module

import geb.Module


class NavbarModule extends Module {
    static content = {
        dashboard { $(".navItem-Dashboard") }
        registration { $(".navItem-Registration") }
        session { $(".navItem-Session") }
        product { $(".navItem-Products") }
    }

    void goToDashboard(){
        dashboard.click()
    }

    void goToRegistrationList(){
        registration.click()
    }

    void goToAddSession(){
        session.click()
    }

    void goToProductsList(){
        product.click()
    }
}
