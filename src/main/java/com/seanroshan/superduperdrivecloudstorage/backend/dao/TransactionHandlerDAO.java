package com.seanroshan.superduperdrivecloudstorage.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;


public abstract class TransactionHandlerDAO {

    @Autowired
    private DataSource dataSource;

    private final DefaultTransactionDefinition transactionDefinition;
    private DataSourceTransactionManager transactionManager;

    public TransactionHandlerDAO() {
        this.transactionDefinition = new DefaultTransactionDefinition();
        this.transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

    protected DefaultTransactionDefinition getTransactionDefinition() {
        return transactionDefinition;
    }

    protected DataSourceTransactionManager getTransactionManager() {
        if (transactionManager == null) {
            transactionManager = new DataSourceTransactionManager(dataSource);
        }
        return transactionManager;
    }

}
