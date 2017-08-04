package com.example.shixi_a.myapplication.work.workTask.tasks;

/**
 * Created by Shixi-A on 2017/5/31.
 *
 * Used with the filter spinner in the tasks list.
 */

public enum TasksFilterType {
    /**
     * Do not filter tasks.
     */
    ALL_TASKS,

    /**
     * Filters only the not start tasks.
     */
    UNSTART_TASKS,

    /**
     * Filters only the not completed yet tasks.
     */
    PROCESSING_TASKS,

    /**
     * Filters only the participate tasks.
     */
    PARTICIPATE_TASKS,

    /**
     * Filters only the created by user's tasks.
     */
    CREATED_TASKS,

    /**
     * Filters only the completed tasks.
     */
    COMPLETED_TASKS
}
