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
        fieldFirstName { $('div', ("aria-labelledby"): "firstName-label", 0) }
    }

    String getPersonName() {
        fieldFirstName.text()
    }

    Long getPersonId() {
        browser.currentUrl.substring(browser.currentUrl.lastIndexOf(url + '/') + (url + '/').length()) as Long
    }
}
