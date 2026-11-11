# Testbed: Jaybird in a Graal native image

This is a simple application based on https://github.com/FirebirdSQL/jaybird/issues/965.
Its purpose is to demonstrate [Jaybird](https://github.com/FirebirdSQL/jaybird)
can be compiled to a native image with Graal.

## Instructions

* The application expects a Firebird server running on localhost port 3050, with
  user SYSDBA and its well-known and insecure password (or change it in the code)
* Use `./gradlew nativeRun`
* The native image, if it doesn't already exist, is created, and the following
  output is produced (version will vary on your running Firebird server):

  ```
  > Task :nativeRun
  Firebird 5.0.4
  Prepared statement: 42
  Driver loaded; the connection failed: Dynamic SQL Error; SQL error code = -804; Data type unknown [SQLState:HY004, ISC error code:335544573]
  ```
  
  This last error is expected: it's used to demonstrate error message resources
  are included in the native image.
