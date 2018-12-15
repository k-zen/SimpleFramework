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

import javax.swing.*;

/**
 * Skeleton class for all frames.
 *
 * @author Andreas P. Koenzen <akc at apkc.net>
 * @version 0.1
 */
public abstract class AbstractFrame extends JFrame {

    /**
     * Override this method to include code that builds this frame.
     *
     * <p>
     * The following tasks should be executed inside this method:
     * <ul>
     * <li>Initialize all GUI components.</li>
     * <li>Add components to each other.</li>
     * <li>Some generic task.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The following tasks should NOT be executed inside this method:
     * <ul>
     * <li>All tasks inside the {@link configure()} method.</li>
     * </ul>
     * </p>
     *
     * @return This GUI instance.
     */
    protected abstract AbstractFrame createGUI();

    /**
     * Override this method to include code that configures this frame.
     *
     * <p>
     * The following tasks should be executed inside this method:
     * <ul>
     * <li>Make calls to other resources.</li>
     * <li>Receive information to add to components. i.e. Populating a table
     * with database information.</li>
     * <li>Some generic configuration task.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The following tasks should NOT be executed inside this method:
     * <ul>
     * <li>All tasks inside the {@link createGUI()} method.</li>
     * </ul>
     * </p>
     *
     * @return This GUI instance.
     */
    public abstract AbstractFrame configure();

    /**
     * Override this method to include code that makes this frame visible.
     *
     * <p>
     * The following tasks should be executed inside this method:
     * <ul>
     * <li>Any action to make this GUI visible.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The following tasks should NOT be executed inside this method:
     * <ul>
     * <li>All tasks inside the {@link createGUI()} method.</li>
     * <li>All tasks inside the {@link configure()} method.</li>
     * </ul>
     * </p>
     *
     * @return This GUI instance.
     */
    public abstract AbstractFrame makeVisible();
}
