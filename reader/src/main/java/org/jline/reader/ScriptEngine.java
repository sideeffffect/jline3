/*
 * Copyright (c) 2002-2020, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * https://opensource.org/licenses/BSD-3-Clause
 */
package org.jline.reader;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

/**
 * Manage scriptEngine variables, statements and script execution.
 *
 * @author <a href="mailto:matti.rintanikkola@gmail.com">Matti Rinta-Nikkola</a>
 */
public interface ScriptEngine {

    /**
     *
     * @return scriptEngine name
     */
    String getEngineName();

    /**
     *
     * @return script file name extensions
     */
    Collection<String> getExtensions();

    /**
     * Tests if console variable exists
     * @param name variable name
     * @return true if variable exists
     */
    boolean hasVariable(String name);

    /**
     * Creates variable
     * @param name variable name
     * @param value value
     */
    void put(String name, Object value);

    /**
     * Gets variable value
     * @param name variable name
     * @return value of the variable
     */
    Object get(String name);

    /**
     * Gets all variables with values
     * @return map of the variables
     */
    default Map<String,Object> find() {
        return find(null);
    }

    /**
     * Gets all the variables that match the name. Name can contain * wild cards.
     * @param name variable name
     * @return map the variables
     */
    Map<String,Object> find(String name);

    /**
     * Deletes variables. Variable name cab contain * wild cards.
     * @param vars variables to be delted
     */
    void del(String... vars);

    /**
     * Serialize object to JSON string.
     * @param object object to serialize to JSON
     * @return formatted JSON string
     */
    String toJson(Object object);

    /**
     * Converts object to string.
     * @param object object to converts to string
     * @return object string value
     */
    String toString(Object object);

    /**
     * Converts object fields to map.
     * @param object object to convert to map
     * @return object fields map
     */
    Map<String,Object> toMap(Object object);

    /**
     * Deserialize value
     * @param value value to deserialize
     * @return deserialized value
     */
    default Object deserialize(String value) {
        return deserialize(value, null);
    }

    /**
     * Deserialize value
     * @param value value to deserialize
     * @param format serialization format
     * @return deserialized value
     */
    Object deserialize(String value, String format);

    /**
     *
     * @return Supported serialization formats
     */
    List<String> getSerializationFormats();

    /**
    *
    * @return Supported deserialization formats
    */
   List<String> getDeserializationFormats();

    /**
     * Persists object value to file.
     * @param file file
     * @param object object
     */
    void persist(Path file, Object object);

    /**
     * Persists object value to file.
     * @param file file
     * @param object object
     * @param format serialization format
     */
    void persist(Path file, Object object, String format);

    /**
     * Executes scriptEngine statement
     * @param statement statement
     * @return result
     * @throws Exception
     */
    Object execute(String statement) throws Exception;

    /**
     * Executes scriptEngine script
     * @param script script
     * @return result
     * @throws Exception
     */
    default Object execute(File script) throws Exception {
        return execute(script, null);
    }

    /**
     * Executes scriptEngine script
     * @param script script
     * @param args arguments
     * @return result
     * @throws Exception
     */
    Object execute(File script, Object[] args) throws Exception;

    /**
     * Executes scriptEngine closure
     * @param closure closure
     * @param args arguments
     * @return result
     */
    Object execute(Object closure, Object... args);

}
