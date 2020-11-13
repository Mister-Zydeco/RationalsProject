# RationalsProject
Scala classes to make generic rationals where the numerator and denominator belong to the same Numeric

I did this little bit of code because the other examples of Rational classes in the gitosphere had only Integer numerators and denominators. What if you instead wanted to use Long or BigInt or integers mod 29? And it seemed like a good exercise to improve if not perfect my understanding of Scala implicits.

In src/main/scala/euler/rational/Rational.scala, if I try to use the expected implicit _numeric_ from the constructor in my _conv_ method (and later in my fromInt method), an attempt to compile yields
```
private value numeric escapes its defining scope as part of type Rational.this.numeric.Ops
```

UPDATE:
11/13/20: I've just now found what might be a useful post on Reddit about this message. I admit to be ignorant of the consequences of letting a private
value escape from its scope. Does that mean it pollutes the namespace of the sxope into which it has escaped? To be continued...

So I worked aorund this by doing
```scala
val numericCopy = numeric
```
early on. I'm left wondering how bad this is. At least my copy is a _val_, and so it should not be a back door through which something in the original _numeric_ might get messed up. "What is the danger of having _numeric_ escape its defining scope?" he asked ignorantly...Yes, I have googled this and am still scratching my head.

The other point to this pedagogical exercise was to get my feet wet with scalatest and try to write a couple of expressive unit tests using it. With proper installations of reasonably recent versions of sbt and scala, running ```sbt test``` in the top-level directory should yield output like this:

```
[info] RationalTest:
[info] the rationals given by (369, 861) and (3,7)
[info] - should be equal
[info] the BigInt rationals given by (100101100101100101100101,500505500505500505500505)
[info]   and from(5)
[info] - should be reciprocals
[info] Run completed in 430 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 2, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 6 s, completed Apr 14, 2018 6:37:32 AM
```
