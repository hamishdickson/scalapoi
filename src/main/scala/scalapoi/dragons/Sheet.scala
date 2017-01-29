package scalapoi
package dragons

import org.apache.poi.hssf.usermodel._
import java.io.{File, FileOutputStream}
import scala.collection.JavaConverters._

object Sheet {
  // totes unsafe
  def create(name: String, wb: HSSFWorkbook): HSSFSheet = {
    val sheet = wb.createSheet(name)
  //  val fileOut = new FileOutputStream("thing.xlsx");
  //  wb.write(fileOut);
  //  fileOut.close();
    sheet
  }

  // todo implement
  private def validateName(n: String): Either[String, String] = {
    Right(n)
  }
}
