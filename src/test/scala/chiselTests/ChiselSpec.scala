/*
 Copyright (c) 2011-2015 The Regents of the University of
 California (Regents). All Rights Reserved.  Redistribution and use in
 source and binary forms, with or without modification, are permitted
 provided that the following conditions are met:

    * Redistributions of source code must retain the above
      copyright notice, this list of conditions and the following
      two paragraphs of disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      two paragraphs of disclaimer in the documentation and/or other materials
      provided with the distribution.
    * Neither the name of the Regents nor the names of its contributors
      may be used to endorse or promote products derived from this
      software without specific prior written permission.

 IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF
 ANY, PROVIDED HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION
 TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 MODIFICATIONS.
*/

package chiselTests

import org.scalatest._
import org.scalatest.prop._
import org.scalacheck._
import Chisel._
import Chisel.testers._

class ChiselPropSpec extends PropSpec with PropertyChecks {
  def execute(t: => BasicTester): Boolean = TesterDriver.execute(() => t)
  def elaborate(t: => Module): Circuit = Driver.elaborate(() => t)

  val smallPosInts = Gen.choose(1, 4)
  val safeUIntWidth = Gen.choose(1, 30) 
  val safeUInts = Gen.choose(0, (1 << 30))
  val vecSizes = Gen.choose(1, 4)
  val binaryString = for(i <- Arbitrary.arbitrary[Int]) yield "b" + i.toBinaryString
  def enSequence(n: Int) = Gen.containerOfN[List,Boolean](n,Gen.oneOf(true,false))

  def safeUIntN(n: Int) = for {
    w <- smallPosInts
    i <- Gen.containerOfN[List,Int](n, Gen.choose(0, (1 << w) - 1))
  } yield (w, i)
  val safeUInt = for {
    w <- smallPosInts
    i <- Gen.choose(0, (1 << w) - 1)
  } yield (w, i)

  def safeUIntPairN(n: Int) = for {
    w <- smallPosInts
    i <- Gen.containerOfN[List,Int](n, Gen.choose(0, (1 << w) - 1))
    j <- Gen.containerOfN[List,Int](n, Gen.choose(0, (1 << w) - 1))
  } yield (w, i zip j)
  val safeUIntPair= for {
    w <- smallPosInts
    i <- Gen.choose(0, (1 << w) - 1)
    j <- Gen.choose(0, (1 << w) - 1)
  } yield (w, i, j)
}
