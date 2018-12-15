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
package net.apkc.sf.utils;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to manage fonts inside the framework.
 *
 * @author Andreas P. Koenzen <akc at apkc.net>
 * @version 0.1
 */
public class Fonts
{

    /** This class' logger. */
    private static final Logger LOG = Logger.getLogger(Fonts.class.getName());
    /** This instance. */
    private static final Fonts _INSTANCE = new Fonts();
    /** Default font size. */
    private int size = 12;
    /** Default font plain. */
    private Font defaultFontPlain;
    /** Default font bold. */
    private Font defaultFontBold;

    /**
     * Private constructor.
     */
    private Fonts()
    {
        InputStream s1 = null;
        InputStream s2 = null;
        try
        {
            s1 = Fonts.class.getResourceAsStream("/resources/fonts/Roboto-Regular.ttf");
            s2 = Fonts.class.getResourceAsStream("/resources/fonts/Roboto-Bold.ttf");

            defaultFontPlain = Font.createFont(Font.TRUETYPE_FONT, s1);
            defaultFontBold = Font.createFont(Font.TRUETYPE_FONT, s2);

            LOG.info("Initiating default font \"Roboto\" with size " + size + "px.");
        }
        catch (FontFormatException | IOException e)
        {
            LOG.error("Can't access application's fonts. Error: " + e.toString(), e);
            defaultFontPlain = new Font("Dialog", Font.TRUETYPE_FONT, size);
            defaultFontBold = new Font("Dialog", Font.BOLD, size);

            LOG.info("Initiating backup font \"Dialog\" with size " + size + "px.");
        }
        finally
        {
            try
            {
                if (s1 != null)
                {
                    s1.close();
                }
                if (s2 != null)
                {
                    s2.close();
                }
            }
            catch (IOException ex)
            {
                LOG.error("Can't close stream. Error: " + ex.toString(), ex);
            }
        }
    }

    /**
     * Returns this instance.
     *
     * @return This instance.
     */
    public static Fonts getInstance()
    {
        return _INSTANCE;
    }

    /**
     * Configures the fonts.
     *
     * @param defaultFontSize The default font size.
     *
     * @return This instance.
     */
    public Fonts configure(int defaultFontSize)
    {
        size = defaultFontSize;
        LOG.info("Re-initiating default font with size " + defaultFontSize + "px.");

        return this;
    }

    /**
     * Returns the default plain font.
     *
     * @return The default plain font.
     */
    public Font getDefaultFontPlain()
    {
        return defaultFontPlain.deriveFont((float) size);
    }

    /**
     * Returns the default bold font.
     *
     * @return The default bold font.
     */
    public Font getDefaultBold()
    {
        return defaultFontBold.deriveFont((float) size).deriveFont(Font.BOLD);
    }

    /**
     * Returns the default bold font with a fixed size.
     *
     * @param size The size of the font.
     *
     * @return The default bold font.
     */
    public Font getDefaultBold(int size)
    {
        return defaultFontBold.deriveFont((float) size).deriveFont(Font.BOLD);
    }
}
