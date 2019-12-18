package personcrud.pages

import geb.Page

class ShowPersonPage extends Page {

    static url = '/person/show'

    @Override
    String convertToPath(Object[] args) {
        args ? '/' + args*.toString().join('/') : ""
    }

    static at = {
        title == 'Show Person'
    }

    static content = {
        h1Header { $('#show-person h1', 0) }
    }

    String getPersonName() {
        h1Header.text()
    }

    Long getPersonId() {
        browser.currentUrl.substring(browser.currentUrl.lastIndexOf(url + '/') + (url + '/').length()) as Long
    }
}
