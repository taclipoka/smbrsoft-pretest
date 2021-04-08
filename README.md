Downloads file from url, splits it to tokens and prints count of each token.

USAGE

Compile JAR file.
Execute jar-file with parameters

java -jar smbrsoft-pretest.jar [options] url

Options:
  -s              use Scanner with regexp instead
  
  -d \<delimiter>  use delimiter, default .,:;!?"[]{}<>/'
  
  
Example usage

java -jar smbrsoft-pretest.jar https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

splits by default delimiters .,:;!?"[]{}<>/'

java -jar smbrsoft-pretest.jar -s -d \W https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html https://simbirsoft.timepad.ru/event/1569477/

splits two files by \W pattern (not word character)
