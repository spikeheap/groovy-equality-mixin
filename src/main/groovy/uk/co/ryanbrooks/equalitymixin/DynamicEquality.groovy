package uk.co.ryanbrooks.equalitymixin

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

		// We ignore property names prefixed with two underscores ('__')
		// to prevent comparisons of meta-properties
		def propertiesToCompare = this.metaClass.properties.findAll{ !(it.name =~ /^__/) }

		println propertiesToCompare

		// Find the first non-matching property
		def inequality = propertiesToCompare.find { property ->
			println "${property} -> ${obj[property.name]} != ${this[property.name]} "
			obj[property.name] != this[property.name]
		}
		return inequality == null
	}
}
