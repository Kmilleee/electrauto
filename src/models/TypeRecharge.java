package models;

public class TypeRecharge {
    public enum TypeCharge {
        Normal(3),
        Rapide(50),
        Ultra_Rapide(150);

        private final int puissance;

        TypeCharge(int puissance) {
            this.puissance = puissance;
        }

        public int getPuissance() {
            return this.puissance;
        }
    }

    private TypeCharge type;

    public TypeRecharge(TypeCharge type) {
        this.type = type;
    }

    public String getNom() {
        return type.name();
    }

    public int getPuissance() {
        return type.getPuissance();
    }

    @Override
    public String toString() {
        return type.name() + ", puissance : " + type.getPuissance() + " kW";
    }
}