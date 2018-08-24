package Entities.Commands;

public enum  Refutation {

        REFUTE("Make Refutation"), PASS("Pass");
        private final String label;

        Refutation(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

}
