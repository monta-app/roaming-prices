package com.monta.roamingPrices.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.CompletedFileUpload
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

@Controller("/excel")
class ExcelController {
@Post(value = "/upload", consumes = [MediaType.MULTIPART_FORM_DATA])
    fun uploadExcel(file: CompletedFileUpload): String {
        return try {
            val inputStream: InputStream = file.inputStream
            val workbook = WorkbookFactory.create(inputStream)

            // Loop through the sheets and print the data
            for (sheetIndex in 0 until workbook.numberOfSheets) {
                val sheet = workbook.getSheetAt(sheetIndex)
                println("Reading Sheet: ${sheet.sheetName}")

                for (row in sheet) {
                    for (cell in row) {
                        print("$cell \t")
                    }
                    println() // Move to the next line after each row
                }
            }

            workbook.close()
            "File uploaded and processed successfully!"
        } catch (e: Exception) {
            e.printStackTrace()
            "Failed to process the file: ${e.message}"
        }
    }
}