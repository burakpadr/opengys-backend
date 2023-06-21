package com.abctech.gys.domain.realestate.status.constant;

public enum ForSaleSubStatus implements SubStatus {

    IN_PREPARATION {

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
    SALES_CONFIRMATION {

        @Override
        public String getValue() {
            return "Satış Onayında";
        }

        @Override
        public Integer getOrder() {
            return 3;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    },
    PENDING_PAYMENT {

        @Override
        public String getValue() {
            return "Ödeme Bekleniyor";
        }

        @Override
        public Integer getOrder() {
            return 4;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    },
    DEED_TRANSFER {

        @Override
        public String getValue() {
            return "Tapu Devrinde";
        }

        @Override
        public Integer getOrder() {
            return 5;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    },
    SOLD {

        @Override
        public String getValue() {
            return "Satıldı";
        }

        @Override
        public Integer getOrder() {
            return 6;
        }

        @Override
        public String getAlias() {
            return this.name();
        }
    }
}
