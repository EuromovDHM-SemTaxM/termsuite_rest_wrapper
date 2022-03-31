# termitup_rest_wrapper

## Prerequisites: 
Apache Maven, Java 1.13+

## Compilation: 
```shell
git clone https://github.com/EuromovDHM-SemTaxM/termsuite_rest_wrapper.git
de termsuite_rest_wrapper
mvn clean package
```

## Running
```shell
java -Dserver.port=8083 -jar target/termsuite-rest-0.0.1-SNAPSHOT.jar
```

## Querying
Example in python
```python
import requests
corpus = "MYCORPUS..."
result = requests.post("http://localhost:8083/extract_terminology?language=en", data=corpus,
                      headers={'Accept': 'application/json', 'Content-Type': 'plain/text'}).json()
```