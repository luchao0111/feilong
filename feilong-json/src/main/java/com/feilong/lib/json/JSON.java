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

import java.io.Serializable;

/**
 * Marker interface, identifies a valid JSON value.<br>
 * A JSON value may be a {@link JSONObject}, a {@link JSONArray} or a
 * {@link JSONNull}.
 *
 * @author <a href="mailto:aalmiray@users.sourceforge.net">Andres Almiray</a>
 */
public interface JSON extends Serializable{

    /**
     * Returns the number of properties in an object or the size of the array.
     *
     * @return the size of an json object or array
     * @throws JSONException
     *             if called on a 'null' object
     */
    int size();

    //---------------------------------------------------------------

    /**
     * Make a prettyprinted JSON text.
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
    String toString(int indentFactor,int indent);

}