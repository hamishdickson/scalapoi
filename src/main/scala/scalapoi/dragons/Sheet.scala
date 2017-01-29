package scalapoi
package dragons

import org.apache.poi.hssf.usermodel._
import org.apache.poi.ss.util.WorkbookUtil._
import java.io.{File, FileOutputStream}
import scala.collection.JavaConverters._
import utils._

object Sheet {
  // totes unsafe
  def create(name: String, wb: Workbook): HSSFSheet = {
    val safeName = createSafeSheetName(name)
    val sheet = wb.hssfWb.createSheet(safeName)
    val fileOut = new FileOutputStream(wb.file);
    wb.hssfWb.write(fileOut);
    fileOut.close();
    sheet
  }
}
