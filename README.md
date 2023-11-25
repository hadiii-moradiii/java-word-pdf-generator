# java-word-pdf-generator

in this repository i wrote sample code for creating dynamic pdf and docx with java support persian letters

# sample Code

```

List<SampleSubDto> sampleSubDtoList = List.of(SampleSubDto.builder().sample1("تست").sample2("تست 2").build());

Map<String, Object> variables = new HashMap<>();
variables.put("contract", "تست");
variables.put("sublist", sampleSubDtoList);

// define template html file in resource and send name and varialbles 
byte[] repsonePdf = pdfGenerator.generatePdf("sampleForm", variables);

try (FileOutputStream fos = new FileOutputStream("test.pdf")) {
    fos.write(repsone);
}

byte[] repsoneDocx = docxGenerator.generateDocx("sampleForm", variables);
try (FileOutputStream fos = new FileOutputStream("test.docx")) {
    fos.write(repsone);
}

```

# keywords:

java, spring boot, openToHtml

# Requirements:

- JDK 17
- MAVEN

# Main dependencies in maven :

for pdf manipulation

```
  <dependency>
            <groupId>com.openhtmltopdf</groupId>
            <artifactId>openhtmltopdf-pdfbox</artifactId>
            <version>1.0.10</version>
  </dependency>
  <dependency>
    <groupId>com.openhtmltopdf</groupId>
    <artifactId>openhtmltopdf-rtl-support</artifactId>
    <version>1.0.10</version>
  </dependency>
```

for docx file processing

```
<dependency>
   <groupId>org.docx4j</groupId>
   <artifactId>docx4j-ImportXHTML</artifactId>
   <version>11.4.8</version>
</dependency>
<dependency>
    <groupId>org.docx4j</groupId>
    <artifactId>docx4j-JAXB-ReferenceImpl</artifactId>
    <version>11.4.9</version>
</dependency>
```

for html template processing

```
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>3.1.1.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring6</artifactId>
    <version>3.1.1.RELEASE</version>
</dependency>
```
