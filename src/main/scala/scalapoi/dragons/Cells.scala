package scalapoi
package dragons

import org.apache.poi.hssf.usermodel._
import java.io.{File, FileOutputStream}

object Cells {
  // todo pass wb and loc somehow? implicits?
  
  def put[T](cell: T, sheet: HSSFSheet): Unit = {
    /*val row = sheet.createRow(0)
    val cell = row.createCell(0)
    cell.setCellValue(1)

    val fileOut = new FileOutputStream("things.xlsx");
    wb.write(fileOut);
    fileOut.close();*/
  }
}
