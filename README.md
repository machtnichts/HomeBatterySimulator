# HomeBatterySimulator
Simulator for the home battery (PV production + house load + battery)

example runs:

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 0 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 154.96kwh used: 334.35kwh exported: 420.68kwh in battery: 0.00kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 1 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 99.22kwh used: 334.35kwh exported: 364.94kwh in battery: 0.00kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 2 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 68.06kwh used: 334.35kwh exported: 333.08kwh in battery: 0.70kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 3 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 45.58kwh used: 334.35kwh exported: 309.60kwh in battery: 1.70kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 4 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 35.06kwh used: 334.35kwh exported: 298.08kwh in battery: 2.70kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 5 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 30.29kwh used: 334.35kwh exported: 292.31kwh in battery: 3.70kwh

java -jar target/batterySimulator-1.0-SNAPSHOT-shaded.jar -bc 10 -i src/test/resources/2019-03.csv -t 12

result: produced: 600.07kwh imported: 19.56kwh used: 334.35kwh exported: 276.58kwh in battery: 8.70kwh

warning: the files exported from solarEdge are aggregated for 15 min, use -t 4 for this files or results will be wrong!