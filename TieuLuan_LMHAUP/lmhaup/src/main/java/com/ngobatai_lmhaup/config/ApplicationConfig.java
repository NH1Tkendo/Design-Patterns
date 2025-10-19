package com.ngobatai_lmhaup.config;

/**
 * Application configuration class for LMHAUP Mining Application.
 * Implements Builder Pattern for flexible configuration.
 * 
 * This class centralizes all application-level configuration parameters,
 * making it easy to modify settings without changing multiple files.
 */
public class ApplicationConfig {
    private final String profitFilePath;
    private final String transactionFilePath;
    private final double delta;
    private final boolean verbose;

    /**
     * Private constructor - use builder to create instances
     */
    private ApplicationConfig(Builder builder) {
        this.profitFilePath = builder.profitFilePath;
        this.transactionFilePath = builder.transactionFilePath;
        this.delta = builder.delta;
        this.verbose = builder.verbose;
    }

    public String getProfitFilePath() {
        return profitFilePath;
    }

    public String getTransactionFilePath() {
        return transactionFilePath;
    }

    public double getDelta() {
        return delta;
    }

    public boolean isVerbose() {
        return verbose;
    }

    /**
     * Create a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create default configuration
     */
    public static ApplicationConfig defaults() {
        return builder().build();
    }

    /**
     * Builder for ApplicationConfig
     */
    public static class Builder {
        // Default values
        private String profitFilePath = "src/main/java/com/ngobatai_lmhaup/datasets/BangLoiNhuan.txt";
        private String transactionFilePath = "src/main/java/com/ngobatai_lmhaup/datasets/BangTransaction.txt";
        private double delta = 0.17;
        private boolean verbose = true;

        private Builder() {
        }

        /**
         * Set profit file path
         */
        public Builder profitFile(String path) {
            this.profitFilePath = path;
            return this;
        }

        /**
         * Set transaction file path
         */
        public Builder transactionFile(String path) {
            this.transactionFilePath = path;
            return this;
        }

        /**
         * Set minimum average utility threshold (delta)
         */
        public Builder delta(double delta) {
            if (delta < 0) {
                throw new IllegalArgumentException("Delta must be non-negative");
            }
            this.delta = delta;
            return this;
        }

        /**
         * Set verbose output mode
         */
        public Builder verbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        /**
         * Build the configuration
         */
        public ApplicationConfig build() {
            return new ApplicationConfig(this);
        }
    }

    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "profitFilePath='" + profitFilePath + '\'' +
                ", transactionFilePath='" + transactionFilePath + '\'' +
                ", delta=" + delta +
                ", verbose=" + verbose +
                '}';
    }
}
