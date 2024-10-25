package Cal

import spock.lang.Specification

class CalculatorTest extends Specification {

    def "sum"() {
        given:
        def calculator = new Calculator()

        expect:
        calculator.sum(a, b) == expected

        where:
        a   | b    | expected
        10  | 20   | 30
        100 | 1000 | 1100
    }

    def "exception"() {
        given:
        def a = -1
        def b = 10
        def calculator = new Calculator()

        when:
        calculator.sum(a, b)

        then:
        thrown(RuntimeException)
    }
}
