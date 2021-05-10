package org.simple.app;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.simple.app.activity.SimpleActivityImpl;
import org.simple.app.workflow.SimpleWorkflowImpl;

public class StartWorker {
    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("simple-queue-java");

        // register the java workflow
        worker.registerWorkflowImplementationTypes(SimpleWorkflowImpl.class);

        // register the java activity
        worker.registerActivitiesImplementations(new SimpleActivityImpl());

        factory.start();

    }
}
