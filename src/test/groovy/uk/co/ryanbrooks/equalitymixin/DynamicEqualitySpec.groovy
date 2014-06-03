package uk.co.ryanbrooks.equalitymixin

import spock.lang.Specification
import groovy.transform.TupleConstructor
import java.lang.Void as Should

/**
* @author Ryan Brooks
**/
class DynamicEqualitySpec extends Specification {

	Should "fail equality without the mixin"(){

		given:
		def things = [
			new Something("Bill", "Something", 12, "Testsville"),
			new Something("Bill", "Something", 12, "Testsville"),
			new Something(null, "Something", 12, "Testsville"),
			new Something("Bill", null, 12, "Testsville"),
			new Something("Bill", "Something", 0, null),
		]

		expect:
		things.eachWithIndex { thing, index ->
			thing.equals(thing)
			things.eachWithIndex { thing2, index2 ->
				if(index != index2) {
					assert !thing.equals(thing2)
				}
			}
		}
	}

	Should "pass equality with the mixin"(){

		given:
		Something.mixin DynamicEquality
		def things = [
			new Something("Bill", "Something", 12, "Testsville"),
			new Something(null, "Something", 12, "Testsville"),
			new Something("Bill", null, 12, "Testsville"),
			new Something("Bill", "Something", 0, null),
		]

		def things2 = [
			new Something("Bill", "Something", 12, "Testsville"),
			new Something(null, "Something", 12, "Testsville"),
			new Something("Bill", null, 12, "Testsville"),
			new Something("Bill", "Something", 0, null),
		]

		expect:
		// Old things still lack equality
		things.eachWithIndex { thing, index ->
			thing.equals(thing)
			things.eachWithIndex { thing2, index2 ->
				if(index != index2) {
					assert !thing.equals(thing2)
				}
			}
		}

		things.eachWithIndex { thing, index ->
			assert thing.equals(things2[index])
		}
	}
}


@TupleConstructor
class Something {
	String name
	String description
	int age
	String address
}
