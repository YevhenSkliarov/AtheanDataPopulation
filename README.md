# AthenaDataPopulation installation
1. Pull Project.
2. Install dependencies.

# Configuration
1. Open /src/main/resources/config.properties file.
2. Set ACCESS_KEY, SECRET_KEY, S3_OUTPUT_LOCATION, REGION and OUTPUT_SUBFOLDER(subfolder will created for each table).
  * ACCESS_KEY for ex. AKIAJ47TQIDSE6YWFYBT.
  * SECRET_KEY for ex. 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.
  * REGION for ex. us-east-1.
  * S3_OUTPUT_LOCATION for ex. s3://aws-query-results-238481145111-us-east-1/test/.
  * OUTPUT_SUBFOLDER for ex. 's3://aws-athena-query-results-238481145111-us-east-1/test/%s' - this should be in single brackets '' and with %s at the end.
3. Open /src/main/java/helpers/Config.java.
4. Set Data in constructor to your values (Database should be already exist).
  * databaseName - name of your data base.
  * tableName - this name will be use to create tables for ex. tableName = customer_data will create such tables customer_data1, customer_data2, customer_data3 ...
  * tablesCount - how many tables should be created.
  * rowsCount - how many rows will be inserted in each table.
  * copyFrom - this parameter use if you want to copy data from some table to another tables (boolean).
  * addColumnToExistingTable - this parameter use if you want update existing table with some additional column.
  * copyFromExistingTable - this parameter use when you want to set from which table data will be copied.
Also you have constructor where you can pass all this params.
By default this code set to create 1000 tables in scale database and insert 10 rows in each.
Please note that if you set copyFrom = true, code will copy from one table to another but it won't be insert rows.
Also you can change table structure by changing Customer.java file add/remove/update fields.

# Best Practise
1. Run this script with the max count of tables that you need to create all tables with min rows to be inserted (INSERT row operation takes to musch time ~1300 rows per hour).
2. After all tales was created set tablesCount = 1.
3. Set rowsCount to the target count and run code and wait for the end.
4. Set copyFrom = true and set copyFromExistingTable with the target count of rows.
5. Run script. This will copy data to all tables. This proccess much faster(It copy 5000 rows tables to 1000 tables in ~1 hour).
