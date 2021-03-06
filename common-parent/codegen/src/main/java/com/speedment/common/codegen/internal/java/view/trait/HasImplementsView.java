/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.common.codegen.internal.java.view.trait;

import com.speedment.common.codegen.Generator;
import com.speedment.common.codegen.Transform;
import com.speedment.common.codegen.model.trait.HasImplements;

import static com.speedment.common.codegen.internal.util.CollectorUtil.joinIfNotEmpty;

/**
 * A trait with the functionality to render models with the trait 
 * {@link HasImplements}.
 * 
 * @author     Emil Forslund
 * @param <M>  The model type
 * @see        Transform
 */
public interface HasImplementsView<M extends HasImplements<M>> extends 
    Transform<M, String> {
    
    /**
     * Returns the correct term to use when describing the supertype of this
     * component. In java, <code>"extends"</code> is used when an interface 
     * has another interface as a supertype and <code>"implements"</code> when a 
     * class and an enum uses it.
     * 
     * @return  "extends" or "implements".
     */
    String extendsOrImplementsInterfaces();
    
    /**
     * Render the supertype-part of the model. The 
     * {@link #extendsOrImplementsInterfaces()}-method should be implemented to
     * answer which wording to use; 'implements' or 'extends'.
     * 
     * @param gen    the generator
     * @param model  the model
     * @return       the generated code
     */
    default String renderInterfaces(Generator gen, M model) {
        return gen.onEach(model.getInterfaces()).collect(
            joinIfNotEmpty(
                ", ", 
                extendsOrImplementsInterfaces(), 
                " "
            )
        );
    }
}