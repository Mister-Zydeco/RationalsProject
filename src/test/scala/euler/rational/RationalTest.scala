package euler.rational

import org.scalatest.FlatSpec
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner


class RationalTest extends FlatSpec {
  "the rationals given by (369, 861) and (3,7)" should "be equal" in {
     val rat1 = new Rational(369,861)
     val rat2 = new Rational(3, 7)
     val rat1St = "Rational(369,861)"
     val rat2St = "Rational(3,7)"
     println(s"compare(rat1, rat2) = ${rat1.compare(rat1, rat2)}")
     assert(!(rat1 > rat2), s"$rat1 is not greater than $rat2")
     assert(!(rat1 < rat2), s"$rat1 is not less than $rat2")
     assert(rat1 eq rat2, s"$rat1 equals $rat2")
  }
  
  """the BigInt rationals given by (100101100101100101100101,500505500505500505500505)
  and from(5)""" should "be reciprocals" in {
    val nu = BigInt("100101100101100101100101")
    val de = BigInt("500505500505500505500505")
    val rat = new Rational(nu, de)
    val bigFive = rat.fromInt(5)
    val bigOne = rat.fromInt(1)
    assert (rat * bigFive eq bigOne) 
  }
       
}
