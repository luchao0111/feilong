/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.lib.json;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.ListOrderedMap;

import com.feilong.lib.json.util.CycleDetectionStrategy;
import com.feilong.lib.json.util.JSONUtils;

/**
 * A JSONObject is an unordered collection of name/value pairs. Its external
 * form is a string wrapped in curly braces with colons between the names and
 * values, and commas between the values and names. The internal form is an
 * object having <code>get</code> and <code>opt</code> methods for accessing
 * the values by name, and <code>put</code> methods for adding or replacing
 * values by name. The values can be any of these types: <code>Boolean</code>,
 * <code>JSONArray</code>, <code>JSONObject</code>, <code>Number</code>,
 * <code>String</code>, or the <code>JSONNull</code> object. A JSONObject
 * constructor can be used to convert an external form JSON text into an
 * internal form whose values can be retrieved with the <code>get</code> and
 * <code>opt</code> methods, or to convert values into a JSON text using the
 * <code>element</code> and <code>toString</code> methods. A
 * <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>opt</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an
 * object, which you can cast or query for type. There are also typed
 * <code>get</code> and <code>opt</code> methods that do type checking and
 * type coercion for you.
 * <p>
 * The <code>put</code> methods adds values to an object. For example,
 *
 * <pre>
 * myString = new JSONObject().put("JSON", "Hello, World!").toString();
 * </pre>
 *
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * the JSON syntax rules. The constructors are more forgiving in the texts they
 * will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 * before the closing brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a
 * quote or single quote, and if they do not contain leading or trailing spaces,
 * and if they do not contain any of these characters:
 * <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers and
 * if they are not the reserved words <code>true</code>, <code>false</code>,
 * or <code>null</code>.</li>
 * <li>Keys can be followed by <code>=</code> or <code>=></code> as well as
 * by <code>:</code>.</li>
 * <li>Values can be followed by <code>;</code> <small>(semicolon)</small>
 * as well as by <code>,</code> <small>(comma)</small>.</li>
 * <li>Numbers may have the <code>0-</code> <small>(octal)</small> or
 * <code>0x-</code> <small>(hex)</small> prefix.</li>
 * <li>Comments written in the slashshlash, slashstar, and hash conventions
 * will be ignored.</li>
 * </ul>
 *
 * @author JSON.org
 */
public final class JSONObject implements JSON{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7895449812672706822L;

    /**
     * Creates a JSONObject.<br>
     * Inspects the object type to call the correct JSONObject factory method. Accepts JSON formatted strings, Maps, DynaBeans and
     * JavaBeans.
     *
     * @param object
     *            the object
     * @return the JSON object
     * @throws JSONException
     *             if the object can not be converted to a proper
     *             JSONObject.
     */
    public static JSONObject fromObject(Object object){
        return fromObject(object, new JsonConfig());
    }

    /**
     * Creates a JSONObject.
     * <p>
     * Inspects the object type to call the correct JSONObject factory method.
     * Accepts JSON formatted strings, Maps, DynaBeans and JavaBeans.
     * </p>
     *
     * @param object
     *            the object
     * @param jsonConfig
     *            the json config
     * @return the JSON object
     * @throws JSONException
     *             if the object can not be converted to a proper
     *             JSONObject.
     */
    public static JSONObject fromObject(Object object,JsonConfig jsonConfig){
        return JSONObjectBuilder.build(object, jsonConfig);
    }

    //---------------------------------------------------------------

    /**
     * Creates a JSONDynaBean from a JSONObject.
     *
     * @param jsonObject
     *            the json object
     * @return the object
     */
    public static Object toBean(JSONObject jsonObject){
        return JSONObjectToBeanUtil.toBean(jsonObject);
    }

    /**
     * Creates a bean from a JSONObject, with the specific configuration.
     *
     * @param jsonObject
     *            the json object
     * @param jsonConfig
     *            the json config
     * @return the object
     */
    public static Object toBean(JSONObject jsonObject,JsonConfig jsonConfig){
        return JSONObjectToBeanUtil.toBean(jsonObject, jsonConfig);

    }

    /**
     * Creates a bean from a JSONObject, with the specific configuration.
     *
     * @param jsonObject
     *            the json object
     * @param root
     *            the root
     * @param jsonConfig
     *            the json config
     * @return the object
     */
    public static Object toBean(JSONObject jsonObject,Object root,JsonConfig jsonConfig){
        return toBean(jsonObject, root, jsonConfig);
    }

    // ------------------------------------------------------

    /** identifies this object as null. */
    private boolean nullObject;

    /**
     * The Map where the JSONObject's properties are kept.
     */
    final Map       properties;

    /**
     * Construct an empty JSONObject.
     */
    public JSONObject(){
        this.properties = new ListOrderedMap();
    }

    /**
     * Creates a JSONObject that is null.
     *
     * @param isNull
     *            the is null
     */
    public JSONObject(boolean isNull){
        this();
        this.nullObject = isNull;
    }

    /**
     * Accumulate values under a key. It is similar to the element method except
     * that if there is already an object stored under the key then a JSONArray
     * is stored under the key to hold all of the accumulated values. If there is
     * already a JSONArray, then the new value is appended to it. In contrast,
     * the replace method replaces the previous value.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object to be accumulated under the key.
     * @param jsonConfig
     *            the json config
     * @return this.
     * @throws JSONException
     *             If the value is an invalid number or if the key is null.
     */
    public JSONObject accumulate(String key,Object value,JsonConfig jsonConfig){
        if (isNullObject()){
            throw new JSONException("Can't accumulate on null object");
        }

        if (!has(key)){
            setInternal(key, value, jsonConfig);
        }else{
            Object o = get(key);
            if (o instanceof JSONArray){
                ((JSONArray) o).element(value, jsonConfig);
            }else{
                setInternal(key, new JSONArray().element(o).element(value, jsonConfig), jsonConfig);
            }
        }

        return this;
    }

    /**
     * Put a key/value pair in the JSONObject. If the value is null, then the key will be removed from the JSONObject if it is present.<br>
     * If there is a previous value assigned to the key, it will call accumulate.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object which is the value. It should be of one of these
     *            types: Boolean, Double, Integer, JSONArray, JSONObject, Long,
     *            String, or the JSONNull object.
     * @param jsonConfig
     *            the json config
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number or if the key is null.
     */
    public JSONObject element(String key,Object value,JsonConfig jsonConfig){
        verifyIsNull();
        if (key == null){
            throw new JSONException("Null key.");
        }
        if (value != null){
            value = ProcessValueUtil.processJsonObjectValue(key, value, jsonConfig);
            _setInternal(key, value, jsonConfig);
        }else{
            remove(key);
        }
        return this;
    }

    /**
     * Get the value object associated with a key.
     *
     * @param key
     *            A key string.
     * @return The object associated with the key.
     * @throws JSONException
     *             if this.isNull() returns true.
     */
    public Object get(String key){
        verifyIsNull();
        return this.properties.get(key);
    }

    /**
     * Determine if the JSONObject contains a specific key.
     *
     * @param key
     *            A key string.
     * @return true if the key exists in the JSONObject.
     */
    public boolean has(String key){
        verifyIsNull();
        return this.properties.containsKey(key);
    }

    /**
     * Returs if this object is a null JSONObject.
     *
     * @return the identifies this object as null
     */
    public boolean isNullObject(){
        return nullObject;
    }

    /**
     * Get an enumeration of the keys of the JSONObject.
     *
     * @return An iterator of the keys.
     */
    public Iterator keys(){
        verifyIsNull();
        Set keySet = Collections.unmodifiableSet(properties.keySet());
        return keySet.iterator();
    }

    /**
     * Produce a JSONArray containing the names of the elements of this
     * JSONObject.
     *
     * @param jsonConfig
     *            the json config
     * @return A JSONArray containing the key strings, or null if the JSONObject
     *         is empty.
     */
    public JSONArray names(JsonConfig jsonConfig){
        verifyIsNull();
        JSONArray ja = new JSONArray();
        Iterator keys = keys();
        while (keys.hasNext()){
            ja.element(keys.next(), jsonConfig);
        }
        return ja;
    }

    /**
     * Remove a name and its value, if present.
     *
     * @param key
     *            The name to be removed.
     * @return The value that was associated with the name, or null if there was
     *         no value.
     */
    public Object remove(String key){
        verifyIsNull();
        return this.properties.remove(key);
    }

    /**
     * Get the number of keys stored in the JSONObject.
     *
     * @return The number of keys in the JSONObject.
     */
    @Override
    public int size(){
        return this.properties.size();
    }

    /**
     * Make a JSON text of this JSONObject. For compactness, no whitespace is
     * added. If this would not result in a syntactically correct JSON text, then
     * null will be returned instead.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a printable, displayable, portable, transmittable representation
     *         of the object, beginning with <code>{</code>&nbsp;<small>(left
     *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
     *         brace)</small>.
     */
    @Override
    public String toString(){
        if (isNullObject()){
            return JSONNull.getInstance().toString();
        }
        return ToStringUtil.toString(this.properties);
    }

    //---------------------------------------------------------------

    /**
     * Make a prettyprinted JSON text of this JSONObject.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @param indentFactor
     *            The number of spaces to add to each level of
     *            indentation.
     * @param indent
     *            The indentation of the top level.
     * @return a printable, displayable, transmittable representation of the
     *         object, beginning with <code>{</code>&nbsp;<small>(left brace)</small>
     *         and ending with <code>}</code>&nbsp;<small>(right brace)</small>.
     * @throws JSONException
     *             If the object contains an invalid number.
     */
    @Override
    public String toString(int indentFactor,int indent){
        if (isNullObject()){
            return JSONNull.getInstance().toString();
        }
        //---------------------------------------------------------------
        int n = size();
        if (n == 0){
            return "{}";
        }
        if (indentFactor == 0){
            return toString();
        }
        return ToStringUtil.toString(properties, indentFactor, indent);
    }

    /**
     * Put a key/value pair in the JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object which is the value. It should be of one of these
     *            types: Boolean, Double, Integer, JSONArray, JSONObject, Long,
     *            String, or the JSONNull object.
     * @param jsonConfig
     *            the json config
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number or if the key is
     *             null.
     */
    private JSONObject _setInternal(String key,Object value,JsonConfig jsonConfig){
        verifyIsNull();
        if (key == null){
            throw new JSONException("Null key.");
        }

        //---------------------------------------------------------------
        if (JSONUtils.isString(value) && JSONUtils.mayBeJSON(String.valueOf(value))){
            this.properties.put(key, value);
        }else{
            if (CycleDetectionStrategy.IGNORE_PROPERTY_OBJ == value || CycleDetectionStrategy.IGNORE_PROPERTY_ARR == value){
                // do nothing
            }else{
                this.properties.put(key, value);
            }
        }
        return this;
    }

    //---------------------------------------------------------------

    /**
     * Put a key/value pair in the JSONObject.
     *
     * @param key
     *            A key string.
     * @param value
     *            An object which is the value. It should be of one of these types: Boolean, Double, Integer, JSONArray, JSONObject, Long,
     *            String, or the JSONNull object.
     * @param jsonConfig
     *            the json config
     * @return this.
     * @throws JSONException
     *             If the value is non-finite number or if the key is null.
     */
    JSONObject setInternal(String key,Object value,JsonConfig jsonConfig){
        Object processJsonObjectValue = ProcessValueUtil.processJsonObjectValue(key, value, jsonConfig);
        return _setInternal(key, processJsonObjectValue, jsonConfig);
    }

    /**
     * Checks if this object is a "null" object.
     */
    private void verifyIsNull(){
        if (isNullObject()){
            throw new JSONException("null object");
        }
    }

}
