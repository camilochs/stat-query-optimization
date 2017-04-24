# stat-query-optimization

Basic stats

Table(table_stat.txt):

TABLE_NAME|ROWS_NUMBER|COLUMN_NUMBER

* Customer|150000|8
* Orders|1500000|9
* Nation|25|4

Columns(<column_name>_stat.txt):

COLUMN_NAME|DISTINCT|NULL|MAX|MIN|AVERAGE|

* Total_price|1500000|0|555285.16|857.71|151219.53763164

Normal---------------

* [Selection] Rows found: 0

Total time: 0.007 seconds.

* [Agregation Max] total_price: 555285.16

Total time: 0.094 seconds.

* [Agregation Min] total_price: 857.71

Total time: 0.093 seconds.

* [Distinct value] total_price: 1464556

Total time: 0.623 seconds.

* [Join Customer and Orders tables] Rows found: 133

Total time: 2.069 seconds.

* [Join Nations and Customers tables] Rows found: 24

Total time: 0.005 seconds.

With Optimization---------------

* [Selection] Rows found: 0

Total time: 0.001 seconds.

* [Agregation Max] total_price: 555285.16

Total time: 0.0 seconds.

* [Agregation Min] total_price: 857.71

Total time: 0.0 seconds.

* [Distinct value] total_price: 1500000

Total time: 0.0 seconds.
