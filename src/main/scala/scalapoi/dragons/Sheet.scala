package scalapoi
package dragons

import org.apache.poi.hssf.usermodel._
import org.apache.poi.ss.util.WorkbookUtil._
import java.io.{File, FileOutputStream}
import scala.collection.JavaConverters._

object Sheet {
  // totes unsafe
  def create(name: String, wb: HSSFWorkbook): HSSFSheet = {
    val safeName = createSafeSheetName(name)
    val sheet = wb.createSheet(safeName)
    val fileOut = new FileOutputStream("things.xlsx");
    wb.write(fileOut);
    fileOut.close();
    sheet
  }
}
