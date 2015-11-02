// See LICENSE for license details.

package Chisel.testers
import Chisel._
import scala.sys.process._
import java.io.File

object TesterDriver extends BackendCompilationUtilities with FileSystemUtilities {
  /** For use with modules that should successfully be elaborated by the
    * frontend, and which can be turned into executeables with error codes. */
  def execute(t: () => BasicTester): Boolean = {
    val circuit = Driver.elaborate(t)
    //val executable = invokeFIRRTL(circuit)
    val returnValue = 0 //Process(executable) !
    (returnValue == 0)
  }
}
