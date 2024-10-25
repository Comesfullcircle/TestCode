package filter

import spock.lang.Specification

class ListFilterTestSpock extends Specification {
    def "filter"() {
        given:
        def filter = new ListFilter()
        def target = [] as ArrayList
        for (int i = 1; i <= 100; i++) {
            target.add(i)
        }

        def from = 10
        def to = 20
        def expectedSize = to - from - 1

        when:
        def actual = filter.filterBy(target, from, to)

        then:
        expectedSize == actual.size()
    }

    def "exception"(){
        given:
        def filter = new ListFilter()
        def target = [] as ArrayList
        for (int i = 1; i <= 100; i++) {
            target.add(i)
        }

        def from = 20
        def to = 10
        def expectedSize = to - from - 1

        when:
        def actual = filter.filterBy(target, from, to)

        then:
        def error = thrown(RuntimeException.class)
        error.getMessage() == "from=20 값이 to = 10 보다 더 크거나 같습니다."
    }
}
