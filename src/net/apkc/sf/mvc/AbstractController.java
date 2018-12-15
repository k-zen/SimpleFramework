/*
 * Copyright (c) 2014, Andreas P. Koenzen <akc at apkc.net>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.apkc.sf.mvc;

import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton class for all controllers.
 *
 * @author Andreas P. Koenzen <akc at apkc.net>
 * @version 0.1
 */
public abstract class AbstractController implements PropertyChangeListener {

    private static final Logger LOG = Logger.getLogger(AbstractController.class);
    private List<AbstractViewPanel> registeredViews;
    private List<AbstractModel> registeredModels;

    public AbstractController() {
        registeredViews = new ArrayList<>();
        registeredModels = new ArrayList<>();
    }

    /**
     * Adds a new model.
     *
     * @param model A model object.
     */
    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    /**
     * Removes a model.
     *
     * @param model A model object to remove.
     */
    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    /**
     * Adds a new view to this controller.
     *
     * @param view The view object.
     */
    public void addView(AbstractViewPanel view) {
        registeredViews.add(view);
    }

    /**
     * Removes a view from this controller.
     *
     * @param view The view object to remove.
     */
    public void removeView(AbstractViewPanel view) {
        registeredViews.remove(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        for (AbstractViewPanel view : registeredViews) {
            view.modelPropertyChange(evt);
        }
    }

    /**
     * Sets a new property. This method is only valid for generic models, that
     * means an {@link AbstractModel} object.
     *
     * @param propertyName The name of the property to change.
     * @param newValue     The new value.
     */
    protected void setModelProperty(String propertyName, Object newValue) {
        for (Object model : registeredModels) // Iterate all models.
        {
            try {
                // Invoke the method and update the property if available.
                Method method = model
                        .getClass()
                        .getMethod("set" + propertyName,
                                new Class[]{
                                        newValue.getClass()
                                });
                method.invoke(model, newValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                LOG.warn("Property not found in model: " + model.getClass().getName(), e);
            }
        }
    }
}
