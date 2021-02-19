This library models source code metadata.

Currently, two metadata models are provided:

* Source code metadata for Java (and Java-like) code (package
  `org.clyze.persistent.model.jvm`). Elements of this metadata provide
  Java-specific information (such as `static` modifiers and class
  supertypes). Example clients of this model are the [source-ir-fitter
  tool](https://github.com/plast-lab/source-ir-fitter/) and the [Doop
  javac plugin](https://bitbucket.org/yanniss/doop-jcplugin/).

* Language-agnostic source code metadata that categorize source
  constructs (and provide their location). An example client of this
  model is [antlr2datalog](https://github.com/gfour/antlr2datalog/).
