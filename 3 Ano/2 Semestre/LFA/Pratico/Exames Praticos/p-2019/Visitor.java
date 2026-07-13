import java.util.HashMap;
import java.util.ArrayList;

@SuppressWarnings("CheckReturnValue")
public class Visitor extends VectorBaseVisitor<Object> {

    private HashMap<String, Object> vars = new HashMap<>();

    @Override
    public Object visitProgram(VectorParser.ProgramContext ctx) {
        for (VectorParser.StatContext stat : ctx.stat()) {
            visit(stat);
        }
        return null;
    }

    @Override
    public Object visitStatShow(VectorParser.StatShowContext ctx) {
        Object value = visit(ctx.expr());
        System.out.println(value);
        return null;
    }

    @Override
    public Object visitStatAssign(VectorParser.StatAssignContext ctx) {
        Object value = visit(ctx.expr());
        String id = ctx.ID().getText();

        vars.put(id, value);
        return null;
    }

    @Override
    public Object visitExprNumber(VectorParser.ExprNumberContext ctx) {
        return Double.parseDouble(ctx.number().getText());
    }

    @Override
    public Object visitExprVector(VectorParser.ExprVectorContext ctx) {
        return visit(ctx.vector());
    }

    @Override
    public Object visitExprID(VectorParser.ExprIDContext ctx) {
        String id = ctx.ID().getText();

        if (!vars.containsKey(id)) {
            System.err.println("Erro: variável " + id + " não definida");
            return 0.0;
        }

        return vars.get(id);
    }

    @Override
    public Object visitExprParent(VectorParser.ExprParentContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitVector(VectorParser.VectorContext ctx) {
        ArrayList<Double> vector = new ArrayList<>();

        for (VectorParser.NumberContext n : ctx.number()) {
            vector.add(Double.parseDouble(n.getText()));
        }

        return vector;
    }

    @Override
    public Object visitExprUnary(VectorParser.ExprUnaryContext ctx) {
        Object value = visit(ctx.expr());

        if (ctx.op.getText().equals("+")) {
            return value;
        }

        if (value instanceof Double) {
            return -((Double) value);
        }

        if (value instanceof ArrayList<?>) {
            ArrayList<Double> vec = (ArrayList<Double>) value;
            ArrayList<Double> result = new ArrayList<>();

            for (Double x : vec) {
                result.add(-x);
            }

            return result;
        }

        System.err.println("Erro: operador unário inválido");
        return 0.0;
    }

    @Override
    public Object visitExprAddSub(VectorParser.ExprAddSubContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        if (left instanceof Double && right instanceof Double) {
            double a = (Double) left;
            double b = (Double) right;

            if (op.equals("+")) {
                return a + b;
            } else {
                return a - b;
            }
        }

        if (left instanceof ArrayList<?> && right instanceof ArrayList<?>) {
            ArrayList<Double> v1 = (ArrayList<Double>) left;
            ArrayList<Double> v2 = (ArrayList<Double>) right;

            if (v1.size() != v2.size()) {
                System.err.println("Erro: vetores com tamanhos diferentes");
                return new ArrayList<Double>();
            }

            ArrayList<Double> result = new ArrayList<>();

            for (int i = 0; i < v1.size(); i++) {
                if (op.equals("+")) {
                    result.add(v1.get(i) + v2.get(i));
                } else {
                    result.add(v1.get(i) - v2.get(i));
                }
            }

            return result;
        }

        System.err.println("Erro: não podes somar/subtrair escalar com vetor");
        return 0.0;
    }
}