This library models code as metadata that can be used for applications
such as code navigation, high-level code structure analysis, or IDE
integration.

Currently, two metadata models are provided:

* Code metadata for Java (and Java-like) code (package
  `org.clyze.persistent.model.jvm`). Elements of this metadata provide
  Java-specific information (such as `static` modifiers and class
  supertypes). Example generators of such metadata are the
  [source-ir-fitter
  tool](https://github.com/plast-lab/source-ir-fitter/), [the Doop
  Jimple parser](https://bitbucket.org/yanniss/doop), the [Doop
  javac plugin](https://bitbucket.org/yanniss/doop-jcplugin/), and
  the [Kotlin compiler plugin](https://github.com/efstratia-ev/KotlinCompilerPlugin).
  Doop is also a consumer of such metadata, to allow for SARIF integration
  with IDEs.

* Language-agnostic source code metadata that categorize source
  constructs (and provide their location). An example generator of
  such metadata is
  [antlr2datalog](https://github.com/gfour/antlr2datalog/).
