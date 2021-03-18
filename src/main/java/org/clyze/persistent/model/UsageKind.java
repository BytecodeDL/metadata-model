package org.clyze.persistent.model;

/** The kind of a usage of an existing element. */
public enum UsageKind {
	/** Used as a package. */
	PACKAGE,
	/** Used as a type. */
	TYPE,
	/** Used as a function. */
	FUNCTION,
	/** Used to read data. */
	DATA_READ,
	/** Used to write data. */
	DATA_WRITE
}
