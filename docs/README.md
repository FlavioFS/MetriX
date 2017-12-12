<h1 name="metrix-docs">:green_book: Documentation</h1>

## Index
 - **<a href="#coupling">Coupling</a>**
 	- <a href="#rco">(RCo) Raising Coupling</a>
 	- <a href="#sco">(SCo) Signaling Coupling</a>
 	- <a href="#hco">(HCo) Handling Coupling</a>
 - **<a href="#size">Size</a>**
 	- <a href="#nor">(NoR) Number of Raising</a>
 	- <a href="#nos">(NoS) Number of Signaling</a>
 	- <a href="#noh">(NoH) Number of Handling</a>
 	- <a href="#noeh">(NoEH) Number of Empty Handlers</a>
 	- <a href="#nogh">(NoGH) Number of General Handlers</a>
 - **<a href="#ratio">Ratio</a>**
 	- <a href="#rocloc">(RoCLoC) Ratio of Catch Block Lines of Code</a>
 	- <a href="#rotloc">(RoTLoC) Ratio of Try Block Lines of Code</a>
 	- <a href="#rofloc">(RoFLoC) Ratio of Finally Block Lines of Code</a>
 - **<a href="#concern">Concern</a>**
 	- <a href="#hdos">(HDoS) Handling Density of Scattering</a>
 - **<a href="#usage">Usage</a>**
 	- <a href="#ehmu">(EHMU) Exception Handling Mechanism Usage</a>
 - **<a href="#refs">References</a>**


## <div name="coupling">Coupling Metrics</div>
In general, coupling metrics search through every constructor and method of each class and count occurrences of **different** exception types. These metrics do not count twice the same type thus **coupling** similar results.

### <div name="rco">(RCo) Raising Coupling</div>
Raising Coupling counts raised exception types, that is, when a method or constructor **throws** an exception. See the example below:
```java
// RCo == 2.0
public class RaisingClass {

	public void raisingMethod1 () {
		throw new NullPointerException("Raising!");	// 1
	}

	public void raisingMethod2 () {
		throw new IOException("Raising!");			// 2
	}

}
```

### <div name="sco">(SCo) Signaling Coupling</div>
Signaling Coupling counts signaled exception types, that is, when a method or constructor **signals** it can lead to an exception. See the example below:
```java
// SCo == 3.0
public class SignalingClass {
	
	// 1
	public void signalingMethod1 () throws Exception {
	}

	// 2 - Same type: IOException
	public void signalingMethod2_1 () throws IOException { }
	public void signalingMethod2_2 () throws IOException { }

	// 3
	public void signalingMethod3 () throws NullPointerException { }

}
```

### <div name="hco">(HCo) Handling Coupling</div>
Handling Coupling counts handled exception types, that is, the amount of **catch** blocks. See the example below:
```java
// HCo == 1.0
public class SignalingClass {

	public void handlingMethod () {
		try {

		} catch (IOException e) {
			// 1
		}
	}

}
```


## <div name="size">Size Metrics</div>
The metrics belonging to this group do not couple similar metrics and do account for each occurrence. Some results are absolute values and others are proportions.

### <div name="nor">(NoR) Number of Raising</div>
Number of Raising counts raised exceptions throughout the class and divides it by the amount of methods and constructors on it.
```java
// NoR == 1/2 == 0.5
public class RaisingClass {
	
	// 1 method
	public RaisingClass () {}

	// 2 methods
	public void raisingMethod () {
		throw new Exception("Raising!");	// 1 exception
	}

}
```

### <div name="nos">(NoS) Number of Signaling</div>
Number of Signaling counts signaled exceptions throughout the class and divides it by the amount of methods and constructors on it.
```java
// NoS == 2/3 = 0.6666
public class SignalingClass {
	
	// 1 method
	public SignalingClass () throws Exception {}	// 1 exception

	// 2 methods
	public void signalingMethod () throws IOException {} // 2 exceptions

	// 3 methods
	public void nonSignalingMethod () {}

}
```

### <div name="noh">(NoH) Number of Handling</div>
Number of Handling counts handled exceptions (**catch** blocks) throughout the class and divides it by the amount of methods and constructors on it.
```java
// NoH == 2/1 = 2.0
public class HandlingClass {

	// 1 method
	public void handlingMethod () {
		try { }
		catch (Exception e) { }		// 1 exception

		try { }
		catch (Exception e) { }		// 2 exception
	}

}
```

### <div name="noeh">(NoEH) Number of Empty Handlers</div>
Number of Empty Handlers counts **empty** catch blocks throughout the class.
```java
// NoEH == 1.0
public class EmptyClass {
	
	int fakeAttribute;

	public void method () {
		try { }
		catch (Exception e) { }		// 1 empty exception

		try { }
		catch (Exception e) {
			fakeAttribute++;
		}
	}

	public void nonHandlingMethod () {}

}
```

### <div name="nogh">(NoGH) Number of General Handlers</div>
Number of General Handlers counts catch blocks handling the Exception class (and not a child of it).
```java
// NoGH == 2.0
public class GeneralClass {
	
	public void method () {
		try { }
		catch (IOException e) { }	// Specific exception

		try { }
		catch (Exception e) { }		// 1 general exception

		try { }
		catch (Exception e) { }		// 2 general exceptions
	}

	public void nonHandlingMethod () {}

}
```


## <div name="ratio">Ratio Metrics</div>
Ratio metrics evaluate the *proportion* of code (by *length*) dedicated to try-catch-finally blocks.

### <div name="rocloc">(RoCLoC) Ratio of Catch Block Lines of Code</div>
The **catch** blocks length divided by the whole class length (mesured in lines of code).
```java
// RoCLoC == 6/20 == 0.3
public class CatchClass {
	// (class) 1

	public void method () {
		try { }
		catch (IOException e) {
		}							// (catch) 1

		try { }
		catch (Exception e) {
									// (catch) 2
		}							// (catch) 3

		try { }
		catch (Exception e) {
									// (catch) 4
									// (catch) 5
		}							// (catch) 6
	}

}	// (class) 20
```

### <div name="rotloc">(RoTLoC) Ratio of Try Block Lines of Code</div>
The **try** blocks length divided by the whole class length (mesured in lines of code).
```java
// RoTLoC == 4/18 == 0.2222
public class TryClass {
	// (class) 1

	public void method () {
		try {
		} catch (IOException e) {}	// (try) 1

		try {
									// (try) 2
		}							// (try) 3
		catch (Exception e) {		// (try) 4 <- does count
		}

		try { }						// Does not count (zero lines)
		catch (Exception e) {}
	}


}	// (class) 18
```

### <div name="rofloc">(RoFLoC) Ratio of Finally Block Lines of Code</div>
The **finally** blocks length divided by the whole class length (mesured in lines of code).
```java
// RoTLoC == 3/20 == 0.15
public class TryClass {
	// (class) 1

	public void method () {
		try {}
		catch (IOException e) {}
		finally {}					// Does not count (zero lines)

		try { }
		catch (Exception e) {
		} finally {
									// (finally) 1
		}							// (finally) 2

		try { }
		catch (Exception e) {}
		finally {
		}							// (finally) 3
	}

}	// (class) 20
```

## <div name="concern">Concern Metrics</div>
The metric in this group belongs to a <a href="#ref1">scientific research</a> that studies the side-effects of crosscutting concerns.

### <div name="hdos">(HDos) Handling Density of Scattering</div>
This metric is calculated using a formula proposed by <a href="#ref1">[1]</a>.

## <div name="usage">Usage Metrics</div>
This class contains a metric that decides if the exception handling feature (try-catch-finally) was used or not.

### <div name="rocloc">(EHMU) Exception Handling Mechanism Usage</div>
Outputs 1.0 if there are any exceptions in the class, 0.0 otherwise.
```java
// EHMU == 0.0
public class EHMUClass0 {
	public EHMUClass0 () {}
	public void method () { }
}

// EHMU == 1.0
public class EHMUClass1 {	
	public EHMUClass1 () {}
	public void method () {
		try {}
		catch (IOException e) {}
	}
}
```

## <div name="refs">References</div>
<a href="http://www.cs.columbia.edu/~eaddy/publications/tse.online.pdf" target="_blank" name="ref1">:link: [1] Marc Eaddy, Thomas Zimmermann, Kaitlin D. Sherwood, Vibhav Garg, Gail C. Murphy, Nachiappan Nagappan, and Alfred V. Aho. 2008. Do Crosscutting Concerns Cause Defects?. IEEE Trans. Softw. Eng. 34, 4 (July 2008), 497-515.</a>

<h3 align="center"><a href="https://github.com/FlavioFS/MetriX/#metrix-home">:back:</a></div>
