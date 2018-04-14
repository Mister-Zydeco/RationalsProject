# RationalsProject
Scala classes to make generic rationals where the numerator and denominator belong to the same Numeric

I did this little bit of code because the other examples of Rational classes in the gitosphere had only Integer numerators and denominators. What if you instead wanted to use Long or BigInt or integers mod 29? And it seemed like a good exercise to improve if not perfect my understading of Scala implicits.

In src/main/scala/euler/rational/Rational.scala, if I try to use the expected implicit _numeric_ from the constructor in my _conv_ method (and later in my fromInt method), an attempt to compile yields
```
private value numeric escapes its defining scope as part of type Rational.this.numeric.Ops
```

So I worked aorund this by doing
```scala
val numericCopy = numeric
```
early on. I'm left wondering how bad this is. At least my copy is a _val_, and so it should not be a back door trough which something in the original _numeric_ might get messed up. "What is the danger of having _numeric_ escape its defining scope?" he asked ignorantly...
