package page.sessionManagement

import geb.Page


class EditSessionPage extends SessionManagementPage{

    static at = {
        $("h3").text() == 'Edytuj Sesję'
    }

}
