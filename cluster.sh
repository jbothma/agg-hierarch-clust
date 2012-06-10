CLASSPATH="bin"
MAINCLASS="uk.co.jbothma.taxonomy.ClusterFromFile"
STARTMEM=200m
MAXMEM=1G
java -Xms$STARTMEM -Xmx$MAXMEM -XX:+HeapDumpOnOutOfMemoryError -cp $CLASSPATH $MAINCLASS $1
