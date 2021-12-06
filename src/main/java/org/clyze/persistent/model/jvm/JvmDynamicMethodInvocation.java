package org.clyze.persistent.model.jvm;

/** The model of "invokedynamic" instructions. */
@SuppressWarnings("unused")
public class JvmDynamicMethodInvocation {
    /**
     * Creates an instruction ID for a generic invokedynamic instruction.
     *
     * @param bootName     the simple name of the boot method
     * @param dynamicName  the simple name of the dynamic method
     * @return             the invokedynamic ID
     */
    public static String genericId(String bootName, String dynamicName) {
        return "invokedynamic_" + bootName + "::" + dynamicName;
    }
}
