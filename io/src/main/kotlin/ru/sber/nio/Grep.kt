package ru.sber.nio

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.useLines

/**
 * Реализовать простой аналог утилиты grep с использованием калссов из пакета java.nio.
 */
class Grep {
    /**
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) {
        File("E:\\develop\\IdeaProjects\\Sprint-3\\io\\", "result.txt").printWriter().use { out ->
            val path = Paths.get("E:\\develop\\IdeaProjects\\Sprint-3\\io\\logs")
            val logsFile = Files.walk(path).filter { it.toString().endsWith(".log") }.collect(Collectors.toList())

            logsFile.forEach {
                val linesFile = it.useLines { line ->
                    line.toList()
                }

                for ((index, value) in linesFile.withIndex()) {
                    if (value.contains(subString)) {
                        out.println("${it.fileName} : ${index + 1} : $value")
                    }
                }
            }
        }
    }
}

fun main() {
    val grep = Grep()
    grep.find("22/Jan/2001:14:27:46")
}