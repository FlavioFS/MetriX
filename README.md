<h1 align="center">
<img align="center" src="https://raw.githubusercontent.com/FlavioFS/MetriX/master/img/metrix-logo.png">
<br>
MetriX
</h1>
<h3 align="center">:triangular_ruler: Exception Handling Metric Suite</h3>

## Description
*MetriX* analyzes your Java code and outputs several code quality metrics (detailed below). Its goal is to facilitate quality assurance.

## How to use
Please refer to our <a name="samples-link" href="src/samples/">**samples section**</a> for some code snippets.

## Metrics
### Coupling Metrics 
| Acronym  | Metric        | Description |
| :------: | :-----------: | ----------- |
| RCo | Raising Coupling   | The number of different exception types **raised** by all methods and constructors of a given class.   |
| SCo | Signaling Coupling | The number of different exception types **signaled** by all methods and constructors of a given class. |
| HCo | Handling Coupling  | The number of different exception types **handled** by all methods and constructors of a given class. |

### Size Metrics
| Acronym  | Metric        | Description |
| :------: | :-----------: | ----------- |
| NoR | Number of Raising   | The number of exception **raised** by a given class divided by a number of methods and constructors of this class.   |
| NoS | Number of Signaling | The number of exception **signaled** by a given class divided by a number of methods and constructors of this class. |
| NoH | Number of Handling  | The number of exception **handled** by a given class divided by a number of methods and constructors of this class. |
| NoEH | Number of Empty Handlers  | The number of **empty** handlers (i.e., catch blocs) in a given class. |
| NoGH | Number of General Handlers  | The number of **general** handlers in a given class (i.e., the catch blocks that catches java.lang.Exception or java.lang.Throwable). |

### Ratio Metrics
| Acronym  | Metric        | Description |
| :------: | :-----------: | ----------- |
| RoCLoC | Ratio of Catch Block Lines of Code | The sum of all **catch** blocks lines of code of given class divided by the number of line of codes of this class. |
| RoTLoC | Ratio of Try Block Lines of Code | The sum of all **try** blocks lines of code of given class divided by the number of line of codes of this class. |
| RoFLoC | Ratio of Finally Block Lines of Code | The sum of all **finally** blocks lines of code of given class divided by the number of line of codes of this class. |

### Concern Metrics
| Acronym  | Metric        | Description |
| :------: | :-----------: | ----------- |
| HDoS | Handling Density of Scattering | The density of scattering of handling code across methods of a given class. This metric is calculated using a formula proposed by <a href="#ref1">[1]</a>. |

### Usage Metrics
| Acronym  | Metric        | Description |
| :------: | :-----------: | ----------- |
| EHMU | Exception Handling Mechanism Usage | This metric turns 1 if a given class uses an exception handling construct or 0 otherwise. |

### References
<a href="http://www.cs.columbia.edu/~eaddy/publications/tse.online.pdf" target="_blank" name="ref1">:link: [1] Marc Eaddy, Thomas Zimmermann, Kaitlin D. Sherwood, Vibhav Garg, Gail C. Murphy, Nachiappan Nagappan, and Alfred V. Aho. 2008. Do Crosscutting Concerns Cause Defects?. IEEE Trans. Softw. Eng. 34, 4 (July 2008), 497-515.</a>