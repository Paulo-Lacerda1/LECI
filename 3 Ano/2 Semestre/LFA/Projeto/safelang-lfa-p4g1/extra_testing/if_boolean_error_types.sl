# Test 2: Negative case - Type mismatched boundaries in boolean branches

x: integer;
y: real;
flag: bool;

x := 5;
y := 4.5;

# SEMANTIC ERROR 1: You cannot compare an integer and a real cleanly 
# without dimensional or explicit type checking casting rules.
if x > y then
    writeln "This should trigger a comparison mismatch error";
end;

# SEMANTIC ERROR 2: Assigning a numeric expression rule to a true boolean type descriptor
flag := x + 10; 

# SEMANTIC ERROR 3: Using a raw structural number expression directly where a boolean condition is expected
if (x * 2) then
    writeln "Fails because an integer is not a boolean condition expression";
end;