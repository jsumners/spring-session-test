# session-test

A simple project to test integrating [spring-session][ss] into
a skeleton project as created by [jrfom-spring-webmvc][jrf].

Running the web application and then navigating to the index
controller will result in session data being written to an
embedded Redis database. This can be verified by using
`redis-cli` and issuing a `KEYS *` command to it.

[ss]: http://projects.spring.io/spring-session/
[jrf]: https://bitbucket.org/jsumners/jrfom-spring-webmvc-archetype