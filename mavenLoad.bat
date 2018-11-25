mvn install:install-file -Dfile=./local-maven-repo/ojdbc8.jar -DgroupId=oracledriver -DartifactId=oracledriver -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true & mvn install:install-file -Dfile=./local-maven-repo/Agent-1.0-SNAPSHOT.jar -DgroupId=Agent -DartifactId=Agent -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true

