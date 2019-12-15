package personcrud

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class PersonSpec extends Specification implements DomainUnitTest<Person> {

    @Unroll("age #someAge should have caused validate() to return #shouldBeValid")
    void 'test age constraint'() {
        expect:
        new Person(age: someAge).validate(['age']) == shouldBeValid

        where:
        someAge | shouldBeValid
        -1      | false
        0       | false
        1       | true
        2       | true
        119     | true
        120     | true
        121     | false
        122     | false
    }
}
