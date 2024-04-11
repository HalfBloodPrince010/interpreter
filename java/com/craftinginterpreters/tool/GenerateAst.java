package com.craftinginterpreters.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        /*
        We need a way to represent the different expressions. We will
        need something like below for the Binary Expression.

        package com.craftinginterpreters.lox;

        abstract class Expr {
          static class Binary extends Expr {
            Binary(Expr left, Token operator, Expr right) {
              this.left = left;
              this.operator = operator;
              this.right = right;
              }

          final Expr left;
          final Token operator;
          final Expr right;
          }

          // Other expressions...
        }

        This Script generates the class for each expression like above.
        */
        if(args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        defineAst(outputDir, "Expr", Arrays.asList(
                "Binary   : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal  : Object value",
                "Unary    : Token operator, Expr right"
        ));
    }

    private static void defineAst(
            String outputDir, String baseName, List<String> types
    ) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package com.craftinginterpreters.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        // AST Classes
        for (String type: types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        writer.println("}");
        writer.close();
    }

    private static void defineType(
            PrintWriter writer, String basename,
            String className, String fieldList) {
        writer.println(" static class " + className + " extends " + basename + " {");

        // Constructor.
        writer.println("    " + className + "(" + fieldList + ") {");

        // Store Parameters in fields
        String[] fields = fieldList.split(", ");
        for(String field: fields) {
            String name = field.split(" ")[1];
            writer.println("      this." + name + "=" + name + ";");
        }

        writer.println("    }");

        // Fields.
        writer.println();
        for (String field : fields) {
            writer.println("    final " + field + ";");
        }

        writer.println("  }");
    }
}
