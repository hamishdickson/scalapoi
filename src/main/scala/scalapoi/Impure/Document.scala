package scalapoi
package interpreter

import org.apache.poi.xssf.usermodel._
import java.io.{File, FileInputStream}
import scala.collection.JavaConverters._

object Document {

  // I guess this should really return an Either
  def create(name: String): Unit = {
    val file = new FileInputStream(new File(name))
    val wb = new XSSFWorkbook(name)
  }
}
