package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream


/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    private val workingDirectory = "E:\\develop\\IdeaProjects\\Sprint-3\\io\\"

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        try {
            ZipOutputStream(FileOutputStream(workingDirectory.plus("logfile.zip"))).use { zout ->
                FileInputStream(workingDirectory.plus("logfile.log")).use { fis ->
                    val zipEntry = ZipEntry("logfile.log")
                    zout.putNextEntry(zipEntry)
                    val buffer = ByteArray(8192)

                    var bytesRead: Int

                    while (fis.read(buffer).also { bytesRead = it } > 0) {
                        zout.write(buffer, 0, bytesRead)
                    }
                    zout.closeEntry()
                }
            }
        } catch (ex: IOException) {
            throw ex
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        ZipInputStream(FileInputStream(workingDirectory.plus("logfile.zip"))).use { zis ->
            FileOutputStream(workingDirectory.plus("unzippedLogfile.log")).use { fos ->
                if (zis.nextEntry != null) {
                    val buffer = ByteArray(1024)

                    var readBytes: Int

                    while (zis.read(buffer).also { readBytes = it } > 0) {
                        fos.write(buffer, 0, readBytes)
                    }
                    zis.closeEntry()
                }
            }
        }
    }
}