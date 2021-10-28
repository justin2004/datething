# datething

## what

An Apache Jena value function wrapper around the parse function of [Duckling (old)](https://github.com/facebookarchive/duckling_old).
The parse function can parse a string like:
```
"the first Tuesday of October"
```
into 
```
{:value "2014-10-07T00:00:00.000-07:00"
                                   :grain :day}
```

## why

This allows one to use the parse function inside a SPARQL query to convert strings representing dates into xsd:dateTime values.

## how

- have make and docker installed

- git clone this repo

- cd into it

- run `make`

- Then find `target/datething-0.1.0-SNAPSHOT-standalone.jar` and put it on your Apache Jena Fuseki classpath.

- Then invoke the parse function in a SPARQL query like so:
```
PREFIX datething: <java:datething.>
select *
WHERE {
  ?row xyz:when ?when_string .
  bind(strdt(datething:parse(?when_string),xsd:dateTime) as ?when)
}'

```
- you can also follow [this]() blog post for more detail instructions.

## notes / limitations

You can't parse a literal like:             `bind(strdt(datething:parse("12 oct 2021"),xsd:dateTime) as ?when)`.
You can only parse a variable binding like: `bind(strdt(datething:parse(?when_string),xsd:dateTime) as ?when)`.
