примеры по базовому курсу javabegin

Минимальные настройки

для сборки maven
- создать файл src/main/resources/META-INF/MANIFEST.MF
- добавить в него как минимум
  - Main-Class: App
  - Manifest-Version: 1.0
- в pom.xml добавить
 
         <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.2.0</version>
          <configuration>
            <archive>
              <manifestFile>src/main/resources/META-INF/MANIFEST.MF</manifestFile>
            </archive>
          </configuration>
        </plugin>
  
- далее собрать 
  - mvn compile
  - mvn package

- java -jar target/test3-mvn-project-1.0-SNAPSHOT.jar

*****
для сборки через идею
(в таком случае физически манифест можно не создавать, его сгенерит сама идея)

- project structure
- Artifacts
- add Jar Empty
- create manifest (тут надо самим создать структуру папок src/main/resources/META-INF)
- select main class
- select Module Output
- add Library Files
- ok
- В панеле идеи 
    - Build -> Rebuild Project
    - Build -> Build Artifacts...
  
Если не виден junit
- проверить депенденси
- в свойствах проекта добавить Global Library mvn именно эту версию junit
 
