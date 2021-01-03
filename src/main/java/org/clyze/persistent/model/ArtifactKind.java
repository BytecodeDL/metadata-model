package org.clyze.persistent.model;

/**
 * Kinds of analysis inputs/artifacts.
 */
public enum ArtifactKind {
	/** Android Archive (AAR) format. */
	AAR,
	/** Android Package (APK) format. */
	APK,
	/** Standard JAR format. */
	JAR,
	/** Java WAR file. */
	WAR,
	/** Android .dex file (Dalvik). */
	DEX,
	/** Other kinds, not covered here. */
    OTHER
}
