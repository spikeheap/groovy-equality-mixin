# Groovy Equality Mixin

This is a simple mixin to build a dynamic `equals(Object o)` method for a class at runtime.

A simple example:
```groovy
class Something {
	String name
	String description
	int age
	String address
}

// Just add the mixin and do some equality checking
Something.mixin DynamicEquality

assert new Something().equals(new Something())
```

### Maven

To grab this library from Maven, use JCenter (see [the JCenter website for instructions](https://bintray.com/bintray/jcenter#)),

The artifact details are:

* artifactId: groovy-equality-mixin
* groupId: uk.co.ryanbrooks.groovy


### Use case

This mixin is best used to supplement external libraries which can't have the source code directly modified, but which need to be compared for equality.

### Limitations

This mixin does everything at runtime and doesn't (currently) cache the properties list. As a result it is likely to be slower than a properly implemented `equals` method or the `@EqualsAndHashCode` AST transformation.

# Contributing

Pull requests, issues and abuse on Twitter are all welcome!

### Publishing
To publish this to Bintray you need to update the credentials in `build.gradle` and then run the following:

```bash
$ gradle build
$ gradle bintrayUpload
```

# License

This project is released under the MIT License (MIT)

Copyright (c) 2014 Ryan Brooks

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
