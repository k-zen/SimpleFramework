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
package net.apkc.cmvc.mvc;

import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;

/**
 * Skeleton class for all panel views.
 *
 * @author Andreas P. Koenzen <akc at apkc.net>
 * @version 0.1
 */
public abstract class AbstractViewPanel extends JPanel
{

    /**
     * Builds the component.
     *
     * @return This panel.
     */
    protected abstract AbstractViewPanel createComponent();

    /**
     * Called from the controller to receive a change notification.
     *
     * @param evt The change event.
     */
    public abstract void modelPropertyChange(PropertyChangeEvent evt);

    /**
     * Configure this Component.
     *
     * @param data Generic object with data.
     *
     * @return This panel.
     */
    public abstract AbstractViewPanel configure(Object data);

    /**
     * Makes the GUI visible.
     *
     * @param visible TRUE if the component should be visible, FALSE otherwise.
     *
     * @return This panel.
     */
    public final AbstractViewPanel markVisibility(boolean visible)
    {
        setVisible(visible);

        return this;
    }

    /**
     * Returns a controller associated with this Frame.
     *
     * @return The controller.
     */
    public abstract AbstractController getController();
}