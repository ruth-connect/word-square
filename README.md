# word-square
Given an n*n grid, and a set of letters, generate a valid word square.

## Installation
1. `git clone https://github.com/ruth-connect/word-square.git`
2. `cd word-square`
3. `sh mvnw install`

N.B. If installing on a Windows machine, you will need to run `mvnw.cmd install` for step 3 above.

## Running
**IMPORTANT:** The application must be run from the same directory that the Maven install (as in step 3 above) was run from.

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

#### 5-letter word square
`java -jar target/wordsquare-0.0.1-SNAPSHOT-jar-with-dependencies.jar 5 aaaeeeefhhmoonssrrrrttttw`

Expected output:
```
feast
earth
armer
steno
throw
```

#### 7-letter word square
**WARNING: This will take several minutes to run! Best to enjoy a nice coffee while this is running!**

`java -jar target/wordsquare-0.0.1-SNAPSHOT-jar-with-dependencies.jar 7 aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy`

Expected output:
```
bravado
renamed
analogy
valuers
amoebas
degrade
odyssey
```

## Caveats
1. The test for the 7-letter word square currently has an @Ignore annotation. This is because this
test takes several minutes to run, which would delay the Maven build (as Maven runs all the tests during
the build process). Please remove the @Ignore annotation if you want to run this test.
2. When executing from the command line, only the first valid word square encountered is returned. We do, however, check for all valid word squares in the unit tests (there are a couple of cases where we get
two word squares returned).