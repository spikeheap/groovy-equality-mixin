package uk.co.ryanbrooks.equalitymixin

/**
 * A simple mixin class to dynamically add `equals` method to any other groovy class.
 * Take advantage of it by using: `MyClass.mixin DynamicEquality`
 *
 * @author Ryan Brooks
 **/
class DynamicEquality {
	@Override
	public boolean equals(Object obj){

		// Bail out early if the object is null or it's not the same class
		if(obj == null ){
			return false
		}
		if(!(obj.class == this.class)) {
			return false
		}

		// Are they references to the same object?
		if(this.is(obj)){
			return true
		}

        // We ignore 'metaClass', and property names prefixed with two underscores
        // ('__') or dollar ('$') symbols to prevent comparisons of meta-properties
        def propertiesToCompare = this.class.declaredFields.findAll {
            !(it.name =~ /^__/ || it.name =~ /^\$/ || it.name == "metaClass")
        }

        // Find the first non-matching property
        def inequality = propertiesToCompare.find { property ->
            obj[property.name] != this[property.name]
        }
        return inequality == null
	}
}
