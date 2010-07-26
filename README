Example Usage
=============

Build the project
    mvn assembly:assembly

Populate some input 
    ./hadoop-0.20 fs -put text.file hdfs:///tmp/input


Get the usage help
    ./hadoop-0.20 jar hadoop-maven-stub-1-jar-with-dependencies.jar com.github.emtrane.Main

Run the word count example
    ./hadoop-0.20 jar hadoop-maven-stub-1-jar-with-dependencies.jar com.github.emtrane.Main -i /tmp/input -o /tmp/output

View the output
    ./hadoop-0.20 fs -cat /tmp/output/*
