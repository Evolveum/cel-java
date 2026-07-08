// Copyright 2026 Evolveum
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package dev.cel.runtime;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import dev.cel.common.values.NullValue;

import java.util.Objects;

/** Interface describing the general signature of all CEL custom function implementations. */
@Immutable
public class NullabilityProperties {

    public static final NullabilityProperties NOT_NULLABLE = new NullabilityProperties(false, null);
    public static final NullabilityProperties NULLABLE = new NullabilityProperties(true, null);
    public static final NullabilityProperties NULLABLE_NULL = new NullabilityProperties(true, args -> NullValue.NULL_VALUE);
    public static final NullabilityProperties NULLABLE_FALSE = new NullabilityProperties(true, args -> false);
    public static final NullabilityProperties NULLABLE_TRUE = new NullabilityProperties(true, args -> true);
    public static final NullabilityProperties NULLABLE_ZERO = new NullabilityProperties(true, args -> 0);
    public static final NullabilityProperties NULLABLE_NEGATIVE_ONE = new NullabilityProperties(true, args -> -1);
    public static final NullabilityProperties NULLABLE_EMPTY_LIST = new NullabilityProperties(true, args -> ImmutableList.of());

    private final boolean isNullable;
    private final CelFunctionOverload defaultFunction;

    public NullabilityProperties(boolean isNullable, CelFunctionOverload defaultFunction) {
        this.isNullable = isNullable;
        this.defaultFunction = defaultFunction;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public CelFunctionOverload getDefaultFunction() {
        return defaultFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NullabilityProperties)) return false;
        NullabilityProperties that = (NullabilityProperties) o;
        return isNullable == that.isNullable && Objects.equals(defaultFunction, that.defaultFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isNullable, defaultFunction);
    }
}
