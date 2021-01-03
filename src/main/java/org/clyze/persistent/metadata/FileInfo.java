package org.clyze.persistent.metadata;

import java.nio.CharBuffer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.tools.JavaFileObject;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import org.clyze.persistent.model.BasicMetadata;

/**
 * This class holds per-file information and offers functions relevant to files.
 */
public abstract class FileInfo {

    /** Input source file path */
    protected final String inputFilePath;

    /** File path to store in "sourceFileName" property of elements */
    private final String sourceFileName;

    /** JSON output file name */
    private final String outputFilePath;

    /** The contents of the input source file */
    protected String source;

    /** The container of found elements in the input source file */
    private final BasicMetadata elements;

    public FileInfo(String packageName, String inputName, String inputFilePath, String source, BasicMetadata elements) {
        this.inputFilePath = inputFilePath;
        this.sourceFileName = packageName.replaceAll("\\.", "/") + inputName;
        this.outputFilePath = packageName.replaceAll("/", ".") + getBaseName(inputFilePath) + ".json";
        this.elements = elements;
        this.source = source;
    }

    public static String getFilePath(JavaFileObject file) {
        return file.getName();
    }

    @Override
    public int hashCode() {
        return inputFilePath.hashCode();
    }

    /**
     * Searches for a string in the text.
     *
     * @param text      string to search for in the source code
     * @param offset    character offset in the source code to start searching from
     * @return          "true" if the given text existed at the given offset; otherwise "false"
     */
    public boolean existsInSource(String text, long offset) {

        return text.regionMatches(0, source, (int)offset, text.length());
    }

    /**
     * Applies a regex on the text.
     *
     * @param offset    character offset in the source code to apply the regex
     * @param regex     the ... regex string! ^^
     * @return          "offset" plus the length of the string that resolved the regex
     */
    public long countRegexLength(long offset, String regex) {

        /*
         * First, search only in the next few characters (e.g. 64), after "offset". In practice, this should be enough because:
         * 1) The regex will always match. That is because the source should have already been successfully parsed and this
         *    method's caller should search with a reasonable regex, context-wise.
         * 2) At the moment, the used regex-es do not describe code that would require more than a few characters to express,
         *    thus even 64 characters should be enough for the regex to match.
         *
         * If not matched, then search in more characters and finally search the whole source from "offset" until the end.
         */

        Pattern p = Pattern.compile(regex);
        Matcher m;
        int start = (int)offset;
        int[] ends = {
            Math.min(source.length(), start + 64),
            Math.min(source.length(), start + 512),
            source.length()
        };
        int prevEnd = -1;

        for(int end : ends) {
            if(prevEnd == end) {
                // Do not repeat the exact same search
                break;
            }
            prevEnd = end;

            // "CharBuffer.wrap" is used instead of "source.substring" for better performance.
            // "String::substring" is O(N) instead of O(1) since Java 7.

            m = p.matcher(CharBuffer.wrap(source, start, end));
            if(m.find()) {
                return offset + m.end() - m.start();
            }
        }

        // Normally, it should never reach here.
        printPatternError(regex, offset);

        return offset;
    }

    protected abstract void printPatternError(String regex, long offset);
    public abstract long getLineNumber(long offset);
    public abstract long getColumnNumber(long offset);

    /**
     * Counts the number of whitespace characters.
     *
     * @param offset    character offset in the source code to start counting whitespaces
     * @return          the character offset of the first non-whitespace character after "offset-1"
     */
    public long countWhitespaces(long offset) {
        return countRegexLength(offset, "\\A(\\s*)");
    }

    /**
     * Counts the length of the method prefix.
     *
     * @param offset    character offset in the source code where the method reference prefix (e.g. java.lang.String::) begins
     * @return          the character offset where the referenced method name begins
     */
    public long countMethodRefPrefix(long offset) {
        return countRegexLength(offset, "\\A([^:]*::[\\s]*)");
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    String getOutputFilePath() {
        return outputFilePath;
    }

    public BasicMetadata getElements() {
        return elements;
    }

}
