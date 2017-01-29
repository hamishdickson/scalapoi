package scalapoi
package dragons

import org.apache.poi.hssf.usermodel._
import java.io.{File, FileOutputStream}
import scala.collection.JavaConverters._

object Workbook {

  // totes unsafe - should at least return an either
  def create(name: String): HSSFWorkbook = {
    val file = new FileOutputStream(new File(name))
    val wb = new HSSFWorkbook()
    wb.write(file)
    file.close()
    wb
  }
}
