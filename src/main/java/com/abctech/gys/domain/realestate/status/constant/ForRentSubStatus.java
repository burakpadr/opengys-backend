package com.abctech.gys.domain.realestate.status.constant;

public enum ForRentSubStatus implements SubStatus {

    IN_PREPARATION() {

        @Override
        public String getValue() {
            return "Hazırlık Aşamasında";
        }

        @Override
        public Integer getOrder() {
            return 1;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    },
    IN_NOTICE {

        @Override
        public String getValue() {
            return "İlanda";
        }

        @Override
        public Integer getOrder() {
            return 2;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    },
    RENTED {

        @Override
        public String getValue() {
            return "Kiralandı";
        }

        @Override
        public Integer getOrder() {
            return 3;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    }
}
