public class TypeDescriptor {
    
    public enum BaseType { INTEGER, REAL, STRING, UNIT, BOOL, ERROR }
    //  INTEGER -> um numero inteiro
    //  REAL    -> um numero real
    //  STRING  -> string (print, input, etc)
    //  ERROR   -> exceptions

    public final BaseType base;
    public boolean isList;

    // if 0 it's infinite
    public final Integer bitRange;


    // dimension -> m, cm, etc
    // null se não tiver dimensão (adimensional)
    // mais que uma dimension -> converter para array
    public final String dimension;
    public final String unit;

    public String errorCode;

    /* Construtores base */
    public TypeDescriptor(BaseType base, String dimension, String unit) {
        this.base = base;
        this.dimension = dimension;
        this.unit = unit;
        this.errorCode = "";
        this.isList = false;
        this.bitRange = 0;
    }

    public TypeDescriptor(BaseType base, Integer bitRange) {
        this.base = base;
        this.dimension = null;
        this.unit = null;
        this.errorCode = "";
        this.isList = false;
        this.bitRange = bitRange;
    }

    public TypeDescriptor(BaseType base) {
        this.base = base;
        this.dimension = null;
        this.unit = null;
        this.errorCode = "";
        this.isList = false;
        this.bitRange = 0;
    }

    public static final TypeDescriptor INTEGER = new TypeDescriptor(BaseType.INTEGER);
    public static final TypeDescriptor REAL    = new TypeDescriptor(BaseType.REAL);
    public static final TypeDescriptor STRING  = new TypeDescriptor(BaseType.STRING);
    public static final TypeDescriptor UNIT  = new TypeDescriptor(BaseType.UNIT);
    public static final TypeDescriptor BOOL  = new TypeDescriptor(BaseType.BOOL);
    public static final TypeDescriptor ERROR   = new TypeDescriptor(BaseType.ERROR);


    // Ja temos BaseType antes de executar esta função
    // Apenas verificamos se e um numero ou nao
    public boolean isNumeric() { return base == BaseType.INTEGER || base == BaseType.REAL; }
    public boolean isInteger() { return base == BaseType.INTEGER; }
    public boolean isReal() { return base == BaseType.REAL; }
    public boolean isBool() { return base == BaseType.BOOL; }
    public boolean isError() { return base == BaseType.ERROR; }

    public boolean isString() { return base == BaseType.STRING; }

    // se tem dimensão ou não definida
    public boolean hasDimension() {
        return dimension != null;
    }

    public boolean sameDimension(TypeDescriptor other, TypeSystem ts) {
        if (dimension == null && other.dimension == null) return true;
        if (dimension == null || other.dimension == null) return false;
        return ts.isDimensionCompatible(dimension, other.dimension);
        // return (dimension == null ^ other.dimension == null) &&  dimension.equals(other.dimension);
    }

    public boolean sameUnit(TypeDescriptor other) {
        if (unit == null && other.unit == null) return true;
        if (unit == null || other.unit == null) return false;
        return unit.equals(other.unit);
        // return (dimension == null ^ other.dimension == null) &&  dimension.equals(other.dimension);
    }

    public boolean hasBitRange() {
        return bitRange != null && bitRange > 0;
    }

    public boolean isPlainPrimitive() {
        return !isList && !hasDimension() && !hasBitRange();
    }
    
    @Override
    public String toString() {
        String prefix = isList ? "list[" : "";
        String suffix = isList ? "]" : "";

        if (dimension != null)
            return prefix + base + "[" + dimension + "]" + suffix;

        if (hasBitRange())
            return prefix + base + "[" + bitRange + "]" + suffix;

        return prefix + base.toString() + suffix;
    }
}