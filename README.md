Базовая работа с БД через JDBC на примере СУБД MySql.

Пакеты и типы, представляющие интерес:

```
java.sql
	DriverManager
		.getConnection()
	Connection
		.prepareStatement()
	PreparedStatement
		.setInt(), .setString() ...
	ResultSet
		.next()
		.getInt(), .getString() ...
	SQLException
```

