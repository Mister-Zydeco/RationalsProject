package euler.rational

                
class Rational[T](val nu: T, val de: T)(implicit numeric: Numeric[T])
              extends Fractional[Rational[T]]{
  // nu is short for "numerator"; de, for "denominator"
  require(de != numeric.zero)  
  
  val numericCopy = numeric   // workaround for "private value numeric escaping from scope"
  implicit def conv(lhs: T) = numericCopy.mkNumericOps(lhs)
  
  def plus(x: Rational[T], y: Rational[T]) = 
    new Rational(x.nu * y.de + x.de * y.nu, x.de * y.de)
  def minus(x: Rational[T], y: Rational[T]) = 
    new Rational(x.nu * y.de - x.de * y.nu, x.de * y.de)
  def times(x: Rational[T], y: Rational[T]) = 
    new Rational[T](x.nu * y.nu, x.de * y.de)
  def div(x: Rational[T], y: Rational[T]) = 
    new Rational(x.nu * y.de, x.de * y.nu)
 
  def negate(x: Rational[T]) = new Rational(-x.nu, x.de)
  def fromInt(x: Int) = {
    new Rational(numericCopy.fromInt(x), numericCopy.fromInt(1))
  }
  def toInt(x: Rational[T]) = conv(x.nu).toInt()/conv(x.de).toInt()
  def toLong(x: Rational[T]) = conv(x.nu).toLong()/conv(x.de).toLong()
  def toFloat(x: Rational[T]) = conv(x.nu).toFloat()/conv(x.de).toFloat()
  def toDouble(x: Rational[T]) = conv(x.nu).toDouble()/conv(x.de).toDouble()
  override def toString() = s"nu=${nu}, de=${de}"
  def compare(x: Rational[T], y: Rational[T]) = {
    val sgn = conv(x.de * y.de).signum()
    val comp = numericCopy.compare(x.nu * y.de, x.de * y.nu)
    if (sgn > 0) comp else -comp
  }
  
  def eq(other: Rational[T]) = this.compare(this, other) == 0
}

object Rational {
    implicit def mkRationalOps[T](lhs: Rational[T]) = lhs.mkNumericOps(lhs)
    implicit def mkRationalOrderingOps[T](lhs: Rational[T]) = lhs.mkOrderingOps(lhs)
}

object TryRational extends App { 
  val myOne = new Rational(1, 1)
  
  val nearlyOneThirdButBigger = new Rational(101, 300) 
  val evenMoreNearlyOneThirdButBigger = new Rational(1001, 3000)
  val shouldBeLessThanOne = evenMoreNearlyOneThirdButBigger/nearlyOneThirdButBigger
  val isOrIsNot = if (shouldBeLessThanOne < myOne) "is" else "is not"
  println(s"val 'shouldBeLessThanOne' in fact $isOrIsNot less than val 'myOne'")
  
  val thousand = BigInt(1000)
  val million = thousand * thousand * thousand
  val quadrillion = thousand * million * million
  val septillion = thousand * quadrillion
  val quadrP1 = quadrillion + 1
  val threeQuadrP1 = 3 * quadrillion + 1
  val septP1 = septillion + 1
  val threeSeptP1 = 3 * septillion + 1
  val quiteNearlyOneThirdButBigger = new Rational(quadrP1, threeQuadrP1)
  val yetMoreNearlyOneThirdButBigger = new Rational(septP1, threeSeptP1)
  val bigOne = new Rational(BigInt(1), BigInt(1))
  val alsoShouldBeLessThanOne = yetMoreNearlyOneThirdButBigger/quiteNearlyOneThirdButBigger
  val isOrIsNotRedux = if (alsoShouldBeLessThanOne < bigOne) "is" else "is not"
  println(s"val 'alsoShouldBeLessThanOne' in fact $isOrIsNotRedux less than val 'bigOne'")

}
