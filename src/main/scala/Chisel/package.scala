package object Chisel {
  implicit def toLiteralMaker(x: Int) = new LiteralMaker(BigInt(x))
  implicit def toLiteralMaker(x: BigInt) = new LiteralMaker(x)
}
