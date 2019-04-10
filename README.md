# HomeBatterySimulator
Simulator for the home battery (PV production + house load + battery)

example runs:

java -jar batterySimulator.jar -bc 20 -i src/test/resources/2019

batteryCapacity(kwh), batteryDrawn(kwh,saved), batteryStored(kwh), produced(kwh), imported(kwh), used(kwh), exported(kwh)
0                   , 0                      , 0                 , 8790         , 2793         , 5417     , 6167
1                   , 499                    , 500               , 8790         , 2294         , 5417     , 5667
2                   , 827                    , 829               , 8790         , 1967         , 5417     , 5338
3                   , 1089                   , 1092              , 8790         , 1704         , 5417     , 5075
4                   , 1279                   , 1283              , 8790         , 1515         , 5417     , 4884
5                   , 1413                   , 1418              , 8790         , 1380         , 5417     , 4749
6                   , 1504                   , 1510              , 8790         , 1289         , 5417     , 4657
7                   , 1565                   , 1572              , 8790         , 1228         , 5417     , 4595
8                   , 1609                   , 1617              , 8790         , 1184         , 5417     , 4550
9                   , 1640                   , 1649              , 8790         , 1153         , 5417     , 4518
10                  , 1659                   , 1669              , 8790         , 1134         , 5417     , 4498
11                  , 1674                   , 1685              , 8790         , 1119         , 5417     , 4482
12                  , 1688                   , 1700              , 8790         , 1105         , 5417     , 4467
13                  , 1701                   , 1714              , 8790         , 1092         , 5417     , 4453
14                  , 1713                   , 1727              , 8790         , 1081         , 5417     , 4440
15                  , 1721                   , 1736              , 8790         , 1072         , 5417     , 4431
16                  , 1729                   , 1745              , 8790         , 1064         , 5417     , 4422
17                  , 1736                   , 1753              , 8790         , 1057         , 5417     , 4413
18                  , 1742                   , 1760              , 8790         , 1051         , 5417     , 4406
19                  , 1748                   , 1767              , 8790         , 1045         , 5417     , 4399
20                  , 1754                   , 1774              , 8790         , 1040         , 5417     , 4393

warning: the files exported from solarEdge are aggregated for 15 min, use -t 4 for this files or results will be wrong!