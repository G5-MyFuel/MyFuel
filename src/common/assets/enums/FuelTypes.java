package common.assets.enums;

public enum FuelTypes {
    Gasoline95 {
        @Override
        public String toString() {
            return "Gasoline95";
        }
    },
    Diesel {
        @Override
        public String toString() {
            return "Diesel";
        }
    },
    ScooterFuel {
        @Override
        public String toString() {
            return "ScooterFuel";
        }
    },
    HomeHeatingFuel {
        @Override
        public String toString() {
            return "HomeHeatingFuel";
        }
    };
}
