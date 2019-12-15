package personcrud

class Person {
    String firstName
    String lastName
    Integer age

    static constraints = {
        age range: 1..120
    }
}
