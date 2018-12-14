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
package net.apkc.cmvc.tasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

/**
 * Class to handle all tasks in Emma.
 *
 * @author Andreas P. Koenzen <akc at apkc.net>
 * @version 0.1
 * @see <a href="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton Pattern</a>
 */
public class TasksHandler
{

    /** This class' logger. */
    private static final Logger LOG = Logger.getLogger(TasksHandler.class.getName());
    /** This instance. */
    private static final TasksHandler _INSTANCE = new TasksHandler();
    /** The fixed thread pool. */
    private ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * Private constructor.
     */
    private TasksHandler()
    {
        LOG.info("Initiating finite task pool with 10 slots.");
    }

    /**
     * Returns this instance.
     *
     * @return This instance.
     */
    public static TasksHandler getInstance()
    {
        return _INSTANCE;
    }

    /**
     * Configures the tasks handler.
     *
     * @param poolSize The size in threads of the finite task pool.
     *
     * @return This instance.
     */
    public TasksHandler configure(int poolSize)
    {
        pool = Executors.newFixedThreadPool(poolSize);
        LOG.info("Re-initiating finite task pool with " + poolSize + " slots.");

        return this;
    }

    /**
     * Submits a infinite task to be executed in a single new thread. Useful for starting services
     * that will run in the background as daemons.
     *
     * @param task The task to be executed.
     */
    public void submitInfiniteTask(Runnable task)
    {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(task);
    }

    /**
     * Submits a finite task to be executed in a fixed thread pool.
     *
     * @param task The task to be executed.
     *
     * @return A future object, which can be used to monitor the task.
     */
    public Future<Boolean> submitFiniteTask(Runnable task)
    {
        return pool.submit(task, true);
    }
}
