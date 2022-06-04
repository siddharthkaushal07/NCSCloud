package com.sunilos.common.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BaseJob implements Job {

	public void beforeJob(JobExecutionContext ctx) {
	}

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		beforeJob(ctx);
		run(ctx);
		afterJob(ctx);
	}

	public void afterJob(JobExecutionContext ctx) {
	}

	public void run(JobExecutionContext ctx) {

	}

}
