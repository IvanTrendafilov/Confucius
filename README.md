Confucius - lightweight configuration
==============================================

A lightweight Java configuration library and a drop-in replacement for `java.util.Properties`. Confucius is the easiest way to add configuration to your application. It is licensed under the Apache License, Version 2.0.

Why use Confucius?
------------------

* a __fluent API__ to access key-value pairs, as well as lists
* __meticulously tested__ - with 84 test cases and __97% branch coverage__
* support for a powerful __context-based Properties file format__
* support for the __standard Java Properties file format__
* __automatically load__ configuration from a specified Properties file or InputStream
* use a single Properties file to __wire multiple app instances via contexts__
* tiny, yet powerful - __only 16K on disk__
* no XML required... unless you use Maven

Getting started
---------------

Four steps are required to get started with Confucius.

First, you need the Confucius jar file on your classpath. Confucius is available from Maven Central, so add to your `pom.xml` (or [manually download](http://repo1.maven.org/maven2/org/trendafilov/confucius/confucius/1.2.2/), if need be):

```xml
    <dependency>
        <groupId>org.trendafilov.confucius</groupId>
        <artifactId>confucius</artifactId>
        <version>1.2.2</version>
    </dependency>
```

Second, define your configuration file. In this example, we will showcase the recommended context-based Properties format with three contexts, including the mandatory `Default` context. By convention, a Confucius Properties file has a __.cfg__ extension. 

Alternatively, you can supply any existing standard Java Properties file, but you will not be able to take advantage of context-driven configuration.

__shop.cfg__:
```properties
[Default]
slogan  = The best bagels store in town.
bagels  = plain, cinnamon raisin, chocolate

[NY]
slogan  = NY bagel place - Try them to love them.
bagels  = plain, plain /w cream cheese, whole wheat 

[SF]
slogan  = The Round Food Experience(tm).
```

Note: the `SF` context is missing the `bagels` key. This is intentional and will cause the value of the `bagels` property to be inherited from the `Default` context.

Third, reference the configuration keys in your code.

**BagelShop.java**:

```java
package org.trendafilov.confucius.examples.bagels;

import org.trendafilov.confucius.*;

public class BagelShop {
    
    public static void main(String[] args) {
        Configurable config = Configuration.getInstance();
        System.out.println("Hello and welcome to our store. " + config.getStringValue("slogan"));
        System.out.println("Today's selection: " + config.getStringList("bagels"));
    }
}
```

Finally, to launch the resulting application, specify the `conf.properties` and `conf.context` system properties using the `-D` switch, as follows:

__Referencing the NY context__:
```bash
$ java -Dconf.properties=`pwd`/shop.cfg -Dconf.context=NY -cp *.jar org.trendafilov.confucius.examples.bagels.BagelShop

Hello and welcome to our store. NY bagel place - Try them to love them.
Today's selection: [plain, plain /w cream cheese, whole wheat]
```
__Referencing the SF context__:
```bash
$ java -Dconf.properties=`pwd`/shop.cfg -Dconf.context=SF -cp *.jar org.trendafilov.confucius.examples.bagels.BagelShop

Hello and welcome to our store. The Round Food Experience(tm).
Today's selection: [plain, cinnamon raisin, chocolate]
```

__Without specifying a context__:

```bash
$ java -Dconf.properties=`pwd`/shop.cfg -cp *.jar org.trendafilov.confucius.examples.bagels.BagelShop

Hello and welcome to our store. The best bagels store in town.
Today's selection: [plain, cinnamon raisin, chocolate]
```

Examples are available at [Confucius-examples](https://github.com/IvanTrendafilov/Confucius-examples).

FAQ
---

__Q: How do I get the documentation?__  
A: Ideally, you will be referencing the documentation as assisted by your IDE. If you need to look at an HTML version of the javadoc, please checkout the project and execute `mvn javadoc:javadoc`.

__Q: I want to see configuration information in my log files. How?__  
A: Confucius exposes logging via SLF4J. To enable it, please place the desired binding on your classpath, [as instructed here](http://www.slf4j.org/manual.html).

__Q: I want to inject a `Configurable` instance via a dependency injection framework. How do I do this correctly?__  
A: DI containers should control the scope of the `Configurable` singleton. Therefore, please wire the `InjectableConfiguration` object instead of `Configuration` with your DI framework of choice.

__Q: Please summarise how context-based Properties files work?__  
A: At a minimum, you must specify a `Default` context section. Its contents are always processed and are processed first. Each context section contains a list of key-value pairs. Additional contexts may be defined - if so, you should specify which context should be processed via the `conf.context` property. Please note - the values of keys which already exist in `Default` will be overriden. Furthermore, it is possible to define variable substitutions, including substitutions which span the `Default` and the user-specified context.

__Q: Please explain variable substitution?__  
A: Sure, here is an example:

```properties
...
name            = John Smith
home.address    = 22 Main street
mailing.address = ${home.address}
...
```

the value `mailing.address` is set to be whatever the value of `home.address` is.  
In general, you could also assign variables to other variables and there is no limit on the depth of variable references. Circular definitions are unresolvable and will be treated as literals.


__Q: I need to use a standard existing Java properties file, how do I set this up?__  
A: There is no special setup required. Just set the `conf.properties` property to the file path, as usual.

__Q: Why not just use `java.util.Properties`?__  
A: Confucius aims to fix a lot of the issues present in that class (extends Hashtable, no clean way to interpolate with a HashMap, doesn't use generics, manual type conversions required, and so on) and adds several powerful features, whilst still maintaining the simplicity of the Properties concept and compatibility with existing configuration files.

__Q: Why NoXML? XML is much more expressive and powerful.__  
A: Indeed, it is! XML is a great tool for the right tasks. Nevertheless, for the vast majority of applications, XML configuration adds more complexity than value. This results in configuration files which are difficult to understand, cumbersome to update, and hard to maintain. For example, if we were to translate the Confucius properties file from the example above to XML, we might end up with something like this:

__config.xml__:
```xml
<configuration>
    <Default>
        <property name="slogan">
            <value>The best bagels store in town.</value>
        </property>
        <util:list name="bagels">
            <value>plain</value>
            <value>cinnamon raisin</value>
            <value>chocolate</value>
        </util:list>
    </Default>
    <NY>
        <property name="slogan">
            <value>NY bagel place - Try them to love them.</value>
        </property>
        <util:list name="bagels">
            <value>plain</value>
            <value>plain /w cream cheese</value>
            <value>whole wheat</value>
        </util:list>
    </NY>
    <SF>
        <property name="slogan">
            <value>The Round Food Experience(tm).</value>
        </property>
        <util:list name="bagels"> <!-- no inheritance -->
            <value>plain</value>
            <value>cinnamon raisin</value>
            <value>chocolate</value>
        </util:list>
    </SF>
</configuration>
```

Even in this trivial example, the corresponding XML file has grown notably large. Confucius aims to capture the 90% case and save you time.

Support, questions, contributions
---------------------------------

Please open a ticket and we will discuss. 

Contributors
------------

* Bartłomiej Dudała @bartlomiej-dudala. Work on support for InputStreams in InjectableConfiguration.
