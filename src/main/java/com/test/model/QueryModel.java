package com.test.model;

import com.google.cloud.bigquery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QueryModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueryModel.class.getName());

    public TableResult getQueryResultFromBigQuery(String query, BigQuery bigQuery) throws Exception {

        QueryJobConfiguration.Builder builder;
        builder = QueryJobConfiguration.newBuilder(query);

        QueryJobConfiguration queryConfig = builder.build();

        JobId jobId = JobId.of(UUID.randomUUID().toString());

        Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        try {
            queryJob = queryJob.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {

            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        TableResult result = queryJob.getQueryResults();

        LOGGER.info("Query Result" + result);
        return result;
    }
}
