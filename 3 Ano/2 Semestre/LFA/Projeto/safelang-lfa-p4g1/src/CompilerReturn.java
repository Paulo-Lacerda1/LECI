import org.stringtemplate.v4.ST;

public class CompilerReturn {
    public final ST template;
    // null se for irrelevante
    public final TypeDescriptor type;

    public CompilerReturn(ST template, TypeDescriptor type) {
        this.template = template;
        this.type = type;
    }

    public CompilerReturn(ST template) {
        this.template = template;
        this.type = null;
    }
}
