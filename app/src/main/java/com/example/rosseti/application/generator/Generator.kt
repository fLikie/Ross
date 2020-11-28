package com.example.rosseti.application.generator

import android.content.Context
import android.os.Environment
import com.example.rosseti.R
import com.example.rosseti.application.Form
import com.example.rosseti.domain.entities.User
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class Generator(private val form: Form, private val context: Context) {

    companion object {
        const val TEXT_HOLDER_MEDIUM = "_____________________________________________"
        const val TEXT_HOLDER_SMALL = "_____________________"

        const val UNFILLED_CHECK_BOX = "☐"
        const val FILLED_CHECK_BOX = "☑"
    }

    fun generateDocx(): XWPFDocument {
        val fileIS = context.resources.openRawResource(R.raw.doc)
        val document = XWPFDocument(fileIS)
        fileIS.close()
        val paragraphs: List<XWPFParagraph> = document.paragraphs
        paragraphs.forEachIndexed { _, paragraph ->
            paragraph.runs.forEach { run ->
                val text = run.text().split(".", limit = 1)
                val result: String = when(text[0]) {
                    Holders.APPLICATION_REG_NUMBER -> form.applicationRegNumber.toString()
                    Holders.APPLICATION_REG_DATE -> {
                        val format = SimpleDateFormat("dd.MM.yyyy")
                        format.format(Date())
                    }
                    Holders.ORGANIZATION_NAME -> {
                        form.authors.getOrNull(0)?.organizationName ?: ""
                    }
                    Holders.AUTHOR_FULL_NAME -> {
                        getCurrentUserIfPresent(text)?.fullName ?: ""
                    }
                    Holders.AUTHOR_NUMBER -> {
                        text.getOrNull(1) ?: ""
                    }
                    Holders.AUTHOR_REWARD -> {
                        val index = getIndexIfPresent(text)
                        if (index != null) {
                            form.rewards[form.authors[index]]?.toString() ?: ""
                        } else {
                            ""
                        }
                    }
                    Holders.POSITION -> {
                        getCurrentUserIfPresent(text)?.position ?: ""
                    }
                    Holders.STRUCTURE_NAME -> {
                        getCurrentUserIfPresent(text)?.structureName ?: ""
                    }
                    Holders.BACKGROUND -> {
                        getCurrentUserIfPresent(text)?.education ?: ""
                    }
                    Holders.BIRTH_YEAR -> {
                        val user = getCurrentUserIfPresent(text)
                        val format = SimpleDateFormat("yyyy")
                        val date = user?.birthdate
                        date?.let { format.format(date) } ?: ""
                    }
                    Holders.EXPERIENCE -> {
                        val user = getCurrentUserIfPresent(text)
                        val format = SimpleDateFormat("yyyy")
                        val date = user?.dateOfEmployment
                        date?.let { "С ${format.format(date)} года" } ?: ""
                    }
                    Holders.SHORT_NAME -> { form.shortname }
                    Holders.CATEGORY_CHECK_BOX -> {
                        val index = getIndexIfPresent(text)
                        val category = form.categories.find { it.id == index }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.ISSUE_DESCRIPTION -> {
                        form.issueDescription
                    }
                    Holders.ISSUE_SOLUTION -> {
                        form.issueSolution
                    }
                    Holders.ISSUE_EFFECT -> {
                        form.issueSolution
                    }
                    Holders.SPEND_NUMBER -> {
                        text.getOrNull(1) ?: ""
                    }
                    Holders.SPEND_NAME -> {
                        val index = getIndexIfPresent(text)
                        index?.let { form.expenditures[index].name } ?: ""
                    }
                    Holders.SPEND_SUM -> {
                        val index = getIndexIfPresent(text)
                        index?.let { form.expenditures[index].sum.toString() } ?: ""
                    }
                    Holders.STEP_NUM -> {
                        text.getOrNull(1) ?: ""
                    }
                    Holders.STEP_NAME -> {
                        val index = getIndexIfPresent(text)
                        index?.let { form.steps[index].name } ?: ""
                    }
                    Holders.STEP_PERIOD -> {
                        val index = getIndexIfPresent(text)
                        index?.let { form.steps[index].period.toString() } ?: ""
                    }
                    else -> ""
                }
                run.setText(result, 0)
            }
        }
        return document
    }

    private fun getCurrentUserIfPresent(text: List<String>): User? {
        val index: Int? = getIndexIfPresent(text)
        return index?.let { form.authors.getOrNull(index) }
    }

    private fun getIndexIfPresent(text: List<String>): Int? {
        return text.getOrNull(1)?.toInt()?.minus(1)
    }

    fun saveDocumentInDownloads(document: XWPFDocument): File {
        val dir = Environment.getExternalStorageDirectory()
        val newFile = File("${dir.path}/Download/application_${form.applicationRegNumber}.docx")
        if (!newFile.exists()) {
            newFile.createNewFile()
        }
        val fos = FileOutputStream(newFile)
        document.write(fos)
        fos.close()
        return newFile
    }

    fun saveDocumentInDocuments(document: XWPFDocument): File {
        val dir = Environment.getExternalStorageDirectory()
        val newFile = File("${dir.path}/documents/application_${form.applicationRegNumber}.docx")
        if (!newFile.exists()) {
            newFile.createNewFile()
        }
        val fos = FileOutputStream(newFile)
        document.write(fos)
        fos.close()
        return newFile
    }
}