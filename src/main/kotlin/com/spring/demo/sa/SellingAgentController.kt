package com.spring.demo.sa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@RestController
@RequestMapping("/api/selling-agents")
class SellingAgentController() {
    @Autowired
    private lateinit var sellingAgentService: SellingAgentService

    @GetMapping
    fun getAllSellingAgents(): List<SellingAgent> {
        return sellingAgentService.getAllSellingAgents()
    }

    @GetMapping("/code/{code}")
    fun getByCode(@PathVariable code: String): SellingAgent? {
        return sellingAgentService.getByCode(code)
    }

    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile
    ): String {
        val rootPath = Paths.get("./uploads").toAbsolutePath().normalize() // /workspace/spring-demo/uploads
        Files.createDirectory(rootPath) // created directory /workspace/spring-demo/uploads

        val fileName = StringUtils.cleanPath(file.originalFilename!!) // get file name
        val destinationFile = rootPath.resolve(fileName) // /workspace/spring-demo/uploads/fileName

        Files.copy(file.inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING) // copy file to destinationFile

        return "File uploaded successfully"
    }
}