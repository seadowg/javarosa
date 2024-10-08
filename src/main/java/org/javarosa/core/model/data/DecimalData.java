/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javarosa.core.model.data;

import org.javarosa.core.util.externalizable.ExtUtil;
import org.javarosa.core.util.externalizable.PrototypeFactory;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


/**
 * A response to a question requesting an Decimal Value.  Adapted from IntegerData
 * @author Brian DeRenzi
 *
 */
public class DecimalData implements IAnswerData {
    private double d;

    /**
     * Empty Constructor, necessary for dynamic construction during deserialization.
     * Shouldn't be used otherwise.
     */
    public DecimalData() {

    }

    public DecimalData(double d) {
        this.d = d;
    }
    public DecimalData(@NotNull Double d) {
        setValue(d);
    }

    @Override
    public IAnswerData clone () {
        return new DecimalData(d);
    }

    @Override
    public String getDisplayText() {
        return String.valueOf(d);
    }

    @Override
    public @NotNull Object getValue() {
        return d;
    }

    @Override
    public void setValue(@NotNull Object o) {
        if(o == null) {
            throw new NullPointerException("Attempt to set an IAnswerData class to null.");
        }
        d = (Double) o;
    }

    @Override
    public void readExternal(DataInputStream in, PrototypeFactory pf) throws IOException {
        d = ExtUtil.readDecimal(in);
    }

    @Override
    public void writeExternal(DataOutputStream out) throws IOException {
        ExtUtil.writeDecimal(out, d);
    }

    @Override
    public UncastData uncast() {
        return new UncastData(getValue().toString());
    }

    @Override
    public DecimalData cast(UncastData data) throws IllegalArgumentException {
        try {
            return new DecimalData(Double.parseDouble(data.value));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid cast of data [" + data.value + "] to type Decimal");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecimalData that = (DecimalData) o;
        return Double.compare(that.d, d) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(d);
    }
}
