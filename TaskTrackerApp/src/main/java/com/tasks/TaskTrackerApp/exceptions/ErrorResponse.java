package com.tasks.TaskTrackerApp.exceptions;

public record ErrorResponse(int status,String message,String details)
{

}

