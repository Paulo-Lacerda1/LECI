import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import rational.FractionType;

public class TypeSystem {
    // ─── Dimension ───────────────────────────────────────────────────────────

    // { dimensionName : baseType }
    private final HashMap<String, TypeDescriptor> dimensionTypeMap;

    // { dimensionName : baseUnitName }
    private final HashMap<String, String> dimensionUnitMap;

    // example: { dimensionName : { {exp1Name: 1}, {exp2Name, -1} } }.
    // Maps the dimension to it's dependencies by using exponents
    private final HashMap<String, HashMap<String, Integer>> dimensionExponentsMap;

    // ─── Units ───────────────────────────────────────────────────────────────

    // { baseUnitName : dimensionName }
    private final HashMap<String, String> unitDimensionMap;

    // { unitName : suffixName}.
    // If null it has no suffix
    public final HashMap<String, String> unitSuffixesMap;

    // { unitName : unitValue }.
    // Value of the unit in relation to it's dimension's base unit
    private final HashMap<String, FractionType> unitValueMap;

    // ─── Bit Size Types ──────────────────────────────────────────────────────

    // { typeName : bitRange }
    private final HashMap<String, Integer> typeRangeMap;

    // ─── Dimension Methods ───────────────────────────────────────────────────

    public TypeSystem() {
        this.dimensionTypeMap = new HashMap<>();
        this.dimensionUnitMap = new HashMap<>();
        this.dimensionExponentsMap = new HashMap<>();
        this.unitDimensionMap = new HashMap<>();
        this.unitSuffixesMap = new HashMap<>();
        this.unitValueMap = new HashMap<>();
        this.typeRangeMap = new HashMap<>();
    }

    public boolean insertDimensionType(String dimensionName,
                                       String baseUnitName,
                                       String[] exponents,
                                       String suffix,
                                       TypeDescriptor baseType
    ) {
        if (!dimensionExists(dimensionName)) {
            dimensionTypeMap.put(dimensionName, baseType);
            dimensionUnitMap.put(dimensionName, baseUnitName);
            // Register unit before generating exponents so the dimension is visible
            unitDimensionMap.put(baseUnitName, dimensionName);
            unitSuffixesMap.put(baseUnitName, suffix);
            unitValueMap.put(baseUnitName, new FractionType(new BigInteger("1")));

            HashMap<String, Integer> exponentMap = generateExponents(exponents);
            dimensionExponentsMap.put(dimensionName, exponentMap);

            return true;
        }

        System.err.println("dimension/unit " + dimensionName + " " + baseUnitName + " already exist");
        return false;
    }

    public boolean insertDimensionType(String dimensionName,
                                       String baseUnitName,
                                       String[] exponents,
                                       TypeDescriptor baseType
    ) {
        if (!typeGenExists(dimensionName)) {
            dimensionTypeMap.put(dimensionName, baseType);
            dimensionUnitMap.put(dimensionName, baseUnitName);
            // Register unit before generating exponents so the dimension is visible
            unitDimensionMap.put(baseUnitName, dimensionName);
            unitSuffixesMap.put(baseUnitName, null);
            unitValueMap.put(baseUnitName, new FractionType(new BigInteger("1")));

            HashMap<String, Integer> exponentMap = generateExponents(exponents);
            dimensionExponentsMap.put(dimensionName, exponentMap);

            return true;
        }

        System.err.println("dimension/unit " + dimensionName + " " + baseUnitName + " already exist");
        return false;
    }

    /**
     * Generates an exponent map for a dimension from a string array.
     *
     * For a base (independent) dimension the array is: [ "DimName" ]
     * For a dependent dimension the array is:          [ "Dim1", "*" or "/", "Dim2" ]
     *
     * Both referenced dimensions must already exist in dimensionExponentsMap.
     */
    public HashMap<String, Integer> generateExponents(String[] exponentStrings) {
        if (exponentStrings.length == 1) {
            // Base (independent) dimension: maps to itself with exponent 1.
            // The dimension was just registered, so it is now in dimensionUnitMap.
            HashMap<String, Integer> exponentMap = new HashMap<>();
            exponentMap.put(exponentStrings[0], 1);
            return exponentMap;
        }

        if (exponentStrings.length == 3) {
            String left  = exponentStrings[0];
            String op    = exponentStrings[1];
            String right = exponentStrings[2];

            HashMap<String, Integer> leftExp  = dimensionExponentsMap.get(left);
            HashMap<String, Integer> rightExp = dimensionExponentsMap.get(right);

            if (leftExp == null || rightExp == null) return null;

            HashMap<String, Integer> result = new HashMap<>(leftExp);

            switch (op) {
                case "*":
                    rightExp.forEach((k, v) -> result.merge(k, v, Integer::sum));
                    break;
                case "/":
                    rightExp.forEach((k, v) -> result.merge(k, -v, Integer::sum));
                    break;
                default:
                    return null;
            }

            result.entrySet().removeIf(e -> e.getValue() == 0);
            return result;
        }

        return null;
    }

    /**
     * Compute a combined exponent map from two already-registered dimensions,
     * applying the given operator ("*" or "/").
     * Returns null if either dimension is unknown.
     */
    public HashMap<String, Integer> combineExponents(String dim1, String op, String dim2) {
        return generateExponents(new String[]{dim1, op, dim2});
    }

    public boolean dimensionExists(String name) {
        return dimensionUnitMap.containsKey(name);
    }

    public String getUnit(String name) {
        return dimensionUnitMap.get(name);
    }

    public String resolveSignature(HashMap<String, Integer> signature) {
        if (signature == null) return null;
        return dimensionExponentsMap.entrySet().stream()
            .filter(e -> e.getValue().equals(signature))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null); // null = undeclared dimension
    }

    public boolean isDimensionCompatible(String left, String right) {
        HashMap<String, Integer> expoLeft  = getExponentsForDimension(left);
        HashMap<String, Integer> expoRight = getExponentsForDimension(right);
        if (expoLeft == null || expoRight == null) return false;
        return expoLeft.equals(expoRight);
    }

    public HashMap<String, Integer> getExponentsForDimension(String name) {
        if (name == null) return null;
        
        if (dimensionExponentsMap.containsKey(name)) {
            return dimensionExponentsMap.get(name);
        }
        
        // If it's not in the map it's a compiler generated dimension
        HashMap<String, Integer> result = new HashMap<>();
        
        if (name.contains("/")) {
            String[] parts = name.split("/", 2);
            HashMap<String, Integer> leftPart = getExponentsForDimension(parts[0]);
            HashMap<String, Integer> rightPart = getExponentsForDimension(parts[1]);
            if (leftPart != null) result.putAll(leftPart);
            if (rightPart != null) {
                rightPart.forEach((k, v) -> result.merge(k, -v, Integer::sum));
            }
        } else if (name.contains("*")) {
            String[] parts = name.split("\\*");
            for (String part : parts) {
                HashMap<String, Integer> component = getExponentsForDimension(part);
                if (component != null) {
                    component.forEach((k, v) -> result.merge(k, v, Integer::sum));
                }
            }
        } else {
            if (!name.equals("1")) {
                result.put(name, 1);
            }
        }
        
        result.entrySet().removeIf(e -> e.getValue() == 0);
        return result;
    }

    // ─── Unit Methods ────────────────────────────────────────────────────────

    public boolean insertUnitType(String dimensionName,
                                  String unitName,
                                  FractionType value
    ) {
        if (!unitExists(unitName)) {
            unitDimensionMap.put(unitName, dimensionName);
            unitSuffixesMap.put(unitName, null);
            // Default to 1 if no conversion value is provided
            unitValueMap.put(unitName, value);
            return true;
        }

        System.err.println("unit " + unitName + " already exist");
        return false;
    }

    public boolean insertUnitType(String dimensionName,
                                  String unitName,
                                  FractionType value,
                                  String suffix
    ) {
        if (!unitExists(unitName)) {
            unitDimensionMap.put(unitName, dimensionName);
            unitSuffixesMap.put(unitName, suffix);
            unitValueMap.put(unitName, value);
            return true;
        }

        System.err.println("unit " + unitName + " already exist\n");
        return false;
    }

    public boolean changeUnitValue(String unitName, FractionType value) {
        boolean status = unitExists(unitName);

        if (status)
            unitValueMap.put(unitName, value);
        else
            System.err.println("unit " + unitName + " doesn't exist");

        return status;
    }

    public boolean isCompatible(String left, String right) {
        String dimLeft  = unitDimensionMap.get(left);
        String dimRight = unitDimensionMap.get(right);
        if (dimLeft == null || dimRight == null) return false;
        return dimLeft.equals(dimRight);
    }

    public String multiplyDimentsionally(String dimensionLeft, String dimensionRight) {
        if (!dimensionExists(dimensionLeft) || !dimensionExists(dimensionRight)) return null;

        HashMap<String, Integer> result = new HashMap<>(dimensionExponentsMap.get(dimensionLeft));
        dimensionExponentsMap.get(dimensionRight).forEach((k, v) ->
            result.merge(k, v, Integer::sum)
        );
        result.entrySet().removeIf(e -> e.getValue() == 0);
        return resolveSignature(result);
    }

    public String divide(String dimensionLeft, String dimensionRight) {
        if (!dimensionExists(dimensionLeft) || !dimensionExists(dimensionRight)) return null;

        HashMap<String, Integer> result = new HashMap<>(dimensionExponentsMap.get(dimensionLeft));
        dimensionExponentsMap.get(dimensionRight).forEach((k, v) ->
            result.merge(k, -v, Integer::sum)
        );
        result.entrySet().removeIf(e -> e.getValue() == 0);
        return resolveSignature(result);
    }

    public FractionType getConversionValue(String unit) {
        return unitValueMap.get(unit);
    }

    public String getSuffix(String unit) {
        return unitSuffixesMap.get(unit);
    }

    public String getDimensionForUnit(String unit) {
        return unitDimensionMap.get(unit);
    }

    public FractionType getUnitValue(String unit) {
        return unitValueMap.get(unit);
    }

    public boolean unitExists(String name) {
        return unitDimensionMap.containsKey(name);
    }

    public boolean suffixExists(String suf) {
        return unitSuffixesMap.containsValue(suf);
    }

    public String getDimensionForSuffix(String suf) {
        return unitSuffixesMap.entrySet().stream()
            .filter(e -> suf.equals(e.getValue()))
            .map(e -> unitDimensionMap.get(e.getKey()))
            .findFirst()
            .orElse(null);
    }

    // ─── Type Range Methods ──────────────────────────────────────────────────

    public boolean insertTypeRange(String typeName,
                                   Integer range,
                                   TypeDescriptor baseType
    ) {
        if (!typeGenExists(typeName)) {
            dimensionTypeMap.put(typeName, baseType);
            typeRangeMap.put(typeName, range);
            return true;
        }

        System.err.println("type " + typeName + " already exists");
        return false;
    }

    public boolean rangedTypeExists(String name) {
        return typeRangeMap.containsKey(name);
    }

    public Integer getTypeRange(String name) {
        return typeRangeMap.get(name);
    }

    // ─── General Methods ─────────────────────────────────────────────────────

    public boolean typeGenExists(String name) {
        return dimensionTypeMap.containsKey(name)
            || typeRangeMap.containsKey(name);
    }

    public TypeDescriptor getBaseType(String dimensionOrUnitOrRangedType) {
        if (dimensionExists(dimensionOrUnitOrRangedType))
            return dimensionTypeMap.get(dimensionOrUnitOrRangedType);

        if (unitExists(dimensionOrUnitOrRangedType))
            return dimensionTypeMap.get(unitDimensionMap.get(dimensionOrUnitOrRangedType));

        if (rangedTypeExists(dimensionOrUnitOrRangedType))
            return dimensionTypeMap.get(dimensionOrUnitOrRangedType);

        return TypeDescriptor.ERROR;
    }

    @Override
    public String toString() {
        return "DimensionTypeMap: " + dimensionTypeMap.toString() + "\n" +
               "DimensionUnitMap: " + dimensionUnitMap.toString() + "\n" +
               "DimensionExponentMap: " + dimensionExponentsMap.toString() + "\n" +
               "UnitDimensionMap: " + unitDimensionMap.toString() + "\n" +
               "UnitSuffixesMap: " + unitSuffixesMap.toString() + "\n" +
               "UnitValueMap: " + unitValueMap.toString() + "\n";
    }
}