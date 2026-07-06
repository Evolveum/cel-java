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
import dev.cel.common.annotations.Internal;
import java.util.List;

/**
 * TODO
 */
@Immutable
@Internal
public class CelResolvedNullOverload extends CelResolvedOverload {

  private final ImmutableList<Class<?>> parameterTypes;

  private static final OptimizedFunctionOverload OPTIMIZED_FUNCTION_OVERLOAD = new OptimizedFunctionOverload() {
    public Object apply(Object[] args) throws CelEvaluationException {
      return CelFunctionOverload.NULL_VALUE;
    }
  };

  CelResolvedNullOverload(ImmutableList<Class<?>> parameterTypes) {
    this.parameterTypes = parameterTypes;
  }

  /** The base function name. */
  public String getFunctionName() {
    return "nullOverload";
  }

  /** The overload id of the function. */
  public String getOverloadId() {
    return "null-overload";
  }

  /** The types of the function parameters. */
  public ImmutableList<Class<?>> getParameterTypes() {
    return parameterTypes;
  }

  public boolean isStrict() {
    return false;
  }

  public boolean isNullable() {
    return true;
  }

  /** The function definition. */
  public CelFunctionOverload getDefinition() {
    return OPTIMIZED_FUNCTION_OVERLOAD;
  }

  OptimizedFunctionOverload getOptimizedDefinition() {
    return OPTIMIZED_FUNCTION_OVERLOAD;
  }

  public Object invoke(Object[] args) throws CelEvaluationException {
    return CelFunctionOverload.NULL_VALUE;
  }

  public Object invoke(Object arg) throws CelEvaluationException {
    return CelFunctionOverload.NULL_VALUE;
  }

  public Object invoke(Object arg1, Object arg2) throws CelEvaluationException {
    return CelFunctionOverload.NULL_VALUE;
  }

  public static CelResolvedNullOverload of(List<Class<?>> parameterTypes) {
    return new CelResolvedNullOverload(ImmutableList.copyOf(parameterTypes));
  }

}
