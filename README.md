# word-square
Given an n*n grid, and a set of letters, generate a valid word square.

## Installation
1. `git clone https://github.com/ruth-connect/word-square.git`
2. `cd word-square`
3. `sh mvnw install`

## Running
Usage: `java -jar target/wordsquare-0.0.1-SNAPSHOT-jar-with-dependencies.jar <length> <letters>`

where:

`<length>` is the length of each word in the word square

`<letters>` is the letters to create the word square from

### Examples
#### 3-letter word square
`java -jar target/wordsquare-0.0.1-SNAPSHOT-jar-with-dependencies.jar 3 ddggoooox`

Expected output:
```
dog
oxo
god
```

#### 4-letter word square
`java -jar target/wordsquare-0.0.1-SNAPSHOT-jar-with-dependencies.jar 4 aaccdeeeemmnnnoo`

Expected output:
```
moan
once
acme
need
```
