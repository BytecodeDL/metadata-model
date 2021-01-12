package org.clyze.persistent.model;

import java.util.*;

/**
 * A class providing a common container of basic Doop metadata.
 *
 * The metadata is generated by processing some form of a
 * syntactic representation (e.g. Java source file, Jimple IR).
 */
public class BasicMetadata {
    public final Set<Class> classes                  = new HashSet<>();
    public final Set<Field> fields                   = new HashSet<>();
    public final Set<Method> methods                 = new HashSet<>();
    public final Set<Variable> variables             = new HashSet<>();
    public final Set<MethodInvocation> invocations   = new HashSet<>();
    public final Set<HeapAllocation> heapAllocations = new HashSet<>();
    public final Set<Usage> usages                   = new HashSet<>();
    public final Set<StringConstant> stringConstants = new HashSet<>();

    /**
     * Return a metadata collection sorted by id, to make output canonical.
     * @param set    the collection of elements (that have Doop ids)
     * @param <T>    the actual type of the set elements
     * @return       the sorted output list
     */
    public static <T extends SymbolWithDoopId> List<T> getSortedByDoopId(Set<T> set) {
        List<T> ret = new ArrayList<>(set);
        ret.sort(Comparator.comparing(SymbolWithDoopId::getDoopId));
        return ret;
    }
}
