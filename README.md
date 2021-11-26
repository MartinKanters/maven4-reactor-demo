# ECO Search

### Project's purpose
This project is a sample multi-module Maven project which is used to showcase the improvements that have been done in the reactor of Maven.  
Maarten Mulders and I will show this project during the talk "Making Maven Marvellous" which we have shown at several conferences and meetups.  

The use-case in this example is that you are a developer working on the "app" submodule and do not want to waste time building other parts of the system.

### Try it yourself
#### Resolving "inter module" dependencies 
When using Maven 3.6.3 the following invocations will fail because dependencies on modules in the same multi module project cannot be resolved:

- Let's first build all dependencies to prove it builds.  
  Notice the slow tests in the "scraper" module, which is not required to be built for building "app"  
  `mvn test` 
- Prevent running the scraper module by trying:
  - `mvn test --resume-from :app`  
  - `mvn test --projects :app`
  - `mvn test --file app/pom.xml`  
  - `cd app && mvn test`  
  Starting from Maven 4 this is resolved.

### Try it out yourself

There are several options for trying it out Maven 4:

- Using Maarten's [brew formula](https://github.com/mthmulders/homebrew-maven-snapshot#how-do-i-install-this-formula): `brew install mthmulders/maven-snapshot/maven-snapshot`
- Using Martin's [Chocolatey package](https://community.chocolatey.org/packages/maven-snapshot): `choco install maven-snapshot -y`
- At the time of writing, it has not been released yet, but it can be built from sources from https://github.com/apache/maven.

### Related JIRA issues:
  - [MNG-4660](https://issues.apache.org/jira/browse/MNG-4660)
  - [MNG-5760](https://issues.apache.org/jira/browse/MNG-5760)
  - [MNG-6863](https://issues.apache.org/jira/browse/MNG-6863)
  - [MNG-6118](https://issues.apache.org/jira/browse/MNG-6118)
