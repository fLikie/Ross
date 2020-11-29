package com.example.rosseti.application.generator

import android.content.Context
import android.os.Environment
import androidx.core.content.FileProvider
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
                val text = run.text()
                var holder: String = "!213123214231312123412434534534"
                val result: String = when(text) {
                    Holders.APPLICATION_REG_NUMBER -> {
                        holder = Holders.APPLICATION_REG_NUMBER
                        form.applicationRegNumber.toString()
                    }
                    Holders.APPLICATION_REG_DATE -> {
                        holder = Holders.APPLICATION_REG_DATE
                        val format = SimpleDateFormat("dd.MM.yyyy")
                        format.format(Date())
                    }
                    Holders.ORGANIZATION_NAME -> {
                        holder = Holders.ORGANIZATION_NAME
                        form.authors[0].organizationName ?: ""
                    }
                    Holders.AUTHOR_FULL_NAME -> {
                        holder = Holders.AUTHOR_FULL_NAME
                        form.authors[0].fullName
                    }
                    Holders.AUTHOR_NUMBER -> {
                        holder = Holders.AUTHOR_NUMBER
                        "1"
                    }
                    Holders.AUTHOR_REWARD -> {
                        holder = Holders.AUTHOR_REWARD
                        form.rewards[form.authors[0]]?.toString() ?: ""

                    }
                    Holders.POSITION -> {
                        holder = Holders.POSITION
                        form.authors[0].position ?: ""
                    }
                    Holders.STRUCTURE_NAME -> {
                        holder = Holders.STRUCTURE_NAME
                        form.authors[0].structureName ?: ""
                    }
                    Holders.BACKGROUND -> {
                        holder = Holders.BACKGROUND
                        form.authors[0].education ?: ""
                    }
                    Holders.BIRTH_YEAR -> {
                        holder = Holders.BIRTH_YEAR
                        val user = form.authors[0]
                        val format = SimpleDateFormat("yyyy")
                        val date = user?.birthdate
                        date?.let { format.format(date) } ?: ""
                    }
                    Holders.EXPERIENCE -> {
                        holder = Holders.EXPERIENCE
                        val user = form.authors[0]
                        val format = SimpleDateFormat("yyyy")
                        val date = user?.dateOfEmployment
                        date?.let { "С ${format.format(date)} года" } ?: ""
                    }
                    Holders.SHORT_NAME -> {
                        holder = Holders.SHORT_NAME
                        form.shortname }
                    Holders.CATEGORY_CHECK_BOX1 -> {
                        holder = Holders.CATEGORY_CHECK_BOX1
                        val category = form.categories.find { it.id == 0 }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.CATEGORY_CHECK_BOX2 -> {
                        holder = Holders.CATEGORY_CHECK_BOX2
                        val category = form.categories.find { it.id == 1 }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.CATEGORY_CHECK_BOX3 -> {
                        holder = Holders.CATEGORY_CHECK_BOX3
                        val category = form.categories.find { it.id == 2 }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.CATEGORY_CHECK_BOX4 -> {
                        holder = Holders.CATEGORY_CHECK_BOX4
                        val category = form.categories.find { it.id == 3 }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.CATEGORY_CHECK_BOX5 -> {
                        holder = Holders.CATEGORY_CHECK_BOX5
                        val category = form.categories.find { it.id == 4 }
                        if (category != null) FILLED_CHECK_BOX else UNFILLED_CHECK_BOX
                    }
                    Holders.ISSUE_DESCRIPTION -> {
                        holder = Holders.ISSUE_DESCRIPTION
                        form.issueDescription
                    }
                    Holders.ISSUE_SOLUTION -> {
                        holder = Holders.ISSUE_SOLUTION
                        form.issueSolution
                    }
                    Holders.ISSUE_EFFECT -> {
                        holder = Holders.ISSUE_EFFECT
                        form.issueEffect
                    }
                    Holders.SPEND_NUMBER -> {
                        holder = Holders.SPEND_NUMBER
                        "1"
                    }
                    Holders.SPEND_NAME -> {
                        holder = Holders.SPEND_NAME
                        form.expenditures[0].name
                    }
                    Holders.SPEND_SUM -> {
                        holder = Holders.SPEND_SUM

                        form.expenditures[0].sum.toString()
                    }
                    Holders.STEP_NUM -> {
                        holder = Holders.STEP_NUM
                        "1"
                    }
                    Holders.STEP_NAME -> {
                        holder = Holders.STEP_NAME
                        form.steps[0].name
                    }
                    Holders.STEP_PERIOD -> {
                        holder = Holders.STEP_PERIOD
                        form.steps[0].period.toString()
                    }
                    Holders.ECONOMY_CHECK_BOX1 -> {
                        holder = Holders.ECONOMY_CHECK_BOX1
                        if (form.doesSaveMoney) { FILLED_CHECK_BOX} else UNFILLED_CHECK_BOX
                    }
                    Holders.ECONOMY_CHECK_BOX2 -> {
                        holder = Holders.ECONOMY_CHECK_BOX2
                        if (form.doesSaveMoney) { UNFILLED_CHECK_BOX} else FILLED_CHECK_BOX
                    }
                    else -> ""
                }
                run.setText(text.replace(holder, result), 0)
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